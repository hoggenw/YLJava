<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header start -->
<%@ include file="tplate/header.php" %>
<!-- header end -->

<!-- topfixed start -->
<%@ include file="tplate/topfixed.php" %>
<!-- topfixed end -->

<div class="am-cf admin-main">
	<!-- sidebar start -->
	<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
		<div class="am-offcanvas-bar admin-offcanvas-bar">
			<ul class="am-list admin-sidebar-list">
				<li class=""><a href="/">用户管理</a></li>
				<li class=""  style="background-color: #f3f3f3"><a href="/bill">订单管理</a></li>
				<li class=""><a href="/recommend">推荐管理</a></li>
				<li class=""><a href="/backCash">返现管理</a></li>
			</ul>
		</div>
	</div>
	<!-- sidebar end -->


	<!-- content start -->
	<div class="admin-content" id="app" v-cloak>
		<div class="admin-content-body am-u-lg-12">
			
			<div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg ">订单管理</strong></div>
      </div>
			
			<div class="am-g am-form">
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-2">
					<div class="am-form-group">
						<input type="text" v-model="search_info.realName" class="am-input-sm" placeholder="姓名查询">
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-2">
					<div class="am-form-group">
						<input type="text" v-model="search_info.phone" class="am-input-sm" placeholder="电话号码查询">
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-fr">
					<button class="am-btn am-btn-primary am-btn-sm am-fr am-margin-right-sm" @click="getList(1)" type="button">搜索</button>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-text-nowrap am-table-hover">
							<thead>
								<tr>
									<th width="20%">姓名</th>
									<th width="15%">状态</th>
									<th width="15%">性别</th>
									<th width="20%">电话</th>
									<th width="30%">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="item in gridData">
									<td>{{item.realName}}</td>
									<td>{{item.status | capitalize}}</td>
									<td>{{item.sex | sexFilter}}</td>
									<td>{{item.mobile}}</td>
									<td class="am-cf">
										<button class="am-btn am-btn-primary am-btn-xs am-fl am-margin-right-sm" @click="addBill(item)">添加交易信息</button>
										<button class="am-btn am-btn-success am-btn-xs am-fl am-margin-right-sm" @click="tradeBill(item)">查看交易记录</button>
										<%--<button class="am-btn am-btn-warning am-btn-xs am-fl am-margin-right-sm" v-show="item.status==0" @click="statusAccount(item.userId,1)">冻结账号</button>--%>
										<%--<button class="am-btn am-btn-success am-btn-xs am-fl am-margin-right-sm" v-show="item.status==1" @click="statusAccount(item.userId,0)">解冻账号</button>--%>
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
	seajs.use(['/backEnd/assets/js/bill']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->