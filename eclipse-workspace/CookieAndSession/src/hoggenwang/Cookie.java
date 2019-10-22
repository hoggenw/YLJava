package hoggenwang;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie
 */
@WebServlet("/Cookie")
public class Cookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Cookie() {
		// TODO Auto-generated constructor stub
	}

	public Cookie(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		/**
		 * cookie
		 */
		// 1）创建Cookie对象，用于保存会话数据
		// new Cookie(java.lang.String name, java.lang.String value)
		javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("name", "waterStrong");
		javax.servlet.http.Cookie cookieS = new javax.servlet.http.Cookie("email", "hoggenWang@qq.com");
		String nameP = "王大大";
		javax.servlet.http.Cookie cookieF = new javax.servlet.http.Cookie("realName", nameP);

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
		String dateString = formatter.format(currentTime);
		System.out.println(dateString);
		javax.servlet.http.Cookie cookieT = new javax.servlet.http.Cookie("customdDate", dateString);

		// 2）设置Cookie对象
		// void setPath(java.lang.String uri) 设置cookie有效路径,如果cookie在有效路径下，那么浏览器在下次访问该有效
		// 路径时就会带着cookie数据到服务器
		// cookie.setPath("/Cookie");
		// void setMaxAge(int expiry) 设置cookie的有效时长
		// 正整数： cookie数据保存在浏览器的缓存目录中（硬盘中）。例如10。10秒之后就cookie就会丢失。（从最后一次访问cookie开始计算）
		// 负整数： cookie数据保存在浏览器的内存中，浏览器关闭cookie数据就会丢失。
		// 零： 删除同名的cookie
		// cookie.setMaxAge(5);

		// void setValue(java.lang.String newValue) 设置cookie的值
		// cookie.setValue("name");

		// 3）发送Cookie数据到浏览器保存,通过响应头携带cookie数据给浏览器（set-cookie）

		// response.setHeader("set-cookie", "name=hoggen");
		response.addCookie(cookie);
		response.addCookie(cookieS);
		response.addCookie(cookieF);
		response.addCookie(cookieT);

		// 4）接收浏览器发送的Cookie数据
		// Cookie[] request.getCookies()
		// String name = request.getHeader("cookie");
		// System.out.println(name);

		javax.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (javax.servlet.http.Cookie cookie2 : cookies) {
				String name = cookie2.getName();
				String value = cookie2.getValue();
				System.out.println(name + " : " + value);
				if (name.equals("customdDate")) {
					response.getWriter().write("欢迎访问本网站，上次访问时间为：" + value + "  目前时间为： " + dateString);
				}

			}
		} else {
			System.out.println("没有cookie 信息");
			response.getWriter().write("欢迎首次访问本网站，当前时间为：" + dateString);
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
