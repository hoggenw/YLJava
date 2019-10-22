seajs.use(['base'], function(base) {

	base.headMobile(); //解决手机端input获取焦点时候 头部固定偏移问题

	var app = new Vue({
		el: "#app",
		data: {
			countType: 1, //默认选中第一项
			countOptions: [{
					name: '访问数量',
					value: 1
				},
				{
					name: '使用时间',
					value: 2
				},
				{
					name: '上传图片数量',
					value: 3
				}
			],
			timeType: 1, //默认选中第一项
			timeOptions: [{
					name: '按天查看',
					value: 1
				},
				{
					name: '按月查看',
					value: 2
				}
			],
			gridData: [], //返回的数据
			endNum: 0, //截止值
		},
		created: function() {
			var _self = this;
			//判断用户是否登录
			base.userInfo();

			_self.getData();
		},
		filters: {
			capitalize: function(value, b, c) {
				if (!value) return ''
				value = value.toString()
				var a = value.charAt(0).toUpperCase() + value.slice(1);
				if (!b || !c) return a;
				return a + b + c;
			}
		},
		mounted: function() {
			var _self = this;
			//☆☆☆☆☆vue的生命周期    jquery 的初始方法写这里面
			laydate.render({
				elem: '#startTime', //指定元素
				type:'datetime'
			});
			
			laydate.render({
				elem: '#endTime', //指定元素
				type:'datetime'
			});
			_self.showCharts();
		},
		methods: {
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
			},
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
			getData: function() {
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
					url: app_config.API_URL + 'admin/listStatistics',
					type: 'POST',
					data: {
						begin_time: begin_time, //开始时间
						over_time: over_time, //结束时间
						type: _self.timeType,
					}
				}, function(data) {
					if (data.ErrorCode == 0) {
						_self.gridData = data.Data.statistics;
						
						console.log(data.Data);
						
						_self.showCharts();

					}
				})
			},
			showCharts:function(){
				var _self = this;
				var data = _self.gridData;
				var barBottom = []; //下方x轴
				var loginData = []; //登录数据
				var uploadData = []; //图片上传数据
				
				for (var i = 0; i < data.length; i++) {
					barBottom.push(data[i].statisticsTime);
					
					loginData.push(Number(data[i].count));
				
					uploadData.push(Number(data[i].pictureCount));
				}
				
				var chart = Highcharts.chart('container', {
					title: {
						text: '网站访问数据统计'
					},
					subtitle: {
						text: '数据来源'
					},
					xAxis: {
						categories: barBottom
					},
					yAxis: [{ // 第一个 Y 轴，放置在左边（默认在坐标）
						title: {
							text: null
						},
						labels: {
							align: 'left',
							x: 3,
							y: 16,
							format: '{value:.,0f}'
						},
						showFirstLabel: false
					}, { // 第二个坐标轴，放置在右边
						linkedTo: 0,
						gridLineWidth: 0,
						opposite: true, // 通过此参数设置坐标轴显示在对立面
						title: {
							text: null
						},
						labels: {
							align: 'right',
							x: -3,
							y: 16,
							format: '{value:.,0f}'
						},
						showFirstLabel: false
					}],
					legend: {
						align: 'left',
						verticalAlign: 'top',
						y: 20,
						floating: true,
						borderWidth: 0
					},
					tooltip: {
						shared: true,
						crosshairs: true,
						// 时间格式化字符
						// 默认会根据当前的数据点间隔取对应的值
						// 当前图表中数据点间隔为 1天，所以配置 day 值即可
						dateTimeLabelFormats: {
							day: '%Y-%m-%d'
						}
					},
					series: [{
						name: '访问数量',
						data: loginData
					}, {
						name: '上传图片数量',
						data: uploadData
					}],
					credits: {
						enabled: false //不显示LOGO 
					},
					plotOptions: {
						series: {
							cursor: 'pointer',
							point: {
								events: {
									// 数据点点击事件
									// 其中 e 变量为事件对象，this 为当前数据点对象
									click: function(e) {
										$('.message').html(Highcharts.dateFormat('%Y-%m-%d', this.x) + ':<br/>  访问量：' + this.y);
									}
								}
							},
							marker: {
								lineWidth: 1
							}
						}
					}
				});
			}
		}
	})


});
