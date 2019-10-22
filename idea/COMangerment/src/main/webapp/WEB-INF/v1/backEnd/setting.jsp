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
                <li class="" ><a href="/">用户管理</a></li>
                <li class=""><a href="/bill">订单管理</a></li>
                <li class=""><a href="/billListReally">订单列表</a></li>
                <li class=""><a href="/recommend">推荐管理</a></li>
                <li class=""><a href="/backCash">返现管理</a></li>
                <li class="" style="background-color: #f3f3f3"><a href="/setting">活动参数配置</a></li>

            </ul>
        </div>
    </div>
    <!-- sidebar end -->


    <!-- content start -->
    <div class="admin-content" id="app" v-cloak>
        <div class="admin-content-body am-u-lg-12">

            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg ">参数配置</strong></div>
            </div>

            <div class="am-g am-form">

                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <label for="first_time_limit">第一期现返现时间设置(必填，时间单位为天):</label>
                    <input type="text" placeholder="输入第一期现返现时间" id="first_time_limit" v-model="search_info.first_time_limit" value="" >
                </div>

                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <label for="first_percent">第一期现返现比例：% (必填，如填30 即为30%):</label>
                    <input type="text" placeholder="输入第一期现返现比例" id="first_percent"  v-model=" search_info.first_percent" value="">
                </div>

                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <label for="second_time_limit">第二期现返现时间设置(必填，时间单位为天):</label>
                    <input type="text" placeholder="输入第二期现返现时间" id="second_time_limit" v-model="search_info.second_time_limit" value="" >
                </div>

                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <label for="second_percent">第二期现返现比例：% (必填，如填30 即为30%):</label>
                    <input type="text" placeholder="输入第二期现返现比例" id="second_percent"  v-model=" search_info.second_percent" value="">
                </div>

                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <label for="third_time_limit">第三期现返现时间设置(必填，时间单位为天):</label>
                    <input type="text" placeholder="输入第三期现返现时间" id="third_time_limit" v-model="search_info.third_time_limit" value="" >
                </div>

                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <label for="third_percent">第三期现返现比例：% (必填，如填30 即为30%):</label>
                    <input type="text" placeholder="输入第三期现返现比例" id="third_percent"  v-model=" search_info.third_percent" value="">
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
    seajs.use(['/backEnd/assets/js/setting']);
</script>

<!-- footer start -->
<%@ include file="tplate/footer.php" %>
<!-- footer end -->