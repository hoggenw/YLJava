var app = new Vue({
	el: '#myApp',
	data: {
		industry:{},
		couponList: [],
		showDate:[],
		oilWeight:'',
		
		isRun:true
	},
	created: function() {
		var _t = this;
		_t.getList();
	},
	methods: {
		getList: function() {
			var _t = this;
			base.Ajax({
				url: app_config.API_URL + 'info/user',
				data: {},
			}, function(data) {
				if (data.ErrorCode == 0) {
					var list = data.corps;
					for (var i = 0; i < list.length; i++) {
						var arr = {
							id: list[i].restCorpId,//企业id
							eqptId:list[i].eqpts[0].eqptId,//设备id
							corpName: list[i].corpName,//餐厅名
							address:list[i].address,//餐厅地址
							eqptTypeName:list[i].eqpts[0].eqptTypeName//油水分离器名字
						}
						_t.couponList.push(arr)
					}
					
					 _t.industry = _t.couponList[0];
					
					console.log(_t.industry);
				} else {
					layer.msg(data.Message);
				}
			})
		},
		submit:function(){
			var _t = this;
			
			var reg = /^(-)?(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
			
			if(!reg.test(_t.oilWeight)){
				layer.msg('购入有质量输入错误');
				return false;
			}
			
			if(_t.oilWeight.trim() <= 0){
				layer.msg('购入有质量输入错误');
				return false;
			}
			
			if(_t.isRun){
				_t.isRun = false;
				base.Ajax({
					type:'POST',
					url: app_config.API_URL + 'info/oil',
					data: {
						eqptId:_t.industry.eqptId,
						nowRestCorpId:_t.industry.id,
						oilWeight:_t.oilWeight,
					},
				}, function(data) {
					if (data.ErrorCode == 0) {
						layer.msg('录入成功', {icon: 1}); 
						setTimeout(function() {
							window.location.href = '/record';
						}, 1000);
					} else {
						layer.msg(data.Message);
						_t.isRun = true;
					}
				})
			}
		}
	}
})
