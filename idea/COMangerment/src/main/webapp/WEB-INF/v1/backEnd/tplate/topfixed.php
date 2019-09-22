<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="am-topbar  admin-header">
  <div class="am-topbar-brand">
    <strong>客户管理系统</strong>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a readonly="readonly" href="#"><span class="am-icon-user"></span> <span id="showUserName"></span></a></li>
      <li><a href="javascript:void(0)" id="exit"><span class="am-icon-power-off"></span> 退出</a></li>
    </ul>
  </div>
</header>
<script>
	$('#exit').click(function(){
		layer.confirm('是否确认退出', {
			title: '退出提示',
			icon: 3,
		}, function(index) {
			localStorage.setItem('userInfo', '');

			layer.close(index);
			setTimeout(function() {
				window.location.href = '/login';
			}, 800)
		})
	})
</script>