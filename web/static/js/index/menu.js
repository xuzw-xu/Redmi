$(function (){
	//菜单点击变色处理
	//菜单点击跳转处理
	$('.hp-nav-child a').on('click', function(){
		$('.hp-nav-child a').css("background-color", "");
		$(this).css("background-color", "#009688");
		
		var url = $(this).attr("href");
		$('.hp-context').load(url);
		return false;
	})
	
	//菜单点击隐藏/显示处理
	$('.hp-nav-item>div').on('click', function(){
		$(this).next().toggle();
	})
})