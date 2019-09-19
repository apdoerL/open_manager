package org.apdoer.manager.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apdoer.manager.redis.FastJsonRedisSerializer;
import org.apdoer.manager.redis.RedisCacheService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author apdoer
 * @date 2018-11-24
 */
@Slf4j
@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.host}")
    private String	hostName;
    @Value("${spring.redis.port}")
    private int		port;
    @Value("${spring.redis.password}")
    private String	password;
    @Value("${spring.redis.testOnBorrow}")
    private boolean	testOnBorrow;
    @Value("${spring.redis.pool.max-total}")
    private int		maxTotal;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int		maxIdle;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int		maxActive;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long	maxWaitMillis;


    /**
     * springboot2.0+ 配置redis缓存管理器,这样配置,
     * new RedisCacheManager(RedisTemplate template)的方法已经没有了
     */
    @Bean("webRedisCacheManager")
    public CacheManager cacheManager(@Qualifier("webJedisConnectionFactory") RedisConnectionFactory webJedisConnectionFactory) {
        //初始化一个RedisCacheWriter 输出流
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(webJedisConnectionFactory);
        //采用Jackson2JsonRedisSerializer序列化机制
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class );
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility( PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY );
        mapper.enableDefaultTyping( ObjectMapper.DefaultTyping.NON_FINAL );
        serializer.setObjectMapper(mapper);
        //创建一个RediSerializationContext.SerializationPair 给定的适配器pair
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
        //创建CacheConfig
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        return new RedisCacheManager(redisCacheWriter,defaultCacheConfig);
    }

    @Bean(name = "webJedisConnectionFactory")
    public JedisConnectionFactory webJedisConnectionFactory() {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle( maxIdle );
        poolCofig.setMinIdle( 6 );
        poolCofig.setMaxTotal( maxTotal );
        poolCofig.setMaxWaitMillis( maxWaitMillis );
        poolCofig.setTestOnBorrow( testOnBorrow );
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName( hostName );
        factory.setPort( port );
        factory.setPassword( password );
        factory.setTimeout( 6000 ); // 设置连接超时时间
        // testOnBorrow为true时，返回的连接是经过测试可用的
        factory.setPoolConfig( poolCofig );
        return factory;
    }

    @Bean(name = "webRedisTemplate")
    public RedisTemplate<Object, Object> webRedisTemplate(@Qualifier("webJedisConnectionFactory") RedisConnectionFactory webJedisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory( webJedisConnectionFactory );
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setDefaultSerializer(  new org.springframework.data.redis.serializer.StringRedisSerializer()  );
        template.setKeySerializer( new org.springframework.data.redis.serializer.StringRedisSerializer() );
        template.setValueSerializer( new org.springframework.data.redis.serializer.StringRedisSerializer() );
        template.setHashKeySerializer( new org.springframework.data.redis.serializer.StringRedisSerializer() );
        template.setHashValueSerializer( new org.springframework.data.redis.serializer.StringRedisSerializer() );
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "webRedisCacheService")
    public RedisCacheService webRedisCacheService(@Qualifier("webRedisTemplate") RedisTemplate<Object, Object> webRedisTemplate) throws Exception {
        webRedisTemplate.setDefaultSerializer(  new org.springframework.data.redis.serializer.StringRedisSerializer()  );
        webRedisTemplate.setKeySerializer( new org.springframework.data.redis.serializer.StringRedisSerializer() );
        webRedisTemplate.setValueSerializer( new org.springframework.data.redis.serializer.StringRedisSerializer() );
        webRedisTemplate.setHashKeySerializer( new org.springframework.data.redis.serializer.StringRedisSerializer() );
        webRedisTemplate.setHashValueSerializer( new org.springframework.data.redis.serializer.StringRedisSerializer() );
        RedisCacheService redisCacheService = new RedisCacheService( webRedisTemplate );
        redisCacheService.afterPropertiesSet();
        return redisCacheService;
    }


    /**
     *  设置 redis 数据默认过期时间，默认1天
     *  设置@cacheable 序列化方式
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofDays(1));
        return configuration;
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);

        // 全局开启AutoType，不建议使用
         ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单
//        ParserConfig.getGlobalInstance().addAccept("包名");
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new FastJsonRedisSerializer<>(Object.class));
        template.setHashKeySerializer(new FastJsonRedisSerializer<>(Object.class));
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 自定义缓存key生成策略
     * 使用方法 @Cacheable(keyGenerator="keyGenerator")
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(JSON.toJSONString(obj).hashCode());
            }
            return sb.toString();
        };
    }
}
