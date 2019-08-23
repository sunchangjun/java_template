/**
 * 
 */
package hk.reco.music.iptv.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description Bean实例获取辅助类
 * @author Randy ran
 * @Date 2019年8月7日 上午10:09:44
 */
@Component
public class SpringContextsUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextsUtil.applicationContext = applicationContext;
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}
	
	/**
	 * 获取对象 
	 * @param name bean name
	 * @param reqType bean type
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(String name, Class<T> reqType) throws BeansException {
		return applicationContext.getBean(name, reqType);
	}
}
