package hoggenwang;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TargetServlet(目标资源)
 */
@WebServlet("/TargetServlet")
public class TargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TargetServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("执行目标servlet");
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 3000; i++) {
			stringBuffer.append(":美女:");

		}
		// 压缩前
		System.out.println("压缩前大小：" + stringBuffer.toString().getBytes().length);
		// // 进行压缩gzip
		// // 创建临时的字节数组容器
		// ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// // 创建GZIPOutputStream对象
		// GZIPOutputStream gzipOutputStream = new
		// GZIPOutputStream(byteArrayOutputStream);
		// // 开始写出压缩内容
		// gzipOutputStream.write(stringBuffer.toString().getBytes());
		// // 刷新缓冲区
		// gzipOutputStream.finish();
		// // 从临时的字节数组容器中得到压缩后的网页内容
		// byte[] result = byteArrayOutputStream.toByteArray();
		//
		// System.out.println("压缩后的数据大小：" + result.length);
		// /**
		// * 注意：告诉浏览器数据压缩格式 发送响应头：content-encoding:gzip
		// */
		// response.setHeader("content-encoding", "gzip");
		//
		// response.getOutputStream().write(result);

		response.getWriter().write(stringBuffer.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
