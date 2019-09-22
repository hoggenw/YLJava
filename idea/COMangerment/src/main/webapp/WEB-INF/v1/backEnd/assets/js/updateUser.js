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
				birthdayType: itemUser.birthdayType
			},


			update_info:{
				realName: '',
				mobile:'',
				birthday:'',
				address:'',
				remark:'',
				sex: '' ,//默认选中第一项
				userId:'',
				birthdayType:''
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
				_self.update_info.realName = _self.search_info.realName;
				_self.update_info.mobile = _self.search_info.mobile;
				_self.update_info.birthday = _self.search_info.birthday;
				_self.update_info.address = _self.search_info.address;
				_self.update_info.remark = _self.search_info.remark;
				_self.update_info.sex = _self.search_info.sex;
				_self.update_info.birthdayType = _self.search_info.birthdayType;
				_self.update_info.userId = itemUser.userId;
				axios.post('/api/user/update',_self.update_info,_config)
					.then(res =>{
						if(res.data.errno==0){
							layer.msg('修改成功', {
								icon: 1,
								time: 800
							});

							setTimeout(function() {
								window.location.href = "/";
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
