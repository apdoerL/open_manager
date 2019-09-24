package org.apdoer.manager.redis;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 *  spring 提供了两种初始化bean 的方法,
 *  	1.实现 InitializingBean 接口,实现 afterPropertiesSet 方法
 *  	2. 在配置文件中通过 init-method 指定,
 *  第一种方式是直接调用 afterPropertiesSet 方法,比通过反射调用 init-method 指定的方法效率高些,
 *  但是 init-method 方式消除了对spring 的依赖
 *  这个类是为了初始化lua脚本
 *
 *  spring offered two ways to initial bean,
 *  	1.implement @Interface[InitializingBean] ,and implement @Method[afterPropertiesSet]
 *		2.definition in properties by <init-method/>,but this way requires invoke,it is Inefficient,otherwise,
 *			it eliminate spring dependcies injection
 *		3.if exception occured during execute @Method[afterPropertiesSet],the method assigned by <init-method/> will not be executed
 *	this class offered some method to Operate redis and initial lua scripts
 * @author apdoer
 * @date 2019/8/10 20:43
 * @since jdk1.8
 */
@ConditionalOnProperty(prefix = "spring.redis", name = "host")
@Component
public class RedisCacheService implements InitializingBean {
	private RedisTemplate<Object, Object> redisTemplate;
	private ConcurrentHashMap<String, RedisScript<Long>> commands = new ConcurrentHashMap<>();





	@Autowired
	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public RedisCacheService(RedisTemplate<Object, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * expire:x秒后过期. <br/>
	 * @param key key
	 * @param time timeout
	 * @return r
	 */
	public Boolean expire(final String key, final Long time) {
		return this.redisTemplate.expire( key, time, TimeUnit.SECONDS );
	}

	/**
	 * expireAt:在某一时刻过期.
	 * @param key key
	 * @param date 过期时间
	 * @return r
	 */
	public Boolean expireAt(final String key, final Date date) {
		return this.redisTemplate.expireAt( key, date );
	}


	public Object get(final String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public void set(final String key, Object value) {
		this.redisTemplate.opsForValue().set(key, value);
	}
	
	public void set(final String key, Object value, long timeout, TimeUnit timeUnit) {
		this.redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}

	/**
	 * incr和expire的组合命令,原子在redis服务端执行
	 * @param key key
	 * @param timeout timeout
	 * @return result
	 */
	public Long incrExpire(final String key, Long timeout) {
		return incr( RedisCommandConst.INCR_EXPIRE, key, timeout );
	}

	/**
	 * incr和expireAt的组合命令,原子在redis服务端执行
	 * @param key key
	 * @param date 指定的过期时间点
	 * @return result
	 */
	public Long incrExpireAt(final String key, Long date) {
		return incr( RedisCommandConst.INCR_EXPIREAT, key, date );
	}

	public boolean hasKey(final String key) {
		return redisTemplate.hasKey( key );
	}

	/**
	 *  incr 原子操作
	 * @param command lua scripts
	 * @param key key
	 * @param date timeout
	 * @return result
	 */
	private Long incr(final String command, final String key, Long date) {
		if (StringUtils.isBlank( key )) {
			throw new IllegalArgumentException( "key为空" );
		}
		if (date <= 0) {
			throw new IllegalArgumentException( "timeout不能为负数" );
		}
		Object[] args = { date.toString() };
		RedisScript<Long> script = commands.get( command );
		return redisTemplate.execute( script, Arrays.asList( key ), args );
	}

	/**
	 * hmset:批量保存Map
	 *
	 * @param key key
	 * @param hash hash
	 */
	public <HV> void hmset(final String key, final Map<String, HV> hash) {
		redisTemplate.opsForHash().putAll( key, hash );
	}

	/**
	 * hmget:批量获取Map.
	 *
	 * @param key key
	 * @return result
	 */
	@SuppressWarnings("unchecked")
	public <HK, HV> Map<HK, HV> hmget(final String key) {
		return (Map<HK, HV>) redisTemplate.opsForHash().entries( key );
	}

	/**
	 * hget:获取hash中的某个value. <br/>
	 *
	 * @param key key
	 * @param field field
	 * @return s
	 */
	@SuppressWarnings("unchecked")
	public <HV> HV hget(final String key, final String field) {
		return (HV) redisTemplate.opsForHash().get( key, field );
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources( "classpath*:org/apdoer/manager/redis/*.lua" );

		for (Resource resource : resources) {
			DefaultRedisScript<Long> script = new DefaultRedisScript<Long>();
			script.setLocation( resource );
			script.setResultType( Long.class );
			commands.put( resource.getFilename().replace( ".lua", "" ), script );
		}
	}

	/**
	 * setex 命令,以秒为单位,set 和expire的组合,但是是原子执行
	 * 
	 * @param key key
	 * @param value value
	 * @param timeout timeout
	 */
	public <V> void setex(final String key, final V value, final int timeout) {
		redisTemplate.opsForValue().set( key, value, timeout, TimeUnit.SECONDS );
	}

	/**
	 * setnx 命令
	 *
	 * @param key key
	 * @param value value
	 * @return result
	 */
	@SuppressWarnings("unchecked")
	public boolean setnx(final String key, final String value) {
		return redisTemplate.opsForValue().setIfAbsent( key, value );
	}

	/**
	 * 删除缓存
	 * @param key key
	 */
	@SuppressWarnings("unchecked")
	public void delete(final String key) {
		redisTemplate.delete( key );
	}

}
