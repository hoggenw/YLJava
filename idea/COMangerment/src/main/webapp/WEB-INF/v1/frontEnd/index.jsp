<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="component/header.php" %>
	<%@ include file="component/topfixed.php" %>
	<div class="am-box am-margin-top-lg" id="myApp" v-cloak>
		<div class="am-container am-text-center">
			<h1>油水分离器录入</h1>
			<select id="doc-select-1" class="select-1" v-model="industry">
				<option :value="key" v-for="(key,index) in couponList">{{key&&key.corpName}}</option>
			</select>
			<form class="am-form am-padding-xs">
				<div class="index-border  am-g">
					<div class="am-form-group am-u-sm-12 am-margin-top-lg">
						<div class="am-u-sm-6 w5">油水分离器</div>
						<div class="am-u-sm-6 border-bottom">
							{{industry.eqptTypeName}} + {{industry.eqptId}}
						</div>
					</div>
					<div class="am-form-group am-u-sm-12 am-margin-top-lg">
						<div class="am-u-sm-6 w3">餐厅名</div>
						<div class="am-u-sm-6 border-bottom">
							{{industry.corpName}}
						</div>
					</div>
					<div class="am-form-group am-u-sm-12 am-margin-top-lg">
						<div class="am-u-sm-6 w4">企业地址</div>
						<div class="am-u-sm-6 border-bottom">
							{{industry.address}}
						</div>
					</div>
					<div class="am-form-group am-u-sm-12 am-margin-top-lg">
						<div class="am-u-sm-6 w5">购入油脂量</div>
						<div class="am-u-sm-6">
							<input type="text" class="am-text-center am-input-sm" v-model="oilWeight" maxlength="11" placeholder="单位KG">
						</div>
					</div>
				</div>
			</form>
			<button class="am-margin-top-lg am-btn am-btn-secondary am-radius am-btn-block" @click="submit()">提交</button>
		</div>
	</div>

	<script type="text/javascript" src="/frontEnd/assets/js/index.js"></script>


<%@ include file="component/footer.php" %>
