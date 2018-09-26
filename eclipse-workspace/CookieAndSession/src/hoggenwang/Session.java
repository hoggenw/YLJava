package hoggenwang;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Session
 */
@WebServlet("/Session")
public class Session extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * 1）创建或得到Session对象
		 */
		// HttpSession getSession()
		// HttpSession getSession(boolean create)
		HttpSession session = request.getSession();

		/**
		 * 2）session对象操作会话数据（域对象）
		 * 
		 */
		// void setAttribute(java.lang.String name, java.lang.Object value) 保存数据
		// java.lang.Object getAttribute(java.lang.String name) 得到数据
		// void removeAttribute(java.lang.String name) 清除数据
		String name = (String) session.getAttribute("name");
		System.out.println(name);
		session.setAttribute("name", "hoggenWang");

		/**
		 * 3）设置session对象
		 * 
		 */
		// void setMaxInactiveInterval(int interval) 设置session有效时长
		// java.lang.String getId() 得到session对象编号
		// void invalidate() 销毁session对象
		// session.setMaxInactiveInterval(20);

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
