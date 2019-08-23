/**
 * 
 */
package hk.reco.music.iptv.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 清除缓存标记
 * @Description  清除规则==>iptv_object::{rid}_{pinyin}_{type}_{ctype}_*<br/>
 * 处理优先级：skip() > keyEvictor() > returnVaule()....
 * 
 * @author Randy ran
 * @Date 2019年8月1日 下午3:12:21
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface IptvCacheEvict {
	
	/**
	 * 用于跳过清缓存逻辑的标识字段名称<br/>
	 * 如：@IptvCacheEvict(skip="skipParamName")表示取入参名称为skipParamName的字段来判断是否需要执行缓存清理。<br/>
	 * 注意，skipParamName字段的类型为boolean
	 * @return
	 */
	String skip() default "";
	
	/**
	 * The bean name of the custom {@link hk.reco.music.iptv.common.interceptor.KeyEvictor} to use.
	 */
	String keyEvictor() default "";
	
	/**
	 * 根据该值匹配返回值，匹配？清除：不做处理
	 * @return
	 */
	String returnVaule() default "";
	/**
	 * 规则中字段rid对应的参数名称
	 * @return
	 */
	String rid() default "rid";
	/**
	 * 规则中字段pinyin对应的参数名称
	 * @return
	 */
	String pinyin() default "pinyin";
	/**
	 * 规则中字段type对应的参数名称
	 * @return
	 */
	String type() default "type";
	/**
	 * 规则中字段ctype对应的参数名称
	 * @return
	 */
	String ctype() default "ctype";
	/**
	 * 直接设置rid的值，优先取该值用于规则中的{rid}
	 * @return
	 */
	String custormRidValue() default "";
	/**
	 * 直接设置type的值，优先取该值用于规则中的{type}
	 * @return
	 */
	String custormTypeValue() default "";
}
