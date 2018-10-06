package hoggenwang.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class JDBCUtil {
	private static String url = "jdbc:mysql://localhost:3306/hoggen";
	// jdbc协议+数据库协议：//主机地址+端口+链接的数据库
	private static String user = "root";
	private static String password = "12345678";
	private static String driverClass = "com.mysql.cj.jdbc.Driver";
	private static BasicDataSource bDataSource = null;
	private static JDBCUtil instance;

	private JDBCUtil() {

	}

	static {
		System.out.println("只加载一次，创建表");
		// try {
		// JDBCUtil.getInstance();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static boolean validateTableNameExist(Connection con, String tableName) throws SQLException {
		ResultSet rs = con.getMetaData().getTables(null, null, tableName, null);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public static JDBCUtil getInstance() throws SQLException {
		if (instance == null) {
			instance = new JDBCUtil();
			BasicDataSource bDataSource = JDBCUtil.getDataSource();
			Connection conn = bDataSource.getConnection();
			if (JDBCUtil.validateTableNameExist(conn, "ImageFileDB")) {
				System.out.println("表已经被创建");
			} else {
				String sql = "CREATE TABLE ImageFileDB " + "(id INT PRIMARY KEY AUTO INCREMENT, "
						+ " name VARCHAR(255), " + " size VARCHAR(10), " + " ADDTIME DATETIME,  "
						+ " file_path VARCHAR(50)" + " info VARCHAR(50)";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.executeUpdate();
			}
		}
		return instance;
	}

	public static BasicDataSource getDataSource() throws SQLException {
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
}
