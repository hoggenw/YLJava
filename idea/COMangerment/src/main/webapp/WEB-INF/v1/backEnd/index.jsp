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
				<li class="" style="background-color: #f3f3f3"><a href="/">用户管理</a></li>
				<li class=""><a href="/bill">订单管理</a></li>
				<li class=""><a href="/billListReally">订单列表</a></li>
				<li class=""><a href="/recommend">推荐管理</a></li>
				<li class=""><a href="/backCash">返现管理</a></li>
				<li class="" ><a href="/setting">系统参数配置</a></li>
			</ul>
		</div>
	</div>
	<!-- sidebar end -->


	<!-- content start -->
	<div class="admin-content" id="app" v-cloak>
		<div class="admin-content-body am-u-lg-12">
			
			<div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg ">客户管理</strong></div>
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
				<%--<div class="am-u-sm-12 am-u-md-6 am-u-lg-2">--%>
					<%--<div class="am-form-group">--%>
						<%--<select class="am-input-sm" v-model="search_info.status">--%>
							<%--<option v-for="item in options" :value="item.value">--%>
									<%--{{item.name}}--%>
							<%--</option>--%>
						<%--</select>--%>
					<%--</div>--%>
				<%--</div>--%>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-fr">
					<button class="am-btn am-btn-primary am-btn-sm am-fr" @click="addAcount()" type="button">新增客户</button>
					<button class="am-btn am-btn-primary am-btn-sm am-fr am-margin-right-sm" @click="getList(1)" type="button">搜索</button>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<div class="am-scrollable-horizontal">
						<table class="am-table am-table-striped am-text-nowrap am-table-hover">
							<thead>
								<tr>
									<th width="10%">姓名</th>
									<th width="10%">性别</th>
									<th width="15%">积分</th>
									<th width="15%">电话</th>
									<th width="10%">登记人</th>
									<th width="15%">创建时间</th>
									<th width="25%">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="item in gridData">
									<td>{{item.realName}}</td>
									<td>{{item.sex | sexFilter}}</td>
									<td>{{item.integral}}</td>
									<td>{{item.mobile}}</td>
									<td>{{item.salesperson}}</td>
									<td>{{item.createTime | timeForMart}}</td>
									<td class="am-cf">
										<button class="am-btn am-btn-primary am-btn-xs am-fl am-margin-right-sm" @click="showDetail(item)">查看详情</button>
										<button class="am-btn am-btn-success am-btn-xs am-fl am-margin-right-sm" @click="update(item)">修改信息</button>
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
	seajs.use(['/backEnd/assets/js/index']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->