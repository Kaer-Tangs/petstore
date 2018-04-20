<%@page import="com.newer.petstore.domain.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.newer.petstore.AppInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
	.addCart {
		background-color: #4a4;
		padding: 0.5em 2em;
		color: #fff;
	}
	.buy {
		background-color: red;
		color:#fff;
		margin-left: 2em;
		font-size: 0.7em;
	}
</style>

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		// JS
		// 放入购物车
		// 查找文档中 class='.addCart' 的元素
		$('.addCart').on('click', function() {
			// alert($(this).val());
			// 发送 AJAX （异步）请求:1)URL 2)数据 3)回调函数
			// 闭包（函数的状态）
			var e = $(this);
			$.post(
					'cart', 
					{id:$(this).val(),action:'add'},     
					function(data) {
						// 给点击的元素增加一个类选择器
						//e.addClass('buy');
						// 在事件源后创建一个新元素
						// <span class='buy'>已购买</span>
						e.after($('<span>').text('已购买').addClass('buy'));
					});
		});
		
		// 搜索
		$('#btnSearch').on('click', function() {
			
			$.get(
					'search',
					{keyWords:$('#key').val()}, 
					function(data) {
						//alert(data);
						
						$('table').remove();
						$('#r').text(data);
						
						// DOM
						// todo list
					});
		});
	});
</script>

<link href="css/master.css" rel="stylesheet">

</head>
<body>
	<%@ include file="header.jsp"%>
	<div id="r"></div>
	<table>
		<tr>
			<th>名称</th>
			<th>价格</th>
			<th>库存</th>
			<th>销量</th>
			<th>图片的路径</th>
			<th></th>
		</tr>

		<c:forEach items="${products}" var="p">
			<tr>
				<td>${p.title }</td>
				<td>${p.price }</td>
				<td>${p.quantity }</td>
				<td>${p.salesNum }</td>
				<td><img alt="${p.title }" src="${p.picPath }" width="100px"
					height="100px"></td>
				<td><button class="addCart" value="${p.id}">加入购物车</button></td>
			</tr>
		</c:forEach>

	</table>

	<%@ include file="footer.jsp"%>

</body>
</html>