<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
		<title>爱购商品详情页</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/iconfont/iconfont.css">
		<link rel="stylesheet" href="${ctx }/static/css/reset.css" />
		<link rel="stylesheet" href="${ctx }/static/css/top.css" />
		<link rel="stylesheet" href="${ctx }/static/css/goodsDetail.css" />
		<link rel="stylesheet" href="${ctx }/static/css/footer.css" />
		<link rel="stylesheet" href="${ctx }/static/css/font-awesome-4.7.0/Font-Awesome-master/css/font-awesome.min.css" />
		<script src="${ctx}/static/plugs/jquery.js"></script>
		<script>
			$(function(){
				//增加商品数量
				$(".add").click(function(){
					$("#goodsNum").val(parseInt($("#goodsNum").val())+1);
					$(".reduce").css({"backgroundPositionX": "0", "backgroundPositionY": "25px"});
				});
				//减少商品数量
				$(".reduce").click(function(){
					console.log($("#goodsNum").val());
					if($("#goodsNum").val()>1){
						$("#goodsNum").val(parseInt($("#goodsNum").val())-1);
						$(this).css({"backgroundPositionX": "0", "backgroundPositionY": "25px"});
					}
					if($("#goodsNum").val()==1){
						$(this).css({"backgroundPositionX": "-24px", "backgroundPositionY": "25px"});
					}
					
				});
				var timer;
				//点击添加购物车
				$("#addCart").click(function(){
					var num = $('#goodsNum').val();
					var product_id = $('#product_id').val();
					$.post('${ctx}/front/shop_cart/addProductToCart', {
						'product_id' : product_id,
						'product_num' : num
					},function (e){
						if (e.result) {
							clearTimeout(timer);
							$("#tips").css("display","inline-block");
							timer = setTimeout(function(){
								$("#tips").css("display","none");
							},3000);
						}else {
							alert(e.message);
						}
					})
					
					
				});
				
				//搜索按钮
				$('.search-btn').on('click', function(){
					var words = $('.search-txt').val();
					window.location.href = "${ctx}/front/productType/index?type=&words=" + words;
				})
				
				//热搜词
				$('.hotSearch').on('click', function (){
					$('.search-txt').val($(this).text());
					$('.search-btn').click();
				})
			});	
		</script>
	</head>
	<body>
		<!--头部-->
		<div class="top" id="top">
			<!--头部bar-->
			<%@ include file="../head.jsp" %>
			<!--logo+搜索-->
			<div class="top-header w1230 clear-float">
				<a href="${ctx}/front/index" class="logo">
					<img src="${ctx }/static/img/jingbaby.png"/>
				</a>
				<div class="top-header-right">
					<!--搜索框-->
					<div class="search clear-float">
						<input type="text"  placeholder="牛奶" class="search-txt"/>
						<a href="#" class="search-btn">搜索</a>
					</div>
					<!--热搜-->
					<p class="hotkey">
						<c:forEach items="${searchHistorys}" var="searchHistory">
							<a href="javascript:;;" class="hotSearch">${searchHistory.searchWords }</a>
						</c:forEach>
					</p>
				</div>
			</div>
		</div>
		<!--导航栏-->
		<div class="nav">
			<div class="w1230">
				<div id="all-goods">
					<span class="all-goods">所有商品分类</span>
					<div class="nav-er" id="nav-er">
						<ul>
							<c:forEach items="${allProductTypes}" var="productType">
								<li>
									<h3><a href="${ctx }/front/productType/index?type=${productType.id}"><i class="iconfont ${productType.productTypeIcon }"></i>${productType.productTypeName }</a></h3>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>			
			</div>
		</div>
		<!--商品详情-->
		<div class="w1230 clear-float goods-main">
			<div class="big-img">
				<img src="${ctx}/common/getImage?name=${products.productImage}" />
			</div>
			<div class="goods-detail">
				<input type="hidden" id="product_id" value="${products.id}">
				<h3 class="goods-title">${products.productName }</h3>
				<p class="price">价格<span>￥${products.price }</span></p>
				<p class="store-num">销量：<span>${sum}件</span></p>
				<div class="update-num">
					<div>
						<input type="text" value="1" id="goodsNum"/>
						<span class="add"></span>
						<span class="reduce"></span>
					</div>
					<a href="javascript:;;" id="addCart" title="添加购物车"><i class="fa fa-shopping-cart"></i>添加购物车</a><span id="tips"><i class="fa fa-check-circle-o"></i>添加成功</span>
				</div>
			</div>
			<!--店家承诺-->
			<div class="promise">
				<h3><i class="fa fa-handshake-o"></i>爱购承诺</h3>
				<p>爱购平台卖家销售并发货的商品，由平台卖家提供发票和相应的售后服务。请您放心购买！
注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若没有及时更新，请大家谅解！</p>
			</div>
		</div>
		<!--商品介绍-->
		<div class="w1230 clear-float">
			<!--商品推荐-->
			<div class="recommend goods-show">
				<h3>看了本商品的用户最终还看了</h3>
				<ul class="clear-float">
					<c:forEach items="${list.list}" var="p">
						<li><a href="#">
							<div class="g-img"><a href="${ctx}/front/product_detail/productDetail?productId=${p.id}"><img src="/common/getImage?name=${p.productImage}" alt="" width="230px" height="230px"/></a></div>
							<p class="g-title">${p.productName }</p>
							<span class="g-price">￥${p.price }</span>
							<span class="g-num">销量:${othersum}</span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
			<!--商品介绍-->
			<div class="goods-des">
				<h3 class="goods-tro">商品介绍</h3>
				<div class="goods-info">
					<ul>
						<c:forEach var="url" items="${fn:split(products.productDesc,',')}">
							<li><img src="${ctx }/common/getImage?name=${url}" alt="" /></li>
						</c:forEach>
					</ul>
				</div>
			</div>	
		</div>
		<!--脚注-->
		<%@ include file="../footer.jsp" %>
	</body>
</html>
