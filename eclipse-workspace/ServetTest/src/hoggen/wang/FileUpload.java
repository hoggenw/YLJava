package hoggen.wang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUpload() {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub\

		System.out.println("进入post方法");
		// 获取实体内容
		InputStream inputStream = request.getInputStream();
		// 转为字符流
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		// 读取一行
		// 文件
		String fileTag = reader.readLine();

		String name = reader.readLine();
		// 获取文件名
		String fileName = name.substring(name.indexOf("filename=\"") + 10, name.length() - 1);
		// 跳过两行
		reader.readLine();
		reader.readLine();
		// 读取文件内容

		String content = null;
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/wangliugen/Desktop/" + fileName));
		while ((content = reader.readLine()) != null) {
			// 排除掉文件的结束符号
			if (content.equals(fileTag + "--")) {
				continue;
			}
			// 写出
			writer.write(content);
			writer.flush();
		}
		writer.close();
		reader.close();
	}

}
