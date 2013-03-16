package com.showbt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showbt.web.util.UserSessionUtil;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getRequestURL().toString();
		if((url.lastIndexOf("/")+1) == url.length()){
			response.sendRedirect(url+"index");
			return;
		}
		if(url.indexOf("/login.jsp") !=-1 || url.indexOf("/callback.jsp") !=-1 || UserSessionUtil.getInstance().isLogin(request)){
			chain.doFilter(req, res);
			return;
		}else{
			response.sendRedirect("/login.jsp");
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
