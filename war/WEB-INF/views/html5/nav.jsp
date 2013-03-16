<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
	<ul id="menu" class="clearfix">
		<li <c:if test="${navId==0}">class="current"</c:if>><a href="index.html">首页</a></li>
		<li <c:if test="${navId==1}">class="current"</c:if>><a href="about.html">关于我们</a>

			<ul>
				<li <c:if test="${navId==11}">class="current"</c:if>><a href="about.html">Subpage</a></li>
				<li <c:if test="${navId==12}">class="current"</c:if>><a href="about.html">Subpage 2</a></li>
			</ul></li>
		<li <c:if test="${navId==2}">class="current"</c:if>><a href="reg.html">注册</a></li>
	</ul>

	<br class="clear" />
</nav>