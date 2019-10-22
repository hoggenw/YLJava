package hoggen.wang.O2O.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hoggen.wang.cache.JedisPoolWriper;
import hoggen.wang.cache.JedisUtil;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

	@Value("${redis.hostname}")
	private String hostname;

	@Value("${redis.port}")
	private int port;
//	@Value("${redis.database}")
//	private String jdbcDriver;
	@Value("${redis.pool.maxActive}")
	private int maxTotal;

	@Value("${redis.pool.maxIdle}")
	private int maxIdle;

	@Value("${redis.pool.maxWait}")
	private long maxWaitMills;

	@Value("${redis.pool.testOnBorrow}")
	private boolean testOnBorrow;

	@Autowired
	private JedisPoolConfig jedisPoolConfig;

	@Autowired
	private JedisPoolWriper jedisWritePool;

	@Autowired
	private JedisUtil jedisUtil;

	/**
	 * @注释 连接池的设置
	 */
	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig createJedisPoolConfig() {
		// 控制一个pool可分配多少个jedis实例
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 控制一个pool可分配多少个redis是咧
		jedisPoolConfig.setMaxTotal(maxTotal);
		// 链接池中最多可空闲maxidle个链接，这里取值20，表示即使没有数据库链接时仍然可以保持20的空闲链接
		jedisPoolConfig.setMaxIdle(maxIdle);
		// 最大等待时间：当没有可用链接时，连接池等待链接被归还的最大时间（毫秒），超过时间则抛出异常
		jedisPoolConfig.setMaxWaitMillis(maxWaitMills);
		// 获取链接的时候检查有效性
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);

		return jedisPoolConfig;
	}

	/**
	 * @注释 创建redis连接池，并做相关配置
	 */
	@Bean(name = "jedisWritePool")
	public JedisPoolWriper createJedisPoolWriper() {
		JedisPoolWriper jedisPoolWriper = new JedisPoolWriper(jedisPoolConfig, hostname, port);
		return jedisPoolWriper;
	}



	/**
	 * @注释 创建redis 工具类
	 */

	@Bean(name = "jedisUtil")
	public JedisUtil createJedisUtil() {
		JedisUtil jedisUtil = new JedisUtil();
		jedisUtil.setJedisPool(jedisWritePool);
		return jedisUtil;

	}

	/**
	 * @注释 redis的key操作
	 */
	@Bean(name = "jedisKeys")
	public JedisUtil.Keys createJedisKeys() {
		JedisUtil.Keys jKeys = jedisUtil.new Keys();
		return jKeys;
	}

	/**
	 * @注释 redis的Strings操作
	 */
	@Bean(name = "jedisStrings")
	public JedisUtil.Strings createJedisStrings() {
		JedisUtil.Strings jKeys = jedisUtil.new Strings();
		return jKeys;
	}

	/**
	 * @注释 redis的Lists操作
	 */
	@Bean(name = "jedisLists")
	public JedisUtil.Lists createJedisLists() {
		JedisUtil.Lists jKeys = jedisUtil.new Lists();
		return jKeys;
	}

	/**
	 * @注释 redis的Sets操作
	 */
	@Bean(name = "jedisSets")
	public JedisUtil.Sets createJedisSets() {
		JedisUtil.Sets jKeys = jedisUtil.new Sets();
		return jKeys;
	}

	/**
	 * @注释 redis的Hash操作
	 */
	@Bean(name = "jedisHash")
	public JedisUtil.Hash createJedisHash() {
		JedisUtil.Hash jKeys = jedisUtil.new Hash();
		return jKeys;
	}

}
