<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>添加用户</title>

</head>
<body>
	<div class="hp-context-page">
		<form class="hp-form">
			<input type="hidden" name="type" value="0">
			<div class="hp-form-item">
				<label class="hp-form-label">用户名称</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="userName" id="username">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">用户密码</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="password" id = "pwd">
				</div>	
			</div>
			<div class="hp-form-item">
				<button class="bt_save">保存</button>
				<button class="bt_close">关闭</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			$('.bt_close').on('click', function(){
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
				return false;
			})
		})

		// 添加用户

		$('.bt_save').on('click',function(){
			if($("#username").val().trim()==""){
				parent.layer.msg("用户名不能为空！", {icon: 2});
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			}
			if($("#pwd").val().trim()==""){
				parent.layer.msg("密码不能为空！", {icon: 2});
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			}
			if($("#username").val().trim() !="" && $("#pwd").val().trim() != ""){
				$.post("/admin/userInformation/addUser", $("form").serialize(), function (e) {
					console.log(e);
					if (e == "0") {
						parent.layer.msg('添加成功！', {icon: 1});
						$('.hp-context').load("${ctx}/admin/userInformation/list");
						$('.hp-context',parent.document).load("${ctx}/admin/userInformation/list");
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					} else if (e == "1") {
						parent.layer.msg('用户已存在！', {icon: 2});
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}else{
						parent.layer.msg('添加失败！' + e, {icon: 2});
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					}

				});
				return false;
			}
		})
	</script>
</body>
</html>