package com.hoggen.sublimation.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import java.time.Duration;

/**设置Redis多实例的基类
 * @author hoggen
 * @data
 */
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 配置lettuce连接池
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    public GenericObjectPoolConfig redisPool() {
        return new GenericObjectPoolConfig<>();
    }

    /**
     * 配置第一个数据源的
     *
     * @return
     */
    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
    public RedisStandaloneConfiguration redisConfig1(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") int port
            , @Value("${spring.redis.database}") int db, @Value("${spring.redis.password}") String password) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        redisStandaloneConfiguration.setDatabase(db);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return redisStandaloneConfiguration;
    }

    /**
     * 配置第二个数据源
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.redis2")
    public RedisStandaloneConfiguration redisConfig2(@Value("${spring.redis2.host}") String host, @Value("${spring.redis2.port}") int port
            , @Value("${spring.redis2.database}") int db, @Value("${spring.redis2.password}") String password) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        redisStandaloneConfiguration.setDatabase(db);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return redisStandaloneConfiguration;
    }

    /**
     * 配置第一个数据源的连接工厂
     * 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
     *
     * @param config
     * @param redisConfig
     * @return
     */
    @Bean("factory")
    @Primary
    public LettuceConnectionFactory factory(GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfig1) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
        return new LettuceConnectionFactory(redisConfig1, clientConfiguration);
    }

    @Bean("factory2")
    public LettuceConnectionFactory factory2(GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfig2) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
        return new LettuceConnectionFactory(redisConfig2, clientConfiguration);
    }

    /**
     * 配置第一个数据源的RedisTemplate
     * 注意：这里指定使用名称=factory 的 RedisConnectionFactory
     * 并且标识第一个数据源是默认数据源 @Primary
     *
     * @param factory
     * @return
     */
    @Bean("redisTemplate")
    @Primary
    public RedisTemplate<String, String> redisTemplate(@Qualifier("factory") RedisConnectionFactory factory) {
        return getStringStringRedisTemplate(factory);
    }

    /**
     * 配置第一个数据源的RedisTemplate
     * 注意：这里指定使用名称=factory2 的 RedisConnectionFactory
     *
     * @param factory2
     * @return
     */
    @Bean("redisTemplate2")
    public RedisTemplate<String, String> redisTemplate2(@Qualifier("factory2") RedisConnectionFactory factory2) {
        return getStringStringRedisTemplate(factory2);
    }

    /**
     * 设置序列化方式 （这一步不是必须的）
     *
     * @param factory
     * @return
     */
    private RedisTemplate<String, String> getStringStringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
