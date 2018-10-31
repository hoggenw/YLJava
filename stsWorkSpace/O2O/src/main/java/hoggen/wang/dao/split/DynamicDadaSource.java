package hoggen.wang.dao.split;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @注释 配置主从分离数据库
 */
public class DynamicDadaSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DynamicDataSourceHolder.getDbType();
	}

}
