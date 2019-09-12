/**
 * 
 */
package hk.reco.music.iptv.common.utils;

import hk.reco.music.iptv.common.builder.LogBuilder;
import hk.reco.music.iptv.common.interceptor.LogServiceAspect;
import hk.reco.music.iptv.common.stats.IptvStatsAction;
import lombok.extern.log4j.Log4j2;

/**
 * @Description 
 * @author Randy ran
 * @Date 2019年9月12日 上午11:38:08
 */
@Log4j2
public class LogHelper {
	
	public static void printNewUser(String partner, String mac, String userid, Boolean test) {
		LogServiceAspect lsa = SpringContextsUtil.getBean("logServiceAspect", LogServiceAspect.class);
		if (lsa.isLogOn())
			log.info("monitorLog:{}", LogBuilder.create(partner, mac, userid).setAction(IptvStatsAction.newuser).setTest(test).build());
	}
}
