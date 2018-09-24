package hoggen.wang;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequstDemo
 */
@WebServlet("/RequstDemo")
public class RequstDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequstDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 读取Properties文件
		 */
		// 当前目录的位置
		// File file = new File(".");
		// System.out.println(file.getAbsolutePath());
		//
		// FileInputStream inStream = new FileInputStream(
		// "/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/ServetTest/WebContent/news.properties");
		// Properties properties = new Properties();
		// properties.load(inStream);
		// System.out.println(properties.getProperty("name"));
		// System.out.println(properties.getProperty("password"));

		/**
		 * 转发
		 */
		// String name = (String) request.getAttribute("name");
		// System.out.println(name);

		/**
		 * 域对象
		 */
		//
		// ServletContext servletContext = this.getServletContext();
		// String file = (String) servletContext.getAttribute("butilfulGirl");
		// System.out.println(file);

		// // 读取本地文件
		// FileInputStream inputStream = new FileInputStream(file);
		// // 写出给浏览器(字节内容)
		// OutputStream outputStream = response.getOutputStream();
		// byte[] buf = new byte[1024];
		// int len = 0;
		// while ((len = inputStream.read(buf)) != -1) {
		// outputStream.write(buf, 0, len);
		//
		// }
		// inputStream.close();
		// outputStream.close();

		/**
		 * 请求重定向 【location + 302】
		 */
		// 设置302状态码 浏览器拿到302会再次请求location指向的地址
		// response.setStatus(302);
		// // 设置location响应头
		// response.setHeader("location", "/ServetTest/index.html");
		// // TODO Auto-generated method stub

		/**
		 * 请求来源
		 */
		// String referer = request.getHeader("referer");
		// System.out.println("referer: " + referer);
		// // 下载资源文件
		// response.getWriter().append("下载 Served at:
		// ").append(request.getContextPath());

		javax.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (javax.servlet.http.Cookie cookie2 : cookies) {
				String name = cookie2.getName();
				String value = cookie2.getValue();
				System.out.println(name + " : " + value);
			}
		} else {
			System.out.println("没有cookie 信息");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
