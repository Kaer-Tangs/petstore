<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
	<h1><a href="index.jsp">宠物店</a></h1>
	<menu>
		<c:forEach items="${categories}" var="c">
			<li>${c.title}</li>
		</c:forEach>
		<input type="text" id="key" name="keyWords" placeholder="请输入关键字" />
		<button id="btnSearch">搜索</button>
		<li>注销</li>
		<li><a href="cart.jsp">购物车</a></li>
	</menu>
</header>