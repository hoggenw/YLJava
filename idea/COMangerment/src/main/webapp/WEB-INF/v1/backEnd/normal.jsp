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
			<div class="am-cf am-padding am-text-center">
				<strong class="am-text-primary am-text-lg am-text-success">环 比 正 常 设 备</strong>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-text-nowrap am-table-hover">
							<thead>
								<tr>
									<th>油水分离器</th>
									<th>餐厅名</th>
									<th>本次回收量(KG)</th>
									<th>环比回收均量(KG)</th>
									<th>差值预警(KG)</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="item in gridData">
									<td>{{item.eqptId}}</td>
									<td>{{item.restName}}</td>
									<td>{{item.oilFlow}}</td>
									<td>{{item.aveFlow}}</td>
									<td class="back-green">{{item.num}}</td>
								</tr>
							</tbody>
						</table>
						<div class="am-text-center not-data" v-if="status == 1">
							暂无数据
						</div>
					</div>
					<div class="am-cf"> 
						<pagination @getlist="getList" :is-page="isPage" :cur-page="curPage" :show-pages="showPages" :total-pages="totalPages" ref="pagination"></pagination>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<%@ include file="tplate/footerScript.php" %>

<script type="text/javascript">
	seajs.use(['/backEnd/assets/js/normal']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->