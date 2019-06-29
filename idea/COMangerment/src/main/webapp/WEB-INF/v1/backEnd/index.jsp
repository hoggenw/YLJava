<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header start -->
<%@ include file="tplate/header.php" %>
<!-- header end -->

<!-- topfixed start -->
<%@ include file="tplate/topfixed.php" %>
<!-- topfixed end -->

<div class="am-cf admin-main">
	<!-- sidebar start -->
	<%@ include file="tplate/sidebar.php" %>
	<!-- sidebar end -->


	<!-- content start -->
	<div class="admin-content" id="app" v-cloak>
		<div class="admin-content-body am-u-lg-12">
			
			<div class="am-cf am-padding">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">全局地图</strong></div>
			</div>
			<button type="button" class="am-btn" :class="btnClass==1?'am-btn-primary':'am-btn-default'" @click="checkBtn(1)">环比预警</button>
			<button type="button" class="am-btn" :class="btnClass==2?'am-btn-primary':'am-btn-default'" @click="checkBtn(2)">周期预警</button>
			<div id="map" class="am-margin-top-sm">
				<div style="width: 100%; height: 800px;" id="allmap"></div>
			</div>
			
		</div>
	</div>
	<!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<%@ include file="tplate/footerScript.php" %>

<script type="text/javascript">
	seajs.use(['/backEnd/assets/js/index']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->