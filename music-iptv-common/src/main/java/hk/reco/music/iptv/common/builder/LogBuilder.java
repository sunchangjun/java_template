/**
 * 
 */
package hk.reco.music.iptv.common.builder;

import com.alibaba.fastjson.JSON;

import hk.reco.music.iptv.common.domain.OperationLog;
import hk.reco.music.iptv.common.stats.IptvStatsAction;

/**
 * @Description 
 * @author Randy ran
 * @Date 2019年8月16日 上午9:59:29
 */
public class LogBuilder {
	private OperationLog ol;
	public LogBuilder() {
		ol = new OperationLog();
	}
	
	public static LogBuilder create() {
		return new LogBuilder();
	}
	
	public static LogBuilder create(String partner, String mac, String userId) {
		LogBuilder lb = new LogBuilder();
		lb.setPartner(partner);
		lb.setUserId(userId);
		lb.setMac(mac);
		return lb;
	}
	
	public static LogBuilder create(String partner, String ip, String mac, String userId) {
		LogBuilder lb = new LogBuilder();
		lb.setPartner(partner);
		lb.setIp(ip);
		lb.setMac(mac);
		lb.setUserId(userId);
		return lb;
	}
	
	public LogBuilder setPartner(String partner) {
		ol.setPartner(partner);
		return this;
	}
	
	public LogBuilder setAction(IptvStatsAction action) {
		ol.setAction(action);
		return this;
	}
	
	public LogBuilder setUserId(String userId) {
		ol.setUserId(userId);
		return this;
	}
	
	public LogBuilder setMac(String mac) {
		ol.setMac(mac);
		return this;
	}
	
	public LogBuilder setIp(String ip) {
		ol.setIp(ip);
		return this;
	}
	
	public LogBuilder setTest(Boolean test) {
		ol.setTest(test);
		return this;
	}
	
	public String build() {
		return JSON.toJSONString(ol);
	}
}
