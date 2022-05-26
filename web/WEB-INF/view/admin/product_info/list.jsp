<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>商品列表</title>
<script type="text/javascript">
	$(function (){
		//分页
		var currentPage = ${productPages.page};
		var pageSize = ${productPages.size};
		var totalPage = ${productPages.getLocalPage()};
		$("#pagination3").pagination({
			currentPage: currentPage,
			totalPage: totalPage,
			isShow: true,
			count: pageSize,
			homePageText: "首页",
			endPageText: "尾页",
			prevPageText: "上一页",
			nextPageText: "下一页",
			callback: function(current) {
				var data = $('#product-search-from').serialize();
				$('.hp-context').load("${ctx}/admin/products/list?" + data + "&page=" + current);
			}
		});
		
		//添加方法
		$('.bt_add').on('click', function(){
			layer.open({
				title: "添加",
		  		type: 2,
			  	area: ['1000px', '650px'],
			  	fixed: false, //不固定
			  	maxmin: true,
			  	content: basePath + '/admin/products/addPage'
			});
		})
		
		//修改方法
		$('.bt_update').on('click', function(){
			var id = $(this).parent().parent().children("td:eq(1)").text();
			layer.open({
				title: "修改",
		  		type: 2,
			  	area: ['1000px', '650px'],
			  	fixed: false, //不固定
			  	maxmin: true,
			  	content: basePath + '/admin/products/updatePage?id='+id
			});
		})

		//删除方法
		$('.bt_delete.bt_op').on('click', function(){
			var id = $(this).parent().parent().children("td:eq(1)").text();
			layer.open({
				content: '确定是删除吗？',
				btn: ['确认', '取消'],
				shadeClose: false,
				yes: function(){
					$.post('/admin/products/delete', {
						id: id
					}, function (e) {
						if (e=="0") {
							$('.hp-context').load("${ctx}/admin/products/list");
						}
						layer.closeAll();
					})
				}
			});
		})

		//多选
		$('[type=checkbox]:eq(0)').on('click',function(){
			var checked = $(this).prop("checked");
			if (checked) {
				$("[type='checkbox']").prop("checked", true);
			}else {
				$("[type='checkbox']").prop("checked", false);
			}
		})

		//多删
		$("#bt_top_delete").click(function () {
			var cks = $("[type='checkbox']:gt(0):checked");
			if (cks.length==0) {
				layer.msg('至少选中一条数据', {icon: 2});
			}else {
				layer.open({
					content: '确定是删除吗？',
					btn: ['确认', '取消'],
					shadeClose: false,
					yes: function(){
						var ids = [];
						for (var i = 0; i < cks.length; i++) {
							var ck = cks[i];
							ids.push($(ck).parent().next().text());
						}
						$.post('/admin/products/deleteAll', {
							ids: ids.join()
						}, function (e) {
							if (e=="0") {
								$('.hp-context').load("${ctx}/admin/products/list");
							}
							layer.closeAll();
						})
					}
				});
			}
		})


		//查询
		$('.bt_search').on('click', function(){
			var data = $('#product-search-from').serialize();
			$('.hp-context').load("${ctx}/admin/products/list?page=1&" + data);
			return false;
		})
		
		//重置
		$('.bt_reset').on('click', function(){
			$('input[name="productName"]').val("");
			$('select[name="productType"]').val("");
			$('.bt_search').click();
			return false;
		})
	})
</script>
</head>
<body>
	<div class="hp-context-page">
		<div class="bt_top_bar">
			<form action="" id="product-search-from">
				<label class="hp-label">商品名称</label><input class="hp-input-line" name="productName" value="${productName }">
				<label class="hp-label">所属分类</label>
				<select class="hp-input-line" name="productType">
					<option value="">--全部--</option>
					<c:forEach items="${productTypes}" var="productType">
						<c:if test="${type!=null && type==productType.id}">
							<option selected="selected" value="${productType.id }">${productType.productTypeName }</option>
						</c:if>
						<c:if test="${type==null || type!=productType.id}">
							<option value="${productType.id }">${productType.productTypeName }</option>
						</c:if>
					</c:forEach>
				</select>
				<button class="bt_search">查询</button>
				<button type="reset" class="bt_reset">重置</button>
			</form>
		</div>
		<div class="bt_top_bar">
			<button class="bt_add">添加</button>
			<button class="bt_delete" id ="bt_top_delete">删除</button>
		</div>
		<table>
			<tr>
				<th style="width: 40px;"><input type="checkbox"></th>
				<th width="10%">id</th>
				<th>商品名称</th>
				<th>商品图片</th>
				<th>商品价格</th>
				<th>所属分类</th>
				<th>商品品牌</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>	
			<c:forEach items="${productPages.list }" var="product">
				<tr>
					<td><input type="checkbox"></td>
					<td>${product.id }</td>
					<td>${product.productName }</td>
					<td><img alt="" src="${ctx}/common/getImage?name=${product.productImage }" width="50px;"></td>
					<td>${product.price }</td>
					<td>${product.productType}</td>
					<td>${product.productBrand}</td>
					<td>${product.createTime }</td>
					<td style="width: 170px;">
						<button class="bt_update bt_op">修改</button>
						<button class="bt_delete bt_op">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="pagination3" class="page_fl"></div>
	</div>
</body>
</html>