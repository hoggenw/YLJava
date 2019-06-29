seajs.use(['base', 'page'], function(base) {

	base.headMobile(); //解决手机端input获取焦点时候 头部固定偏移问题

	var app = new Vue({
		el: "#app",
		data: {
			account: '',
			gridData: [], //返回的数据

			curPage: 0, //当前页
			showPages: 0, //显示多少页
			totalPages: 0, //总页数
			isPage: true, //是否显示分页
			pageSize: 15, //每页显示多少条	

			myCheck: [], //选中文件数组

			status: '', //是否有数据
		},
		mounted: function() {

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
		methods: {
			getList: function(p) {
				var _self = this;

				base.Ajax({
					url: app_config.API_URL + 'admin/alertEqpmList',
					type: 'POST',
					data: {
						level: 2,
						type: 0,
						pageIndex: p,
						page_size: _self.pageSize
					}
				}, function(data) {
					if (data.ErrorCode == '0') {

						_self.gridData = data.Data.list;

						if (data.Data.list.length == 0) {
							_self.status = 1;
						}


						data.Data.list.map((val, index) => {
							val.num = (val.oilBuy - val.interValFlow).toFixed(2);
						})

						let d = data.Data;
						console.log(d);
						let lastPage = Math.ceil(d.count / _self.pageSize); //总页数（向上取整,有小数就整数部分加1）
						_self.curPage = p; //当前页
						_self.totalPages = lastPage; //总页数
						_self.showPages = d.count == 0 ? 0 : lastPage < 5 ? lastPage : 5; //可以点击的页数

						if (lastPage == 0) {
							_self.isPage = false;
						}

					} else {
						layer.msg(data.Message);
					}
				})
			},

		}
	})


});
