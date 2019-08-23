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
	<div class="admin-content" id="app"  v-cloak>
		<div class="admin-content-body am-u-lg-12">
			
			<div class="am-cf am-padding">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">结果配置</strong></div>
			</div>
			
			<div class="am-g am-margin-top" v-for="itemBig in gridData">
				<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
					<div class="am-g am-u-sm-6 am-u-md-6 am-u-lg-6">
						<h4>{{itemBig.catalogName}}</h4>
					</div>
					<div class="am-g am-u-sm-4 am-u-md-4 am-u-lg-4">
						<button class="am-btn am-btn-primary am-btn-sm am-fr" @click="configNumerical(itemBig.catalogId)" type="button">配置</button>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
					<div class="am-progress" style="margin-bottom:0.5em;">
						<div v-for="item in itemBig.standardList" class="am-progress-bar" :style="{'width': item.width + '%','background-color':'#' + item.color }" >{{item.standardTitle}}</div>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
					<div class="am-cf">
						<div class="am-fl" v-for="(item,index) in itemBig.standardList" :style="{'width': item.width + '%'}">
							<div class="am-fl am-text-center" style="width:3rem;margin-left:-1.5rem" >{{item.min}}</div>
							<div v-if="index+1 == item.thisLength" class="am-fr am-text-center" style="width:3rem;margin-right:-1.5rem">{{item.max}}</div>
						</div>
					</div>
				</div>
				<!--配置数值-->
 				
				<!--配置数值结束-->
			</div>
		</div>
		
		
	</div>
	<!-- content end -->

	

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<%@ include file="tplate/footerScript.php" %>

<script type="text/javascript">
	seajs.use(['/backEnd/assets/js/config']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->