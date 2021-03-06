package hk.reco.music.iptv.common.filter;

import hk.reco.music.iptv.common.domain.IptvConsoleUser;
import hk.reco.music.iptv.common.utils.Constant;
import hk.reco.music.iptv.common.utils.JsonUtil;
import hk.reco.music.iptv.common.utils.SymmetricEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
@WebFilter(urlPatterns = "/console/*",filterName = "sessionFilter")
public class SessionFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		Object attribute = request.getSession().getAttribute(Constant.CONSOLE_USER_IN_SESSION);
		if (attribute != null) {
			filterChain.doFilter(request, response);
		} else {
			// 2、获取sessionId
			String userinfo = getSessionUserInfoInCookie(request);
			if (StringUtils.isNotBlank(userinfo)) {
				// 已登录,但是此时user信息存放于redis缓存中
				IptvConsoleUser user = JsonUtil.toObjectByJson(SymmetricEncoder.AESDncode(userinfo), IptvConsoleUser.class);
				request.getSession().setAttribute(Constant.CONSOLE_USER_IN_SESSION, user);
				filterChain.doFilter(request, response);
			} else {
				// 没有登录
				System.out.println("doFilter>>>obj is null");
				boolean isAjaxRequest = isAjaxRequest(request);
				// 判断是不是ajax请求
				if (isAjaxRequest) {
					response.setCharacterEncoding("UTF-8");
					response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
					System.out.println("doFilter>>>ajax request");
					return;
				} else {
					String redirectUrl = "";
					String s  = request.getContextPath();
					if(StringUtils.isBlank(s)){
						redirectUrl = request.getRequestURL().toString().split(request.getRequestURI())[0];
					}else{
						redirectUrl = request.getRequestURL().toString().split(s)[0]+s;
					}
					response.sendRedirect(redirectUrl);
					// 跳转到登录页面
					return;
				}
			}
		}
	}
		

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header)) {
			// ajax request
			return true;
		} else {
			// traditional sync http request
			return false;
		}
	}

	private String getSessionUserInfoInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies==null||cookies.length==0){
			return null;
		}
		String userInfo = null;
		for (Cookie cookie : cookies) {
			if ("token".equals(cookie.getName())) {
				userInfo = cookie.getValue();
				break;
			}
		}
		try {
			if(StringUtils.isBlank(userInfo)){
				return null;
			}
			return URLDecoder.decode(userInfo, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
