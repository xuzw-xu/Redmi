<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
		<title>商品分类</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/iconfont/iconfont.css">
		<link rel="stylesheet" href="${ctx }/static/css/reset.css" />
		<link rel="stylesheet" href="${ctx }/static/css/top.css" />
		<link rel="stylesheet" href="${ctx }/static/css/footer.css" />
		<link rel="stylesheet" href="${ctx }/static/css/classify.css" />
		<link rel="stylesheet" href="${ctx }/static/css/font-awesome-4.7.0/Font-Awesome-master/css/font-awesome.min.css" />
	</head>
	<body>
		<!-- 隐藏内容 -->
		<input type="hidden" name="type" id="ptype" value="${productType2.id }">
	
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
						<input type="text"  placeholder="牛奶" class="search-txt" value="${words }">
						<a href="javascript:;;" class="search-btn">搜索</a>
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
		<!--导航栏-->
		<div class="nav">
			<div class="w1230">
				<div id="all-goods">
					<span class="all-goods">所有商品分类</span>
					<div class="nav-er" id="nav-er">
						<ul>
							<c:forEach items="${allProductTypes }" var="productType">
								<li>
									<h3><a href="${ctx }/front/productType/index?type=${productType.id}"><i class="iconfont ${productType.productTypeIcon }"></i>${productType.productTypeName }</a></h3>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
			</div>
		</div>
		<!--面包屑导航栏-->
		
		<c:if test="${productType2.productTypeIcon!=null}">
			<div class="crumbSlide w1230">
				<i class="iconfont ${productType2.productTypeIcon } classify-icon"></i>
				<span>${productType2.productTypeName }</span>
				<i class="fa fa-angle-right"></i>
			</div>
		</c:if>
		
		<!--相关分类（品牌）-->
		<div class="classify-brand w1230 clear-float">
			<div class="brand-title">品牌</div>
			<div class="brands">
				<ul class="clear-float">
					<c:forEach items="${listBrand}" var="brand" varStatus="v">
					<%-- <li>
						<a class="brandP" href="javascript:;;" data="${brand.id }">
							<img src="${ctx}/common/getImage?image=${brand.brandImg }" width="50" height="50px" /><br />${brand.brandName }
						</a>
					</li> --%>
					<li>
						<input type="checkbox" class="chk" id="chk${v.count}" value="${brand.id }"/>
						<label for="chk${v.count}">
							<img src="${ctx}/common/getImage?name=${brand.brandImg }" width="50" height="50px"/><br />${brand.brandName }
							<img src="${ctx}/static/img/choose.PNG" class="choose"/>
						</label>
					</li>
				</c:forEach>
				</ul>
				<div class="brands-btn">
					<a href="#" class="brands-sure">确定</a>
					<a href="javascript:void(0)" class="brands-cancel" id="brands-cancel">重置</a>
				</div>
			</div>
		</div>
		<!--综合分类-->
		<div class="search-select w1230">
			<ul>
				<li><a href="#">综合</a></li>
				<li><a href="#">销量</a></li>
				<li><a href="#">新品</a></li>
				<li>
					<a href="#">
						<span>价格</span>
						<input type="number" placeholder="￥" /> -
						<input type="number" placeholder="￥" />
					</a>
					<a href="#" class="price-sure">确定</a>
					<a href="javascript:void(0)" class="price-cancel" id="price-cancel">清空</a>
				</li>
			</ul>
		</div>
		<!--商品展示-->
		<div class="goods-show w1230">
			<ul class="clear-float">
				<c:forEach items="${datas}" var="product">
					<li><a href="#">
						<div class="g-img"><a href="${ctx}/front/product_detail/productDetail?id=${product.id}"><img src="${ctx}/common/getImage?image=${product.productImage}" alt="" width="230px" height="230px"/></a></div>
						<p class="g-title">${product.productName }</p>
						<span class="g-price">￥${product.price }</span>
						<span class="g-num">销量:${product.sales }</span>
					</a></li>
				</c:forEach>
			</ul>
		</div>
		<!--脚注-->
		<%@ include file="../footer.jsp" %>
		<script src="${ctx}/static/plugs/jquery.js"></script>
		<script>
			var all = document.getElementById("all-goods");
			var navEr = document.getElementById("nav-er");
			all.onmouseover = function(){
				navEr.style.display = "block";
			}
			all.onmouseout = function(){
				navEr.style.display = "none";
			}
			
			$(function (){
				var _brand = '${brands}';
				var _brands = _brand.split(",");
				for (var _b in _brands) {
					$('.chk[value="'+ _brands[_b] +'"]').prop("checked", true);
				}
				
				
				//品牌确定按钮
				$('.brands-sure').on('click', function (){
					var ids = "";
					$('.chk:checked').each(function (i,d){
						ids = ids + $(this).val();
						if (i!=$('.chk:checked').length-1) {
							ids = ids + ",";
						}
					})
					window.location.href = "${ctx}/front/productType/index?type="+ $('#ptype').val() +"&brands=" + ids;
				})
				
				//品牌重置按钮
				$('.brands-cancel').on('click', function (){
					$('.chk').prop("checked", false);
				})
				
				//搜索按钮
				$('.search-btn').on('click', function(){
					var words = $('.search-txt').val();
					window.location.href = "${ctx}/front/productType/index?type="+ $('#ptype').val() +"&words=" + words;
				})
				
				/* $('.brandP').on('click', function(){
					var brand = $(this).attr('data');
					window.location.href = "${ctx}/front/productType/index?type="+ $('#ptype').val() +"&brandP=" + brand;
				}) */
				
				//热搜词
				$('.hotSearch').on('click', function (){
					$('.search-txt').val($(this).text());
					$('.search-btn').click();
				})
				
				//搜索框回车事件
				$('.search-txt').on('keyup', function(event){
					if(event.keyCode ==13){
						$('.search-btn').click();
				  	}
				})
			})
		</script>
	</body>
</html>
