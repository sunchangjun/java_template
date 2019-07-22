package hk.reco.music.iptv.common.service;

import hk.reco.music.iptv.common.ctrl.stb.RestResponse;

/**
 * 资源定价服务,待项目实现
 * @author zhangsl
 * @date 2019年7月10日
 */
public interface IptvNoticeService {
	
	/**
	 * 设置资源的免费状态
	 * @param rid
	 * @param isfree true收费,false免费
	 * @return true成功,false失败
	 */
	void setFree(long rid, boolean isfree) throws Exception;

	/**
	 *
	 *  未来资源下线通知
	 *
	 *
	 * @Author wangpq
	 * @Param [rid]
	 * @Date 2019/7/19 11:27
	 * @return hk.reco.music.iptv.common.ctrl.stb.RestResponse
	 */
	RestResponse newTVResOffline(long rid);
	
}
