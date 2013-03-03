<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="photoService" class="com.showbt.web.service.impl.PhotoServiceImpl" />
<jsp:useBean id="photo" class="com.showbt.web.pojo.Photo" />
<%
	String a1 = request.getParameter("a1");
	String a2 = request.getParameter("a2");
	photo.setTitle(a1);
	photo.setCaption(a1);
	photo.setContentType(a1);
	photo.setPhotoData(a2.getBytes());
	photo.setPhotoPath(a2);
	boolean res = photoService.addPhoto(photo);
	out.print(res);
%>