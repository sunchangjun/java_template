package hk.reco.music.iptv.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
/**
 * 网络工具
 * @author zhangsl
 * @date 2018-04-26
 */
public class NetworkUtils {
	
	/**
	 * 获取最为匹配的本机ip地址
	 * @return
	 * @throws Exception
	 */
	public static String getLocalIpAddress() throws Exception {
		InetAddress candidateAddress = null;
		Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
		while(ifaces.hasMoreElements()){// 遍历所有的网络接口
			NetworkInterface iface = ifaces.nextElement();
			Enumeration<InetAddress> inetAddrs = iface.getInetAddresses();
			while(inetAddrs.hasMoreElements()){// 在所有的接口下再遍历IP
				InetAddress inetAddr = inetAddrs.nextElement();
				if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
					if (inetAddr.isSiteLocalAddress()) {// 如果是site-local地址，就是它了
						return inetAddr.getHostAddress();
					} else if (candidateAddress == null) {// site-local类型的地址未被发现，先记录候选地址
						candidateAddress = inetAddr;
					}
				}
			}
		}
		if (candidateAddress != null) {
			return candidateAddress.getHostAddress();
		}
		// 如果没有发现 non-loopback地址.只能用最次选的方案
		InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
		return jdkSuppliedAddress.getHostAddress();
	}
	
	/**
	 * 返回客户端真实的ip地址
	 * @param req
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest req){
		String real_ip = req.getHeader("x-forwarded-for");
		real_ip = (real_ip == null)?req.getRemoteAddr():real_ip;
		if(real_ip.equals("0:0:0:0:0:0:0:1")){
			real_ip = "127.0.0.1";
		}
		return real_ip;
	}
}
