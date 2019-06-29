seajs.use(['base', 'page'], function(base) {

	base.headMobile(); //解决手机端input获取焦点时候 头部固定偏移问题

	var app = new Vue({
		el: "#app",
		data: {
			gridData: [], //返回的数据
			btnClass: 1,
			opts:{
				width: 250, // 信息窗口宽度
				height: 150, // 信息窗口高度
				title: "", // 信息窗口标题
				enableMessage: true //设置允许信息窗发送短息
			}
		},
		mounted: function() {
			var _self = this;
			_self.newMap();


		},
		created: function() {
			var _self = this;
			//判断用户是否登录
			base.userInfo();


			_self.getList();
		},
		components: {
			// 引用组件
			'pagination': pagination
		},
		filters: {
			capitalize: function(value) {
				return value == '0' ? '正常' : '冻结';
			}
		},
		methods: {
			newMap: function() {
				var _self = this;
				var map = new BMap.Map("allmap", {
					minZoom: 1,
					maxZoom: 20
				}); // 创建Map实例
				var point = new BMap.Point(_self.gridData[0].nowGPS.split(',')[0], _self.gridData[0].nowGPS.split(',')[1]);
				map.centerAndZoom(point, 12);
				map.enableScrollWheelZoom(true);
				
				
				

				
				
				
				for (var i = 0; i < _self.gridData.length; i++) {
					var points = new BMap.Point(_self.gridData[i].nowGPS.split(',')[0], _self.gridData[i].nowGPS.split(',')[1]);
					
					
					
					
					
					if(_self.btnClass == 1){//环比预警
						var content = '油水分离器：' + _self.gridData[i].eqptId + '<br>' + 
						'餐厅名：' + _self.gridData[i].restName + '<br>' + 
						'本次回收量：' + _self.gridData[i].oilFlow + '<br>' + 
						'环比回收均量：' + _self.gridData[i].aveFlow + '<br>';
						
						//正常是绿色
						if(_self.gridData[i].aveAlert == 0){
							var myIcon = new BMap.Icon("/backEnd/assets/images/green.png", new BMap.Size(32,32));
							var markers = new BMap.Marker(points,{icon:myIcon});  // 创建标注
							content += '<span style="color:#00cc66">差值预警：' + (_self.gridData[i].oilFlow-_self.gridData[i].aveFlow).toFixed(2);
						}
						//一级是黄色
						if(_self.gridData[i].aveAlert == 1){
							var myIcon = new BMap.Icon("/backEnd/assets/images/yellow.png", new BMap.Size(32,32));
							var markers = new BMap.Marker(points,{icon:myIcon});  // 创建标注
							content += '<span style="color:#FF9900">差值预警：' + (_self.gridData[i].oilFlow-_self.gridData[i].aveFlow).toFixed(2);
						}
						//二级是红色
						if(_self.gridData[i].aveAlert == 2){
							var myIcon = new BMap.Icon("/backEnd/assets/images/red.png", new BMap.Size(32,32));
							var markers = new BMap.Marker(points,{icon:myIcon});  // 创建标注
							content += '<span style="color:#FF0000">差值预警：' + (_self.gridData[i].oilFlow-_self.gridData[i].aveFlow).toFixed(2);
						}
					}else{
						var content = '油水分离器：' + _self.gridData[i].eqptId + '<br>' + 
						'餐厅名：' + _self.gridData[i].restName + '<br>' + 
						'购入油脂量：' + _self.gridData[i].oilBuy + '<br>' + 
						'周期回收量：' + _self.gridData[i].interValFlow + '<br>';
						
						//正常是绿色
						if(_self.gridData[i].intervalAlert == 0){
							var myIcon = new BMap.Icon("/backEnd/assets/images/green.png", new BMap.Size(32,32));
							var markers = new BMap.Marker(points,{icon:myIcon});  // 创建标注
							content += '<span style="color:#00cc66">差值预警：' + (_self.gridData[i].oilBuy-_self.gridData[i].interValFlow).toFixed(2);
						}
						//一级是黄色
						if(_self.gridData[i].intervalAlert == 1){
							var myIcon = new BMap.Icon("/backEnd/assets/images/yellow.png", new BMap.Size(32,32));
							var markers = new BMap.Marker(points,{icon:myIcon});  // 创建标注
							content += '<span style="color:#FF9900">差值预警：' + (_self.gridData[i].oilBuy-_self.gridData[i].interValFlow).toFixed(2);
						}
						//二级是红色
						if(_self.gridData[i].intervalAlert == 2){
							var myIcon = new BMap.Icon("/backEnd/assets/images/red.png", new BMap.Size(32,32));
							var markers = new BMap.Marker(points,{icon:myIcon});  // 创建标注
							content += '<span style="color:#FF0000">差值预警：' + (_self.gridData[i].oilBuy-_self.gridData[i].interValFlow).toFixed(2);
						}
					}
					
					
					map.addOverlay(markers); // 将标注添加到地图中
					_self.addClickHandler(map,content, markers);
				}

				
				

			},
			addClickHandler:function(map,content, marker){
				var _self = this;
				marker.addEventListener("click", function(e) {
					_self.openInfo(map,content,e)
				});
			},
			openInfo:function(map,content, e) {
				var _self = this;
				var p = e.target;
				var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
				var infoWindow = new BMap.InfoWindow(content, _self.opts); // 创建信息窗口对象 
				map.openInfoWindow(infoWindow, point); //开启信息窗口
			},
			getList: function(p) {
				var _self = this;
				base.Ajax({
					url: app_config.API_URL + 'admin/info',
					type: 'POST',
					data: {}
				}, function(data) {
					if (data.ErrorCode == '0') {
						_self.gridData = data.Data;

					} else {
						layer.msg(data.Message);
					}
				})
			},
			checkBtn: function(type) {
				var _t = this;
				_t.btnClass = type;
				_t.newMap();
			}

		}
	})


});
