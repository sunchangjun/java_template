package hk.reco.music.iptv.common.service;
/**
 * 资源定价服务,待项目实现
 * @author zhangsl
 * @date 2019年7月10日
 */
public interface IptvPriceService {
	
	/**
	 * 设置资源的免费状态
	 * @param rid
	 * @param isfree true收费,false免费
	 * @return true成功,false失败
	 */
	public void setFree(long rid, boolean isfree) throws Exception;
	
}
