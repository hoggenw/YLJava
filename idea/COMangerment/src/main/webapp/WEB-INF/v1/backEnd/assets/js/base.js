define(function(require, exports, module) {

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
				layer.msg('登录已过期!!!', {
					icon: 2
				});
				setTimeout(function() {
					window.location.href = "/manager/login";
					return false;
				}, 1000);
			} else {
				Info = JSON.parse(localStorage.getItem('userInfo'));
				$('#showUserName').text(Info.name);
			}
			return Info;
		},
		Ajax: function(parameter, successCallback, errorCallback) {
			let t = parameter.type.toUpperCase();
			if (t) {
				var onSite = parameter.onSite; //站内还是站外
				var ajaxParameter = {
					type: t, //HTTP请求类型
					url: parameter.url,
					data: !parameter.data ? {} : parameter.data,
					async: false,
					dataType: 'json', //服务器返回json格式数据
					timeout: 10000, //超时时间设置为10秒；
					headers: {
						'X-Requested-With': 'XMLHttpRequest',
					},
					success: function(data) {
						if (data) {
							if (data.ErrorCode == '1001') {
								layer.msg('登录已过期!!!', {
									icon: 2
								});
								setTimeout(function() {
									window.location.href = "/manager/login";
									return false;
								}, 800);
							} else {
								successCallback(data);
							}
						} else {
							console.info(data);
						}
					},
					error: function(error) {
						if (typeof errorCallback === 'function') {
							errorCallback(error);
						} else {
							console.info(error);
							layer.msg('error:' + error);
						}
					}
				}
                //
				// if (!onSite) { //站内
				// 	ajaxParameter.headers.token = base.userInfo().token;
				// }

				$.ajax(ajaxParameter);
			} else {
				layer.alert("请求type错误！");
			}
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
		 * 数字转汉字
		 */
		Utils: {
			/*
			    单位
			*/
			units: '个十百千万@#%亿^&~',
			/*
			    字符
			*/
			chars: '零一二三四五六七八九',
			/*
			    数字转中文
			    @number {Integer} 形如123的数字
			    @return {String} 返回转换成的形如 一百二十三 的字符串            
			*/
			numberToChinese: function(number) {
				var a = (number + '').split(''),
					s = [],
					t = this;
				if (a.length > 12) {
					throw new Error('too big');
				} else {
					for (var i = 0, j = a.length - 1; i <= j; i++) {
						if (j == 1 || j == 5 || j == 9) { //两位数 处理特殊的 1*
							if (i == 0) {
								if (a[i] != '1') s.push(t.chars.charAt(a[i]));
							} else {
								s.push(t.chars.charAt(a[i]));
							}
						} else {
							s.push(t.chars.charAt(a[i]));
						}
						if (i != j) {
							s.push(t.units.charAt(j - i));
						}
					}
				}
				//return s;
				return s.join('').replace(/零([十百千万亿@#%^&~])/g, function(m, d, b) { //优先处理 零百 零千 等
					b = t.units.indexOf(d);
					if (b != -1) {
						if (d == '亿') return d;
						if (d == '万') return d;
						if (a[j - b] == '0') return '零'
					}
					return '';
				}).replace(/零+/g, '零').replace(/零([万亿])/g, function(m, b) { // 零百 零千处理后 可能出现 零零相连的 再处理结尾为零的
					return b;
				}).replace(/亿[万千百]/g, '亿').replace(/[零]$/, '').replace(/[@#%^&~]/g, function(m) {
					return {
						'@': '十',
						'#': '百',
						'%': '千',
						'^': '十',
						'&': '百',
						'~': '千'
					} [m];
				}).replace(/([亿万])([一-九])/g, function(m, d, b, c) {
					c = t.units.indexOf(d);
					if (c != -1) {
						if (a[j - c] == '0') return d + '零' + b
					}
					return m;
				});
			}
		},

	};
	module.exports = base;
});
