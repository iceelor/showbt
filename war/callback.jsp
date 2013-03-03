<%@page import="com.showbt.weibo.util.HttpUrlFetch"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.showbt.weibo.Weibo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%
	String code = request.getParameter("code");
	Weibo weibo = new Weibo();
	String res = weibo.getAccessToken(code,1);
	JSONObject obj = JSON.parseObject(res);
	String accessToken = obj.getString("access_token");
	String uid = obj.getString("uid");
	int expiresIn = obj.getIntValue("expires_in");
	HttpUrlFetch.addCookie(response, "access_token", accessToken, expiresIn);
	HttpUrlFetch.addCookie(response, "sina_uid", uid, expiresIn);
	out.println(res);
%>
<a href="./index.jsp">回首页</a>