package cn.edu.interceptor;

import cn.edu.entity.Permission;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
public class AuthInteceptor implements HandlerInterceptor {
    private List<String> excludeUrls;

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
        if(uri.contains("?")){
            uri=uri.substring(0,uri.lastIndexOf("?"));
        }
        List<Permission>permissions=(List<Permission>)httpServletRequest.getSession().getAttribute("permissions");
        if(permissions.size()>0) {
            List<String> urls = new ArrayList<String>();
            for (Permission permission : permissions) {
                if (!permission.getUrl().trim().equals("") && permission.getUrl() != null) {
                    urls.add(permission.getUrl());
                }
            }
            if (urls.size()>0) {
                for (String url : urls) {
                    if (url.endsWith(uri)) {
                        System.out.println(url.endsWith(uri));
                        return true;
                    }
                }
            }
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/unAuth");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
