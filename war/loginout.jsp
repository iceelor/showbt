<%@page import="com.showbt.web.util.UserSessionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
UserSessionUtil.getInstance().getLoginOut(request, response);
response.sendRedirect("/index.jsp");
%>