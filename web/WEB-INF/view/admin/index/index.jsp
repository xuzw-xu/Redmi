<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>首页</title>
<script type="text/javascript" src="${ctx}/static/js/index/menu.js"></script>
<script type="text/javascript">
	$(function (){
		/* 修改密码按钮点击事件 */
		$('.change-password').on('click', function (){
			var name = $("#userchangepwd").val();
			layer.open({
				title: "修改密码",
		  		type: 2,
			  	area: ['600px', '300px'],
			  	fixed: false, //不固定
			  	maxmin: true,
			  	content: basePath + '/admin/userInformation/changePasswordPage?name='+name
			});
			return false;
		})

	})
</script>
</head>
<body>
	<div class="hp-header">
		<ul class="hp-header-right">
			<li>
				<input type="hidden" value="${usernameforchangepwd}" name = "username" id = "userchangepwd">
				<span class="hp-nav-bar"></span>
				<a href="javascript:void();;"><i class="iconfont icon-people_fill"></i>&nbsp;${_admin}</a>
			</li>
			<li class="change-password">
				<span class="hp-nav-bar"></span>
				<a href="javascript:void();;"><i class="iconfont icon-brush"></i>&nbsp;修改密码</a>
			</li>
			<li class="logout">
				<span class="hp-nav-bar"></span>
				<a href="${ctx}/admin/userInformation/logout"><i class="iconfont icon-logout"></i>&nbsp;退出</a>
			</li>
		</ul>
	</div>
	<div class="hp-site-menu">
		<div class="hp-site-scroll">
			<div class="hp-logo">
				<span>ESHOP ADMIN</span>
			</div>
			<ul class="hp-nav-tree">
				<li class="hp-nav-item">
					<div>
						<span class="hp-nav-tree-bar"></span>
						<a href="javascript:;"><i class="iconfont icon-setup_fill"></i>&nbsp;系统管理</a>
					</div>
					<dl class="hp-nav-child">
						<dd>
							<div>
								<span class="hp-nav-tree-bar"></span>
								<a href="${ctx}/admin/userInformation/list">用户维护</a>
							</div>
						</dd>
					</dl>
				</li>
				<li class="hp-nav-item">
					<div>
						<span class="hp-nav-tree-bar"></span>
						<a href="javascript:;"><i class="iconfont icon-picture"></i>&nbsp;轮播图</a>
					</div>
					<dl class="hp-nav-child">
						<dd>
							<div>
								<span class="hp-nav-tree-bar"></span>
								<a href="${ctx}/admin/carousels/list">轮播图维护</a>
							</div>
						</dd>
					</dl>
						
				</li>
				<li class="hp-nav-item">
					<div>
						<span class="hp-nav-tree-bar"></span>
						<a href="javascript:;"><i class="iconfont icon-qrcode_fill"></i>&nbsp;商品分类</a>
					</div>
					<dl class="hp-nav-child">
						<dd>
							<div>
								<span class="hp-nav-tree-bar"></span>
								<a href="${ctx}/admin/productType/list">商品分类维护</a>
							</div>
						</dd>
					</dl>
				</li>
				<li class="hp-nav-item">
					<div>
						<span class="hp-nav-tree-bar"></span>
						<a href="javascript:;"><i class="iconfont icon-shop_fill"></i>&nbsp;商品品牌</a>
					</div>	
					<dl class="hp-nav-child">
						<dd>
							<div>
								<span class="hp-nav-tree-bar"></span>
								<a href="${ctx}/admin/brands/list">商品品牌维护</a>
							</div>
						</dd>
					</dl>
				</li>
				<li class="hp-nav-item">
					<div>
						<span class="hp-nav-tree-bar"></span>
						<a href="javascript:;"><i class="iconfont icon-commodity"></i>&nbsp;商品管理</a>
					</div>
					<dl class="hp-nav-child">
						<dd>
							<div>
								<span class="hp-nav-tree-bar"></span>
								<a href="${ctx}/admin/products/list">商品维护</a>
							</div>
						</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>
	<div class="hp-context">
		
	</div>
</body>
</html>