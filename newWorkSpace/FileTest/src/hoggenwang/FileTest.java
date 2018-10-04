package hoggenwang;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class FileTest
 */
@WebServlet("/FileTest")
public class FileTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileTest() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		//
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory(10 * 1024,
					new File("/Users/wangliugen/Desktop/temp"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			upload.setFileSizeMax(3 * 1024 * 1024);
			upload.setSizeMax(3 * 3 * 1024 * 1024);
			upload.setProgressListener(new MyProgressListener());

			ArrayList<FileItem> list = (ArrayList<FileItem>) upload.parseRequest(request);

			if (list != null) {
				for (FileItem item : list) {

					if (item.isFormField()) {// 除file以外的其他数据
						System.out.println("非文件单位" + item.getString() + " : " + item.getFieldName());
					} else {

						String contentType = item.getContentType();
						if (!contentType.contains("image/")) {
							throw new Exception("上传文件格式不是图片格式");
						}
						String uuid = UUID.randomUUID().toString();
						String fileName = item.getName();
						// 后缀名
						;
						String newFileName = uuid + fileName.substring(fileName.lastIndexOf("."));

						String baseFile = "/Users/wangliugen/Desktop/";
						String finalPath = baseFile + newFileName;

						File file = new File(finalPath);
						if (!file.exists()) {
							FileUtils.copyInputStreamToFile(item.getInputStream(), file);
						}
						item.delete();
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("接口请求成功");
	}

}

class MyProgressListener implements ProgressListener {

	@Override
	public void update(long arg0, long arg1, int arg2) {
		// TODO Auto-generated method stub
		System.out.println("目前上传了多少字节： " + arg0 + "  总长度（字节）： " + arg1 + " 目前正在上传第" + arg2 + "个文件");
	}

}
