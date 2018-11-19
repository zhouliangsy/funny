package com.liang.funny.util.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


/**
 * 此类为redis缓存操作的自定义设置
 * 1、自定义缓存key的生成策略
 * 2、缓存管理
 * 3、RedisTemplate的缓存value 序列化
 */

@Configuration
@EnableCaching // 开启缓存支持
//@PropertySource("classpath:application.xml")
public class RedisConfig extends CachingConfigurerSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Value("${spring.redis.database}")
//    private int database;
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.timeout}")
//    private long timeout;
//
//    @Value("${spring.redis.lettuce.pool.max-idle}")
//    private int maxIdle;
//
//    @Value("${spring.redis.lettuce.pool.min-idle}")
//    private int minIdle;
//
//    @Value("${spring.redis.lettuce.pool.max-active}")
//    private int maxActive;
//
//    @Value("${spring.redis.lettuce.pool.max-wait}")
//    private long maxWait;


    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;


    /**
     * 自定义线程池，有commons-pool2的话，会默认生成线程池,如果自定义，需要
     */
//    @Bean
//    LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig genericObjectPoolConfig) {
//        // 单机版配置
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setDatabase(database);
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
//    }


    /**
     * 自定义缓存key的生成策略。默认的生成策略是看不懂的(乱码内容) 通过Spring 的依赖注入特性进行自定义的配置注入并且此类是一个配置类可以更多程度的自定义配置
     * 包名+类名+方法名+所有参数
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                //格式化缓存key字符串
                StringBuffer sb = new StringBuffer();
                //追加类名
                sb.append(target.getClass().getName());
                //追加方法名
                sb.append(method.getName());
                //遍历参数并且追加
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                logger.info("调用Redis缓存key"+sb.toString());
                return sb.toString();
            }
        };
    }


    /**
     * 要启用spring缓存支持,需创建一个 CacheManager的 bean，CacheManager 接口有很多实现，这里Redis 的集成，用
     * RedisCacheManager这个实现类 Redis 不是应用的共享内存，它只是一个内存服务器，就像 MySql 似的，
     * 我们需要将应用连接到它并使用某种“语言”进行交互，因此我们还需要一个连接工厂以及一个 Spring 和 Redis 对话要用的
     * RedisTemplate， 这些都是 Redis 缓存所必需的配置，把它们都放在自定义的 CachingConfigurerSupport 中
     * @return
     */
    // 缓存管理器 可以见 https://www.cnblogs.com/taiyonghai/p/9454764.html 解释
    @Bean
    public CacheManager cacheManager() {

        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(lettuceConnectionFactory);
        @SuppressWarnings("serial")
        Set<String> cacheNames = new HashSet<String>() {
            {
                add("codeNameCache");
            }
        };
        builder.initialCacheNames(cacheNames);
        return builder.build();
    }


    /**
     * RedisTemplate配置
     * 项目启动时此方法先被注册成bean被spring管理,如果没有这个bean，则redis可视化工具中的中文内容（key或者value）都会以二进制存储，不易检查
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {

        /**
         * 以下代码为将RedisTemplate的Value序列化方式由JdkSerializationRedisSerializer更换为Jackson2JsonRedisSerializer
         * 此种序列化方式结果清晰、容易阅读、存储字节少、速度快，所以推荐更换
         * 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
         */
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        om.enableDefaultTyping(DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();//简单的字符串序列化,一般如果key、value都是string字符串的话，就是用这个就可以了
        redisTemplate.setKeySerializer(stringSerializer);// key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
        redisTemplate.setHashKeySerializer(stringSerializer);// Hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


}
