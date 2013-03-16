<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>秀鼻涕---登录入口！</title>
<style type="text/css">
.login_bg {
	text-align: center;
	width: auto;
	height: 600px;
	padding: 200px 20px;
	background:url("/image/bj<%=new Random().nextInt(2)%>.gif");
}
</style>
</head>
<body>
<div class="login_bg">
	<a href="https://api.weibo.com/oauth2/authorize?client_id=1815120700&redirect_uri=http://weibo.showbt.com/callback.jsp&response_type=code&state=12">
		<img alt="新浪微博" src="/image/sina/32.png" />
	</a>
</div>
</body>
</html>