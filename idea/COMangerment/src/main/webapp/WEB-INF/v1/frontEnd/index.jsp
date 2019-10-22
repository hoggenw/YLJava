<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="component/header.php" %>
<%@ include file="component/topfixed.php" %>
<div class="am-cf admin-main" id="myApp" v-cloak>
	<!-- sidebar start -->
	<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
		<div class="am-offcanvas-bar admin-offcanvas-bar">
			<ul class="am-list admin-sidebar-list">
				<li class=""><a href="/manager">账号管理</a></li>
				<li class=""><a href="/manager/log">操作日志</a></li>
				<li class=""><a href="/manager/config">结果配置</a></li>
				<li class=""><a href="/manager/chart">网站数据统计</a></li>
			</ul>
		</div>
	</div>
	<!-- sidebar end -->
	<!-- content start -->
	<div class="admin-content" id="app" v-cloak>
		<div class="admin-content-body am-u-lg-12">

			<div class="am-cf am-padding">
				<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">账号管理</strong></div>
			</div>

			<div class="am-g am-form">
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
					<button class="am-btn am-btn-primary am-btn-sm am-fr" @click="addAcount()" type="button">新增账号</button>
					<button class="am-btn am-btn-primary am-btn-sm am-fr am-margin-right-sm" @click="getList(1)" type="button">搜索</button>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-text-nowrap am-table-hover">
							<thead>
							<tr>
								<th width="30%">账号</th>
								<th width="30%">状态</th>
								<th width="40%">操作</th>
							</tr>
							</thead>
							<tbody>
							<tr v-for="item in gridData">
								<td>{{item.userName}}</td>
								<td>{{item.status | capitalize}}</td>
								<td class="am-cf">
									<button class="am-btn am-btn-primary am-btn-xs am-fl am-margin-right-sm" @click="editorPwd(item)">修改密码</button>
									<button class="am-btn am-btn-warning am-btn-xs am-fl am-margin-right-sm" v-show="item.status==0" @click="statusAccount(item.userId,1)">冻结账号</button>
									<button class="am-btn am-btn-success am-btn-xs am-fl am-margin-right-sm" v-show="item.status==1" @click="statusAccount(item.userId,0)">解冻账号</button>
									<button class="am-btn am-btn-danger am-btn-xs am-fl" @click="removeAccount(item.userId)">删除账号</button>
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


<script type="text/javascript" src="/frontEnd/assets/js/index.js"></script>

<%@ include file="component/footerScript.php" %>
<%@ include file="component/footer.php" %>
