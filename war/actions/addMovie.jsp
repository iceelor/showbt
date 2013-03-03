<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="movieService" class="com.showbt.web.service.impl.MovieServiceImpl" />
<jsp:useBean id="movie" class="com.showbt.web.pojo.Movie" />
<%
	String title = request.getParameter("title");
	String picture = request.getParameter("picture");
	String content = request.getParameter("content");
	String tag = request.getParameter("tag");
	String downUrl = request.getParameter("downUrl");
	movie.setTitle(title);
	movie.setPicture(picture.split(",|，"));
	movie.setContent(content);
	movie.setTag(tag.split(",|，"));
	movie.setDownUrl(downUrl);
	movie.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	movie.setLike(0);
	movie.setUserId(123444l);
	movie.setNickName("苍龙");
	boolean res = movieService.addMovie(movie);
	response.sendRedirect("../index.jsp");
%>