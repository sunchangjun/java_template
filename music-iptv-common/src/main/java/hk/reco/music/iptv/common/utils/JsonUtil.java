package hk.reco.music.iptv.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

/**
 * @author wangzc
 * @version 1.0.0
 * @2014-11-18下午04:05:23
 */
public class JsonUtil {
private static ObjectMapper om = null;
	
	static{
		om = new ObjectMapper();
	}
	
	/**
	 * 
	 * @author ruanxf
	 * @date 2011-8-11 下午4:37:52
	 * @version V1.0
	 * @param object
	 * @return    
	 * @return String
	 */
	public static String toJsonString(Object object){
		try {
			return om.writeValueAsString(object);	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("json error!",e);
		} 
	}
	/**
	 * 
	 * @author ruanxf
	 * @date 2011-8-11 下午4:37:59
	 * @version V1.0
	 * @return    
	 * @return ObjectMapper
	 */
	public static ObjectMapper getObjectMapper(){
		return om;
	}
	
	/**
	 * 
	 * @author ruanxf
	 * @date 2011-8-11 下午4:37:56
	 * @version V1.0
	 * @param json
	 * @param clazz
	 * @return    
	 * @return T
	 */
	public static <T> T toObjectByJson(String json,Class<T> clazz) {
		try {
			return om.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}

    @SuppressWarnings("unchecked")
	public static <T> List<T> toList(String layout, Class<T> iptvResVerClass) {
		JavaType javaType = getCollectionType(ArrayList.class, iptvResVerClass);
		List<T> lst = new ArrayList<>();
		try {
			lst = (List<T>)om.readValue(layout, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lst;
	}

	/**
	 * 获取泛型的Collection Type
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses 元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return om.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/***
	 *
	 * 对象转json字符串（美化版本）
	 *
	 * @param object 序列化对象
	 * @return java.lang.String
	 **/
	public static String toPrettyJson(Object object){
		try{
			return om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}
}
