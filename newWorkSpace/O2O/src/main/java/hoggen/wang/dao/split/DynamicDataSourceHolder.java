package hoggen.wang.dao.split;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public class DynamicDataSourceHolder {
	private static Logger logger = (Logger) LoggerFactory.getLogger(DynamicDadaSource.class);
	private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static final String DB_MASTER = "masster";
	public static final String DB_SLAVE = "slave";

	public static String getDbType() {
		String db = contextHolder.get();
		if (db == null) {
			db = DB_MASTER;
		}
		return db;
	}

	/**
	 * @注释 设置线程的dbtype
	 */
	public static void setDbType(String s) {
		logger.debug("所使用的数据源为： " + s);

		contextHolder.set(s);
	}

	/**
	 * @注释 清理链接类型
	 */
	public static void clearDbType() {
		contextHolder.remove();
	}
}
