package hk.reco.music.iptv.common.service;

import hk.reco.music.iptv.common.enums.IptvObjectEnum;
import hk.reco.music.iptv.common.utils.IptvRedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
/**
 * 包装了redis的缓存服务
 * @author zhangsl
 * @date 2019年2月22日
 */
@Service
public class IptvCacheService {
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	@Resource
    private ValueOperations<String,Object> valueOperations;
	private static final Logger log = LoggerFactory.getLogger(IptvCacheService.class);
	
	/**
	 * 刷新单个资源缓存
	 * @param rid 对象rid
	 * @return
	 */
	public Long deleteResCache(Long rid){
		return delByKeysByPattern(IptvRedisKey.resName + "::" + rid + "*");
	}
	
	/**
	 * 删除首页的缓存
	 * @return
	 */
	public Long deleteLayoutCache(){
		return delByKey(IptvRedisKey.layoutName+"::root");
	}
	
	/**
	 * 刷新列表,当不分页的时候key like(375_null_null)
	 * @param prid 父级的rid
	 * @return
	 */
	public Long deleteListCaches(Long prid){
		return delByKeysByPattern(IptvRedisKey.listName + "::" + prid + "*");
	}
	
	/**
	 * 删除分类的缓存
	 * @param type 父类型
	 * @param ctype 子类型
	 * @return
	 */
	public Long deleteTypeCache(IptvObjectEnum type,IptvObjectEnum ctype){
		return delByKeysByPattern(IptvRedisKey.typeName+ "::" + type.name() + "_"+ctype.name());
	}
	
	public Long deleteThemeTypeCache(){
		return delByKeysByPattern(IptvRedisKey.typeName+ "::" + IptvObjectEnum.theme.name() + "*");
	}
	
	/**
	 * 批量查询redis中的key
	 * @param keyPattern
	 * @return
	 */
	public Set<String> getKeys(String keyPattern){
		Set<String> keys = this.redisTemplate.keys(keyPattern);
		return keys;
	}
	
	/**
	 * 删除单个key
	 * @param key
	 */
	public Long delByKey(String key){
		log.info("开始查找生产环境缓存: ["+key+"]");
		boolean result = this.redisTemplate.delete(key);
		if(result){
			log.info("成功删除生产环境缓存,key: ["+key+"]");
		}
		return result?1l:0;
	}
	
	/**
	 * 删除多个key
	 * @param pattern 多个key的集合
	 * @return 
	 */
	public Long delByKeysByPattern(String pattern){
		Set<String> keys = getKeys(pattern);
		log.info("开始查找生产环境缓存: ["+pattern+"]");
		for(String key:keys){
			log.info("成功删除生产环境缓存,key: ["+key+"]");
		}
		return this.redisTemplate.delete(keys);
	}
	
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 */
	public void setCache(String key, Object value){
		this.redisTemplate.opsForValue().set(key,value);
	}

	/**
	 *
	 *  获取缓存
	 *
	 *
	 * @Author wangpq
	 * @Param [key]
	 * @Date 2019/6/24 13:12
	 * @return java.lang.Object
	 */
	public <T> T getCache(String key) {
		return (T)this.redisTemplate.boundValueOps(key).get();
	}
	
}
