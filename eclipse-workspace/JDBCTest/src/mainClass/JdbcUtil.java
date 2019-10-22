package mainClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class JdbcUtil {

	private static String url = "jdbc:mysql://localhost:3306/hoggen";
	// jdbc协议+数据库协议：//主机地址+端口+链接的数据库
	private static String user = "root";
	private static String password = "12345678";
	private static String driverClass = "com.mysql.cj.jdbc.Driver";
	private static BasicDataSource bDataSource = null;

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
			// * 这个斜杠代表类路径的根目录
			// */
			// InputStream in = clazz.getResourceAsStream("/db.properties");
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

	public static BasicDataSource getDataSource() {
		if (bDataSource == null) {
			bDataSource = new BasicDataSource();
			bDataSource.setUrl(url);
			bDataSource.setUsername(user);
			bDataSource.setPassword(password);
			bDataSource.setDriverClassName(driverClass);
			// 设置链接参数
			bDataSource.setInitialSize(5);// 初始链接数
			bDataSource.setMaxTotal(8);// 最大连接数
			bDataSource.setMaxWaitMillis(3000);// 超过最大连接数时，最大等待时间
			bDataSource.setMaxIdle(3000);// 最大空闲时间
		}
		return bDataSource;
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
