<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>秀鼻涕---添加电影</title>
</head>
<body>
<form action="../actions/addMovie.jsp" method="post">
  <ul>
  	<li>电影名称</li><li><input type="text" name="title" /></li>
  	<li>图片</li><li><input type="text" name="picture" /></li>
  	<li>内容介绍</li><li><input type="text" name="content" /></li>
  	<li>标签</li><li><input type="text" name="tag" /></li>
  	<li>下载地址</li><li><input type="text" name="downUrl" /></li>
  	<li><input type="submit" value="添加电影"/></li>
  </ul>
</form>
<jsp:include page="../foot.jsp" />
</body>
</html>