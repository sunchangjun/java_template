package hk.reco.music.iptv.common.filter;

import hk.reco.music.iptv.common.stats.DebugCtrlInfo;
import hk.reco.music.iptv.common.stats.IptvStatsAction;
import hk.reco.music.iptv.common.stats.IptvStatsEntry;
import hk.reco.music.iptv.common.utils.NetworkUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
/**
 * 记录接口请求参数及返回,开关见application.properties iptv.access.log
 * @author zhangsl
 * @date 2019年3月7日
 */
//@WebFilter(urlPatterns = "/stb/*", filterName = "iptvAccessLoggerFilter")
public class IptvAccessLoggerFilter extends OncePerRequestFilter {

	@Value("${iptv.console.log}")
	private boolean console;
	@Value("${iptv.stats.log}")
	private boolean stats;
	@Autowired
	protected RequestMappingHandlerMapping requestMappingHandlerMapping;
	private static final Logger log = LoggerFactory.getLogger(IptvAccessLoggerFilter.class);
	private static Map<String,DebugCtrlInfo> urlMap = new HashMap<String,DebugCtrlInfo>();
	
	private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(MediaType.valueOf("text/*"), MediaType.APPLICATION_FORM_URLENCODED,
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.valueOf("application/*+json"), MediaType.valueOf("application/*+xml"),
			MediaType.MULTIPART_FORM_DATA);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isAsyncDispatch(request)) {
			filterChain.doFilter(request, response);
		} else {
			if(console || stats){
				doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
			}else{
				filterChain.doFilter(request, response);
			}
		}
	}

	protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			beforeRequest(request, response);
			filterChain.doFilter(request, response);
		} finally {
			afterRequest(request, response);
			response.copyBodyToResponse();
		}
	}

	protected void beforeRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
		if (log.isInfoEnabled() && console) {
			String uri = request.getRequestURI();
			DebugCtrlInfo info = urlMap.get(uri);
			IptvStatsEntry entry = new IptvStatsEntry();
			entry.setUri(uri);
			entry.setAction(IptvStatsAction.reqs.name());
			entry.setIp(NetworkUtils.getIpAddress(request));
			
			String ps = concatParameters(request, info.getParameterNames());
			log.info("{} {} {} {}", request.getRemoteAddr() + "|>", request.getMethod(), request.getRequestURI(), ps);
			//logRequestHeader(request, request.getRemoteAddr() + "|>");
		}
	}

	protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
		if (log.isInfoEnabled() && console) {
			//logRequestBody(request, request.getRemoteAddr() + "|>");
			logResponse(response, request.getRemoteAddr() + "|<");
		}
	}

	protected static void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
		String queryString = request.getQueryString();
		if (queryString == null) {
			log.info("{} {} {}", prefix, request.getMethod(), request.getRequestURI());
		} else {
			log.info("{} {} {}?{}", prefix, request.getMethod(), request.getRequestURI(), queryString);
		}
		Collections.list(request.getHeaderNames()).forEach(
				headerName -> Collections.list(request.getHeaders(headerName)).forEach(headerValue -> log.info("{} {}: {}", prefix, headerName, headerValue)));
		log.info("{}", prefix);
	}

	protected static void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
		byte[] content = request.getContentAsByteArray();
		if (content.length > 0) {
			logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix);
		}
	}

	private static void logResponse(ContentCachingResponseWrapper response, String prefix) {
		int status = response.getStatus();
		log.info("{} {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());
		response.getHeaderNames().forEach(
				headerName -> response.getHeaders(headerName).forEach(headerValue -> log.info("{} {}: {}", prefix, headerName, headerValue)));
		//log.info("{}", prefix);
		byte[] content = response.getContentAsByteArray();
		if (content.length > 0) {
			logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix);
		}
	}

	private static void logContent(byte[] content, String contentType, String contentEncoding, String prefix) {
		MediaType mediaType = MediaType.valueOf(contentType);
		boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
		if (visible) {
			try {
				String contentString = new String(content, contentEncoding);
				Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> log.info("{} {}", prefix, line));
			} catch (UnsupportedEncodingException e) {
				log.info("{} [{} bytes content]", prefix, content.length);
			}
		} else {
			log.info("{} [{} bytes content]", prefix, content.length);
		}
	}

	private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
		if (request instanceof ContentCachingRequestWrapper) {
			return (ContentCachingRequestWrapper) request;
		} else {
			return new ContentCachingRequestWrapper(request);
		}
	}

	private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
		if (response instanceof ContentCachingResponseWrapper) {
			return (ContentCachingResponseWrapper) response;
		} else {
			return new ContentCachingResponseWrapper(response);
		}
	}
	
	private static String concatParameters(ContentCachingRequestWrapper request, String[] parameters){
		StringBuffer sb = new StringBuffer("[");
		for(int i=0;i<parameters.length;i++){
			String pname = parameters[i];
			if(i!=0){
				sb.append(",");
			}
			sb.append(pname);
			sb.append("=");
			sb.append(request.getParameter(pname));
		}
		sb.append("]");
		return sb.toString();
	}
	
	@Autowired
	private void initMapping(){
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
		for(Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
			RequestMappingInfo mappingInfo = item.getKey();
			HandlerMethod handlerMethod = item.getValue();
			for (String urlPattern : mappingInfo.getPatternsCondition().getPatterns()) {
				Method method = handlerMethod.getMethod();
				if(urlPattern.startsWith("/stb")){
					DebugCtrlInfo info = new DebugCtrlInfo();
					info.setUrl(urlPattern);
					info.setMethod(method);
					info.setName(method.getName());
					String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
					Map<String, Integer> pMap = new LinkedHashMap<String,Integer>();
					for(int i=0;i<parameterNames.length;i++){
						pMap.put(parameterNames[i], i);
					}
					info.setParameterMap(pMap);
					info.setParameterNames(parameterNames);
					urlMap.put(urlPattern, info);
					System.out.println(info.toString());
				}
				System.out.println( handlerMethod.getBeanType().getName() + "#" + handlerMethod.getMethod().getName() + " <-- " + urlPattern.toString());
			}
		}      
	}
}
