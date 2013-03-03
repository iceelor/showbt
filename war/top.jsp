<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="com.showbt.weibo.User"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.showbt.weibo.util.HttpUrlFetch"%>
<jsp:useBean id="weibo" class="com.showbt.weibo.Weibo" />
<%
Cookie accessToken = HttpUrlFetch.getCookieByName(request, "access_token");
Cookie sinaUid = HttpUrlFetch.getCookieByName(request, "sina_uid");
String token = "";
String uid = "";
User user = null;
if(accessToken != null && sinaUid != null){
	token = accessToken.getValue();
	uid = sinaUid.getValue();
	JSONObject ujson = weibo.getUserInfo(token, uid);
	user = new User(ujson);
}
%>
<div class="g-hd">
    <h1 class="m-logo"><a href="#">LOFTER-网易轻博</a></h1>
    <div id="topbar" class="m-nav ">
	        
	        <ul class="nav2 tbtag">
		        <li class="j-crt"><a href="/">首页</a></li>
				
				<%if(user != null){%>
					<li><img src="<%=user.getProfileImageUrl()%>" /><%=user.getScreenName()%> </li>
					<li><a href="#">退出</a></li>
				<%}else{%>
					<li>
						<a href="https://api.weibo.com/oauth2/authorize?client_id=1815120700&redirect_uri=http://weibo.showbt.com/callback.jsp&response_type=code&state=12">
							<img alt="新浪微博" src="image/sina/32.png" />
						</a>
					</li>
				<%} %>		
	        </ul>
    </div>
</div>