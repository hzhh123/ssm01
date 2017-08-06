package cn.edu.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginInterceptor implements HandlerInterceptor {
	private List<String>excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		String uri=httpServletRequest.getRequestURI();
		for(String url:excludeUrls){
			if(uri.endsWith(url)){
				return true;
			}
		}
		if(httpServletRequest.getSession().getAttribute("loginUser")!=null){
			return true;
		}
		httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/toLogin");
		return false;
	}

	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}
}
