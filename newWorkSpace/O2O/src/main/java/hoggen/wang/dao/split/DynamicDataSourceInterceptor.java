package hoggen.wang.dao.split;

import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class DynamicDataSourceInterceptor implements Interceptor {
	private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
	private static Logger logger = (Logger) LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		// 判断当前是否是事物
		boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
		Object[] objects = invocation.getArgs();
		MappedStatement mStatement = (MappedStatement) objects[0];
		String lookupKey = DynamicDataSourceHolder.DB_MASTER;
		if (synchronizationActive != true) {

			// 度方法
			if (mStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
				// selectkey 为自增id查询主键（SELECT LAST_INSEERT_ID()）方法，使用主库
				if (mStatement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
					lookupKey = DynamicDataSourceHolder.DB_MASTER;
				} else {
					BoundSql boundSql = mStatement.getSqlSource().getBoundSql(objects[1]);
					String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
					if (sql.matches(REGEX)) {
						lookupKey = DynamicDataSourceHolder.DB_MASTER;
					} else {
						lookupKey = DynamicDataSourceHolder.DB_SLAVE;
					}
				}
			}
		} else {
			lookupKey = DynamicDataSourceHolder.DB_MASTER;
		}
		logger.debug("设置方法[{}] use[{}] strategy, SqlCommonType[{}]..", mStatement.getId(), lookupKey,
				mStatement.getSqlCommandType().name());
		DynamicDataSourceHolder.setDbType(lookupKey);
		return invocation.proceed();
	}

	/**
	 * @注释
	 * 
	 */
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		// Executor 增删改查 拦截
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}

	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}

}
