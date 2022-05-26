<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
		<title>收货地址管理</title>
		<link rel="stylesheet" href="${ctx }/static/css/reset.css" />
		<link rel="stylesheet" href="${ctx }/static/css/top.css" />
		<link rel="stylesheet" href="${ctx }/static/css/deliverAddress.css" />
		<link rel="stylesheet" href="${ctx }/static/css/footer.css" />
		<link rel="stylesheet" href="${ctx }/static/css/font-awesome-4.7.0/Font-Awesome-master/css/font-awesome.min.css" />
	</head>
	<body>
		<!--头部-->
		<%@ include file="../head.jsp" %>
		<div class="w1230">
			<img src="${ctx }/static/img/jingbaby.png" width="100" height="40" class="logo"/>
			<span class="cart">收货地址</span>
		</div>
		<!--编辑收货地址-->
		<div class="w1230">
			<h3 class="add-title">新增收货地址</h3>
			<div class="add-address">
				<form method="post">
					<div class="add-area">
						<label>地址信息：</label>
						<div id="area"></div>
					</div>
					<p>
						<label>详细地址：</label>
						<textarea id="address_detail" placeholder="请输入详细地址信息，如道路、门牌号、小区、楼栋号、单元等信息" class="add-detail"></textarea>
						<input type="hidden" name="receivingAddress" id="receivingAddress">
						<input type="hidden" name="id" id="receivingAddressId">
					</p>
					<p>
						<label>收货人姓名：</label>
						<input type="text" name="receivingPerson" class="add-name" placeholder="请输入收货人姓名"/>
					</p>
					<p>
						<label>手机号码：</label>
						<input type="text" name="mobilePhone" class="add-tel" placeholder="请输入收货人手机号"/>
					</p>
					<p class="chk-address">			
						<input type="checkbox" name="isDefault"/>
						<span>设置为默认收货地址</span>
					</p>
					<input type="submit" value="保存" class="save-btn" />
					<input type="button" value="清除" class="save-btn" id="clean"/>
					<input	type="hidden" name="user_id" value=${user.id}/>
				</form>
			</div>
		</div>
		<!--收货地址列表-->
		<div class="add-list w1230">
			<p class="message"><i class="fa fa-exclamation-circle"></i>只能保存5个地址</p>
			<table class="table-list">
				<tr>
					<th class="t-name">收货人</th>
					<th class="t-address">收货地址</th>
					<th class="t-tel">手机号</th>
					<th class="t-operate">操作</th>
					<th class="t-default"></th>
				</tr>
				<c:forEach items= "${addressList.list}" var="receivingAddress">
					<tr>
						<td>${receivingAddress.receivingPerson }</td>
						<td>${receivingAddress.receivingAddress }</td>
						<td>${receivingAddress.mobilePhone }</td>
						<td><a href="javascript:;;" data="${receivingAddress.id }" class="update">修改</a>|<a href="javascript:;;" data="${receivingAddress.id }" class="delete">删除</a></td>
						<c:if test="${receivingAddress.isDefault==1 }">
							<td class="default-on">默认地址</td>
						</c:if>
						<c:if test="${receivingAddress.isDefault==-1 }">
							<td><a href="javascript:;;" data="${receivingAddress.id }" class="setDefault">设为默认</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!--脚注-->
		<%@ include file="../footer.jsp" %>
		<script src="${ctx}/static/plugs/jquery.js"></script>
		<script src="${ctx }/static/js/area.js"></script>

		<script type="text/javascript">
			$(function () {
				$('form').submit(function () {
					var v1 = $('#area').children(":eq(0)").val()
					var v2 = $('#area').children(":eq(1)").val();
					var v3 = $('#area').children(":eq(2)").val();
					if (v1 == -1 || v2 == -1 || v3 == -1) {
						alert("请选择完整的收货地址");
						return false;
					}
					if ($('#address_detail').val() == "") {
						alert("请填写详细收货地址");
						return false;
					}
					if ($('input[name="receivingPerson"]').val() == "") {
						alert("请填写收货人姓名");
						return false;
					}
					if ($('input[name="mobilePhone"]').val() == "") {
						alert("请填写收货人手机号码");
						return false;
					}
					var address = v1 + v2 + v3 + $('#address_detail').val();
					$('#receivingAddress').val(address);

					var data = $(this).serialize();

					if($("#receivingAddressId").val().trim() == "") {
						$.post('/front/receiveing_address/add', data, function (e) {
							if (e) {
								alert(e);
								window.location.reload();
							} else {
								alert(e);
							}
						})
					}else {
						//更新
							$.post('/front/receiveing_address/update', data, function (e) {
								if (e) {
									alert(e);
									window.location.reload();
								} else {
									alert(e);
								}
							})
					}

					return false;
				})


			//修改按钮点击事件
			$('.update').on('click', function (){
				var id = $(this).attr("data");			//id
				var addressPerson = $(this).parent().prev().prev().prev().text();	//收货人
				var tel = $(this).parent().prev().text();			//电话
				var address = $(this).parent().prev().prev().text();	//地址

				$('.add-name').val(addressPerson);
				$('.add-tel').val(tel);
				$('#receivingAddressId').val(id);

				var _add = address, v1 = "", v2 = "", v3 = "";

				//选中省
				$('#area').children("select:eq(0)").find('option').each(function (index, data){
					if (_add.startsWith($(this).val())) {
						v1 = $(this).val();
						return false;
					}
				})
				$('#area').children("select:eq(0)").find('option[value="'+v1+'"]').prop("selected", true);
				$('#area').children("select:eq(0)").trigger("change");
				_add = _add.replace(v1, "")

				//选中市
				$('#area').children("select:eq(1)").find('option').each(function (index, data){
					if (_add.startsWith($(this).val())) {
						v2 = $(this).val();
						return false;
					}
				})
				$('#area').children("select:eq(1)").find('option[value="'+v2+'"]').prop("selected", true);
				$('#area').children("select:eq(1)").trigger("change");
				_add = _add.replace(v2, "")

				//选中区
				$('#area').children("select:eq(2)").find('option').each(function (index, data){
					if (_add.startsWith($(this).val())) {
						v3 = $(this).val();
						return false;
					}
				})
				$('#area').children("select:eq(2)").find('option[value="'+v3+'"]').prop("selected", true);
				$('#area').children("select:eq(2)").trigger("change");
				_add = _add.replace(v3, "")

				//设置详细收货地址
				$('#address_detail').val(_add);
			})

			//删除按钮点击事件
			$('.delete').on('click', function (){
				var id = $(this).attr("data");
				$.post('${ctx}/front/receiveing_address/delete', {id : id},  function(e){
					if (e) {
						alert(e);
						window.location.reload();
					}else {
						alert(e);
					}
				})
			})

			//设置默认收货地址
			$('.setDefault').on('click', function (){
				var id = $(this).attr("data");
				$.post('${ctx}/front/receiveing_address/setDefault', {id : id},  function(e){
					if (e) {
						alert(e);
						window.location.reload();
					}else {
						alert(e);
					}
				})
			})

			$('#clean').on('click', function (){
				$('form')[0].reset();
				$('#receivingAddress').val("");
				$('#receivingAddressId').val("");
			})
		})
		</script>
	</body>
</html>
