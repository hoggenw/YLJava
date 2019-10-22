seajs.use(['base', 'page'], function(base) {

    base.headMobile(); //解决手机端input获取焦点时候 头部固定偏移问题
    var clientEndInfo = JSON.parse( localStorage.getItem('userInfo') )
    var _config = {
        baseURL: app_config.API_URL ,
        timeout: 1000,
        headers: {'token':clientEndInfo.token },
        transformRequest: [function (data) {
            let ret = ''
            for (let it in data) {
                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            return ret
        }]
    }
    var app = new Vue({
        el: "#app",
        data: {


            options: [
                {
                    name: '女士',
                    value: 0
                },
                {
                    name: '男士',
                    value: 1
                },
            ],

            search_info: {
                first_time_limit: '',
                second_time_limit:'',
                third_time_limit:'',
                first_percent:'',
                second_percent:'',
                third_percent:''
            },

            txtVal: 0





        },
        mounted: function() {


        },
        created: function() {
            var _self = this;
            //判断用户是否登录
            base.userInfo();
            _self.getData();
        },
        components: {
            // 引用组件
            'pagination': pagination
        },
        filters: {
            capitalize: function(value) {
                return value == '0' ? '正常' : '冻结';
            },

            sexFilter: function(value) {
                return value == '0' ? '女士' : '男士';
            }
        },
        methods: {

            submitClick: function() {
                var _self = this;

                //
                // _self.search_info.first_time_limit =  $.trim($('#first_time_limit').val());
                // _self.search_info.second_time_limit =   $.trim($('#second_time_limit').val());
                //
                // _self.search_info.third_time_limit  =   $.trim($('#third_time_limit').val());
                // _self.search_info.first_percent =  $.trim($('#first_percent').val());
                //
                // _self.search_info.second_percent =   $.trim($('#second_percent').val());
                // _self.search_info.third_percent =  $.trim($('#third_percent').val());


                axios.post('/api/admin/updateConfig', _self.search_info,_config)
                    .then(res =>{
                    if(res.data.errno==0){
                    layer.msg('修改成功', {
                        icon: 1,
                        time: 800
                    });
                    setTimeout(function() {
                        window.location.href = "/setting";
                    }, 800);

                }
            else if(res.data.errno=='-10001'){
                    window.location.href = "/"
                }
                else{
                    layer.msg(res.data.errmsg)
                }
            })


            },
            getData: function() { //新增账号
                var _self = this;

                axios.post('/api/admin/getConfig', _self.search_info,_config)
                    .then(res =>{
                    if(res.data.errno==0){
                    _self.gridData = res.data.data;
                        _self.search_info.first_time_limit =  _self.gridData.first_time_limit;
                        _self.search_info.second_time_limit =    _self.gridData.second_time_limit;

                        _self.search_info.third_time_limit  =  _self.gridData.third_time_limit;
                        _self.search_info.first_percent =   _self.gridData.first_percent;

                        _self.search_info.second_percent =    _self.gridData.second_percent;
                        _self.search_info.third_percent =   _self.gridData.third_percent;

                }
            else if(res.data.errno=='-10001'){
                    window.location.href = "/"
                }
                else{
                    layer.msg(res.data.errmsg)
                }
            })
            },



        }
    })


});
