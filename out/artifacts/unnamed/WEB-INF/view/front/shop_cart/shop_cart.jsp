<%@ pge language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
		<title>爱购购物车</title>
		<link rel="stylesheet" href="${ctx }/static/css/reset.css" />
		<link rel="stylesheet" href="${ctx }/static/css/top.css" />
		<link rel="stylesheet" href="${ctx }/static/css/shoppingCart.css" />
		<link rel="stylesheet" href="${ctx }/static/css/footer.css" />
		<link rel="stylesheet" href="${ctx }/static/css/font-awesome-4.7.0/Font-Awesome-master/css/font-awesome.min.css" />
		<script src="${ctx}/static/plugs/jquery.js"></script>
		<script type="text/javascript">
			var basePath = '${ctx}';
		</script>
		<script type="text/javascript" src="${ctx }/static/js/shop_cart/shoppingCart.js" ></script>
	</head>
	<body>
		<!--头部-->
		<%@ include file="../head.jsp" %>
		<div class="w1230">
			<img src="${ctx }/static/img/jingbaby.png" width="100" height="40" class="logo"/>
			<span class="cart">购物车</span>
		</div>
		<!--收货地址-->
		<div class="w1230 address">
			<div class="default">
				<div class="default-title">收货地址</div>
				<div class="default-list">
					<span class="add-person">收货人：<span id="addName"></span></span>
					<span class="add-tel" id="addTel"></span>
					<p class="add">收货地址：<span id="add"></span></p>
					<input type="hidden" id="address_id">
				</div>
				<div class="down"><i class="fa fa-chevron-up" id="down"></i></div>
			</div>
			<div class="address-list" id="address-list">
				<ul>
					<c:forEach var="addr" items="${address}">
						<li class="clear-float">
							<input type="radio" name="address" class="single" />
							<div class="address-des">
								<span class="add-person">${addr.receivingPerson}</span>
								<span class="add-tel">${addr.mobilePhone }</span>
								<p>${addr.receivingAddress }</p>
								<input type="hidden" class="address_id" value="${addr.id }">
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!--购物车展示-->
		<div class="w1230 shopping">
			<!--标题栏-->
			<div class="shopping-title">
				<ul class="clear-float">
					<li class="chk"><input type="checkbox" class="all-chk"/></li>
					<li class="info">商品信息</li>
					<li class="price">单价（元）</li>
					<li class="num">数量</li>
					<li class="min-price-title">小计（元）</li>
					<li class="operate">操作</li>
				</ul>
			</div>
			<!--详细展示-->
			<div class="shopping-list">
				<c:forEach items="${list }" var="shopCartProduct">
					<ul class="clear-float">
						<li class="chk"><input type="checkbox" class="chkbox" data-id="${shopCartProduct.productId}" data-num="${shopCartProduct.productNum }"/></li>
						<li class="info"><a href="#" target="_blank">
							<img src="${ctx }/common/getImage?name=${shopCartProduct.product.productImage}" />
							<p>${shopCartProduct.product.productName }</p>
						</a></li>
						<li class="price">${shopCartProduct.product.price}</li>
						<li class="num">
							<a href="#" class="minus">-</a>
							<input type="text" value="${shopCartProduct.productNum }" class="num-val"/>
							<a href="#" class="add">+</a>
						</li>
						<li class="min-price">${shopCartProduct.product.price *  shopCartProduct.productNum}</li>
						<li class="operate"><a href="javascrip:;;" data="${shopCartProduct.id }" class="del_pro">删除</a></li>
					</ul>
				</c:forEach>
			</div>
			<!--合计-->
			<div class="shopping-footer clear-float">
				<div class="shopping-footer-l">
					<input type="checkbox" class="all-chk2 all-chk"/>
					<span>全选（共<span class="all-num"></span>件）</span>
					<span class="line">|</span>
					<a href="#" class="all-del">批量删除</a>
				</div>
				<div class="shopping-footer-r">
					<span>已选商品<span class="choose-num">0</span>件</span>
					<span class="line">|</span>
					<span>合计：<span class="total">￥0.00</span></span>
					<a href="javascript:;;" class="compute">结算</a>
				</div>
			</div>
		</div>
		<!--脚注-->
		<%@ include file="../footer.jsp" %>
	</body>
</html>
