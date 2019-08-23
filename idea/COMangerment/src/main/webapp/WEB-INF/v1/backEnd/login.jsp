<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- header start -->
<%@ include file="tplate/header.php" %>
<!-- header end -->

<div id="login_box" v-cloak>
	<div class="am-g am-margin-top-lg">
		<div class="am-u-lg-4 am-u-md-7 am-u-sm-centered">
			<div class="am-g am-text-center">
				<h1>运 * 登录</h1>
			</div>
			<div class="am-form">
				<label for="account">手机号:</label>
				<input type="text" placeholder="请输入手机号码" id="account" v-model="user" value="">
				<br>
				<label for="password">密码:</label>
				<input type="password" placeholder="请输入登录密码" id="password" v-model="pwd" value="23dsdssd">
				<br>
				<br>
				<div class="am-cf">
					<input type="submit" name="" @click="loginClick" value="登 录" class="am-btn am-btn-secondary am-btn-block">
				</div>
			</div>
		</div>
	</div>

</div>

<%@ include file="tplate/footerScript.php" %>

<script type="text/javascript">
	seajs.use(["/backEnd/assets/js/login"]);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->