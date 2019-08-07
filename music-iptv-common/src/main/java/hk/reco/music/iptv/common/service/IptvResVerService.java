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
	private IptvResVerDao iptvResVerDao;
	
	public IptvResVer findByResId(long res_id){
		IptvRes res = this.iptvResDao.findByResId(res_id);
		if(res != null){
			IptvResVer ver = new IptvResVer();
			ver.setRid(res.getRid());
			ver.setRes_id(res.getRes_id());
			ver.setNewCreate(false);
			return ver;
		}else{
			return null;
		}
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
	 * 新建资源,当新资源在导入后,可以选择
	 * 应用到生产环境,如果现在不应用,可以以后手动来应用,但是工作量较大
	 * 应用到测试环境,这是推荐的,毕竟新资源只有这一个版本,以便导入后可以立即在test环境看到变化
	 * @param ver
	 * @param applyToProduct true则应用到生产环境
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void createResVer(IptvResVer ver, boolean applyToProduct) {
		this.prepare(ver);
		//1-先在[iptv_res]表创建res
		this.iptvResDao.insert(ver);
		ver.setNewCreate(true);
		//2-再在[iptv_res_ver]创建ver
		this.iptvResVerDao.createVersion(ver);
		this.iptvResDao.markVersion(ver.getRid(), applyToProduct?ver.getVid():0, ver.getVid());
	}
	
	/**
	 * 带父级的对象创建,父级必须是存在的
	 * 创建好后,返回的是link对象的ver,其中带了res,ver及link的三种属性
	 * @param ver
	 * @param prid
	 * @param applyToProduct
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void createResVer(IptvResVer ver, Long prid, boolean applyToProduct) {
		this.createResVer(ver, applyToProduct);
		long rid = ver.getRid();
		//3-接下来创建link
		String name = ver.getName();
		IptvObjectEnum type = ver.getType();
		IptvObjectEnum child_type = ver.getChild_type();
		ver.setLink_prid(prid);
		ver.setLink_crid(rid);
		ver.setName(IptvResVer.createLinkName(prid, rid));
		ver.setType(IptvObjectEnum.link);
		ver.setChild_type(null);
		this.createResVer(ver, applyToProduct);
		ver.setName(name);//恢复名字
		ver.setType(type);//恢复type
		ver.setChild_type(child_type);//恢复child_type
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
		this.iptvResVerDao.createVersion(ver);//此方法后ver的vid有值了
		ver.setNewCreate(false);
		Long vid = ver.getVid();
		Long rid = ver.getRid();
		this.iptvResDao.markVersion(rid, applyToProduct?vid:0, vid);
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
	
	/**
	 * 对象预处理
	 * @param res
	 */
	private void prepare(IptvRes res){
		if(res.getVersion_disable()==null){
			res.setVersion_disable(false);
		}
		if(res.getListen_num()==null){
			res.setListen_num(0l);
		}
		// wpq 资源类型默认收费
		if(res.getType() == IptvObjectEnum.song || res.getType() == IptvObjectEnum.mv){
			res.setFree(0);
		}
	}
}
