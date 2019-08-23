/**
 * 
 */
package hk.reco.music.iptv.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.Cacheable;

import hk.reco.music.iptv.common.utils.IptvRedisKey;

/**
 * @Description 该注解统一采用模板格式为{@link IptvRedisKey.GLOBAL_SPACE_NAME}::{@link IptvRedisKey.GLOBAL_KEY}<br/>
 * 	即: iptv_object::{rid}_{pinyin}_{type}_{ctype}_{offset}_{size}<br />
 *  无参数时，优先
 * @author Randy ran
 * @Date 2019年8月1日 下午3:49:54
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
@Cacheable(cacheNames=IptvRedisKey.GLOBAL_SPACE_NAME, keyGenerator = "springCacheKeyGenerator", condition = "#test != null && #test==false")
public @interface IptvCacheable {
	/**
	 * 直接设置rid的值，优先取该值用于模板中的{rid}
	 * @return
	 */
	String custormRidValue() default "";
	/**
	 * 直接设置type的值，优先取该值用于规则中的{type}
	 * @return
	 */
	String custormTypeValue() default "";
	/**
	 * 模板中rid对应的字段名称
	 * @return
	 */
	String rid() default "rid";
	/**
	 * 模板中pinyin对应的字段名称
	 * @return
	 */
	String pinyin() default "pinyin";
	/**
	 * 模板中type对应的字段名称
	 * @return
	 */
	String type() default "type";
	/**
	 * 模板中ctype对应的字段名称
	 * @return
	 */
	String ctype() default "ctype";
	/**
	 * 模板中offset对应的字段名称
	 * @return
	 */
	String offset() default "offset";
	/**
	 * 模板中size对应的字段名称
	 * @return
	 */
	String size() default "size";
}
