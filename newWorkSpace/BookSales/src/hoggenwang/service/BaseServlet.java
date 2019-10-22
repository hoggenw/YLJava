package hoggenwang.service;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		try {
			Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		try {
			Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
