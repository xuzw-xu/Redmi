<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
		<title>爱购我的订单</title>
		<link rel="stylesheet" href="${ctx }/static/css/reset.css" />
		<link rel="stylesheet" href="${ctx }/static/css/top.css" />
		<link rel="stylesheet" href="${ctx }/static/css/buyerTrade.css" />
		<link rel="stylesheet" href="${ctx }/static/css/footer.css" />
		<link rel="stylesheet" href="${ctx }/static/css/font-awesome-4.7.0/Font-Awesome-master/css/font-awesome.min.css" />
	</head>
	<body>
		<!--头部-->
		<%@ include file="../head.jsp" %>
		<div class="w1230">
			<img src="${ctx }/static/img/logo.png" width="100" height="40" class="logo"/>
			<span class="cart">我的订单</span>
		</div>
		<!--订单列表-->
		<div class="w1230 trade">
			<!--标题栏-->
			<div class="trade-title">
				<ul class="clear-float">
					<li class="info">宝贝</li>
					<li class="price">单价</li>
					<li class="num">数量</li>
					<li class="real-price-title">实付款</li>
					<li class="operate">交易操作</li>
				</ul>
			</div>
			<!--详细展示-->
			<div class="trade-list">
			<c:forEach items="${list }" var="order">
				<div>
					<div class="trade-list-top clear-float">
						<div class="trade-list-l">
							<span class="trade-date">${order.createTime}</span>
							<span class="trade-no">订单号:${order.id }</span>
						</div>
						<a href="javascript:;;" class="delete" data="${order.id }"><i class="fa fa-trash trade-list-r"></i></a>
					</div>
					<div>
						<c:forEach var="orderProduct" items="${order.list }">
							<ul class="clear-float">
								<li class="info">
									<img src="${ctx }/common/getImage?name=${orderProduct.product.productImage}" />
									<p>${orderProduct.product.productName}</p>
								</li>
								<li class="price">￥${orderProduct.product.price }</li>
								<li class="num">${orderProduct.productNum }</li>
								<li class="real-price">￥${orderProduct.product.price *  orderProduct.productNum}</li>
								<li class="operate"><a href="javascript:;;" data="${orderProduct.product.id }" class="addToCart">加入购物车</a></li>
							</ul>
						</c:forEach>
					</div>	
				</div>
			</c:forEach>
			</div>
		</div>
		<!--脚注-->
		<%@ include file="../footer.jsp" %>
		<script src="${ctx}/static/plugs/jquery.js"></script>
		<script type="text/javascript">
			$(function (){
				$('.delete').on('click', function (){
					var id = $(this).attr("data");
					$.post('${ctx}/front/order/delete', {id : id},  function(e){
						if (e.result) {
							alert(e.message);
							window.location.reload();
						}else {
							alert(e.message);
						}
					})
				})
				
				$('.addToCart').on('click', function (){
					console.log("555");
					var productId = $(this).attr("data");
					var productNum = $(this).parent().prev().prev().text();
					$.post('${ctx}/front/order/addToShopCart', {productId : productId, productNum : productNum},  function(e){
						if (e.result) {
							alert(e.message);
							window.location.reload();
						}else {
							alert(e.message);
						}
					})
				})
			})
		</script>
	</body>
</html>
