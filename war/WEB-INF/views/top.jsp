<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="g-hd">
    <h1 class="m-logo"><a href="#">ShowBt-秀鼻涕</a></h1>
    <div id="topbar" class="m-nav ">
	        <ul class="nav2 tbtag">
		        <li class="j-crt"><a href="/">首页</a></li>
					<li><img src="${user.profileImageUrl}" />${user.screenName}</li>
					<li><a href="/loginout.jsp">退出</a></li>
	        </ul>
    </div>
</div>