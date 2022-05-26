<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>登录</title>
<style type="text/css">
	form{
		padding: 10px;
		color : white;
		background-color: #1b1b1b3d;
        position: absolute;
	   	top: calc(50% - 200px);
	   	left: calc(50% - 150px);
	   	width: 300px;
	   	height: 280px;
	}
	form>div{
		margin: 20px;
	}
	form>div:first-child{
	    text-align: center;
	}
	
	body{
		background-size: 100%;
		background-image: url(${ctx}/static/img/bg2.jpg);
	}
	form input{
	    line-height: 1.3;
	    border-width: 0px;
	    border-style: solid;
	    background-color: #fff;
	    display: block;
	    width: 100%;
	    height: 40px;
	}
	
	.hp_bt{
	    display: inline-block;
	    height: 45px;
	    line-height: 38px;
	    color: #fff;
	    white-space: nowrap;
	    text-align: center;
	    font-size: 14px;
	    border: none;
	    border-radius: 2px;
	    cursor: pointer;
	    padding: 0 18px;
	    background-color: #009688;
	}
</style>
</head>
<body>
	<form action="${ctx}/admin/user/login" method="post">
		<div>
			<h2>登录</h2>
		</div>
		<div>
			<input placeholder="用户名" name="username">
		</div>
		<div>
			<input type="password" placeholder="密码" name="password">		
		</div>
		<div style="margin-top: 0px; margin-bottom: 0px; color: red;">${msg}</div>
		<div>
			<input class="hp_bt" type="submit" value="登录">	
		</div>
	</form>
</body>
</html>