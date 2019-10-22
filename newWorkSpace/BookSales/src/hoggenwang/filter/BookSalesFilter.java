package hoggenwang.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class BookSalesFilter
 */
@WebFilter("/BookSalesFilter")
public class BookSalesFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public BookSalesFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		// post提交问题
		request.setCharacterEncoding("utf-8");
		// response.setCharacterEncoding("charset=utf-8");
		// MyServletRequest myRequest = new MyServletRequest((HttpServletRequest)
		// request);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

/**
 * @see HttpServletResponse装饰类
 */

class MyServletRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public MyServletRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getParameter(String name) {
		try {
			/**
			 * GETÌá½»²ÎÊýÎÊÌâ
			 */
			// Ô­À´µÄ
			String value = this.request.getParameter(name);
			if (value != null) {
				if ("GET".equals(this.request.getMethod())) {
					value = new String(value.getBytes("iso-8859-1"), "utf-8");
				}
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
