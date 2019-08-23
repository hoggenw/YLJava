define(function(require, exports, module) {
	var base = require('base');


	var app = new Vue({
		el: '#login_box',
		data: {
			user: '',
			pwd: '',
		},
		created: function() {
			localStorage.setItem('userInfo', '');
		},

		methods: {
			loginClick: function() {
				let _self = this;
				let user = _self.user;
				let pwd = _self.pwd;

				if (!user) {
					layer.msg('请输入用户名');
					return false;
				}
				if (pwd == '' || !pwd) {
					layer.msg('请输入登录密码');
					return false;
				}

				base.Ajax({
					onSite: 1, //标识站内还是站外 1：站外
					type: 'post',
					url: app_config.API_URL + 'api/login/admin',
					data: {
						user_name: user.trim(),
						password: pwd
					},
				}, function(data) {
					if (data.errno == 0) {
						layer.msg('登录成功', {
							icon: 1,
							time: 800
						});

						let callbackData = {
							token: data.data.token,
							name: data.data.name,
							user_id: data.data.userId
						};
						localStorage.setItem('userInfo', JSON.stringify(callbackData));
						setTimeout(function() {
							window.location.href = "/";
						}, 800);
					} else {
						layer.msg(data.errmsg);
					}
				})

			}
		}
	})
})
