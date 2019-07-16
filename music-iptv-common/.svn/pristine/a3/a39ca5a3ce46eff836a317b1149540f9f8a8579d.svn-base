package hk.reco.music.iptv.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * GSON工具
 * @author shilong.zhang
 * @create_date 2019-02-18
 */
public class JsonUtils {

	private static GsonBuilder instance;

	private static synchronized GsonBuilder build() {
		if (instance == null) {
			instance = new GsonBuilder();
		}
		return instance;
	}
	
	private static GsonBuilder getInstance(){
		return build();
	}
	
	public static String toJson(Object obj){
		return getInstance().disableHtmlEscaping().create().toJson(obj);
	}
	
	public static <T> T fromJson(String json, Class<T> clazz){
		return getInstance().create().fromJson(json, clazz);
	}
	
	/**
	 * 整理jsonp格式的返回,如'myjsonp({id:123})'==> '{id:123}'
	 * @param strJsonp
	 * @return
	 */
	public static String splitJsonP(String strJsonp){
		return strJsonp.substring(strJsonp.indexOf("(")+1, strJsonp.lastIndexOf(")"));
	}
	
	/**
	 * 获取JsonObject
	 * @param json
	 * @return
	 */
	public static JsonObject parseJson(String json){
		JsonParser parser = new JsonParser();
	    JsonObject jsonObj = parser.parse(json).getAsJsonObject();
		return jsonObj;
	}
	
	
	/**
	 * 依据json字符串返回Map对象
	 * @param json
	 * @return
	 */
	public static Map<String,Object> toMap(String json){
		return toMap(parseJson(json));
	}
	
	/**
	 * 将JSONObjec对象转换成Map-List集合
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(JsonObject json){
	    Map<String, Object> map = new HashMap<String, Object>();
	    Set<Entry<String, JsonElement>> entrySet = json.entrySet();
	    for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
	    	Entry<String, JsonElement> entry = iter.next();
	    	String key = entry.getKey();
	    	Object value = entry.getValue();
	        if(value instanceof JsonArray)
	            map.put((String) key, toList((JsonArray) value));
	        else if(value instanceof JsonObject)
	            map.put((String) key, toMap((JsonObject) value));
	        else
	            map.put((String) key, value);
	    }
	    return map;
	}
	
	
	/**
	 * 将JSONObjec对象转换成Map集合
	 * @param json
	 * @return
	 */
	public static Map<String, String> toStringMap(String json){
	    Map<String, String> map = new HashMap<String, String>();
	    JsonObject parseJson = parseJson(json);
	    Set<Entry<String, JsonElement>> entrySet = parseJson.entrySet();
	    for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
	    	Entry<String, JsonElement> entry = iter.next();
	    	String key = entry.getKey();
	    	String value = entry.getValue().toString();
	        map.put((String) key, value);
	    }
	    return map;
	}
	
	/**
	 * 将JSONArray对象转换成List集合
	 * @param json
	 * @return
	 */
	public static List<Object> toList(JsonArray json){
	    List<Object> list = new ArrayList<Object>();
	    for (int i=0; i<json.size(); i++){
	    	Object value = json.get(i);
	    	if(value instanceof JsonArray){
	            list.add(toList((JsonArray) value));
	    	}
	        else if(value instanceof JsonObject){
	            list.add(toMap((JsonObject) value));
	        }
	        else{
	            list.add(value);
	        }
	    }
	    return list;
	}
	
	
	public static void main(String[] args) {
		String s ="{\"name\":\"张三\",\"age\":64}";
		Map<String, String> stringMap = toStringMap(s);
		System.err.println(stringMap);
	}
	
}
