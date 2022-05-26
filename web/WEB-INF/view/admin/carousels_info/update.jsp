<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>修改轮播图</title>
<script type="text/javascript">
	$(function(){
		$('.bt_close').on('click', function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
			return false;
		})
		
		$('.bt-upload').on('click', function(){
			var file_form = $('<form method="post" enctype="multipart/form-data"></form>');
			var file_bt = '<input style="display: none" type="file" name="file">';
			if ($(this).next().length<=0) {
				$(file_form).append(file_bt);
				$($(this)).after(file_form);
			}
			$(this).next().children("input").click();
			return false;
		})
		
		$('body').on('change', 'input[type=file]', function(){
			$.ajax({
	            url: 'fileUpload',
	            type: 'POST',
	            cache: false,
	            data: new FormData($(this).parent()[0]),
	            processData: false,
	            contentType: false,
	            success : function(e) {
					$("#img").val(e);
					$("#carouselfigureImg").attr("src", "/common/getImage?name="+e);
	            }
	        });
		})

		$(".bt_save").on('click',function () {
			$.post("/admin/carousels/update",$("form").serialize(),function (e) {
				if(e == '0'){
					parent.layer.msg('修改成功！', {icon: 1});
					$('.hp-context').load("${ctx}/admin/carousels/list");
					$('.hp-context',parent.document).load("${ctx}/admin/carousels/list");
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭
				}else{
					parent.layer.msg(e, {icon: 2});
					$('.hp-context',parent.document).load("${ctx}/admin/carousels/list");
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭
				}
			})
			return false;
		})
	})
</script>
</head>
<body>
	<div class="hp-context-page">
		<form class="hp-form" id="carouselfigure-from">
			<input type="hidden" name="carouselsImg" id="img">
			<input type="hidden" name="id" value="${carouselfigure.id }">
			<input type='hidden' name='url' value='${carouselfigure.url }'>
			<div class="hp-form-item">
				<label class="hp-form-label">序号</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="sequenceNum" value="${carouselfigure.sequenceNum }">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">轮播图</label>
				<div class="hp-input-block">
					<button style="margin-left: 10px;" class="bt-upload">上传</button>
				</div>
			</div>
			<div class="hp-form-item">
				<img alt="" src="${ctx}/common/getImage?name=${carouselfigure.url}" id="url" width="200px">
			</div>
			<div class="hp-form-item">
				<button class="bt_save">保存</button>
				<button class="bt_close">关闭</button>
			</div>
		</form>
	</div>
</body>
</html>