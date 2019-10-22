package mainClass;

import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;

public class dbcpPool {
	private static String url = "jdbc:mysql://localhost:3306/hoggen";
	// jdbc协议+数据库协议：//主机地址+端口+链接的数据库
	private static String user = "root";
	private static String password = "12345678";
	private static String driverClass = "com.mysql.cj.jdbc.Driver";

	public dbcpPool() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			BasicDataSource bDataSource = new BasicDataSource();
			bDataSource.setUrl(url);
			bDataSource.setUsername(user);
			bDataSource.setPassword(password);
			bDataSource.setDriverClassName(driverClass);
			// 设置链接参数
			bDataSource.setInitialSize(5);// 初始链接数
			bDataSource.setMaxTotal(8);// 最大连接数
			bDataSource.setMaxWaitMillis(3000);// 超过最大连接数时，最大等待时间
			bDataSource.setMaxIdle(3000);// 最大空闲时间
			// 4.获取连接
			for (int i = 1; i <= 9; i++) {
				Connection conn = bDataSource.getConnection();
				System.out.println(conn.hashCode());
				if (i == 5) {
					// 释放连接(不是真正的关闭连接对象，而是把连接对象放回连接池)
					conn.close();
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
