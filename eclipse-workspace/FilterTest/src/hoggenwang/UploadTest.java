package hoggenwang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class UploadTest
 */
@WebServlet("/UploadTest")
public class UploadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadTest() {
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
		// TODO Auto-generated method stub
		// doGet(request, response);

		System.out.println("进入post方法sssss");
		// uploadFileSelf(request);

		// 工具
		// DiskFileItemFactory类： 用于设置上传文件的缓存大小和缓存目录。
		// ServletFileUpluad类： 用于解析上传的所有文件
		// List<FileItem> parserRequest(HttpSevletRequest request): 解析上传的文件
		//
		// FileItem对象： 代表一个上传的文件
		// 文件名称
		// 大小
		// 类型
		// 内容
		// ....

		/**
		 * 参数一： 表示文件缓存区的大小。如果上传的文件没有超过缓存区大小，则文件不缓存；否则缓存文件，缓存到临时目录。（byte） 参数二：
		 * 表示缓存区的临时目录。
		 */
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory(10 * 1024,
					new File("/Users/wangliugen/Desktop/temp"));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");

			List<FileItem> list = upload.parseRequest(request);
			int count = list.size();
			System.out.println("====1===" + count);
			if (list != null) {
				System.out.println("====2");
				FileItem item = list.get(0);

				String name = item.getName();

				long size = item.getSize();

				String contentType = item.getContentType();

				InputStream in = item.getInputStream();

				FileUtils.copyInputStreamToFile(in, new File("/Users/wangliugen/Desktop/" + name));

				item.delete();

				System.out.println(name);
				System.out.println(size);
				System.out.println(contentType);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("发现错误");
			e.printStackTrace();
		}

		System.out.println("write dwon");
	}

	private void uploadFileSelf(HttpServletRequest request) throws IOException {
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
