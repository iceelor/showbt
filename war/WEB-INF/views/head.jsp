<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<title>秀鼻涕-----欢迎您的到来!</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/pt_lib_macro.css?e4865c32c96789adb733fa63c1aa534b"></c:url>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/pt_page_dashboard.css?07cd8fe7231587e61deb9f1f2b718a2f"></c:url>">
<script type="text/javascript" src="<c:url value="/js/jquery-1.7.2.min.js"></c:url>"></script>
<script type="text/javascript">
	//$(".imgc").live("click", function(even){
		//$(this).css("display","none");
		//$(".cnt ol").css("display","");
	//});
	
	$(document).ready(function(){
		$(".img").click(function(){
			var dis = $(this).find(".imgc").css("display");
			if(dis == 'none'){
				$(this).css("width","164px");
			}else{
				$(this).css("width","500px");
			}
			$(this).find(".imgc").slideToggle("slow");
			$(this).find("ol").slideToggle("slow");
			$(this).find(".imgc").toggleClass("active"); 
			return false;
		}); 
	});
</script>