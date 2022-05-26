<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>商品分类列表</title>

</head>
<body>
	<div class="hp-context-page">
		<div class="bt_top_bar">
			<button class="bt_add">添加</button>
			<button class="bt_delete" id = "bt_delete">删除</button>
		</div>
		<table>
			<tr>
				<th style="width: 40px;"><input type="checkbox"></th>
				<th width="10%">id</th>
				<th>商品分类名称</th>
				<th>商品分类描述</th>
				<th>分类图标</th>
				<th>操作</th>
			</tr>	
			<c:forEach items="${productTypePages.list }" var="productType">
				<tr>
					<td><input type="checkbox" name = "checkboxs"></td>
					<td>${productType.id }</td>
					<td name = "productname">${productType.productTypeName }</td>
					<td>${productType.productTypeDesc }</td>
					<td><i class="iconfont ${productType.productTypeIcon }" style="font-size: 25px !important"></i></td>
					<td style="width: 170px;">
						<button class="bt_update bt_op">修改</button>
						<button class="bt_delete bt_op">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="pagination3" class="page_fl"></div>
	</div>

	<script type="text/javascript">
		$(function (){
			//分页
			var currentPage = ${productTypePages.page};
			var pageSize = ${productTypePages.size};
			var totalPage = ${productTypePages.localPage};
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
					$('.hp-context').load("${ctx}/admin/productType/list?page=" + current);
				}
			});

			// 添加
			$(".bt_add").click(function(){
				layer.open({
					title: "添加分类",
					type: 2,
					area: ['700px', '450px'],
					fixed: false, //不固定
					maxmin: true,
					content: basePath + '/admin/productType/addPage'
				});
			})

			// 单删
			$('.bt_delete.bt_op').on('click', function(){
				var id = $(this).parent().parent().children("td:eq(1)").text();
				layer.open({
					content: '确定是删除吗？',
					btn: ['确认', '取消'],
					shadeClose: false,
					yes: function(){
						$.post('/admin/productType/deleteAData', {
							id: id
						}, function (e) {
							if (e=="0") {
								$('.hp-context').load("${ctx}/admin/productType/list");
							}
							layer.closeAll();
						})
					}
				});

			})

			// 更新
			$(".bt_update").on('click',function () {
				var id = $(this).parent().parent().children("td:eq(1)").text();
				layer.open({
					title: "修改分类",
					type: 2,
					area: ['700px', '450px'],
					fixed: false, //不固定
					maxmin: true,
					content: basePath + '/admin/productType/updatePage?id=' + id
				});

			})

			//多选
			$("[type='checkbox']:eq(0)").click(function () {
				var checked = $(this).prop("checked");
				if (checked) {
					$("[type='checkbox']").prop("checked", true);
				}else {
					$("[type='checkbox']").prop("checked", false);
				}
			})


			// 多选删除
			$("#bt_delete").click(function () {
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
							$.post('/admin/productType/deleteAll', {
								ids: ids.join()
							}, function (e) {
								if (e=="0") {
									$('.hp-context').load("${ctx}/admin/productType/list");
								}
								layer.closeAll();
							})
						}
					});
				}
			})
		})
	</script>
</body>
</html>