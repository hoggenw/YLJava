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
				<li class=""><a href="/manager/chart">订单管理</a></li>
				<li class=""><a href="/manager/config">推荐管理</a></li>
				<li class=""><a href="/manager/log">积分管理</a></li>
			</ul>
		</div>
	</div>
	<!-- sidebar end -->


	<!-- content start -->
	<div class="admin-content" id="app" v-cloak>
		<div class="admin-content-body am-u-lg-12">
			
			<div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg ">添加用户</strong></div>
				<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-fr">
					<button class="am-btn am-btn-primary am-btn-sm am-fr am-margin-right-sm"  @click="back" value="">返回</button>
				</div>

      </div>
			
			<div class="am-g am-form">

				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<label for="realName">姓名(必填):</label>
					<input type="text" placeholder="请输入客户姓名" id="realName" v-model="search_info.realName" value="" >
				</div>

				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<label for="pMobile">推荐人联系方式:</label>
					<input type="text" placeholder="请输入推荐人手机号码" id="pMobile" v-model="search_info.pMobile" value="">
				</div>


				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<label for="mobile">客户联系方式(必填):</label>
					<input type="text" placeholder="请输入客户手机号码" id="mobile" v-model="search_info.mobile" value="">
				</div>

				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<label for="sex">选择性别(必填):</label>
					<select  v-model="search_info.sex" id="sex" >
						<option v-for="item in options" :value="item.value">
							{{item.name}}
						</option>
					</select>
				</div>

				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<label for="birthday">客户生日:</label>
					<input type="text" placeholder="请输入客户生日" id="birthday" v-model="search_info.birthday" value="">
				</div>

				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<label for="address">客户地址:</label>
					<input type="text" placeholder="请输入客户地址" id="address" v-model="search_info.address" value="">
				</div>


				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<label for="integral">初始积分:</label>
					<input type="text" placeholder="请输入初始积分" id="integral" v-model="search_info.integral" value="">
				</div>


				<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
					<label for="remark">备注:</label>
					<input type="text" placeholder="备注" id="remark" v-model="search_info.remark" value="">
				</div>

				<br>

				<div class="am-cf am-u-sm-12 am-u-md-12 am-u-lg-12 " style="margin-top: 50px;margin-left: auto;margin-right: auto ">
					<input type="submit" name="" @click="submitClick" value="提交" class="am-btn am-btn-secondary am-btn-block ">
				</div>

			</div>

		</div>
	</div>
	<!-- content end -->

</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<%@ include file="tplate/footerScript.php" %>

<script type="text/javascript">
	seajs.use(['/backEnd/assets/js/addUser']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->