package mainClass;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * jdbc链接数据库
 */
public class DBbeginClass {
	// 链接数据库的url
	private static String url = "jdbc:mysql://localhost:3306/hoggen";
	// jdbc协议+数据库协议：//主机地址+端口+链接的数据库
	private static String user = "root";
	private static String password = "12345678";

	public DBbeginClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {

		/**
		 * 直接使用程序来链接的方法
		 */
		// connnectMethod1();
		/**
		 * 直接使用驱动管理类来关键
		 */
		// managerConnectDBMethod();

		/**
		 * 创建表
		 */
		// createTable();

		/**
		 * 插入数据
		 */
		// insertTableData();
		/**
		 * 读取数据
		 */
		// String sql = "select * from student";
		// readData(sql);

		/**
		 * 使用PreparedStatement，预编译接口，防止参数注入
		 */
		// 插入
		// String sql = "insert into student(id,name,adress) values(?,?,?)";// 参数占位
		//
		// prepareDB(sql);
		// 修改
		// String sql = "update student set name =?,adress=? where id = ?";// 参数占位
		// prepareUpdateDB(sql);
		// 删除
		// String sql = "delete from student where id=?";
		// prepareDelete(sql);
		// 查询
		// String sql = "select * from student where id=?";
		// prepareSelect(sql);

		/**
		 * 使用CallableStatement，存储过程
		 */
		// String sql = "select * from student where id=?";
		// Connection connection = null;
		// CallableStatement statement = null;
		// ResultSet resultSet = null;
		// try {
		// connection = JdbcUtil.getConnection();
		// statement = connection.prepareCall(sql);// 语法和权限的检查
		//
		// statement.setInt(1, 1);
		// // 发送参数到数据库，执行sql
		// resultSet = statement.executeQuery();
		// while (resultSet.next()) {
		// int id = resultSet.getInt("id");
		// String name = resultSet.getString("name");
		// String adress = resultSet.getString("adress");
		// System.out.println(id + "\t" + name + "\t" + adress);
		// }
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// } finally {
		// JdbcUtil.close(statement, connection);
		// }

	}

	private static void prepareSelect(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.prepareStatement(sql);// 语法和权限的检查

			statement.setInt(1, 1);
			// 发送参数到数据库，执行sql
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String adress = resultSet.getString("adress");
				System.out.println(id + "\t" + name + "\t" + adress);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(statement, connection);
		}
	}

	private static void prepareDelete(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.prepareStatement(sql);// 语法和权限的检查

			statement.setInt(1, 1);
			// 发送参数到数据库，执行sql
			int count = statement.executeUpdate();
			System.out.println("影响了" + count + "行");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(statement, connection);
		}
	}

	private static void prepareUpdateDB(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.prepareStatement(sql);// 语法和权限的检查

			statement.setString(1, "迪丽热巴");
			statement.setString(2, "新疆打断点");
			statement.setInt(3, 4);// 参数从1开始
			// 发送参数到数据库，执行sql
			int count = statement.executeUpdate();
			System.out.println("影响了" + count + "行");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(statement, connection);
		}
	}

	private static void prepareDB(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.prepareStatement(sql);// 语法和权限的检查
			statement.setInt(1, 1);// 参数从1开始
			statement.setString(2, "美女");
			statement.setString(3, "四川成都新津");
			// 发送参数到数据库，执行sql
			int count = statement.executeUpdate();
			System.out.println("影响了" + count + "行");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(statement, connection);
		}
	}

	private static void readData(String sql) {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String adress = result.getString("adress");
				System.out.println(id + "\t" + name + "\t" + adress);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(result, statement, connection);
		}
	}

	private static void insertTableData() {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 执行类中的静态代码块
			// 可以注册多个
			// DriverManager.registerDriver(driver);
			// 获取链接
			connection = DriverManager.getConnection(url, user, password);
			// 3.通过Connection对象获取Statement对象
			statement = connection.createStatement();
			// 4.准备sql语句
			String sql = "INSERT INTO superman(NAME,gender) VALUES('张三','男')";
			// 5.执行sql语句，返回结果
			int count = statement.executeUpdate(sql);

			System.out.println("影响了" + count + "行");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	private static void createTable() {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 执行类中的静态代码块
			// 可以注册多个
			// DriverManager.registerDriver(driver);
			// 获取链接
			connection = DriverManager.getConnection(url, user, password);
			// 3.通过Connection对象获取Statement对象
			statement = connection.createStatement();
			// 4.准备sql语句
			String sql = "CREATE TABLE superman(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";
			// 5.执行sql语句，返回结果
			int count = statement.executeUpdate(sql);

			System.out.println("影响了" + count + "行");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	private static void managerConnectDBMethod() throws ClassNotFoundException, SQLException {
		// 。注册驱动程序
		// Driver driver = new com.mysql.cj.jdbc.Driver();//本身类已经注册了一次驱动
		/**
		 * 反射：获取类的对象
		 */

		Class.forName("com.mysql.cj.jdbc.Driver");// 执行类中的静态代码块
		// 可以注册多个
		// DriverManager.registerDriver(driver);
		// 获取链接
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
	}

	private static void connnectMethod1() throws SQLException {
		// 1.创建驱动程序的实现类
		Driver driver = new com.mysql.cj.jdbc.Driver();
		Properties info = new Properties();
		info.setProperty("user", user);
		info.setProperty("password", password);
		// 2.链接数据库
		Connection connection = driver.connect(url, info);
		System.out.println(connection);
	}

}
