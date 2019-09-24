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
 * redis操作组件</br>
 *
 * @author William
 * @date 2018/8/10 20:43
 * @since jdk1.8
 */
@ConditionalOnProperty(prefix = "spring.redis", name = "host")
@Component
public class RedisCacheService implements InitializingBean {

	private ConcurrentHashMap<String, RedisScript<Long>>	commands	= new ConcurrentHashMap<String, RedisScript<Long>>();

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	/**
	 * RedisCacheService</br>
	 *
	 * @author William
	 * @date 2018/8/10 20:35
	 * @since jdk1.8
	 */
	public RedisCacheService(RedisTemplate<Object, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * expire:x秒后过期. <br/>
	 *
	 * @param key
	 * @param time
	 * @return
	 * @author William
	 * @since JDK 1.8
	 */
	public Boolean expire(final String key, final Long time) {
		return this.redisTemplate.expire( key, time, TimeUnit.SECONDS );
	}

	/**
	 * expireAt:在某一时刻过期. <br/>
	 *
	 * @param key
	 * @param date
	 * @return
	 * @author William
	 * @since JDK 1.8
	 */
	public Boolean expireAt(final String key, final Date date) {
		return this.redisTemplate.expireAt( key, date );
	}

	/**
	 * get命令. <br/>
	 *
	 * @param key
	 * @return
	 * @author William
	 * @since JDK 1.8
	 */
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
	 * incr和expire的组合命令,原子在redis服务端执行 . <br/>
	 *
	 * @param key
	 * @param timeout
	 * @return
	 * @author William
	 * @since JDK 1.8
	 */
	public Long incrExpire(final String key, Long timeout) {
		return incr( RedisCommandConst.INCR_EXPIRE, key, timeout );
	}

	/**
	 * incr和expireAt的组合命令,原子在redis服务端执行 . <br/>
	 *
	 * @param key
	 * @param date
	 *            指定的过期时间点
	 * @return
	 * @author William
	 * @since JDK 1.8
	 */
	public Long incrExpireAt(final String key, Long date) {
		return incr( RedisCommandConst.INCR_EXPIREAT, key, date );
	}

	public boolean hasKey(final String key) {
		return redisTemplate.hasKey( key );
	}

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
	 * hmset:批量保存Map <br/>
	 *
	 * @param key
	 * @param hash
	 * @author William
	 * @since JDK 1.8
	 */
	public <HV> void hmset(final String key, final Map<String, HV> hash) {
		redisTemplate.opsForHash().putAll( key, hash );
	}

	/**
	 * hmget:批量获取Map. <br/>
	 *
	 * @param key
	 * @return
	 * @author William
	 * @since JDK 1.8
	 */
	@SuppressWarnings("unchecked")
	public <HK, HV> Map<HK, HV> hmget(final String key) {
		return (Map<HK, HV>) redisTemplate.opsForHash().entries( key );
	}

	/**
	 * hget:获取hash中的某个value. <br/>
	 *
	 * @param key
	 * @param field
	 * @return
	 * @author William
	 * @since JDK 1.8
	 */
	@SuppressWarnings("unchecked")
	public <HV> HV hget(final String key, final String field) {
		return (HV) redisTemplate.opsForHash().get( key, field );
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources( "classpath*:com/newcoin/common/web/redis/*.lua" );

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
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public <V> void setex(final String key, final V value, final int timeout) {
		redisTemplate.opsForValue().set( key, value, timeout, TimeUnit.SECONDS );
	}

	/**
	 * setnx 命令
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setnx(final String key, final String value) {
		return redisTemplate.opsForValue().setIfAbsent( key, value );
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 */
	public void delete(final String key) {
		redisTemplate.delete( key );
	}

}
