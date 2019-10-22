// package hoggenwang.main.database;
//
// import java.lang.reflect.ParameterizedType;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
//
// import org.apache.commons.dbutils.QueryRunner;
// import org.apache.commons.dbutils.ResultSetHandler;
// import org.apache.commons.dbutils.handlers.BeanHandler;
//
// import com.mysql.cj.xdevapi.Table;
//
// public class BaseDataBaseManager {
// // ¾ßÌåµÄdaoÉÏÃæµÄ·ºÐÍÀàÐÍ
// private Class targetClass;
// // ±íÃû
// private String tableName;
//
// public BaseDataBaseManager() {
// /**
// * ÐèÒª½â¾öµÄÎÊÌâ£º 1£©
// *
// µÃµ½¾ßÌåµÄÒµÎñdaoÔËÐÐ¹ý³ÌÖÐµÄ·ºÐÍ¾ßÌåÀàÐÍ£¨Student/Teacher£©,¿ÉÒÔ·â×°ResultSet
// * 2) µÃµ½·ºÐÍ¾ßÓÐÀàÐÍÃû³Æ £¬¾ÍÊÇ±íÃû
// */
// // 1)this : ´ú±íµ±Ç°ÔËÐÐµÄdao¶ÔÏó
// // System.out.println(this.getClass());
// // 2)this.getClass(): ´ú±íµ±Ç°ÔËÐÐdao¶ÔÏóµÄClass¶ÔÏó
// Class clazz = this.getClass(); // public class TeacherDao extends
// BaseDao<Teacher>
// // 3)clazz.getGenericSuperclass()£º µÃµ½µ±Ç°dao¶ÔÏóµÄ¸¸Àà£¨²ÎÊý»¯ÀàÐÍ£©
// Type type = clazz.getGenericSuperclass(); // BaseDao<Teacher>
// // 4)°Ñ¸¸ÀàµÄÀàÐÍÇ¿×ª³É×ÓÀà£¨²ÎÊý»¯ÀàÐÍ: ParameterizedType£©
// ParameterizedType param = (ParameterizedType) type; // BaseDao<Teacher>
// // 5)param.getActualTypeArguments():µÃµ½²ÎÊý»¯ÀàÐÍ ÉÏÃæµÄ·ºÐÍÀàÐÍÁÐ±í
// Type[] types = param.getActualTypeArguments(); // <Teacher>
// // 6)È¡³ö·ºÐÍÀàÐÍÁÐ±íÖÐµÄµÚÒ»¸ö·ºÐÍÀàÐÍ
// Type target = types[0]; // Teacher
// // 7)Ç¿ÖÆ³ÉClassÀàÐÍ
// targetClass = (Class) target;
//
// try {
// // System.out.println(targetClass.getSimpleName());
// /**
// * »ñÈ¡±íÃû À´×ÔÓÚ ÀàÉÏÃæµÄ×¢½â
// */
// Table table = (Table) targetClass.getAnnotation(Table.class);
// tableName = table.name();
//
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
//
// public List<T> findAll() {
// try {
// QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
// return (List<T>) qr.query("select * from " + tableName + "", new
// MyBeanListHandler());
// } catch (SQLException e) {
// e.printStackTrace();
// throw new RuntimeException(e);
// }
// }
//
// public T findById(int id) {
// try {
// QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
// return (T) qr.query("select * from " + tableName + " where id=?", new
// BeanHandler(targetClass),
// new Object[] { id });
// } catch (SQLException e) {
// e.printStackTrace();
// throw new RuntimeException(e);
// }
// }
//
// /**
// * ×ÔÐÐÉè¼ÆÒ»¸ö·â×°¶à¸ö¶ÔÏóµÄList¼¯ºÏµÄResultSetHandler
// */
// class MyBeanListHandler implements ResultSetHandler {
//
// @Override
// public Object handle(ResultSet rs) throws SQLException {
// try {
// List<T> list = new ArrayList<T>();
// // µÃµ½½á¹û¼¯µÄÔªÊý¾Ý
// ResultSetMetaData rsmd = rs.getMetaData();
// // µÃµ½±íµÄÁÐÊýÁ¿
// int columnCount = rsmd.getColumnCount();
// while (rs.next()) {// ÐÐ
// // ´´½¨¶ÔÏó
// T obj = (T) targetClass.newInstance();
// // °Ñ×Ö¶ÎÖµ·â×°µ½¶ÔÏóÖÐ
// // ±éÀúÁÐ
// for (int i = 1; i <= columnCount; i++) {
// // µÃµ½ÁÐµÄÖµ
// Object value = rs.getObject(i);
// // µÃµ½ÁÐÃû³Æ
// String columnName = rsmd.getColumnName(i).toLowerCase();
//
// // ±éÀúËùÓÐÊôÐÔ
// Field[] fields = targetClass.getDeclaredFields();
// for (Field field : fields) {
// // µÃµ½ÊôÐÔÉÏÃæµÄ×¢½â
// Column column = field.getAnnotation(Column.class);
// // µÃµ½×¢½âµÄÄÚÈÝ
// String cname = column.name().toLowerCase();
//
// if (columnName.equals(cname)) {
// field.setAccessible(true);
// // ÎÒÐèÒª¸³ÖµµÄÊôÐÔ,¸øÊôÐÔ¸³Öµ
// field.set(obj, value);
// break;
// }
// }
// }
// // °Ñ·â×°ºÃµÄ¶ÔÏó·ÅÈëLIst¼¯ºÏÖÐ
// list.add(obj);
// }
// return list;
// } catch (Exception e) {
// e.printStackTrace();
// throw new RuntimeException(e);
// }
// }
//
// }
//
// }
