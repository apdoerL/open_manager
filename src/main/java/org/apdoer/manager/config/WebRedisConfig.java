//package org.apdoer.manager.config;
//
//import org.apdoer.manager.redis.RedisCacheService;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPoolConfig;
//
//
//@Configuration
//public class WebRedisConfig extends CachingConfigurerSupport {
//
////
//
////	@Bean("redisTemplate")
////	public StringRedisTemplate redisTemplate(){
////		JedisConnectionFactory jedisConnectionFactory = webJedisConnectionFactory();
////		jedisConnectionFactory.setDatabase(0);
////		StringRedisTemplate temple = new StringRedisTemplate();
////		temple.setConnectionFactory(jedisConnectionFactory);
////		return temple;
////	}
//
//
//
//
////	/**
////	 * springboot1.5 配置缓存管理器如下配置
////	 * @param webJedisConnectionFactory
////	 * @return
////	 */
////	@Bean(name = "redisCacheManager")
////	public RedisCacheManager redisCacheManager(@Qualifier("webJedisConnectionFactory") RedisConnectionFactory webJedisConnectionFactory){
////		RedisTemplate<Object, Object> template = new RedisTemplate<>();
////		template.setConnectionFactory( webJedisConnectionFactory );
////
////
////		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
////		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>( Object.class );
////
////		ObjectMapper mapper = new ObjectMapper();
////		mapper.setVisibility( PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY );
////		mapper.enableDefaultTyping( ObjectMapper.DefaultTyping.NON_FINAL );
////		serializer.setObjectMapper( mapper );
////
////		template.setValueSerializer( serializer );
////		// 使用StringRedisSerializer来序列化和反序列化redis的key值
////		template.setKeySerializer( new StringRedisSerializer() );
////		template.afterPropertiesSet();
////		RedisCacheManager cacheManager = new RedisCacheManager( template );
////		return cacheManager;
////	}
//
//}
