package hoggen.wang;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) 只能接收get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 请求数据
		 */
		// m1(request);

		// 请求头
		// m2(request);
		// get获取参数
		// getParams(request);
		// 通用获取参数

		showAllParams(request);

		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		// 想浏览器输出
		response.getWriter().write("当前时间为:  " + new Date());
		// response.getWriter().append("Served at: ").append(request.getContextPath());
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
