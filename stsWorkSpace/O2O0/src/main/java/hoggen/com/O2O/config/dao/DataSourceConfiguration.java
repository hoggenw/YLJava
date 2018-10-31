package hoggen.com.O2O.config.dao;

import java.beans.PropertyVetoException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@MapperScan("hoggen.com.O2O.dao")
public class DataSourceConfiguration {
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;

	
	/**
	 * @throws PropertyVetoException 
	 * @注释
	 *  生成与spring-dao。xml对应的bean datasource
	 * */
	
	@Bean(name = "dataSource")
	public ComboPooledDataSource creatDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
//		跟配置文件一样设置信息
		// 驱动
		dataSource.setDriverClass(jdbcDriver);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setMaxPoolSize(30);
		dataSource.setMinPoolSize(10);
		dataSource.setAutoCommitOnClose(false);
		dataSource.setCheckoutTimeout(10000);
		dataSource.setAcquireRetryAttempts(2);
		return dataSource;
	}

}
