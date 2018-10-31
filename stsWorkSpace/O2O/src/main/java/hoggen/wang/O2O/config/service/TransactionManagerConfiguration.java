package hoggen.wang.O2O.config.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
//@EnableTransactionManagement 开启事务支持后
//在Service方法上添加注解 @Transctional 便可
@EnableTransactionManagement
public class TransactionManagerConfiguration implements TransactionManagementConfigurer {
//注入DataSourceConfiguration中的dataSource
	@Autowired
	private DataSource dataSource;

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		return new DataSourceTransactionManager(dataSource);
	}

}
