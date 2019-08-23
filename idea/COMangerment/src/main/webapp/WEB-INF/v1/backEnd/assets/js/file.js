const app_config = {
	STATIC_V : '20181130', // 版本号
	API_URL : '/', // api请求地址 192.168.10.140:8091
	STATIC_URL : function(url) { // 静态文件地址
		return '/backEnd/assets/js/' + url + '.js?v=' + this.STATIC_V
	},
}

seajs.config({
	alias : {
		'base' : app_config.STATIC_URL("base"),
		'page' : app_config.STATIC_URL("page"),
	},
	map : [
	// 可配置版本号
	[ '.css', '.css?v=' + app_config.STATIC_V ],
			[ '.js', '.js?v=' + app_config.STATIC_V ] ],
	// 编码
	charset : 'utf-8'
});
document.write('<script src="' + app_config.STATIC_URL("vue")
		+ '" charset="utf-8"></script>');
document.write('<script src="' + app_config.STATIC_URL("jquery.min")
		+ '" charset="utf-8"></script>');

document.write('<script src="' + app_config.STATIC_URL("amazeui.min")
		+ '" charset="utf-8"></script>');
