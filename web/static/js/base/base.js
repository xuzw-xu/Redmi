$(function (){
	//点击表格头部复选框，能全选和取消全选
	$('table input[type="checkbox"]:eq(0)').on('change', function (){
		if ($(this).prop("checked")==true) {
			$('input[type="checkbox"]').prop("checked",true);
		}else if ($(this).prop("checked")==false) {
			$('input[type="checkbox"]').prop("checked",false);
		}
	})
	
	//点击表格某行，能取消和选中某行
	$('table tr').on('click', function(){
		var d = $(this).find('input[type="checkbox"]:eq(0)');
		if (d.prop("checked")==true) {
			$(this).find('input[type="checkbox"]:eq(0)').prop("checked", false);
		}else {
			$(this).find('input[type="checkbox"]:eq(0)').prop("checked", true);
		}
	})
})