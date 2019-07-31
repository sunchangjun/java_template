/**
 * 
 */
package hk.reco.music.iptv.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import hk.reco.music.iptv.common.stats.IptvStatsAction;

/**
 * @Description 标记打印操作日志
 * @author Randy ran
 * @Date 2019年7月30日 下午3:12:21
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface LogDetail {
	IptvStatsAction action() default IptvStatsAction.reqs;
	String method();
	String rid() default "";
	String extrid() default "";
}
