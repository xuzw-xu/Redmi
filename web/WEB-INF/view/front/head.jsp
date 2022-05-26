<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<c:if test="${user==null }">
	<div class="top" id="top">
		<div class="top-bar">
			<div class="w1230 top-bar-main">
				<a href="${ctx}/front/index" class="toIndex"> <i
					class="fa fa-home"></i> <span>~京baby~</span>
				</a>
				<ul>
					<li><a href="${ctx}/front/login/loginPage" class="login">登录</a></li>
					<li><a href="${ctx}/front/login/registerPage" class="register">注册</a></li>
				</ul>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${user!=null }">
	<div class="top" id="top">
		<!--头部bar-->
		<div class="top-bar">
			<div class="w1230 top-main">
				<a href="${ctx}/front/index" class="toIndex"> <i
					class="fa fa-home"></i> <span>~京baby~</span>
				</a>
				<div class="account">
					<span>${user.username }<i class="fa fa-caret-down"></i></span>
					<div class="manage">
						<ul>
							<li><a href="${ctx}/front/receiveing_address/index">收货地址</a></li>
							<li><a href="${ctx}/front/shopCart/shopProductCart">购物车</a></li>
							<li><a href="${ctx}/front/order/index">我的订单</a></li>
							<li><a href="${ctx}/front/login/logout">退出</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>