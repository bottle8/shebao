package com.ice.shebao.util;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.ice.shebao.core.CoreUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	public static Logger logger = Logger.getLogger(RedisClient.class);
	public static JedisPool jedisPool;	//池化管理jedis链接池
	
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		
		//设置最大连接数
		int maxActive = Integer.parseInt(CoreUtils.configMap.get("redis.pool.maxActive"));
		config.setMaxTotal(maxActive);
		
		//设置最大空闲数
		int maxIdel = Integer.parseInt(CoreUtils.configMap.get("redis.pool.maxIdel"));
		config.setMaxIdle(maxIdel);
		
		int maxWait = Integer.parseInt(CoreUtils.configMap.get("redis.pool.maxWait"));
		config.setMaxWaitMillis(maxWait);
		
		//初始化连接池
		String ip = CoreUtils.configMap.get("redis.ip");
		int port = Integer.parseInt(CoreUtils.configMap.get("redis.port"));
		
		jedisPool = new JedisPool(config,ip,port);
	}
	
	
	/**
	 * 向缓存中设置字符串
	 * @param key
	 * @param value
	 */
	public static void set(String key,String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 将对象中存入Redis缓存
	 * @param key
	 * @param value
	 */
	public static void set(String key,Object value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			JSONObject object = JSONObject.fromObject(value);
			jedis.set(key, object.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 将集合对象存入redis缓存
	 * @param key
	 * @param value
	 */
	public static void set(String key,@SuppressWarnings("rawtypes") Collection value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			JSONArray jsonArray = JSONArray.fromObject(value);
			jedis.set(key, jsonArray.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 删除缓存内容
	 * @param key
	 * @return
	 */
	public static boolean delete(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 根据key获取内容
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
}
