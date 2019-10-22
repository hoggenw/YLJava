<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header start -->
<%@ include file="tplate/header.php" %>
<!-- header end -->
<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
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
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">网站数据统计</strong></div>
      </div>
			
			<div class="am-g am-form">
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
					<div class="am-form-group">
						<input type="text" id="startTime" class="am-input-sm" placeholder="请选择开始时间">
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
					<div class="am-form-group">
						<input type="text" id="endTime" class="am-input-sm" placeholder="请选择结束时间">
					</div>
				</div>
				<!-- <div class="am-u-sm-12 am-u-md-6 am-u-lg-2">
					<div class="am-form-group">
						<select class="am-input-sm" v-model="countType">
							<option v-for="item in countOptions" :value="item.value">
									{{item.name}}
							</option>
						</select>
					</div>
				</div> -->
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-2">
					<div class="am-form-group">
						<select class="am-input-sm" v-model="timeType">
							<option v-for="item in timeOptions" :value="item.value">
									{{item.name}}
							</option>
						</select>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-fr">
					<button class="am-btn am-btn-primary am-btn-sm am-fr am-margin-right-sm" @click="getData()" type="button">搜索</button>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div id="container" style="width: 100%;height:400px;"></div>
				</div>
			</div>
			
		</div>
	</div>
	<!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<%@ include file="tplate/footerScript.php" %>

<script type="text/javascript">
	seajs.use(['/backEnd/assets/js/chart']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->