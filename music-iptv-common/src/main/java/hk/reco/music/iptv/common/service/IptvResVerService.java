package hk.reco.music.iptv.common.service;

import java.util.ArrayList;
import java.util.List;

import hk.reco.music.iptv.common.dao.IptvResDao;
import hk.reco.music.iptv.common.dao.IptvResVerDao;
import hk.reco.music.iptv.common.domain.IptvPageResult;
import hk.reco.music.iptv.common.domain.IptvRes;
import hk.reco.music.iptv.common.domain.IptvResVer;
import hk.reco.music.iptv.common.enums.IptvObjectEnum;
import hk.reco.music.iptv.common.utils.IptvFileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资源服务
 * @author zhangsl
 * @date 2019年6月13日
 */
@Service
public class IptvResVerService{

	@Autowired
	private IptvResDao iptvResDao;
	@Autowired
	private IptvResService iptvResService;
	@Autowired
	private IptvResVerDao iptvResVerDao;
	
	/**
	 * 新增版本ver
	 * @param ver
	 */
	public void insertVer(IptvResVer ver) {
		if(ver.getVersion_disable()==null){
			ver.setVersion_disable(false);
		}
		this.iptvResVerDao.insertVer(ver);
	}
	
	public IptvResVer findByResId(long res_id){
		return this.iptvResVerDao.findByResId(res_id);
	}
	
	public IptvResVer findByCateIds(String cate_ids){
		IptvRes res = this.iptvResDao.findByCateIds(cate_ids);
		if(res != null){
			IptvResVer ver = new IptvResVer();
			ver.setRid(res.getRid());
			ver.setCate_ids(res.getCate_ids());
			ver.setNewCreate(false);
			return ver;
		}else{
			return null;
		}
	}
	
	/**
	 * 新建资源的res和ver,创建后res表的test_vid指向新建的这个ver
	 * @param ver
	 * @param applyToProduct true则应用到生产环境
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void createResAndVer(IptvResVer ver, boolean applyToProduct) {
		//1-先在[iptv_res]表创建res
		this.iptvResService.insertRes(ver);//完成后rid有值了
		ver.setNewCreate(true);
		//2-再在[iptv_res_ver]创建ver
		this.insertVer(ver);//完成后vid有值了
		this.iptvResService.updateResTestVersion(ver.getRid(), ver.getVid());//设置资源的测试版本
	}
	
	/**
	 * 在已经存在的rid资源上,按照以前的ver,从而copy出一个新版本并让test_vid指向他
	 * @param ver 以前库中的ver
	 * @param applyToProduct
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public long appendResVer(IptvResVer ver, boolean applyToProduct) {
		if(ver.getListen_num()==null){
			ver.setListen_num(0l);
		}
		//2-在[iptv_res_ver]创建
		this.insertVer(ver);//此方法后ver的vid有值了
		ver.setNewCreate(false);
		Long vid = ver.getVid();
		Long rid = ver.getRid();
		this.iptvResService.updateResTestVersion(rid, vid);
		return rid;
	}
	
	/**
	 * 同时指定父类型和子类型查询,但是同时查出了list下的list
	 * @param type 父类型
	 * @param ctype 子类型
	 * @param test
	 * @return
	 */
	public List<IptvResVer> findListByTypeAndChildType2(IptvObjectEnum type, IptvObjectEnum ctype, boolean test){
		List<IptvResVer> cates = this.iptvResVerDao.findResListByTypeAndChildType(type, ctype, 0, 1000000, test);//查第一层,第一层不需要图片
		for(IptvResVer cate : cates){//再查分类下的二级分类
			List<IptvResVer> tops = this.findListExcludeGlobalDisable(type,ctype,cate.getRid(),test);
			IptvFileUtils.toHttp(tops);
			cate.setList(tops);
		}
		return cates;
	}
	
	/**
	 * 同时指定父类型和子类型进行查询,通常用于type类查询
	 * @param type 父类型
	 * @param ctype 子类型
	 * @param test
	 * @return
	 */
	public List<IptvResVer> findListByTypeAndChildType(IptvObjectEnum type, IptvObjectEnum ctype, boolean test){
		List<IptvResVer> vers = this.iptvResVerDao.findResListByTypeAndChildType(type, ctype, 0, 1000000, test);
		IptvFileUtils.toHttp(vers);
		return vers;
	}
	
	public List<IptvResVer> findListByTypeAndChildType(IptvObjectEnum type, IptvObjectEnum ctype, Integer offset, Integer size, boolean test){
		List<IptvResVer> vers = this.iptvResVerDao.findResListByTypeAndChildType(type, ctype, offset, size, test);
		IptvFileUtils.toHttp(vers);
		return vers;
	}

	/**
	 * 包括global_disable=1的,这是因为有些资源在上到不规则界面时,如果后面被面局禁用页面会出现空洞
	 * @param prid 父级rid
	 * @param test
	 * @return
	 */
	public List<IptvResVer> findListIncludeGlobalDisable(long prid, boolean test) {
		List<IptvResVer> vers = this.iptvResVerDao.findResListIncludeGlobalDisable(prid, test);
		for(IptvResVer ver : vers){
			Boolean res_disable = ver.getGlobal_disable();
			Boolean singer_disable = ver.getSinger_global_disable();
			if((res_disable!=null && res_disable) || (singer_disable!=null && singer_disable)){
				ver.setAvailable(false);
			}else{
				ver.setAvailable(true);
			}
			ver.setGlobal_disable(null);
			ver.setSinger_global_disable(null);
		}
		IptvFileUtils.toHttp(vers);//处理地址
		return vers;
	}
	
	public List<IptvResVer> findListExcludeGlobalDisable(IptvObjectEnum type, IptvObjectEnum ctype, long prid, boolean test) {
		List<IptvResVer> vers = this.iptvResVerDao.findResListPageable(type,ctype,prid,null,0,100000,test);
		for(IptvResVer ver : vers){
			ver.setAvailable(true);
		}
		IptvFileUtils.toHttp(vers);//处理地址
		return vers;
	}
	
	public IptvPageResult findListExcludeGlobalDisablePageable(IptvObjectEnum type, IptvObjectEnum ctype, long prid, String pinyin, int offset, int size, boolean test) {
		IptvPageResult result = new IptvPageResult();
		int total = this.iptvResVerDao.findResListPageableCount(type,ctype,prid,pinyin,test);
		if(total>0){
			List<IptvResVer> vers = this.iptvResVerDao.findResListPageable(type, ctype, prid, pinyin, offset, size, test);
			IptvFileUtils.toHttp(vers);
			result.setVers(vers);
			result.setTotal(total);
		}else{
			result.setVers(new ArrayList<IptvResVer>());
			result.setTotal(0);
		}
		return result;
	}
	
}
