package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtil {

	public XMLUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ¶ÁÈ¡xmlÎÄ¼þ£¬·µ»Ødocument¶ÔÏó
	 */
	public static Document getDocument() {
		try {
			Document doc = new SAXReader()
					.read(new File("/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/product.xml"));
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			// °Ñ×ª»»ÎªÔËÐÐÊ±Òì³£Å×³ö¼´¿É£¡
			throw new RuntimeException(e);
		}
	}

	/**
	 * ´«Èçdocuemnt¶ÔÏó£¬Ð´³öµ½xmlÎÄ¼þÖÐ
	 */
	public static void write2xml(Document doc) {
		try {
			OutputStream out = new FileOutputStream(
					"/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/product.xml");
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			// °Ñ×ª»»ÎªÔËÐÐÊ±Òì³£Å×³ö¼´¿É£¡
			throw new RuntimeException(e);
		}
	}

}
