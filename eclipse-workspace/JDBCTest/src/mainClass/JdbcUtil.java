package mainClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	private static String url = "jdbc:mysql://localhost:3306/hoggen";
	// jdbc协议+数据库协议：//主机地址+端口+链接的数据库
	private static String user = "root";
	private static String password = "12345678";
	private static String driverClass = "com.mysql.cj.jdbc.Driver";

	public JdbcUtil() {
		// TODO Auto-generated constructor stub
	}

	static {

		try {

			// Properties prop = new Properties();
			// // ¹¹ÔìÊäÈëÁ÷
			// /**
			// *
			// */
			// // 1)
			// Class clazz = JdbcUtil.class;
			// // 2)
			// /**
			// *
			// */
			// InputStream in = clazz.getResourceAsStream("/jdbc.properties");
			// // 2)¼ÓÔØÎÄ¼þ
			// prop.load(in);
			// // 3)¶ÁÈ¡ÎÄ¼þÄÚÈÝ
			// url = prop.getProperty("url");
			// user = prop.getProperty("user");
			// password = prop.getProperty("password");
			// driverClass = prop.getProperty("driverClass");
			//
			// System.out.println(url);
			// System.out.println(user);
			// System.out.println(password);
			// System.out.println(driverClass);

			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void close(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}
