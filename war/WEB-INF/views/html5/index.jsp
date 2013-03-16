<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/WEB-INF/views/html5/head.jsp" %>

<body>

<div id="wrapper" class="container_12">

    <%@include file="/WEB-INF/views/html5/header.jsp" %>

<div id="content">

    <%@include file="/WEB-INF/views/html5/nav.jsp" %>
    <!-- Show a "Please Upgrade" box to both IE7 and IE6 users (Edit to IE 6 if you just want to show it to IE6 users) - Jquery will load the content from js/ie.html in the div -->
    
    <!--[if lte IE 7]>
    	<div class="ie grid_7"></div>
     <![endif]-->

    <section id="main" class="grid_8 alpha">
    	<c:forEach items="${movieList}" var="m">
	        <article class="post">
	            <h2><a href="#">${m.title}</a></h2>
	            <a href="content.html?id=${m.id}"><img src="${m.pictures[0]}" width="200px" height="200px" alt="" class="thumbnail alignleft" /></a>
	           	<p> 
	           		${m.content}
	            </p>
	            <div class="clear"></div>  
	            
	            <footer class="postmeta">
	            
	                <span class="btn alignleft">
	                	<c:forEach items="${m.tags}" var="t">
	                		<a href="">${t}</a> 
	                	</c:forEach>
						<time datetime="<fmt:formatDate  value="${m.addTime}" type="both" pattern="yyyy-MM-dd" />">
						<fmt:formatDate  value="${m.addTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></time>
	                </span>
	                
	                <a href="${m.downUrl}"　target="_blank" class="more-link alignright">下载</a>
	                
	            </footer> <!-- end post meta -->
	        
	        </article> <!-- end post 1 -->
 		</c:forEach>   
   
    </section> <!-- end main -->

<%@include file="/WEB-INF/views/html5/right.jsp" %>
</div> <!-- end content -->

<%@include file="/WEB-INF/views/html5/footer.jsp" %>
<div class="clear"></div>

</div> <!-- end wrapper -->
</body>
</html>