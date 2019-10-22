package dataManager;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import hoggenwang.Student;
import utils.JDBCUtil;

public class dataManager {

	String sql = "select * from student limit ?,?";// 参数1是起始页码，第二个参数是查询数量

	public dataManager() {
		// TODO Auto-generated constructor stub
	}

	public List<Student> queryData() {
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			int StartNu = 0 * 10;
			int pageSize = 10;
			List<Student> list = (List<Student>) qr.query(sql, new BeanListHandler<>(Student.class),
					new Object[] { StartNu, pageSize });
			for (Student student : list) {
				System.out.println(student.toString());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}
		System.out.println("执行失败");
		return null;

	}

}
