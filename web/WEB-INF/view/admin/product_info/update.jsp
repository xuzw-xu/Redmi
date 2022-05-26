<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/static/common/common.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>修改商品</title>
<script type="text/javascript">
	$(function(){
		var arr = [];
		$('.bt_close').on('click', function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
			return false;
		})
		
		$('.bt-clear').on('click', function(){
			$('input[name="productDesc"]').val("");
			$('#productDesc').empty();
			return false;
		})
		
		$('.bt-upload').on('click', function(){
			console.log("123");
			var file_form = $('<form method="post" enctype="multipart/form-data"></form>');
			var file_bt = '<input style="display: none" type="file" name="file">';
			if ($(this).next().length<=0) {
				$(file_form).append(file_bt);
				$($(this)).after(file_form);
			}
			$(this).next().children("input").click();
			return false;
		})
		
		$('body').on('change', 'input[type=file]', function(e){
			var bt_name = $($(this).parent().prev()).attr("data-name");
			$('#temp').val(bt_name);
			$.ajax({
	            url: '${ctx}/fileUpload',
	            type: 'POST',
	            data: new FormData($(this).parent()[0]),
	            processData: false,
	            contentType: false,
	            success : function(data) {
					if (bt_name == "productImage") {
						$("#productImage").attr("src","/common/getImage?name="+data);
						$("#img1").val(data);
					}else{
						var img = $("<img>").attr("src","/common/getImage?name="+data).attr("width","50px");
						$("#productDesc").append(img);
						arr.push(data);
						$("#img2").val(arr.join());
					}
	            }
	        });
		})
		
		$('select[name="productType"]').on('change', function(){
			var productTypeId = $(this).val();
			$.post(basePath + '/admin/products/getBrandByProductType',{"id" : productTypeId}, function(e){
				var brands = JSON.parse(e);
				$('select[name="productBrand"]').empty();
				for (var i in brands) {
					var option = $('<option>').val(brands[i].id).text(brands[i].brandName);
				}
				$('select[name="productBrand"]').append(option);
			})
		})

		$('select[name="productType"]').trigger("change");

		$(".bt_save").on('click',function () {
			$.post("/admin/products/update",$("form").serialize(),function (e) {
				if(e == '0'){
					parent.layer.msg('修改成功！', {icon: 1});
					$('.hp-context').load("${ctx}/admin/products/list");
					$('.hp-context',parent.document).load("${ctx}/admin/products/list");
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭
				}else{
					parent.layer.msg(e, {icon: 2});
					$('.hp-context',parent.document).load("${ctx}/admin/products/list");
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
		<input type="hidden" id="temp">
		<form class="hp-form" id="product-from">
			<input type="hidden" name="id" value="${product.id }">
			<input type="hidden" name="productImage" value="${product.productImage }" id="img1">
			<input type="hidden" name="productDesc" value="${product.productDesc }" id="img2">
			<div class="hp-form-item">
				<label class="hp-form-label">商品名称</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="productName" value="${product.productName }">
				</div>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">商品图片</label>
				<div class="hp-input-block">
<%--					<input type="hidden" name="productImage" id="img1">--%>
					<button style="margin-left: 10px;" class="bt-upload" data-name="productImage">上传</button>
				</div>	
			</div>
			<div class="hp-form-item">
				<img alt="" src="${ctx}/common/getImage?name=${product.productImage }" id="productImage" width="200px" name="productImage">
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">商品价格</label>
				<div class="hp-input-block">
					<input class="hp-input" type="text" name="price" value="${product.price }">
				</div>	
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">商品详情图</label>
				<div class="hp-input-block">
					<button style="margin-left: 10px;" class="bt-clear">清除</button>
<%--					<input type="hidden" name="productDesc" id="img2">--%>
					<button style="margin-left: 10px;" class="bt-upload" data-name="productDesc">上传</button>
				</div>
			</div>
			<div class="hp-form-item" id="productDesc">
				<c:forEach items="${product.productDesc}" var="img">
					<img alt="" style="padding-right: 10px;" src="${ctx}/common/getImage?name=${img}" width="50px">
				</c:forEach>
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">所属分类</label>
				<div class="hp-input-block">
					<select class="hp-input" name="productType">
						<c:forEach items="${productTypes}" var="productType">
							<c:if test="${productType.id == product.productType}">
								<option value="${productType.id }" selected="selected">${productType.productTypeName }</option>
							</c:if>
							<c:if test="${productType.id != product.productType}">
								<option value="${productType.id }">${productType.productTypeName }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>	
			</div>
			<div class="hp-form-item">
				<label class="hp-form-label">商品品牌</label>
				<div class="hp-input-block">
					<select class="hp-input" name="productBrand">

					</select>
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