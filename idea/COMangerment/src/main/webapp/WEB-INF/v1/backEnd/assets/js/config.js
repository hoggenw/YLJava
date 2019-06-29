seajs.use(['base'], function(base) {

	base.headMobile(); //解决手机端input获取焦点时候 头部固定偏移问题

	var app = new Vue({
		el: "#app",
		data: {
			gridData: [], //返回的数据
			
			section:0,//添加配置时的个数标识
			
			endNum: 0, //截止值
		},
		created: function() {
			var _self = this;
			//判断用户是否登录
			base.userInfo();

			_self.getAllConfig();
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
			//☆☆☆☆☆vue的生命周期    jquery 的初始方法写这里面
		},
		methods: {
			getAllConfig: function() {
				var _self = this;

				base.Ajax({
					url: app_config.API_URL + 'admin/getSantard',
					type: 'POST',
					data: {
						catalog_id: '-1'
					}
				}, function(data) {
					if (data.ErrorCode == 0) {
						_self.gridData = data.Data.standardList;
						console.log(data.Data.standardList.length);
						
						//重组素组长度判断 （防止下表重复显示）
						for (var i = 0; i < _self.gridData.length; i++) {
							for (var j = 0; j < _self.gridData[i].standardList.length; j++) {
								_self.gridData[i].standardList[j].thisLength = _self.gridData[i].standardList.length;
							}
						}
						
						console.log(_self.gridData);
						//计算模块宽度

						for (var i = 0; i < _self.gridData.length; i++) {

							var initList = _self.gridData[i].standardList;
								_self.gridData[i].boxLength = initList.length; //总分块数量
								_self.gridData[i].allMin = initList[0].min; //总分块数量
								_self.gridData[i].allMax = (initList[initList.length - 1].max)-_self.gridData[i].allMin; //总分块数量
						}


						for (var i = 0; i < _self.gridData.length; i++) {

							var initList = _self.gridData[i].standardList;
							// console.log(initList.allMax);

							for (var j = 0; j < initList.length; j++) {

								//获取当前块最小值
								var thisMin = initList[j].min;

								//获取当前块最大值
								var thisMax = initList[j].max;

								//设置当前块宽度((当前最大值-当前最小值)/总最大值) *100 == 当前块所占比）
								initList[j].width = ((thisMax - thisMin) / _self.gridData[i].allMax) * 100;
							}
						}
						
						
						console.log(_self.gridData);

					}
				})
			},
			/**
			 * 配置数值
			 */
			configNumerical: function(id) {
				var _self = this;

				var list = []; //列表

				base.Ajax({
					url: app_config.API_URL + 'admin/getSantard',
					type: 'POST',
					data: {
						catalog_id: id
					}
				}, function(data) {
					if (data.ErrorCode == 0) {

						list = data.Data.standardList[0].standardList;

						_self.openPop(list,data.Data.standardList[0].catalogName,id);

					}
				})
			},
			/**
			 * 弹框的所有操作
			 */
			openPop: function(list,name,id) {
				let _self = this;
				
				//弹窗html
				let htmlMain = '';
				
				//list.length < 1 (首次)
				if(list.length < 1){
					htmlMain = _self.popHtmlFirst ();//首次进来的html模板
					
					//区间几？？？用于显示
					_self.section = 1;
					
				}else{
					htmlMain = _self.popHtmlEdit();//后续修改的html模板
					
					//区间几？？？用于显示
					_self.section = list.length;
				}
				
				console.log(list);

				layer.open({
					type: 1,
					area: ['500px', '300px'],
					title: name,
					closeBtn: false,
					btnAlign: 'c',
					btn: ['保存', '取消'],
					content: htmlMain,
					success: function() {
						for (var i = 0; i < list.length; i++) {
							let readonly;
							if (i == 0) {
								readonly = '';
							} else {
								readonly = 'readonly';
							}
							
							let deleteHtml = '<a href="javascript:void(0)" style="display:block" class="am-fr am-text-danger removebox"><i class="am-icon-remove"></i></a>';
							if(i == 0){
								deleteHtml = '';
							}

							let addHtml = '<div>' +
								// '<span class="am-fl">区间' + base.Utils.numberToChinese(i + 1) +'</span>'+
								deleteHtml +
								'<div class="am-form am-text-xs am-margin-top">' +
								'<table class="am-table am-table-bordered am-table-striped am-table-compact gwTable">' +
								'<tr>' +
								'<td style="vertical-align:middle">初始数值（cm）</td>' +
								'<td style="vertical-align:middle">' +
								'<input type="text" ' + readonly + ' value="' + list[i].min + '" class="am-input-sm" placeholder="初始数值" data-standardId = "'+ list[i].standardId +'">' +
								'</td>' +
								'<td style="vertical-align:middle">截止数值（cm）</td>' +
								'<td style="vertical-align:middle">' +
								'<input type="text" value="' + list[i].max + '" class="keyupEnd am-input-sm" placeholder="截止数值">' +
								'</td>' +
								'</tr>' +
								'<tr>' +
								'<td style="vertical-align:middle">状态名</td>' +
								'<td style="vertical-align:middle">' +
								'<input type="text" value="' + list[i].standardTitle + '" class="am-input-sm" placeholder="状态名">' +
								'</td>' +
								'<td style="vertical-align:middle">标尺颜色</td>' +
								'<td style="vertical-align:middle">' +
								'<input type="text" readonly="readonly" value="' + list[i].color +
								'" class="am-input-sm am-form-field picker pickerInit" placeholder="选择标尺颜色"></input>' +
								'</td>' +
								'</tr>' +
								'</table>' +
								'</div>' +
								'</div>';

							$('#numerical').append(addHtml);
						}

						$('.pickerInit').colpick({
							layout: 'hex',
							colorScheme: 'dark',
							submit: 0,
							onChange: function(hsb, hex, rgb, el, bySetColor) {
								$(el).css({
									'background-color': hex,
									// 'border-right':'32px solid #'+hex
								});
								// Fill the text box just if the color was set using the picker, and not the colpickSetColor function.
								if (!bySetColor) $(el).val(hex);
							}
						}).keyup(function() {
							$(this).colpickSetColor(this.value);
						});
						
						
						/**
						 * 上一个区间截止值 赋值给下一个区间开始值
						 * 1 4  5 8  9 12  13 16 
						 * 写截止处理
						 */							
						
						$('.keyupEnd').on('keyup',function(){
							_self.assignment(this);
						})

						//添加区间
						$('#addBox').click(function() {
							_self.popHtmlAdd()
						});
						//删除区间
						$("body").on("click", ".removebox", function() {
							let t = this;
							
							let main = $(t).parent();//当前父节点
							let next = $(t).parent().next();//下一个兄弟节点
							
							let index = layer.confirm('是否确认删除该区间？', {
								btn: ['确认删除','取消'] //按钮
							}, function(){
								main.remove();
								
								//被删除框的截至值
								let thisBeginInput = main.find('.am-input-sm').eq(0).val();
								
								//赋值给下一个兄弟节点
								next.find('.am-input-sm').eq(0).val(thisBeginInput);
								
								console.log(thisBeginInput);

								layer.close(index);
							})
						});

					},
					yes: function() {
						
						let inp = $('#numerical').find('.am-input-sm');
						
						//0 1   4 5   8 9 
						for (var n = 0; n < inp.length; n++) {

							if(n % 4 == 0){
								if(Number(inp.eq(n).val()) >= Number(inp.eq(n+1).val())){
									layer.msg('初始数值必须小于截止数值');
									return false;
								}
							}
						}
						
						for (var i = 0; i < inp.length; i++) {
							if($.trim(inp.eq(i).val()) == ''){
								layer.msg('请将数据填写完整');
								return false;
							}
						}

						let stringData = [];
						let gwtable = $('.gwTable');

						for (var i = 0; i < gwtable.length; i++) {
							var input = gwtable.eq(i).find('.am-input-sm');

							let array = {
								"min": input.eq(0).val(),
								"max": input.eq(1).val(),
								"standardTitle": input.eq(2).val(),
								"color": input.eq(3).val(),
								// "standardId": input.eq(0).attr('data-standardId')  //这个参数有就是修改否则就是添加，如果以前已经添加则必须带有该参数
							};
							stringData.push(array);
						}

						//设置标尺
						let requestData = {
							catalog_id: id,
							values: JSON.stringify(stringData)
						}
						console.log(requestData);
						
						// return false;
						base.Ajax({
							url: app_config.API_URL + 'admin/changeStandards',
							type: 'POST',
							data: requestData
						}, function(data) {
							if (data.ErrorCode == 0) {
								window.location.reload();
							} else {
								layer.msg(data.Message);
							}
						})


					}
				})
			},
			/**
			 * 只能输入两位小数
			 */
			clearNoNum:function(value){
				value = value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符   
				value = value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的   
				value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
				value = value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
				if(value.indexOf(".")< 0 && value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
					value= parseFloat(value);  
				}
				return value;
			},
			/**
			 * 上一个区间截止值 赋值给下一个区间开始值
			 * 1 4  5 8  9 12  13 16 
			 * 写截止处理
			 */
			assignment:function(t){
				let _self = this;
				$(t).val(_self.clearNoNum($(t).val()));
				
				let input = $('#numerical').find('.am-input-sm');
				
				for (var i = 0; i < input.length; i++) {
					//需要 1 5 9 13 17 21 25 进入
					if((i%4) == 1){
						input.eq(i+3).val(input.eq(i).val());
					}
				}	
			},
			/**
			 * 弹框中 用户第一次 配置时的 html
			 */
			popHtmlFirst:function(){
				let html = '<div class="am-g">' +
					'<div id="numerical" class="am-u-sm-12 am-u-md-12 am-u-lg-12">' +
					// '<span>区间'+ base.Utils.numberToChinese(_self.section) +'</span>' +
					'<div class="am-form am-text-xs">' +
						'<table class="am-table am-table-bordered am-table-striped am-table-compact gwTable">' +
						'<tr>' +
						'<td style="vertical-align:middle">初始数值（cm）</td>' +
						'<td style="vertical-align:middle">' +
						'<input type="text" id="test1" class="am-input-sm" placeholder="初始数值">' +
						'</td>' +
						'<td style="vertical-align:middle">截止数值（cm）</td>' +
						'<td style="vertical-align:middle">' +
						'<input type="text" id="test1" class="am-input-sm" placeholder="截止数值">' +
						'</td>' +
						'</tr>' +
						'<tr>' +
						'<td style="vertical-align:middle">状态名</td>' +
						'<td style="vertical-align:middle">' +
						'<input type="text" id="test1" class="am-input-sm" placeholder="状态名">' +
						'</td>' +
						'<td style="vertical-align:middle">标尺颜色</td>' +
						'<td style="vertical-align:middle">' +
						'<input type="text" readonly="readonly" class="am-input-sm am-form-field picker pickerInit" placeholder="选择标尺颜色"></input>' +
						'</td>' +
						'</tr>' +
						'</table>' +
					'</div>' +
					'</div>' +
					'<div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">' +
					'<div class="am-btn am-btn-primary am-btn-sm am-fr" id="addBox"><i class="am-icon-plus-circle"></i> 添加区间</div>' +
					'</div>' +
					'</div>';
				return html;
			},
			/**
			* 弹框中 用户后续修改 时的 html
			*/
		   popHtmlEdit:function(){
		   	let html = '<div class="am-g">' +
							'<div id="numerical" class="am-u-sm-12 am-u-md-12 am-u-lg-12">' +
							
							'</div>' +
							'<div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">' +
								'<div class="am-btn am-btn-primary am-btn-sm am-fr" id="addBox"><i class="am-icon-plus-circle"></i> 添加区间</div>' +
							'</div>' +
						'</div>';
		   	return html;
		   },
		   /**
			* 添加区间
			*/
		   popHtmlAdd:function(){
			   let _self = this;
				 
				 //这里要获取最后一个区间的 截至值
				 let inp = $('#numerical').find('.am-input-sm');
				 
				 let old3 = $.trim(inp.eq(inp.length-3).val())
				 
				 if(!old3){
					 layer.msg('请输入最后一个区块的截至值');
					 return false;
				 }
				 
				var addHtml = '<div>' +
					// '<span class="am-fl">区间' + base.Utils.numberToChinese(_self.section) +
					'</span><a href="javascript:void(0)" style="display:block" class="am-fr am-text-danger removebox"><i class="am-icon-remove"></i></a>' +
					'<div class="am-form am-text-xs">' +
					'<table class="am-table am-table-bordered am-table-striped am-table-compact gwTable">' +
					'<tr>' +
					'<td style="vertical-align:middle">初始数值（cm）</td>' +
					'<td style="vertical-align:middle">' +
					'<input type="text" readonly="readonly" value='+ old3 +' class="am-input-sm" placeholder="初始数值" data-standardId="">' +
					'</td>' +
					'<td style="vertical-align:middle">截止数值（cm）</td>' +
					'<td style="vertical-align:middle">' +
					'<input type="text" class="keyupEnd am-input-sm" placeholder="截止数值">' +
					'</td>' +
					'</tr>' +
					'<tr>' +
					'<td style="vertical-align:middle">状态名</td>' +
					'<td style="vertical-align:middle">' +
					'<input type="text" class="am-input-sm" placeholder="状态名">' +
					'</td>' +
					'<td style="vertical-align:middle">标尺颜色</td>' +
					'<td style="vertical-align:middle">' +
					'<input type="text" readonly="readonly" class="am-input-sm am-form-field picker pickerInit" placeholder="选择标尺颜色"></input>' +
					'</td>' +
					'</tr>' +
					'</table>' +
					'</div>' +
					'</div>';

				$('#numerical').append(addHtml);

				$('.pickerInit').colpick({
					layout: 'hex',
					colorScheme: 'dark',
					submit: 0,
					onChange: function(hsb, hex, rgb, el, bySetColor) {
						$(el).css({
							'background-color': hex,
							// 'border-right':'32px solid #'+hex
						});
						// Fill the text box just if the color was set using the picker, and not the colpickSetColor function.
						if (!bySetColor) $(el).val(hex);
					}
				}).keyup(function() {
					$(this).colpickSetColor(this.value);
				});
				
				
				$('.keyupEnd').on('keyup',function(){
					_self.assignment(this);
				})
		   }
		}
	})


});
