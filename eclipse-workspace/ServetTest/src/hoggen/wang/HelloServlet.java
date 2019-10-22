package hoggen.wang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {

		super();
		System.out.println("HelloServlet对象创建");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		System.out.println("HelloServlet初始化");
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);

		System.out.println("HelloServlet服务方法");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("HelloServlet销毁方法");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) 只能接收get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		/**
		 * 请求数据
		 */
		// m1(request);

		// 请求头
		// m2(request);
		// get获取参数
		// getParams(request);
		// 通用获取参数

		// showAllParams(request);

		// response.setContentType("image/png;charset=utf-8");
		// TODO Auto-generated method stub
		// 修改响应行
		// 设置状态码
		// response.setStatus(404);//404但是不影响资源索取
		// response.sendError(404);// 404+错误页面

		// 修改响应头
		// response.setHeader("server", "hoggen");

		// 修改响应实体
		// 方法一 字符流
		// response.getWriter().write("当前时间为: " + new Date());
		// 方法二 字节流
		// response.getOutputStream().write(("当前时间为: " + new Date()).getBytes());

		/**
		 * 定时刷新
		 */
		// 定时2秒刷新页面
		// response.setHeader("refresh", "2");

		// response.getWriter().write("3秒之后会跳转主页");
		// response.setHeader("refresh", "3;/ServetTest/index.html");

		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// 获取上下文
		ServletContext servletContext = this.getServletContext();
		// 获取上下文路径
		// 得到web应用的上下文路径
		// java.lang.String getContextPath()

		// contextPath(response, servletContext);

		// 得到web应用的全局参数（所有servlet有效的！！）
		// java.lang.String getInitParameter(java.lang.String name)
		// java.util.Enumeration getInitParameterNames()
		// 这是服务器全局变量
		// initParameter(servletContext);

		//
		// 域对象相关的方法
		// void setAttribute(java.lang.String name, java.lang.Object object) 保存数据
		// java.lang.Object getAttribute(java.lang.String name) 得到数据
		// void removeAttribute(java.lang.String name) 清除数据

		// servletContext.setAttribute("butilfulGirl", "hoggen");
		// System.out.println("存取成功");

		//
		// 转发相关的
		// RequestDispatcher getRequestDispatcher(java.lang.String path)
		// RequestDispatcher rDispatcher =
		// servletContext.getRequestDispatcher("/index.html");
		// //可以吧数据保存到request中
		// request.setAttribute("name", "hoggen");
		//
		// rDispatcher.forward(request, response);

		//
		// web应用读取资源文件
		// java.lang.String getRealPath(java.lang.String path)
		// java.io.InputStream getResourceAsStream(java.lang.String path)
		// 1）web应用内部资源。在当前web应用中的资源
		// 2）其他web应用资源。在同一个站点下的其他web应用的资源。
		// 3）站点外的资源。其他站点下的资源。
		// String path = this.getServletContext().getRealPath("/news.properties");
		// System.out.println(path);
		// FileInputStream inStream = new FileInputStream(new File(path));
		FileInputStream inStream = (FileInputStream) this.getServletContext().getResourceAsStream("/news.properties");

		Properties properties = new Properties();
		properties.load(inStream);
		System.out.println(properties.getProperty("name"));
		System.out.println(properties.getProperty("password"));

		/**
		 * 告诉浏览器以下载方式打开
		 */
		downLoadImage(response);

	}

	private void initParameter(ServletContext servletContext) {
		Enumeration<String> enumeration = servletContext.getInitParameterNames();
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();
			String value = servletContext.getInitParameter(name);
			System.out.println(name + " : " + value);
		}
	}

	private void contextPath(HttpServletResponse response, ServletContext servletContext) throws IOException {
		String path = servletContext.getContextPath();
		// 是tomcat服务器的运行路径，不是项目的名称
		System.out.println(path);
		// response.sendRedirect("/ServetTest/index.html");
		// 可以让文件路径更加灵活
		response.sendRedirect(servletContext.getContextPath() + "/index.html");
	}

	private void downLoadImage(HttpServletResponse response) throws FileNotFoundException, IOException {
		File file = new File(
				"/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/ServetTest/WebContent/img/detail01.jpg");
		// 下载打开方式设置
		// response.setHeader("Content-Disposition", " attachment; filename=" +
		// file.getName());

		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(file);
		// 写出给浏览器(字节内容)
		OutputStream outputStream = response.getOutputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buf)) != -1) {
			outputStream.write(buf, 0, len);

		}
		inputStream.close();
		outputStream.close();
	}

	private void showAllParams(HttpServletRequest request) {
		Enumeration<String> enums = request.getParameterNames();

		System.out.println(enums);
		while (enums.hasMoreElements()) {
			String headerName = enums.nextElement();
			String headerValue = request.getParameter(headerName);
			System.out.println(headerName + " : " + headerValue);

		}
		System.out.println("============entryset========================");
		Map<String, String[]> map = request.getParameterMap();
		System.out.println(map);
		// entryset,获取键值对对象的set集合
		Set<Entry<String, String[]>> entryset = map.entrySet();
		for (Entry<String, String[]> entry : entryset) {
			String key = entry.getKey();
			String[] value = entry.getValue();
			System.out.println(key + " : " + value[0]);
		}
		// 获取所以keys
		System.out.println("============keyset========================");
		Set<String> keyset = map.keySet();
		for (String string : keyset) {
			//
			String value = map.get(string)[0];
			System.out.println(keyset + " : " + value);
		}

		// 获取所以values
		System.out.println("============values========================");
		Collection<String[]> valuse = map.values();
		for (String[] value : valuse) {
			System.out.println(" value: " + value[0]);
		}

	}

	private void getParams(HttpServletRequest request) {
		String params = request.getQueryString();
		System.out.println(params);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		// TODO Auto-generated method stub
		// post参数实体内容
		// getPostPramas(req);
		// 通用获取参数
		// super.doPost(req, resp);
		// 通用获取参数
		// Map<String, String[]> map = request.getParameterMap();
		showAllParams(req);
	}

	private void getPostPramas(HttpServletRequest req) throws IOException {
		InputStream inputStream = req.getInputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buf)) != -1) {
			String string = new String(buf, 0, len);
			System.out.println(string);
		}
	}

	private void m2(HttpServletRequest request) {
		// System.out.println("/请求头 host: " + request.getHeader("Host"));
		System.out.println("请求all头 ");
		Enumeration<String> enums = request.getHeaderNames();
		while (enums.hasMoreElements()) {
			String headerName = enums.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println(headerName + " : " + headerValue);

		}
	}

	private void m1(HttpServletRequest request) {
		// 请求行
		// 请求方式
		System.out.println("/请求方式: " + request.getMethod());
		// 请求资源
		System.out.println("URI: " + request.getRequestURI());
		System.out.println("URL: " + request.getRequestURL());
		// 协议版本
		System.out.println("/请求方式: " + request.getProtocol());
	}

}
