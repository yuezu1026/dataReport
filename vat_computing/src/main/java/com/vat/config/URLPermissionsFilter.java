package com.vat.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vat.service.UserService;


@Component("urlPermissionsFilter")
public class URLPermissionsFilter extends PermissionsAuthorizationFilter{
    
    private final static Logger logger = LoggerFactory.getLogger(URLPermissionsFilter.class);
    
	@Autowired
	private UserService userService;
	
	
	@Value("${server.context-path}")
	private String contextPath;

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		String curUrl = getRequestUrl(request);
		Subject subject = SecurityUtils.getSubject();
		if(subject.getPrincipal() == null 
				|| StringUtils.endsWithAny(curUrl, ".js",".css",".html")
				|| StringUtils.endsWithAny(curUrl, ".jpg",".png",".gif", ".jpeg",".ico",".woff",".xlsx")
				|| StringUtils.equals(curUrl, contextPath+"/unauthor")
				|| StringUtils.equals(curUrl, contextPath+"/unauthor.jsp")
				|| StringUtils.equals(curUrl, contextPath+"/login.jsp")
				|| StringUtils.equals(curUrl, contextPath+"/system/login.jsp")
				|| StringUtils.equals(curUrl, contextPath+"/")
					|| StringUtils.equals(curUrl, contextPath+"/myserv/"
					)	
			) {
			return true;
		}
		List<String> urlList = userService.findPermissionUrl(subject.getPrincipal().toString());
		boolean flag = true; //urlList.contains(curUrl);
		flag = isContains(urlList,curUrl);
		
		return flag;
	}
	
	private boolean isContains(List<String> urlList,String curUrl) {
	    if(CollectionUtils.isNotEmpty(urlList) && StringUtils.isNotBlank(curUrl)) {
        	    for(String url : urlList) {
        		if(url != null && curUrl.contains(url)) {
        		    return true;
        		}
        	    }
	    }
	    return false;
	}
	
	/**
	 * 获取当前URL+Parameter
	 * @author lance
	 * @since  2014年12月18日 下午3:09:26
	 * @param request	拦截请求request
	 * @return			返回完整URL
	 */
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String queryString = req.getQueryString();
		String uri = req.getRequestURI();
		logger.info("uri: " + uri);

		queryString = "";//StringUtils.isBlank(queryString)?"": "?"+queryString;
		return req.getRequestURI()+queryString;
	}
}
