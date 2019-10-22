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
			status: 'all', //默认选中第一项
			options: [{
					name: '全部',
					value: 'all'
				},
				{
					name: '正常',
					value: 0
				},
				{
					name: '冻结',
					value: 1
				},
			],
			gridData: [], //返回的数据
			curPage: 0, //当前页
			showPages: 0, //显示多少页
			totalPages: 0, //总页数
			isPage: true, //是否显示分页
			pageSize: 15, //每页显示多少条
			search_info:{
				realName:'',
				pId:'',
				phone:'',
				page_size:20,
				page_index:1,
				begin_time: null, //开始时间
				over_time: null, //结束时间
			}

		},

		mounted: function() {
			laydate.render({
				elem: '#startTime', //指定元素
				type:'datetime',
			});

			laydate.render({
				elem: '#endTime', //指定元素
				type:'datetime',
			});
		},
		created: function() {
			var _self = this;
			//判断用户是否登录
			base.userInfo();

			_self.getList(1);

			localStorage.setItem('userItem', '');
		},
		components: {
			// 引用组件
			'pagination': pagination
		},
		filters: {
			capitalize: function(value) {
				return value == '0' ? '正常' : '已经作废';
			},

			sexFilter: function(value) {
				return value == '0' ? '女士' : '男士';
			},
			timeForMart: function(v) {
				let date = new Date(v);
				let Y = date.getFullYear() + '-';
				let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
				let D = date.getDate() + '  ';
				let h = date.getHours() < 10?'0' + date.getHours() + ':': date.getHours() + ':';
				let m = date.getMinutes() < 10?'0' + date.getMinutes() + ':': date.getMinutes() + ':';
				let s = date.getSeconds() < 10?'0' + date.getSeconds(): date.getSeconds();
				return Y + M + D + h + m + s;
			}
		},
		methods: {

			/**
			 * 时间大小比较
			 */
			MyData:function(starttime,endtime){
				var start = new Date(starttime.replace("-", "/").replace("-", "/"));
				var end = new Date(endtime.replace("-", "/").replace("-", "/"));
				if(end < start){
					return false;
				}
				return true;
			},

			getList: function(p) {
				var _self = this;
				_self.search_info.page_index = p;
				var begin_time = $.trim($('#startTime').val());
				var over_time = $.trim($('#endTime').val());

				if(begin_time && over_time){
					console.log(_self.MyData(begin_time,over_time));
					if(!_self.MyData(begin_time,over_time)){
						layer.msg('开始时间必须大于结束时间');
						return false;
					}
				}
				_self.search_info.begin_time=begin_time, //开始时
				_self.search_info.over_time=over_time, //结束时间
				axios.post('api/user/listBills', _self.search_info,_config)
					.then(res =>{
						if(res.data.errno==0){
							_self.gridData = res.data.data.bills;
							let d = res.data.data;

							let lastPage = Math.ceil(d.count / _self.pageSize); //总页数（向上取整,有小数就整数部分加1）

							_self.curPage = p; //当前页
							_self.totalPages = lastPage; //总页数
							_self.showPages = d.count == 0 ? 0 : lastPage < 5 ? lastPage : 5; //可以点击的页数

							if (d.count == 0) {
								_self.isPage = false;
							}
						}
						else if(res.data.errno=='-10001'){
							window.location.href = "/login"
						}
						else{
							layer.msg(res.data.errmsg)
						}
					})

			},
			addBill: function(item) { //查看详情
				let _self = this;
				let title = '';
				let url = '';
				let data = {};

				let html = '<div class="am-form am-margin-top-sm">' +
					'<div class="am-form-group am-cf am-u-sm-12 am-u-lg-12">' +
					'<span class="am-u-sm-4 am-text-right am-text-middle">姓名</span>' +
					'<div class="am-u-sm-8">' +
					'<input type="text" id="user_name" class="am-input-sm" placeholder="" value="item.realName">' +
					'</div>' +
					'</div>' +
					'<div class="am-form-group am-cf am-margin-top-sm">' +
					'<span class="am-u-sm-4 am-text-right">交易金额</span>' +
					'<div class="am-u-sm-8">' +
					'<input type="text" id="password" class="am-input-sm" placeholder="交易金额">' +
					'</div>' +
					'</div>' +
					'<div class="am-form-group am-cf am-margin-top-sm">' +
					'<span class="am-u-sm-4 am-text-right">备注</span>' +
					'<div class="am-u-sm-8">' +
					'<input type="text" id="agenpwd" class="am-input-sm" placeholder="备注">' +
					'</div>' +
					'</div>' +
					'</div>';
				title = '添加交易信息';
				url = app_config.API_URL + 'api/bill/add';
				var index = layer.open({
					title: title,
					content: html,
					type: 1,
					btn: ['保存', '取消'],
					btnAlign: 'c',
					area: ['350px', '255px'],
					success: function() {
						$('#user_name').val(item.realName).attr('readonly', 'readonly');
					},
					yes: function() {
						let pwd = $.trim($('#password').val());
						let agenpwd = $.trim($('#agenpwd').val());

						data = {
							"userId": item.userId,
							"integral": pwd,
							"remark": agenpwd
						}
						/**
						 * 最短6位，最长16位 {6,16}
						 *	可以包含小写大母 [a-z] 和大写字母 [A-Z]
						 *	可以包含数字 [0-9]
						 *	可以包含下划线 [ _ ] 和减号 [ - ]
						 */
						var pattern = /^[\w_-]{6,16}$/; //密码

						// var usern = /^([\u4e00-\u9fa5]{0,} + [a-zA-Z0-9]{4,20})$/; //用户名

						if (!pwd) {
							layer.msg('请输入交易金额');
							return false;
						}

						axios.post('api/bill/add', {
							"userId": item.userId,
							"integral": pwd,
							"remark": agenpwd
						},_config)
							.then(res =>{
								if(res.data.errno==0){
									layer.close(index);
									layer.msg('新增交易成功', {
										icon: 1
									});
									_self.getList(_self.curPage);
								}
								else if(res.data.errno=='-10001'){
									window.location.href = "/login"
								}
								else{
									layer.msg(res.data.errmsg)
								}
							})
					}
				})

			},


			update:function(item) { //查看详情
				localStorage.setItem('userItem', JSON.stringify(item));
				window.location.href = "/updateUser";

			},

			addAcount: function() { //新增账号
				window.location.href = "/adduser";
			},

			discardBill: function(id) { //type 1:冻结账号 0:解冻
				let _self = this;
				let title = '是否确认作废该订单';
				layer.confirm(title,{icon: 3, title:'提示'},function(index){
					axios.post('api/bill/discard', {
						billId:id,
					},_config)
						.then(res =>{
							if(res.data.errno==0){
								layer.close(index);
								layer.msg('操作成功成功', {
									icon: 1
								});
								_self.getList(_self.curPage);
							}
							else if(res.data.errno=='-10001'){
								window.location.href = "/login"
							}
							else{
								layer.msg(res.data.errmsg)
							}
						})

				})
				
			},
			removeAccount: function(id) { //删除账号
				let _self = this;
				layer.confirm('是否需要删除该账号？',{icon: 3, title:'提示'},function(index){
					base.Ajax({
						type: 'POST', //HTTP请求类型
						url: app_config.API_URL + 'admin/user/delete',
						data: {
							user_id: id
						},
					}, function(data) {
						if (data.ErrorCode == 0) {
							layer.close(index);
							layer.msg('账号删除成功', {
								icon: 1
							});
							_self.getList(_self.curPage);
						} else {
							layer.msg(data.Message);
						}
					})
					
				})
			},
			layerOpen: function(type, item) { //type 1:增加 2:修改
				let _self = this;
				let title = '';
				let url = '';
				let data = {};

				let html = '<div class="am-form am-margin-top-sm">' +
					'<div class="am-form-group am-cf am-u-sm-12 am-u-lg-12">' +
					'<span class="am-u-sm-4 am-text-right am-text-middle">账号</span>' +
					'<div class="am-u-sm-8">' +
					'<input type="text" id="user_name" class="am-input-sm" placeholder="输入账号">' +
					'</div>' +
					'</div>' +
					'<div class="am-form-group am-cf am-margin-top-sm">' +
					'<span class="am-u-sm-4 am-text-right">登录密码</span>' +
					'<div class="am-u-sm-8">' +
					'<input type="password" id="password" class="am-input-sm" placeholder="输入密码">' +
					'</div>' +
					'</div>' +
					'<div class="am-form-group am-cf am-margin-top-sm">' +
					'<span class="am-u-sm-4 am-text-right">确认密码</span>' +
					'<div class="am-u-sm-8">' +
					'<input type="password" id="agenpwd" class="am-input-sm" placeholder="请再次输入密码">' +
					'</div>' +
					'</div>' +
					'</div>';
				if (type == 1) {
					title = '新增账号信息';
					url = app_config.API_URL + 'admin/addUser';
				} else {
					title = '修改账号密码';
					url = app_config.API_URL + 'admin/user/modifyPassword';
				}
				var index = layer.open({
					title: title,
					content: html,
					type: 1,
					btn: ['保存', '取消'],
					btnAlign: 'c',
					area: ['350px', '255px'],
					success: function() {
						if (type == 2) {
							$('#user_name').val(item.userName).attr('readonly', 'readonly');
						}
					},
					yes: function() {
						let user = $.trim($('#user_name').val());
						let pwd = $.trim($('#password').val());
						let agenpwd = $.trim($('#agenpwd').val());

						if (type == 1) {
							if (!user) {
								layer.msg('请输入用户名');
								return false;
							}
							// var usern = /^[\u4E00-\u9FA5A-Za-z0-9]{4,20}$/; //用户名
							// var usern = /^([\u4E00-\u9FA5A-Za-z0-9]){4,20}$/;
							// var usern = /^(?=[\u4E00-\u9FA5]{4,20}$)([A-Za-z0-9]{4,20})$/

							// 							if(!usern.test(user)){
							// 								layer.msg('用户名中必须要有汉字');
							// 								return false;
							// 							}

							if (user.length < 4 || user.length > 20) {
								layer.msg('用户名长度为4~20个字符');
								return false;
							}
							data = {
								"user_name": user,
								"password": pwd
							}
						} else {
							data = {
								"user_id": item.userId,
								"password": pwd
							}
						}
						/**
						 * 最短6位，最长16位 {6,16}
						 *	可以包含小写大母 [a-z] 和大写字母 [A-Z]
						 *	可以包含数字 [0-9]
						 *	可以包含下划线 [ _ ] 和减号 [ - ]
						 */
						var pattern = /^[\w_-]{6,16}$/; //密码

						// var usern = /^([\u4e00-\u9fa5]{0,} + [a-zA-Z0-9]{4,20})$/; //用户名

						if (!pwd) {
							layer.msg('请输入密码');
							return false;
						}
						if (!pattern.test(pwd)) {
							layer.msg('请按照规则填写新密码');
							return false;
						}
						if (pwd != agenpwd) {
							layer.msg('两次输入的密码不一样');
							return false;
						}

						base.Ajax({
							type: 'POST', //HTTP请求类型
							url: url,
							data: data,
						}, function(data) {
							if (data.ErrorCode == 0) {
								layer.close(index);
								if (type == 1) {
									layer.msg('新增账号成功', {
										icon: 1
									});
									_self.getList(_self.curPage);
								} else {
									layer.msg('账号密码修改成功', {
										icon: 1
									});
									_self.getList(_self.curPage);
								}
							} else {
								layer.msg(data.Message);
							}
						})
					}
				})
			}

		}
	})


});