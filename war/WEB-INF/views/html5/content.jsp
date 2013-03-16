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
    <!-- Show a "Please Upgrade" box to both IE7 and IE6 users (Edit to IE 6 if you just want to show it to IE6 users) - jQuery will load the content from js/ie.html in the div -->
    
    <!--[if lte IE 7]>
    	<div class="ie grid_7"></div>
     <![endif]-->

    <section id="main" class="grid_8 alpha">
    
        <article class="post">
        
            <h2>${movie.title}</h2>
            
            <ul>
            	<c:forEach items="${movie.pictures}" var="p">
                	<li><a href="#"><img src="${p}" alt="" width="500px" /></a></li>
                </c:forEach>
            </ul>
            <p>${movie.content}</p>
            
            <div class="clear"></div>  
        
        </article> <!-- end page 1 -->
    
    </section> <!-- end main -->

<%@include file="/WEB-INF/views/html5/right.jsp" %>

</div> <!-- end content -->

<%@include file="/WEB-INF/views/html5/footer.jsp" %>

<div class="clear"></div>

</div> <!-- end wrapper -->
</body>
</html>