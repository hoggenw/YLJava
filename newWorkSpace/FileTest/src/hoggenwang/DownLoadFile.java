package hoggenwang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownLoadFile
 */
@WebServlet("/DownLoadFile")
public class DownLoadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownLoadFile() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String fileName = "美女.jpg";

		// FileInputStream inputStream = new FileInputStream(
		// this.getServletContext().getRealPath("/downLoad/" + fileName));

		InputStream inputStream2 = this.getServletContext().getResourceAsStream("/downLoad/" + fileName);
		System.out.println(this.getServletContext().getRealPath("/downLoad/" + fileName));

		fileName = URLEncoder.encode(fileName, "utf-8");
		// 从数据拿信息
		DataBaseManager manager = new DataBaseManager();
		FileModel model = manager.findById(1);
		fileName = model.getName();
		FileInputStream inputStream = new FileInputStream(new File(model.getFile_path()));

		/**
		 * s设置一个响应头，content-disposition告诉浏览器以下载方式打开文件
		 */
		String userAgent = request.getHeader("user-agent");
		String content = "";
		if (userAgent.contains("Trident")) { // 判断是否是ie
			// IE
			content = "attachment;filename=" + fileName;
		} else {
			// ·ÇIE
			content = "attachment;filename*=" + fileName;
		}
		// 下载方式
		// response.setHeader("content-disposition", content);

		// response.setContentType("image/jpeg;charset=utf-8");
		OutputStream outputStream = response.getOutputStream();
		// 写出给浏览器(字节内容)
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buf)) != -1) {
			outputStream.write(buf, 0, len);

		}
		inputStream.close();
		outputStream.close();

		System.out.println("完成");
		// response.getWriter().write("下载接口请求成功");
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
