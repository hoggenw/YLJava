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

			timeOptions: [
				{
					name: '国历',
					value: 1
				},
				{
					name: '农历',
					value: 2
				},
			],

			search_info:{
				realName: '',
				pMobile:'',
				mobile:'',
				birthday:'',
				address:'',
				integral:'',
				remark:'',
				salesperson:'',
				sex: 0 ,//默认选中第一项
				birthdayType: 1
			}





		},
		created: function() {
			var _self = this;
			//判断用户是否登录
			base.userInfo();

			_self.getList(1);
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
			},

			timeFilter: function(value) {
				return value == '1' ? '国历' : '农历';
			}
		},
		methods: {
			submitClick: function() {
				var _self = this;
				axios.post('/api/user/add', _self.search_info,_config)
					.then(res =>{
						if(res.data.errno==0){
							layer.msg('添加成功', {
								icon: 1,
								time: 800
							});
							setTimeout(function() {
								window.location.href = "/adduser";
							}, 800);

						}
						else if(res.data.errno=='-10001'){
							window.location.href = "/login"
						}
						else{
							layer.msg(res.data.errmsg)
						}
					})


			},
			back: function() { //新增账号
				window.location.href = "/";
			},



		}
	})


});
