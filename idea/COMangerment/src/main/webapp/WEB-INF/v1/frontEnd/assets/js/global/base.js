var base = {
	/**
	 * 判断移动设备还是电脑浏览器访问
	 */
	browserRedirect: function() {
		let isEquipment;
		let sUserAgent = navigator.userAgent.toLowerCase();
		let bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
		let bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
		let bIsMidp = sUserAgent.match(/midp/i) == "midp";
		let bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
		let bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
		let bIsAndroid = sUserAgent.match(/android/i) == "android";
		let bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
		let bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
		if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
			isEquipment = 'iphone';
		} else {
			isEquipment = 'pc';
		}
		return isEquipment;
	},
	/**
	 *	判断是否是微信浏览器
	 */
	isWx: function() {
		let ua = navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) == "micromessenger") {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 获取url地址传递的参数
	 * 栗子：
	 * 若地址栏URL为：abc.html?id=123&url=http://www.maidq.com
	 * 那么，但你用上面的方法去调用：alert(GetQueryString("url"));
	 */
	GetQueryString: function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]);
		return null;
	},
	/**
	 * 获取用户基本信息
	 */
	userInfo: function() {
		var Info;
		if (!localStorage.getItem('userInfo')) {
			layer.msg('登录过期!!!', {
				icon: 2
			});
			setTimeout(function() {
				window.location.href = "/login";
				return false;
			}, 1000);
		} else {
			Info = JSON.parse(localStorage.getItem('userInfo'));
		}
		return Info;
	},
	Ajax: function(parameter, successCallback, errorCallback) {
		var onSite = parameter.onSite; //站内还是站外 存在就是站外
		var ajaxParameter = {
			type: parameter.type ? parameter.type : 'GET', //HTTP请求类型
			url: parameter.url,
			data: parameter.data,
			async: false,
			dataType: 'json', //服务器返回json格式数据
			timeout: 10000, //超时时间设置为10秒；
			headers:{},
			success: function(data) {
				if (data) {
					if (data.ErrorCode == '1002') {
						window.location.href = "/login"
					} else {
						successCallback(data);
					}
				}
			},
			error: function(error) {
				if (typeof errorCallback === 'function') {
					errorCallback(error);
				} else {
					alert(JSON.stringify(error))
				}
			}
		}

		if (!onSite) { //站内
			ajaxParameter.headers.token = base.userInfo().token;
		}
		
		setTimeout(function() {
			$.ajax(ajaxParameter);
		}, 500);
		

	},
	uuid: function(len, radix) {
		var timestamp = new Date().getTime();
		var char = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz' + timestamp;
		var chars = char.split('');

		var uuid = [],
			i;
		radix = radix || chars.length;

		if (len) {
			// Compact form
			for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
		} else {
			// rfc4122, version 4 form
			var r;

			// rfc4122 requires these characters
			uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
			uuid[14] = '4';

			// Fill in random data.  At i==19 set the high bits of clock sequence as
			// per rfc4122, sec. 4.1.5
			for (i = 0; i < 36; i++) {
				if (!uuid[i]) {
					r = 0 | Math.random() * 16;
					uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
				}
			}
		}
		return uuid.join('');
	},
	/**
	 * 手机号码校验
	 */
	isPhone: function(value) {
		let isTrue = true;
		if (!value || !(/^[1][34578]\d{9}$/).test(value) || !(/^[1-9]\d*$/).test(value) || value.length !== 11) {
			isTrue = false;
		}
		return isTrue;
	},
	/**
	 * 解决手机端头部fixed问题
	 */
	headMobile: function() {
		if (base.browserRedirect() != 'pc') {
			$('input').bind('focus', function() {
				$('.admin-header').css('position', 'static');
				//或者$('#viewport').height($(window).height()+'px');
			}).bind('blur', function() {
				$('.admin-header').css({
					'position': 'fixed'
				});
				//或者$('#viewport').height('auto');
			});
		}
	},
	/**
	 * 错误码
	 */
	errorCode: function(code) {
		var explain = {
			'0002': '数据不存在',
			'0003': '数据已存在',
			'0004': '状态不正确',
			'0005': '金额不够',
			'0006': '金额太小',
			'0007': '有正在处理的提现',
			'0008': '角色已存在',
			'0009': '密码不正确',
			'0010': '已核销过了',
			'0011': '微信认证code错误',
			'0012': '微信认证access_token错误',
			'0013': '微信认证使用refresh_token请求时出错',
			'0014': '微信须要重新授权',
			'0015': '微信支付失败',
			'0016': '用户状态不正确',
			'0017': 'API认证失败，检查token',
			'0018': 'API认证失败，检查时间差',
			'0019': 'API认证失败，检查签名在配置的最大时间内使用过',
			'0020': 'API认证失败，签名结局失败',
			'0021': '验证码数据不存在',
			'0022': '验证码错误',
			'0023': '未关注公众号',
		}
		for (c in explain) {
			if (!explain[code]) {
				layer.msg('错误码不存在！code:' + code);
				return false;
			}
		}
		return explain[code];
	},
	isSendTrue: true, //是否可以点击发送短信
	findPwdUserInfo: '', //存储找回密码的参数
	sendMsg: function(settings) {
		let btn = settings.btn; //按钮id
		let time = settings.time; //倒计时时间
		let mobile = settings.mobile; //手机号

		if (btn && /^[0-9]*$/.test(time) && base.isSendTrue) {
			base.isSendTrue = false;
			base.Ajax({
				onSite: 1, //标识站内还是站外 1：站外
				type: 'post',
				url: app_config.BUS_API_URL + 'business/forget_pwd',
				data: {
					mobile: mobile
				}
			}, function(data) {
				if (data.code == '0000') {
					// 						layer.msg('短信发送成功', {
					// 							icon: 1
					// 						});
					base.findPwdUserInfo = data.data;

					$(btn).addClass('am-disabled');
					var countdown = setInterval(function() {
						time -= 1;
						$(btn).val(time + 's 后重新发送');
						if (time == 0) { //倒计时结束
							clearInterval(countdown);
							$(btn).removeClass('am-disabled');
							$(btn).val('重新发送');
							base.isSendTrue = true;
						}
					}, 1000);

				}
			})
		}
	},
	/**
	 * 文件上传 formatImg:格式验证  formatSize:文件大小验证
	 */
	formatImg: function(n) {
		var t = '.jpg.JPG';
		if (t.indexOf(n) == -1) {
			return true;
		} else {
			return false;
		}
	},
	formatSize: function(size, max) {
		var maxSize = max * 1024 * 1024;
		return size <= maxSize ? true : false;
	},
	uploadImgFn: function(file, callback, n) {
		var fileType = file.name.split('.');
		fileType = fileType[fileType.length - 1];

		if (!base.formatSize(file.size, 3)) {
			layer.msg('上传文件不能大于3M');
			return false;
		}

		if (base.formatImg(fileType)) {
			layer.msg(file.name + '，未允许文件格式，请上传.jpg格式的文件');
			return false;
		}


		var fd = new FormData();
		fd.append("file", file);
		fd.append("token", base.userInfo().token);

		$.ajax({
			url: WEB_CONFIG.API_URL + 'api/wy/upload/file',
			type: "POST",
			async: false,
			data: fd,
			cache: false,
			processData: false,
			contentType: false,
			xhr: function() {
				var myXhr = $.ajaxSettings.xhr();
				return myXhr; //xhr对象返回给jQuery使用  
			},
			success: function(data) {
				if (data.retcode == 0) {
					if (callback && typeof callback == 'function') {
						callback(data);
					}
				} else {
					base.layer.msg(data.retmsg);
				}
			}
		}).always(function() {
			$('#upFile').empty().remove();
		});
	},
	uploadFileFn: function(n, callback) {
		var _self = this;
		if (n == 1) {
			$('body').append('<input id="upFile" type="file"/>');
		} else {
			$('body').append('<input id="upFile" type="file" style="display:none;" multiple="multiple"/>')
		}

		$('#upFile').off('change').on('change', function(e) {
			//支持 FileReader
			if (window.FileReader) {
				if (e.target.files.length > 1) { //批量
					for (var i = 0; i < e.target.files.length; i++) {
						var file = e.target.files[i];
						_self.uploadImgFn(file, function(data) {
							if (callback && typeof callback == 'function') {
								callback(data.data)
							}
						}, n);
					}
				} else { //单张
					var file = e.target.files[0];
					_self.uploadImgFn(file, function(data) {
						if (callback && typeof callback == 'function') {
							callback(data.data)
						}
					}, n);
				}
			} else {
				$('#upFile').empty().remove();
				// layer.msg('上传失败，');
			}
			return false;
		});
		setTimeout(function() {
			alert(0);
			$('#upFile').click();
		}, 0);
		return false;
	}

};
