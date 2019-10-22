seajs.use(['base', 'page'], function(base) {

	base.headMobile(); //解决手机端input获取焦点时候 头部固定偏移问题

	var app = new Vue({
		el: "#app",
		data: {
			status: 'all', //默认选中第一项
			options: [{
					name: '选择日志类型',
					value: 'all'
				},
				{
					name: '全部',
					value: 0
				},
				{
					name: '登录',
					value: 1
				},
				{
					name: '上传头围检测',
					value: 2
				},
				{
					name: '上传腹围测',
					value: 3
				},
				{
					name: '上传股⻣检测',
					value: 4
				},
				{
					name: '上传头臀径检测',
					value: 5
				},
				{
					name: '上传腹围质控',
					value: 6
				},
			],
			account: '',
			gridData: [], //返回的数据

			curPage: 0, //当前页
			showPages: 0, //显示多少页
			totalPages: 0, //总页数
			isPage: true, //是否显示分页
			pageSize: 15, //每页显示多少条	

			myCheck: [], //选中文件数组
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
		},
		components: {
			// 引用组件
			'pagination': pagination
		},
		filters: {
			capitalize: function(v) {
				return v == 1 ? '登录' : v == 2 ? '上传头围检测' : v == 3 ? '上传腹围检测' : v == 4 ? '上传股⻣检测' : v == 5 ? '上传头臀径检测' : v == 6 ? '上传腹围质控' : '';
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
				var begin_time = $.trim($('#startTime').val());
				var over_time = $.trim($('#endTime').val());
				
				if(begin_time && over_time){
					console.log(_self.MyData(begin_time,over_time));
					if(!_self.MyData(begin_time,over_time)){
						layer.msg('开始时间必须大于结束时间');
						return false;
					}
				}

				base.Ajax({
					url: app_config.API_URL + 'admin/operating/listOperatings',
					type: 'POST',
					data: {
						begin_time: begin_time, //开始时间
						over_time: over_time, //结束时间
						account_name: _self.account,
						type: _self.status,
						page_index: p,
						page_size: 15
					}
				}, function(data) {
					if (data.ErrorCode == '0') {
						
						for (var i = 0; i < data.Data.operating.length; i++) {
// 							if(data.Data.operating[i].imageName == null){
// 								data.Data.operating[i].imageName = 'tempname_'+i;
// 							}
							
							if(data.Data.operating[i].type==1 && data.Data.operating[i].success){
								data.Data.operating[i].showText = '登录成功';
							}else{
								data.Data.operating[i].showText = '登录失败';
							}
							
							if(data.Data.operating[i].type!= 1 && !data.Data.operating[i].success){
								data.Data.operating[i].showText = '上传失败';
							}
						}
						_self.gridData = data.Data.operating;
						let d = data.Data;

						let lastPage = Math.ceil(d.count / _self.pageSize); //总页数（向上取整,有小数就整数部分加1）
						_self.curPage = p; //当前页
						_self.totalPages = lastPage; //总页数
						_self.showPages = d.count == 0 ? 0 : lastPage < 5 ? lastPage : 5; //可以点击的页数

						if (lastPage == 0) {
							_self.isPage = false;
						}

					}
				})
			},
			/**
			 * 导出日志
			 */
			exportLog: function() {
				var _self = this;
				var begin_time = $.trim($('#startTime').val());
				var over_time = $.trim($('#endTime').val());

// 				// 创建Form
// 				var form = $('<form></form>');
// 				// 设置属性
// 				form.attr('action', app_config.API_URL + 'admin/getExcel');
// 				form.attr('method', 'post');
// 				// form的target属性决定form在哪个页面提交
// 				// _self -> 当前页面 _blank -> 新页面
// 				form.attr('target', '_self');
// 
// 				// 创建Input
// 				var timeinput1 = $('<input type="text" name="begin_time" />');
// 				timeinput1.attr('value', begin_time);
// 
// 				var timeinput2 = $('<input type="text" name="over_time" />');
// 				timeinput2.attr('value', over_time);
// 
// 				var accountName = $('<input type="text" name="account_name" />');
// 				accountName.attr('value', _self.account);
// 
// 				var type = $('<input type="text" name="type" />');
// 				type.attr('value', _self.status);
// 				// 附加到Form
// 				form.append(timeinput1);
// 				form.append(timeinput2);
// 				form.append(accountName);
// 				form.append(type);
// 				$("body").append(form); //页面添加form标签

				// 提交表单
// 				setTimeout(function() {
// 					form.submit();
// 				}, 1000)
// 				// 注意return false取消链接的默认动作
// 				return false;


				var requestData = {
					token:base.userInfo().token,
					begin_time: begin_time, //开始时间
					over_time: over_time, //结束时间
					account_name: _self.account,
					type: _self.status
				}

				var urlfmt = '';
				$.each(requestData, function (i, a) {
					urlfmt += '&' + i + '=' + a;
				});
				window.location.href = app_config.API_URL + 'admin/getExcel?' + urlfmt.substr(1);
				return false;

				base.Ajax({
					url: app_config.API_URL + 'admin/getExcel',
					type: 'get',
					dataType:'',
					data: {
						begin_time: begin_time, //开始时间
						over_time: over_time, //结束时间
						account_name: _self.account,
						type: _self.status,
					}
				}, function(data) {
					if (data.ErrorCode == '0') {

					}
				})
			},
			/**
			 * 单个下载
			 */
			checkAlone:function(e){
				let imgName = e.srcElement.dataset.name;
				
				var requestData = {
					token:base.userInfo().token,
					file_name:imgName
				}
				var urlfmt = '';
				$.each(requestData, function (i, a) {
					urlfmt += '&' + i + '=' + a;
				});
				window.location.href = app_config.API_URL + 'admin/download/files?' + urlfmt.substr(1);
			},
			/**
			 * 批量下载
			 */
			batchDownload: function() {
				let _self = this;
				let arrSting = '';
				
				if(_self.myCheck == ''){
					layer.msg('请选择要下载的文件');
					return false;
				}
				
				for (var i = 0; i < _self.myCheck.length; i++) {
					if(i == 0){
						arrSting = _self.myCheck[i];
					}else{
						arrSting = arrSting + ',' + _self.myCheck[i];
					}
				}
				
				let ar = JSON.stringify(arrSting);

				let data = {
					files:ar
				}
				
				var requestData = {
					token:base.userInfo().token,
					files:data.files
				}
				var urlfmt = '';
				$.each(requestData, function (i, a) {
					urlfmt += '&' + i + '=' + a;
				});
				
				window.location.href = app_config.API_URL + 'admin/download/files?' + urlfmt.substr(1);

			},
			/**
			 * 选择要下载的文件
			 */
			checkFunction: function(e) {
				let _self = this;
				
				let array = _self.myCheck;
				let imgName = e.srcElement.dataset.name;
				
				let arrString = '';
				
				let index = array.indexOf(imgName);
				if (index > -1) {
					array.splice(index, 1);
				}else{
					array.push(imgName);
				}
				
				_self.myCheck = array;
			}

		}
	})


});
