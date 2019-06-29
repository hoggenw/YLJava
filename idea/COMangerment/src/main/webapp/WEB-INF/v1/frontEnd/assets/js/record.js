var app = new Vue({
	el: '#myApp',
	data: {
		page: 1,
		dataList: [],
		allLoaded: false,
	},
	created: function() {
		var _t = this;
		_t.getData(1);
		$(window).scroll(function() {
			let scrollTop = $(this).scrollTop();
			let scrollHeight = $(document).height();
			let windowHeight = $(this).height();

			if (scrollTop + windowHeight > scrollHeight - 50) {
				_t.getData()
			}

		})
	},
	watch: {
		'data'() {
			this.allLoaded = false;
		}
	},
	filters: {
		/**
		 * 日期格式转化
		 */
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
		getData: function(page) {
			var _t = this;
			
			if (!_t.allLoaded) {
				_t.allLoaded = true;
				base.Ajax({
					type: 'POST',
					url: app_config.API_URL + 'user/record',
					data: {
						pageIndex: page || _t.page,
						page_size: 20
					},
				}, function(data) {
					if (data.ErrorCode == 0) {
						_t.page++;

						_t.allLoaded = false;

						if (page === 1) {
							_t.dataList = data.Data.lists;
							_t.page = page + 1;
							console.log(_t.data);
						} else {
							data.Data.lists.map((item) => {
								_t.dataList.push(item)
							});
						}
					} else {
						layer.msg(data.Message);
					}
				})
			}

		}
	}
})
