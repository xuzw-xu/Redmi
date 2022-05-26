<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
		<title>~Login 京baby~</title>
		<link rel="stylesheet" href="${ctx }/static/css/reset.css" />
		<link rel="stylesheet" href="${ctx }/static/css/footer.css" />
		<link rel="stylesheet" href="${ctx }/static/css/login.css" />
	</head>
	<body>
		<!--登录/注册的头部-->
		<div class="lr-top w1230">
			<a href="index.html"><img src="${ctx }/static/img/jingbaby.png" width="150px" height="60px"/></a>
			<div class="top-link">
				<a href="javascript:;;" class="top-link1"></a>
				<a href="javascript:;;" class="top-link2"></a>
				<a href="javascript:;;" class="top-link3"></a>
			</div>
		</div>
		<!--登录/注册的中部-->
		<div class="lr-main">
			<div class="w1230">
				<div class="reg-div">
					<h3 class="login-title">~Login 京baby~</h3>
					<p class="go-reg">~Not a 京baby~？<a href="${ctx}/front/login/registerPage">在此注册</a></p>
					<form method="post">
						<p><span class="icon-account"></span><input type="text" name="username" placeholder="请输入已注册的账号"/></p>
						<p><span class="icon-pwd"></span><input type="password" name="password" placeholder="请输入密码"/></p>
						<p class="clear-float"><input type="text" name="code" placeholder="验证码" class="code"/><img src="${ctx}/common/getImage?name=code.jpg" width="110px" height="42px" class="code-img"/></p>
						<p><input type="checkbox" name="autoLogin" class="chk-login"/><span>7天免登录</span></p>
						<input type="submit" value="登录" class="sum-btn" />
					</form>
				</div>
			</div>	
		</div>
		<!--登录/注册的底部-->
		<div class="lr-footer footer">
			<p class="w1230">
				<a href="javascript:;;">关于爱购</a>
				<a href="javascript:;;">合作伙伴</a>
				<a href="javascript:;;">营销中心</a>
				<a href="javascript:;;">廉正举报</a>
				<a href="javascript:;;">联系客服</a>
				<a href="javascript:;;">开发平台</a>
				<a href="javascript:;;">诚征英才</a>
				<a href="javascript:;;">联系我们</a>
				<a href="javascript:;;">网站地图</a>
				<a href="javascript:;;">知识产权</a><span>|</span>
				<span>&copy;2018-2020 igo.com 版权所有</span>
			</p>
		</div>
		<input type="hidden" class="to_index" value="${ctx}/front/index">
		<script src="${ctx}/static/plugs/jquery.js"></script>
		<script>

			$(function (){
				var code = "";
				//验证码点击事件，点击更换验证码图片
				$('.code-img').on('click', function (){
					$.post('${ctx}/common/getVerificationCode', function(e){
						code = e;
						$("[name='code']").val(e);
						if (e) {
							$('.code-img').attr("src", "${ctx}/common/getImage?name=code.jpg&" + Math.random());
						}else {
							alert(e.message);
						}
					})
				})
				$('.code-img').trigger("click");

				//登录事件
				$('form').on('submit', function (){
					var data = $(this).serialize();
					$.post('/front/login/login', data,  function(e){
						if (e) {
							alert(e);
						}else{
							window.location.replace("/front/index")
						}
					})
					return false;
				})

			})
		</script>
	</body>
</html>
