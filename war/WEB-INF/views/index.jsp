<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/top.jsp" %>
	<div class="g-bd">
		<div class="g-bdc">
			<div id="main" class="g-mn">
			<%@ include file="/WEB-INF/views/menu.jsp" %>
				<%@ include file="/WEB-INF/views/movie/movieList.jsp" %>
			</div>
			<div id="rside" class="g-sd">
				<%@ include file="/WEB-INF/views/right.jsp" %>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/foot.jsp" %>
</body>
</html> 