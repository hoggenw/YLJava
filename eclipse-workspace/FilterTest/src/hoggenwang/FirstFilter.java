package hoggenwang;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Servlet Filter implementation class FirstFilter
 */
@WebFilter("/FirstFilter")
public class FirstFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public FirstFilter() {
		// TODO Auto-generated constructor stub
		System.out.println("创建过滤器对象");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("销毁过滤器对象");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/**
		 * 关于权限 ，主要是登录后存入session，判断是否登录，区分权限
		 * 
		 */

		// TODO Auto-generated method stub
		// place your code here
		System.out.println("执行申请过滤任务");
		// pass the request along the filter chain 放行
		// response.setCharacterEncoding("utf-8");
		// doFilter(ServletRequest request, ServletResponse response)：执行过滤器链中的下一个过滤器，
		// 如果没有下一个过滤器则执行目标资源。
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 创建自定义对象替换ServletResponse
		MyServletResponse myServletResponse = new MyServletResponse((HttpServletResponse) response);

		chain.doFilter(request, myServletResponse);

		System.out.println("执行响应过滤任务");
		// 过滤响应 response无法获取实体内容

		// 进行压缩gzip
		// 创建临时的字节数组容器
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 创建GZIPOutputStream对象
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
		// 开始写出压缩内容
		char[] content = myServletResponse.getCharArrayWriter();
		gzipOutputStream.write(new String(content).getBytes());
		// 刷新缓冲区
		gzipOutputStream.finish();
		// 从临时的字节数组容器中得到压缩后的网页内容
		byte[] result = byteArrayOutputStream.toByteArray();

		System.out.println("压缩后的数据大小：" + result.length);
		/**
		 * 注意：告诉浏览器数据压缩格式 发送响应头：content-encoding:gzip
		 */

		myServletResponse.setHeader("content-encoding", "gzip");

		response.getOutputStream().write(result);
	}

	/**
	 * @see Filter#init(FilterConfig) 封装了初始化参数
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("初始化方法");
		System.out.println(fConfig.getInitParameter("AAA"));
	}

}

/**
 * @see HttpServletResponse装饰类
 */

class MyServletResponse extends HttpServletResponseWrapper {
	private HttpServletResponse response;
	// 定义一个缓冲对象
	private CharArrayWriter charArrayWriter = new CharArrayWriter();

	public MyServletResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see 提供一个返回CharArrayWriter
	 */
	public char[] getCharArrayWriter() {
		return charArrayWriter.toCharArray();
	}

	/**
	 * @see 重写gerweiter方法，让其返回一个带缓存的printwreiter
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		/**
		 * @see CharArrayWriter 的printwriter
		 */
		return new PrintWriter(charArrayWriter);
	}

}
