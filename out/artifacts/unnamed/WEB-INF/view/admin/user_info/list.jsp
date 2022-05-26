<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<title>用户列表</title>

</head>
<body>
	<div class="hp-context-page">
		<div class="bt_top_bar">
			<button class="bt_add">添加</button>
		</div>
		<table>
			<tr>
				<th style="width: 40px;"><input type="checkbox"></th>
				<th width="10%">id</th>
				<th>用户名称</th>
				<th>用户密码</th>
				<th>用户类型</th>
				<th>操作</th>
			</tr>	
			<c:forEach items="${userPages.list }" var="user">
				<tr>
					<td><input type="checkbox"></td>
					<td>${user.id }</td>
					<td>${user.username }</td>
					<td>${user.password }</td>
					<td>${user.type }</td>
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
			var currentPage = ${userPages.page};
			var pageSize = ${userPages.size};
			var totalPage = ${userPages.getLocalPage()};
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
					$('.hp-context').load("${ctx}/admin/userInformation/list?page=" + current);
				}
			});

			//添加方法
			$('.bt_add').on('click', function(){
				layer.open({
					title: "添加用户",
					type: 2,
					area: ['700px', '450px'],
					fixed: false, //不固定
					maxmin: true,
					content: basePath + '/admin/userInformation/addPage'
				});
			})

			//修改方法
			$('.bt_update').on('click', function(){
				var name = $(this).parent().parent().children("td:eq(2)").text();
				layer.open({
					title: "修改用户",
					type: 2,
					area: ['700px', '450px'],
					fixed: false, //不固定
					maxmin: true,
					content: basePath + '/admin/userInformation/updatePage?name='+name
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
						if(id == "0" || id == "1"){
							parent.layer.msg("超级账号不能删除！", {icon: 2});
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.close(index); //再执行关闭
						}else{
							$.post('/admin/userInformation/delete', {
								id: id
							}, function (e) {
								if (e=="0") {
									$('.hp-context').load("${ctx}/admin/userInformation/list");
								}
								layer.closeAll();
							})
						}
					}
				});

			})
		})
	</script>
</body>
</html>