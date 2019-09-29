package hk.reco.music.iptv.common.service;

import hk.reco.music.iptv.common.annotation.IptvCacheEvict;
import hk.reco.music.iptv.common.dao.IptvResDao;
import hk.reco.music.iptv.common.domain.IptvRes;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * res服务
 * @author zhangsl
 * @date 2019年8月6日
 */
@Service
public class IptvResService{

	@Autowired
	private IptvResDao iptvResDao;
    @Autowired
    private IptvCacheService iptvCacheService;
	@Autowired(required = false)
	private IptvNoticeService noticeService;
	
    public void updateRes(Long rid, IptvObjectEnum child_type, Long test_vid){//未知用途,,暂不处理 TODO
    	IptvRes res = new IptvRes();
    	res.setRid(rid);
    	res.setChild_type(child_type);
    	res.setTest_vid(test_vid);
    	this.iptvResDao.updateRes(res);
    }
    
    /**
     * 更新资源的free,会清除res相关的缓存
     * @param rid
     * @param free
     */
    public void updateResFree(Long rid, Integer free){
    	IptvRes res = new IptvRes();
    	res.setRid(rid);
    	res.setFree(free);
    	this.iptvResDao.updateRes(res);
    	//清除缓存
    	IptvRes ver = this.iptvResDao.findByRid(rid);
    	this.deleteResCache(ver);
    }
    
    /**
     * 更新资源的global_disable,会清除res相关的缓存
     * 改变指定资源的global_disable状态[全局启用|全局禁用]
     * @param rid
     * @param global_disable
     */
    public void updateResGlobalDisable(Long rid, Boolean global_disable){
    	if(global_disable == null){
    		this.iptvResDao.updateGlobalDisable(rid);
    	}else{
        	IptvRes res = new IptvRes();
        	res.setRid(rid);
        	res.setGlobal_disable(global_disable);
        	this.iptvResDao.updateRes(res);
    	}
		if (noticeService != null) {
			noticeService.setGlobalDisable(rid);
		}
    	//清除缓存
    	IptvRes ver = this.iptvResDao.findByRid(rid);
    	this.deleteResCache(ver);
    }
    
	/**
	 * 为res标记版本到测试环境
	 * @param rid
	 * @param test_vid 测试环境版本
	 */
    public void updateResTestVersion(Long rid, Long test_vid){
    	IptvRes res = new IptvRes();
    	res.setRid(rid);
    	res.setTest_vid(test_vid);
    	res.setProd_vid(null);//绝不更新生产
    	this.iptvResDao.updateRes(res);
    }
    
    /**
     * 提交到生产环境(为res标记版本到prod_vid),需要杀缓存
     * @param rid
     * @param pinyin
     * @param type
     * @param ctype
     * @param skip 略过杀缓存
     * @param prod_vid 生产环境prod_vid的vid
     */
    @IptvCacheEvict(skip="skip")
    public void applyProduct(Long rid, String pinyin, IptvObjectEnum type, IptvObjectEnum ctype, 
    		long prod_vid, boolean skip){
    	IptvRes res = new IptvRes();
    	res.setRid(rid);
    	res.setProd_vid(prod_vid);
    	res.setTest_vid(null);//不更新测试
    	this.iptvResDao.updateRes(res);
    }
    
    /**
     * 根据资源杀缓存以及各种关系
     * @param ver
     */
    public void deleteResCache(IptvRes ver) {
        long rid = ver.getRid();
        IptvObjectEnum type = ver.getType();
        System.out.println("del node: rid=>" + rid + "  type=>" + ver.getType().name());
        //1-杀掉自己的详情
        this.iptvCacheService.kill_res_cache(rid);
        //2-杀掉type查询的
        this.iptvCacheService.kill_type_cache(type);
        //3-找直接父级查询并杀掉
        List<IptvRes> parents = this.iptvResDao.findParentResByLinkCrid(rid);//查找link_crid为这个rid的parents
        for (IptvRes parent : parents) {
        	IptvObjectEnum ptype = parent.getType();
        	Long prid = parent.getRid();
        	if(ptype==IptvObjectEnum.cate){
        		this.iptvCacheService.kill_category_cache(ptype, type);
        	}else{
        		this.iptvCacheService.kill_res_child_cache(prid, null, ptype);
        	}
        }
    }
    
    /**
     * 新增res[iptv_res表新增一行,完成后rid有值]
     * @param res
     */
    public void insertRes(IptvRes res){
    	if(res.getListen_num()==null){
			res.setListen_num(0l);
		}
		// wpq song and mv默认收费
		IptvObjectEnum type = res.getType();
		if(type == IptvObjectEnum.song || type == IptvObjectEnum.mv){
			res.setFree(0);
		}
    	this.iptvResDao.insertRes(res);
    }
    
}
