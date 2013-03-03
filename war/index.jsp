<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="head.jsp" />
</head>
<body>
<jsp:include page="top.jsp" />
	<div class="g-bd">
		<div class="g-bdc">
			<div id="main" class="g-mn">
				<jsp:include page="menu.jsp" />
				<jsp:include page="contentList.jsp" />
			</div>
			<div id="rside" class="g-sd">
				<jsp:include page="right.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html> 