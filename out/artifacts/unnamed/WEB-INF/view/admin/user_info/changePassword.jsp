<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>添加用户</title>
<script type="text/javascript">
	$(function(){
		$('.bt_close').on('click', function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
			return false;
		})

		$(".bt_save").on('click',function (e) {

			if($("#oldPwd").val().trim() == "") {
				parent.layer.msg('原密码不能为空！', {icon: 2});
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			}
			if($("#newPwd").val().trim() == "") {
				parent.layer.msg('新密码不能为空！', {icon: 2});
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			}
			if($("#conPwd").val().trim() == "") {
				parent.layer.msg('原密码不能为空！', {icon: 2});
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			}
			if($("#newPwd").val().trim() != "" && $("#conPwd").val().trim() != "" && $("#conPwd").val().trim() != ""){
				$.post("/admin/userInformation/changeUserPwd",$("form").serialize(),function(e){
					console.log(e);
					if(e=='0'){
						parent.layer.msg('新密码不能与原密码一致！', {icon: 2});
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}else if(e=="1"){
						parent.layer.msg('新密码与确认密码输入不一致！', {icon: 2});
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}else if(e=="2"){
						parent.layer.msg('修改成功！', {icon: 1});
						$('.hp-context',parent.document).load("${ctx}/admin/userInformation/list");
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}else if(e=="3"){
						parent.layer.msg('原密码不正确！', {icon: 2});
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}else{
						parent.layer.msg("修改失败！"+e, {icon: 2});
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}
				})
				return false;
			}

		})
	})
</script>
</head>
<body>
	<div class="hp-context-page">
		<form  class="hp-form">
			<input type="hidden" name="type" value="${user.username}">
			<div class="hp-form-item">
				<label class="hp-form-label">旧密码</label>
				<div class="hp-input-block">
					<input class="hp-input" placeholder="请输入当前用户密码" type="text" name="oldPassword" id="oldPwd">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">新密码</label>
				<div class="hp-input-block">
					<input class="hp-input" placeholder="请输入新的密码" type="password" name="newPassword" id="newPwd">
				</div>	
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">确认密码</label>
				<div class="hp-input-block">
					<input class="hp-input" placeholder="请重新输入新的密码" type="password" name="confirmPassword" id="conPwd">
				</div>	
			</div>
			<div class="hp-form-item">
				<button class="bt_save">保存</button>
				<button class="bt_close">关闭</button>
			</div>
		</form>
	</div>
</body>
</html>