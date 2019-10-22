package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import entity.Product;

/**
 * Servlet implementation class DetailProdServlet
 */
@WebServlet("/DetailProdServlet")
public class DetailProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailProdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1)½ÓÊÕidµÄ²ÎÊý
		String id = request.getParameter("id");
		// 2)²éÑ¯Ö¸¶¨idµÄÉÌÆ·¶ÔÏó
		ProductDao dao = new ProductDao();
		Product product = dao.findById(id);
		// 3)ÏÔÊ¾µ½ä¯ÀÀÆ÷ÉÏ
		response.setContentType("text/html;charset=utf-8");
		String html = "";

		html += "<html>";
		html += "<head><title>²é¿´ÉÌÆ·ÏêÇé</title></head>";
		html += "<body>";
		html += "<table border='1' align='center' width='250px'>";
		html += "<tr><th>±àºÅ</th><td>" + product.getId() + "</td></tr>";
		html += "<tr><th>ÉÌÆ·Ãû³Æ</th><td>" + product.getName() + "</td></tr>";
		html += "<tr><th>ÉÌÆ·ÐÍºÅ</th><td>" + product.getType() + "</td></tr>";
		html += "<tr><th>¼Û¸ñ</th><td>" + product.getPrice() + "</td></tr>";
		html += "</table>";
		html += "<center><a href='" + request.getContextPath() + "/ListProdServlet'>[·µ»ØÉÌÆ·ÁÐ±í]</a></center>";
		html += "</body>";
		html += "</html>";

		response.getWriter().write(html);

		// 4)´´½¨Cookie¶ÔÏó£¬´æ·Åä¯ÀÀ¹ýµÄÉÌÆ·±àºÅ
		Cookie c = new Cookie("prodHist", getCookieValue(request, product.getId()));
		// 5)·¢ËÍµ½ä¯ÀÀÆ÷±£´æ
		response.addCookie(c);
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

	private String getCookieValue(HttpServletRequest request, String id) {
		Cookie[] cookies = request.getCookies();
		String prodHist = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("prodHist")) {
					prodHist = cookie.getValue();
					break;
				}
			}
		}
		if (cookies == null || prodHist == null) {
			// 1)Ö±½Ó·µ»Ø´«ÈëµÄid
			return id;
		}

		// 3,10 1
		// Âú×ãÁ½¸öÌõ¼þ£º£±£©·½±ãÅÐ¶ÏÔªËØÊÇ·ñÖØ¸´ 2£©·½±ãÔöÉ¾ÔªËØÄÚÈÝ Ê¹ÓÃ¼¯ºÏ£º
		// Collection coll.contains(id): ÅÐ¶ÏÖ¸¶¨ÔªËØÊÇ·ñ´æÔÚ
		// List: ArrayList LinkedList(Á´±í)
		// String-> String[]
		String[] prodHists = prodHist.split(",");
		// String->Collection
		Collection colls = Arrays.asList(prodHists);
		// Collectoin->LinkedList
		LinkedList list = new LinkedList(colls);

		if (list.size() < 3) {
			// ÓÐÖØ¸´µÄ
			if (list.contains(id)) {
				// É¾³ýÖØ¸´µÄid£¬°Ñ´«ÈëµÄid·ÅÇ°Ãæ
				list.remove(id);
				list.addFirst(id);
			} else {
				// Ö±½Ó°Ñ´«ÈëµÄid·ÅÇ°Ãæ
				list.addFirst(id);
			}
		} else {
			// ÓÐÖØ¸´µÄ
			if (list.contains(id)) {
				// É¾³ýÖØ¸´µÄid£¬°Ñ´«ÈëµÄid·ÅÇ°Ãæ
				list.remove(id);
				list.addFirst(id);
			} else {
				// É¾³ý½áÎ²µÄid£¬°Ñ´«ÈëµÄid·ÅÇ°Ãæ
				list.removeLast();
				list.addFirst(id);
			}
		}

		// List->String
		String str = "";
		for (Object obj : list) {
			str += obj + ",";
		}
		// È¥µô×îºóµÄ¶ººÅ
		str = str.substring(0, str.length() - 1);
		return str;
	}

}
