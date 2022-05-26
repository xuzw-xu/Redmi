<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />      
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
		<title>~京baby~</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/iconfont/iconfont.css">
		<link rel="stylesheet" href="${ctx }/static/css/reset.css" />
		<link rel="stylesheet" href="${ctx }/static/css/top.css" />
		<link rel="stylesheet" href="${ctx }/static/css/index.css" />
		<link rel="stylesheet" href="${ctx }/static/css/footer.css" />
		<link rel="stylesheet" href="${ctx }/static/css/font-awesome-4.7.0/Font-Awesome-master/css/font-awesome.min.css" />
		<script type="text/javascript" src="${ctx}/static/js/banner.js" ></script>
	</head>
	<body>
		<!--头部-->
		<div class="top" id="top">
			<!--头部bar-->
			<%@ include file="../head.jsp" %>
			<!--logo+搜索-->
			<div class="top-header w1230 clear-float">
				<a href="${ctx}/front/index" class="logo">
					<img src="${ctx}/static/img/jingbaby.png" width=""/>
				</a>
				<div class="top-header-right">
					<!--搜索框-->
					<div class="search clear-float">
						<input type="text"  placeholder="牛奶" class="search-txt"/>
						<a href="#" class="search-btn">搜索</a>
					</div>
					<!--热搜-->
					<p class="hotkey">
						<c:forEach items="${searchHistorys }" var="searchHistory">
							<a href="javascript:;;" class="hotSearch">${searchHistory.searchWords }</a>
						</c:forEach>
					</p>
				</div>
			</div>
		</div>
		<!--分类+banner-->
		<div class="main w1230 clear-float">
			<!--商品分类-->
			<div class="classify">
				<ul>
					<c:forEach items="${allProductTypes}" var="productType">
						<li>
							<h3><a href="${ctx }/front/productType/index?type=${productType.id}"><i class="iconfont ${productType.productTypeIcon }"></i>${productType.productTypeName}</a></h3>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!--轮播-->
			<div class="banner" id="main">
				<ul id="pic">
					<c:forEach var="carouselFigure" items="${allcarouselFigures}">
						<li><a href="#"><img src="${ctx}/common/getImage?name=${carouselFigure.url}" alt="" width="1020px" height="360px"/></a></li>
					</c:forEach>
				</ul>
				<ul class="banner-btn" id="list">
					<c:forEach var="carouselFigure" items="${allcarouselFigures}" varStatus="n" >
						<c:if test="${n.index==0 }">
							<li class="on"></li>
						</c:if>
						<c:if test="${n.index!=0 }">
							<li></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!--新品+排行榜-->
		<div class="new-rank w1230 clear-float">
			<a href="#" class="new-img"><img src="${ctx}/static/img/new.jpg" width="267px" height="400px"/></a>
			<!--新品-->
			<div class="new">
				<h3 class="title">新品</h3>
				<div class="new-list">
					<ul>
						<c:forEach items="${newProducts}" var="newProduct">
							<li>
								<a href="${ctx}/front/productType/productDetail?id=${newProduct.id}">
									<img src="${ctx}/common/getImage?name=${newProduct.productImage}" alt="" width="90px" height="90px"/>
									<p>${newProduct.productName }</p>
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!--排行榜-->
			<div class="rank">
				<h3 class="title">排行榜</h3>
				<div class="rank-list">
					<ul>
						<c:forEach items="${rankings }" var="ranking" varStatus="step">
							<li>
								<a href="${ctx}/front/productType/productDetail?id=${ranking.id}">
									<span class="rank-icon${step.count}">${step.count }</span>
									<img src="${ctx}/common/getImage?name=${ranking.productImage}" alt="" width="90px" height="90px"/>
									<p>${ranking.productName }</p>
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!--侧导航栏-->	
		<div class="c-nav">
			<ul>
				<li><a href="#global-foot" class="nav-g">全球进口</a></li>
				<li><a href="#cloth" class="nav-c">服装服饰</a></li>
				<li><a href="#mod" class="nav-m">护肤美妆</a></li>
				<li><a href="#book" class="nav-b">图书学习</a></li>
				<li><a href="#top" class="nav-top">返回顶部</a></li>
			</ul>
		</div>
		<!--全球进口-->
		<div class="global-foot w1230" id="global-foot">
			<h3 class="h-title">全球进口</h3>
			<div class="global-list">
				<ul class="clear-float">
					<c:forEach items="${list}" var="p1">
						<li><a href="${ctx}/front/productType/productDetail?id=${p1.id}">
							<p>${p1.productName }</p>
							<img src="${ctx}/common/getImage?name=${p1.productImage}" alt="" width="150px" height="150px"/></a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!--服装服饰-->
		<div class="cloth w1230" id="cloth">
			<h3 class="h-title">服装服饰</h3>
			<div class="cloth-list">
				<ul class="clear-float">
					<c:forEach items="${list2 }" var="p2">
						<li><a href="${ctx}/front/productType/productDetail?id=${p2.id}">
							<div class="c-img"><img src="${ctx}/common/getImage?name=${p2.productImage}" alt="" width="180px" height="180px"/></div>
							<p class="c-title">${p2.productName}</p>
							<span class="c-price">￥${p2.price }</span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!--护肤美妆-->
		<div class="mod w1230" id="mod">
			<h3 class="h-title">护肤美妆</h3>
			<div class="mod-list">
				<ul class="clear-float">
					<c:forEach items="${list3 }" var="p3">
						<li><a href="${ctx}/front/productType/productDetail?id=${p3.id}">
							<img src="${ctx}/common/getImage?name=${p3.productImage}" alt="" width="100px" height="100px"/>
							<div class="mod-info">
								<p class="m-title">${p3.productName}</p>
								<span class="m-price">￥${p3.price }</span>
							</div>
						</a></li>
					</c:forEach>	
				</ul>
			</div>
		</div>
		<!--图书学习-->
		<div class="book w1230" id="book">
			<h3 class="h-title">图书学习</h3>
			<div class="book-list">
				<ul class="clear-float">
					<c:forEach items="${list4 }" var="p4">
						<li><a href="${ctx}/front/productType/productDetail?id=${p4.id}">
							<img src="${ctx}/common/getImage?name=${p4.productImage}" alt="" width="180px" height="180px"/>
							<p class="c-title">${p4.productName}</p>
							<span class="c-price">￥${p4.price}</span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!--脚注-->
		<div class="footer">
			<p class="w1230">
				<a href="#">关于爱购</a>
				<a href="#">合作伙伴</a>
				<a href="#">营销中心</a>
				<a href="#">廉正举报</a>
				<a href="#">联系客服</a>
				<a href="#">开发平台</a>
				<a href="#">诚征英才</a>
				<a href="#">联系我们</a>
				<a href="#">网站地图</a>
				<a href="#">知识产权</a><span>|</span>
				<span>&copy;2018-2020 igo.com 版权所有</span>
			</p>
		</div>
		<!-- 隐藏内容 -->
		<input type="hidden" name="type" id="ptype" value="">
		<script src="${ctx}/static/plugs/jquery.js"></script>
		<script type="text/javascript">
			$(function (){
				$('.search-btn').on('click', function(){
					var words = $('.search-txt').val();
					window.location.href = "${ctx}/front/index/ ?type="+ $('#ptype').val() +"&words=" + words;
				})
				
				$('.hotSearch').on('click', function (){
					$('.search-txt').val($(this).text());
					$('.search-btn').click();
				})
				
				$('.search-txt').on('keyup', function(event){
					if(event.keyCode ==13){
						$('.search-btn').click();
				  	}
				})

			})
		</script>
	</body>
</html>
