<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
    
        <article class="post">
        
            <h2>Kontakt</h2>
            
            <form action="sendmail.php" method="post" id="contactform">
            
            <p>
                <input type="text" name="name" id="name" class="grid_3" value="" />
                <label for="name">Your Name</label>
            </p>
            
            <p>
                <input type="text" name="email" id="email" class="grid_3" value="" />
                <label for="email">Your Email</label>
            </p>
            
            <p>
                <input type="text" name="subject" id="subject" class="grid_3" value="" />
                <label for="subject">Subject</label>
            </p>
            
            <p>
                <textarea name="message" id="message" class="grid_6" cols="50" rows="10"></textarea>
            </p>
            
            <p>
                <input type="submit" name="submit" class="grid_2" value="Send Email" />
            </p>
            
            <p class="hide grid_7" id="response"></p>
    
            <div class="hide">
                <label for="spamCheck">Do not fill out this field</label>
                <input name="spam_check" type="text" value="" />
            </div>
            
            </form>
            
            <div class="clear"></div>
        
        </article>
    
    </section> <!-- end main -->

<%@include file="/WEB-INF/views/html5/right.jsp" %>
</div> <!-- end content -->

<%@include file="/WEB-INF/views/html5/footer.jsp" %>

<div class="clear"></div>

</div> <!-- end wrapper -->
</body>
</html>