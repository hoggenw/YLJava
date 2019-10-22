package mainClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DBDeepClass {

	static int i = 0;

	public DBDeepClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		/**
		 * 缓存存入
		 * 
		 */
		// String sql = "insert into student(id,name,adress) values(?,?,?)";
		// prepareInsert(sql);
		/**
		 * 缓存删除
		 * 
		 */
		// String sql = "delete from student where id = ?";
		// deletePrepare(sql);

		/**
		 * 操作新闻表单 插入文件 MEDIUMBLOB
		 * 
		 */
		// String sql = "insert into news(title,content,attachment) values(?,?,?)";
		// insertClob(sql);

		/**
		 * 操作新闻表单 读出文件
		 * 
		 */
		// String sql = "SELECT * FROM news where id=?";
		// readClob(sql);

		/**
		 * 获取自增长的值
		 * 
		 */
		// getAutoIncrement();
		/**
		 * 分页获取数据
		 * 
		 */
		//
		String sql = "select * from student where 1=1 and adress like '%新津%' limit ?,?";
		QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
		System.out.println(queryRunner);
		int StartNu = 0 * 10;
		int pageSize = 10;
		List<Student> list = (List<Student>) queryRunner.query(sql, new BeanListHandler<>(Student.class),
				new Object[] { StartNu, pageSize });
		for (Student student : list) {
			System.out.println(student.toString());
		}

	}

	private static void getAutoIncrement() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtil.getConnection();
			// 开启事务
			connection.setAutoCommit(false);// 手动提交
			/**
			 * 同时插入两条数据
			 * 
			 */
			String sql1 = "insert into student(name,adress) values(?,?)";

			statement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);// 返回自增长生成的值
			statement.setString(1, "性感美女10");
			statement.setString(2, "四川成都新津");
			System.out.println("value i :" + i);
			// int j = 100 / i;
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			while (resultSet.next()) {
				System.out.println("自增长的值为" + resultSet.getInt(1));

			}
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			// i++;
			e.printStackTrace();
			try {
				System.out.println("回滚");
				connection.rollback(); // 没有成功回滚
				getAutoIncrement();
			} catch (Exception e2) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} finally {
			JdbcUtil.close(statement, connection);
		}
	}

	private static void readClob(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.prepareStatement(sql);// 语法和权限的检查
			long start = System.currentTimeMillis();
			statement.setInt(1, 1);
			// 执行sql
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				// 字符串读取
				// String content = resultSet.getString("content");
				// 流读取
				Clob clob = resultSet.getClob("content");
				Blob blob = resultSet.getBlob("attachment");
				InputStream inputStream = blob.getBinaryStream();

				Reader reader = clob.getCharacterStream();
				FileWriter writer = new FileWriter(
						"/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/add.xml");
				FileOutputStream outputStream = new FileOutputStream(
						"/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/美女.jpg");
				char[] buf = new char[512];
				int len = 0;
				while ((len = reader.read(buf)) != -1) {
					writer.write(buf, 0, len);
				}

				byte[] buf2 = new byte[1024];
				int len2 = 0;
				while ((len2 = inputStream.read(buf2)) != -1) {
					outputStream.write(buf2, 0, len2);

				}
				inputStream.close();
				outputStream.close();
				writer.close();
				reader.close();

				System.out.println(id + "\t" + title + "\t" + reader);
			}
			long end = System.currentTimeMillis();
			System.out.println("所需时间： " + (end - start) + "count：");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(resultSet, statement, connection);
		}
	}

	private static void insertClob(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.prepareStatement(sql);// 语法和权限的检查
			FileReader reader = new FileReader(
					"/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/product.xml");
			InputStream inputStream = new FileInputStream(
					"/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/firstTest/src/main/java/hoggen/com/firstTest/webApp/img/detail01.jpg");

			long start = System.currentTimeMillis();
			statement.setString(1, "product.xml");
			statement.setClob(2, reader);
			statement.setBlob(3, inputStream);
			// 执行sql
			int count = statement.executeUpdate();
			long end = System.currentTimeMillis();
			System.out.println("所需时间： " + (end - start) + "count：" + count);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(statement, connection);
		}
	}

	private static void deletePrepare(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtil.getConnection();
			statement = connection.prepareStatement(sql);// 语法和权限的检查
			long start = System.currentTimeMillis();
			for (int i = 5; i <= 2005; i++) {
				statement.setInt(1, i);// 参数从1开始
				statement.addBatch();
				if ((i - 5) % 20 == 0) {
					// 发送参数到数据库，执行sql
					statement.executeBatch();
					statement.clearBatch();
					System.out.println("i的值：" + i);
				}

			}

			long end = System.currentTimeMillis();
			System.out.println("所需时间： " + (end - start));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(statement, connection);
		}
	}

	private static void prepareInsert(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtil.getConnection();
			long start = System.currentTimeMillis();
			statement = connection.prepareStatement(sql);// 语法和权限的检查
			for (int i = 5; i <= 2005; i++) {

				statement.setInt(1, i);// 参数从1开始
				statement.setString(2, "美女" + i);

				statement.setString(3, "四川成都新津");

				statement.addBatch();
				// 发送参数到数据库，执行sql
				if ((i - 5) % 20 == 0) {
					// 发送参数到数据库，执行sql
					statement.executeBatch();
					statement.clearBatch();
					System.out.println("i的值：" + i);
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("所需时间： " + (end - start));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(statement, connection);
		}
	}

}
