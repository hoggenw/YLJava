package hoggenwang;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DataBaseManager {
	public void saveFile(FileModel bean) {
		System.out.println("存入开始");
		try {
			System.out.println("存入数据库");
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			qr.update("insert into ImageFileDB(name,size,type,addtime,file_path,info) values(?,?,?,?,?,?)",
					new Object[] { bean.getName(), bean.getSize(), bean.getType(), bean.getAddTime(),
							bean.getFile_path(), bean.getInfo() });
		} catch (SQLException e) {
			System.out.println("存入出错");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public FileModel findById(int id) {
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			return (FileModel) qr.query("select * from ImageFileDB where id=?", new BeanHandler(FileModel.class),
					new Object[] { id });
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * ²éÑ¯ËùÓÐÎÄ¼þ
	 * 
	 * @param args
	 */
	public ArrayList<FileModel> findAll() {
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			return (ArrayList<FileModel>) qr.query("select * from ImageFileDB", new BeanListHandler(FileModel.class),
					new Object[] {});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
