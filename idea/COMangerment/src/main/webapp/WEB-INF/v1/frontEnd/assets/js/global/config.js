const app_config = {
	STATIC_V: '20201129', 		//版本号
	API_URL:'http://192.168.20.9:8888/',					//api请求地址
	STATIC_URL: function(url) { //静态文件地址
		return
	}
}

seajs.config({
	alias: {
		'page': 'page.js',//分页
		'md5': 'md5.js',
		'sha256': 'sha256.js',
	},
	map: [
		//可配置版本号
		['.css', '.css?v=' + app_config.STATIC_V],
		['.js', '.js?v=' + app_config.STATIC_V]
	],
	//编码
	charset: 'utf-8'
});
