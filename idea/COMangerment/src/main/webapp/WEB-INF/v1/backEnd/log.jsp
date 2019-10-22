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
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">操作日志</strong></div>
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
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-2">
					<div class="am-form-group">
						<input type="text" v-model="account" class="am-input-sm" placeholder="账号">
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-2">
					<div class="am-form-group">
						<select class="am-input-sm" v-model="status">
							<option v-for="item in options" :value="item.value">
									{{item.name}}
							</option>
						</select>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-fr">
					<!-- <button class="am-btn am-btn-primary am-btn-sm am-fr" @click="batchDownload()" type="button">批量下载</button> -->
					<a href="javascript:void(0)" class="am-btn am-btn-primary am-btn-sm am-fr am-margin-right-sm" @click="exportLog()">导出日志</a>
					<button class="am-btn am-btn-primary am-btn-sm am-fr am-margin-right-sm" @click="getList(1)" type="button">搜索</button>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-text-nowrap am-table-hover">
							<thead>
								<tr>
									<th>操作时间</th>
									<th>账号</th>
									<th>操作</th>
									<th width="20%">结果</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="item in gridData">
									<td>{{item.operatingTime | timeForMart}}</td>
									<td>{{item.userName}}</td>
									<td>{{item.type | capitalize}}</td>
									<td>
										<span v-if="item.type==1">{{item.showText}}</span>
										<span v-if="item.type != 1 && item.success == false">{{item.showText}}</span>
										<div class="am-cf" v-if="item.imageName">
											<label :title="item.imageName" style="max-width:15rem;" class="am-fl am-text-truncate">
												<!-- <input @click="checkFunction($event)" type="checkbox" :data-name="item.imageName"> -->
												{{item.imageName}} 
											</label>
											<!-- <a class="am-fr" href="javascript:void(0)"><i style="display:block" @click="checkAlone($event)" :data-name="item.imageName" class="am-icon-download"></i></a> -->
										</div>
									</td>
								</tr>
							</tbody>
						</table>
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
	seajs.use(['/backEnd/assets/js/log']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->