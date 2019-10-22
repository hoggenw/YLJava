seajs.use(['base', 'page'], function(base) {

	base.headMobile(); //解决手机端input获取焦点时候 头部固定偏移问题
	var itemUser = JSON.parse( localStorage.getItem('userItem') )
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
				realName: itemUser.realName,
				pMobile:'',
				mobile:itemUser.mobile,
				birthday:itemUser.birthday,
				address:itemUser.address,
				integral:itemUser.integral,
				remark:itemUser.remark,
				sex: itemUser.sex ,//默认选中第一项
				salesperson:itemUser.salesperson,
				birthdayType: itemUser.birthdayType,
			},






		},
		created: function() {
			var _self = this;
			//判断用户是否登录
			base.userInfo();

			_self.submitClick(itemUser.pId);
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
			submitClick: function(pId) {
				if (pId <= 0){
					return;
				}

				var _self = this;
				axios.post('/api/user/get',{
					userId: pId
				},_config)
					.then(res =>{
						if(res.data.errno==0){
							_self.search_info.pMobile = res.data.data.user.mobile;

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
