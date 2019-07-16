package hk.reco.music.iptv.common.stats;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class DebugCtrlInfo {

	private String name;
	private String url;
	private long start;
	private Method method;
	private String[] parameterNames;
	private Map<String,Integer> parameterMap;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public String[] getParameterNames() {
		return parameterNames;
	}
	public void setParameterNames(String[] parameterNames) {
		this.parameterNames = parameterNames;
	}
	public Map<String, Integer> getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map<String, Integer> parameterMap) {
		this.parameterMap = parameterMap;
	}
	@Override
	public String toString() {
		return "DebugCtrlInfo [name=" + name + ", url=" + url + ", start=" + start + ", method=" + method + ", parameterNames="
				+ Arrays.toString(parameterNames) + ", parameterMap=" + parameterMap + "]";
	}
}
