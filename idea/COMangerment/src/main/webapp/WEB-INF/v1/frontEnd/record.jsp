<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="component/header.php" %>
	<%@ include file="component/topfixed.php" %>
	<div class="am-box am-margin-top-lg" id="myApp" v-cloak>
		<div class="am-text-center">
			<div class="fixedtop am-g">
				<h1>购入油脂量记录</h1>
				<div class="am-u-sm-5">
					提交时间
				</div>
				<div class="am-u-sm-5">
					购入油脂量(KG)
				</div>
			</div>
			<div class=" margin-top-80">
				<div class="am-g record-list" v-for="key in dataList">
					<div class="am-u-sm-5">
						{{key.createTime | timeForMart}}
					</div>
					<div class="am-u-sm-5">
						{{key.oilWeight}}
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="/frontEnd/assets/js/record.js"></script>

<%@ include file="component/footer.php" %>
