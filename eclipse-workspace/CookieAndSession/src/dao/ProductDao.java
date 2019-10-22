package dao;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import entity.Product;
import util.XMLUtil;

public class ProductDao {

	public ProductDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ²é¿´ËùÓÐÉÌÆ·
	 */
	public List<Product> findAll() {
		// 1)¶ÁÈ¡xmlÎÄ¼þ
		Document doc = XMLUtil.getDocument();
		// 2)¶ÁÈ¡ËùÓÐproduct±êÇ©
		List<Element> proList = doc.getRootElement().elements("product");
		List<Product> list = new ArrayList<Product>();
		// 3)±éÀú
		for (Element proElem : proList) {
			// 3.1 ´´½¨Product¶ÔÏó
			Product p = new Product();
			// 3.2 °Ñprodcut±êÇ©ÄÚÈÝ·â×°µ½Product¶ÔÏóÖÐ
			p.setId(proElem.attributeValue("id"));
			p.setName(proElem.elementText("name"));
			p.setType(proElem.elementText("type"));
			p.setPrice(proElem.elementText("price"));
			// 3.3 °Ñ·â×°ºÃµÄProduct·ÅÈëListÖÐ
			list.add(p);
		}
		return list;
	}

	/**
	 * ¸ù¾ÝÉÌÆ·±àºÅ²éÑ¯¶ÔÓ¦µÄÉÌÆ·¶ÔÏó
	 */
	public Product findById(String id) {
		// 1)¶ÁÈ¡xml
		Document doc = XMLUtil.getDocument();
		// 2)²éÑ¯Ö¸¶¨idµÄproduct±êÇ©
		Element proElem = (Element) doc.selectSingleNode("//product[@id='" + id + "']");
		// 3)·â×°Product¶ÔÏó
		Product p = null;
		if (proElem != null) {
			p = new Product();
			p.setId(proElem.attributeValue("id"));
			p.setName(proElem.elementText("name"));
			p.setType(proElem.elementText("type"));
			p.setPrice(proElem.elementText("price"));
		}
		return p;
	}

}
