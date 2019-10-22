package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import entity.Product;

/**
 * Servlet implementation class ListProdServlet
 */
@WebServlet("/ListProdServlet")
public class ListProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao dao = new ProductDao();
		List<Product> list = dao.findAll();
		response.setContentType("text/html;charset=utf-8");
		String html = "";

		html += "<html>";
		html += "<head><title>查看当前列表</title></head>";
		html += "<body>";
		html += "<table border='1' align='center' width='600px'>";
		html += "<tr><th>±àºÅ</th><th>ÉÌÆ·Ãû³Æ</th><th>ÉÌÆ·ÐÍºÅ</th><th>¼Û¸ñ</th><th>²é¿´ÏêÇé</th></tr>";
		// 2.1 ±éÀúËùÓÐµÄÉÌÆ·£¬ÓÐ¼¸¸öÉÌÆ·ÓÐ¼¸ÐÐÊý¾Ý
		if (list != null) {
			for (Product product : list) {
				html += "<tr>";
				html += "<td>" + product.getId() + "</td>";
				html += "<td>" + product.getName() + "</td>";
				html += "<td>" + product.getType() + "</td>";
				html += "<td>" + product.getPrice() + "</td>";
				// ·ÃÎÊDetailProdServletÍ¬Ê±´«µÝÒ»¸öÃû³ÆÎªidµÄ²ÎÊý
				html += "<td><a href='" + request.getContextPath() + "/DetailProdServlet?id=" + product.getId()
						+ "'>查看详情</a></td>";
				html += "</tr>";
			}
		}

		html += "</table>";
		// 3)ÏÔÊ¾ä¯ÀÀ¹ýµÄÉÌÆ·
		html += "<hr/>";
		html += "×î½üä¯ÀÀ¹ýµÄÉÌÆ·:<br/>";
		// 3.1)´ÓcookieÈ¡³öprodHist
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("prodHist")) {
					String prodHist = cookie.getValue(); // °üº¬ÉÌÆ·±àºÅ ¡£ 3,2,1
					String[] ids = prodHist.split(",");
					for (String id : ids) {// È¡³öÃ¿¸öÉÌÆ·±àºÅ
						// Ê¹ÓÃÉÌÆ·±àºÅ²éÑ¯¶ÔÓ¦µÄÉÌÆ·
						Product p = dao.findById(id);
						// ÏÔÊ¾ÉÌÆ·ÐÅÏ¢
						html += "" + p.getId() + "&nbsp;" + p.getName() + "&nbsp;" + p.getPrice() + "<br/>";
					}
					break;
				}
			}
		}

		html += "</body>";
		html += "</html>";

		response.getWriter().write(html);

	}

}
