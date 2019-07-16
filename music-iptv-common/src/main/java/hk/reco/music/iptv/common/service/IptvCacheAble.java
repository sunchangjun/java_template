package hk.reco.music.iptv.common.service;

import hk.reco.music.iptv.common.domain.IptvResVer;

import java.util.List;
/**
 * 此接口下实现的方法都要加上缓存标记
 * @author zhangsl
 * @date 2019年2月21日
 */
public interface IptvCacheAble {

	/**
	 * 生产环境资源详情
	 * @param oid 资源id
	 * @return
	 */
	public IptvResVer findPproductResInfo(Long rid);
	
	/**
	 * 生产环境盒子使用的方法,查询一个资源下的子资源
	 * 当资源没有子资源时,返回空集合
	 * @param prid 父结点
	 * @param offset
	 * @param size
	 * @return
	 */
	public List<IptvResVer> findProductResList(Long prid, int offset, int size);
}
