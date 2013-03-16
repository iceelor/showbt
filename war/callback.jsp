<%@page import="com.showbt.web.util.UserSessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	if(UserSessionUtil.getInstance().sinaCallback(request, response)){
		out.println("用户登录成功！");
	}else{
		out.println("登录失败！");		
	}
	
%>
<a href="/index">回首页</a>