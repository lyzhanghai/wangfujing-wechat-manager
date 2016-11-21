package wechat.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lambdaworks.redis.cluster.RedisAdvancedClusterConnection;

public class RedisUtil {
	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	@Resource
	private RedisAdvancedClusterConnection connectionCluster;

	/**
	 * 设置一个key的过期时间（单位：秒）
	 * 
	 * @param key
	 *            key值
	 * @param seconds
	 *            多少秒后过期
	 * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
	 */
	public long expire(String key, int seconds) {
		if (key == null || key.equals("")) {
			return 0;
		}
		try {
			if (connectionCluster.expire(key, seconds)) {
				return 1;
			} else {
				return 0;
			}
			// jedisCluster.expire(key, seconds);
		} catch (Exception ex) {
			logger.error("EXPIRE error[key=" + key + " seconds=" + seconds + "]" + ex.getMessage(),
					ex);
		}
		return 0;
	}

	/**
	 * 设置一个key在某个时间点过期
	 * 
	 * @param key
	 *            key值
	 * @param unixTimestamp
	 *            unix时间戳，从1970-01-01 00:00:00开始到现在的秒数
	 * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
	 */
	public long expireAt(String key, int unixTimestamp) {
		if (key == null || key.equals("")) {
			return 0;
		}
		try {
			if (connectionCluster.expireat(key, unixTimestamp)) {
				return 1;
			} else {
				return 0;
			}
			// return jedisCluster.expireat(key, unixTimestamp);
		} catch (Exception ex) {
			logger.error("EXPIRE error[key=" + key + " unixTimestamp=" + unixTimestamp + "]"
					+ ex.getMessage(), ex);
		}
		return 0;
	}

	/**
	 * 截断一个List
	 * 
	 * @param key
	 *            列表key
	 * @param start
	 *            开始位置 从0开始
	 * @param end
	 *            结束位置
	 * @return 状态码
	 */
	public String trimList(String key, long start, long end) {
		if (key == null || key.equals("")) {
			return "-";
		}
		try {
			return connectionCluster.ltrim(key, start, end);
		} catch (Exception ex) {
			logger.error("LTRIM 出错[key=" + key + " start=" + start + " end=" + end + "]"
					+ ex.getMessage(), ex);
		}
		return "-";
	}

	/**
	 * 检查Set长度
	 * 
	 * @param key
	 * @return
	 */
	public long countSet(String key) {
		if (key == null) {
			return 0;
		}
		try {
			return connectionCluster.scard(key);
		} catch (Exception ex) {
			logger.error("countSet error.", ex);
		}
		return 0;
	}

	/**
	 * 添加到Set中（同时设置过期时间）
	 * 
	 * @param key
	 *            key值
	 * @param seconds
	 *            过期时间 单位s
	 * @param value
	 * @return
	 */
	public boolean addSet(String key, int seconds, String value) {
		boolean result = addSet(key, value);
		if (result) {
			long i = expire(key, seconds);
			return i == 1;
		}
		return false;
	}

	/**
	 * 添加到Set中（同时设置过期时间）
	 * 
	 * @param key
	 *            key值
	 * @param seconds
	 *            过期时间 单位s
	 * @param values
	 * @return
	 */
	public boolean addSet(String key, int seconds, List<String> values) {
		boolean result = addSet(key, values);
		if (result) {
			long i = expire(key, seconds);
			return i == 1;
		}
		return false;
	}

	/**
	 * 批量添加到set中
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public boolean addSet(String key, List<String> values) {
		if (key == null || values == null || values.size() <= 0) {
			return false;
		}
		try {
			connectionCluster.sadd(key, values.toArray(new String[0]));
			return true;
		} catch (Exception ex) {
			logger.error("setList error.", ex);
		}
		return false;
	}

	/**
	 * 添加到Set中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean addSet(String key, String value) {
		if (key == null || value == null) {
			return false;
		}
		try {
			connectionCluster.sadd(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("setList error.", ex);
		}
		return false;
	}

	/**
	 * @param key
	 * @param value
	 * @return 判断值是否包含在set中
	 */
	public boolean containsInSet(String key, String value) {
		if (key == null || value == null) {
			return false;
		}
		try {
			return connectionCluster.sismember(key, value);
		} catch (Exception ex) {
			logger.error("setList error.", ex);
		}
		return false;
	}

	/**
	 * 获取Set
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> getSet(String key) {
		try {
			return connectionCluster.smembers(key);
		} catch (Exception ex) {
			logger.error("getList error.", ex);
		}
		return null;
	}

	/**
	 * 从set中删除value
	 * 
	 * @param key
	 * @return
	 */
	public boolean removeSetValue(String key, String value) {
		try {
			connectionCluster.srem(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("getList error.", ex);
		}
		return false;
	}

	/**
	 * 从list中删除value 默认count 1
	 * 
	 * @param key
	 * @param values
	 *            值list
	 * @return
	 */
	public int removeListValue(String key, List<String> values) {
		return removeListValue(key, 1, values);
	}

	/**
	 * 从list中删除value
	 * 
	 * @param key
	 * @param count
	 * @param values
	 *            值list
	 * @return
	 */
	public int removeListValue(String key, long count, List<String> values) {
		int result = 0;
		if (values != null && values.size() > 0) {
			for (String value : values) {
				if (removeListValue(key, count, value)) {
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * 从list中删除value
	 * 
	 * @param key
	 * @param count
	 *            要删除个数
	 * @param value
	 * @return
	 */
	public boolean removeListValue(String key, long count, String value) {
		try {
			connectionCluster.lrem(key, count, value);
			return true;
		} catch (Exception ex) {
			logger.error("getList error.", ex);
		}
		return false;
	}

	/**
	 * 截取List
	 * 
	 * @param key
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public List<String> rangeList(String key, long start, long end) {
		if (key == null || key.equals("")) {
			return null;
		}
		try {
			return connectionCluster.lrange(key, start, end);
		} catch (Exception ex) {
			logger.error("rangeList 出错[key=" + key + " start=" + start + " end=" + end + "]"
					+ ex.getMessage(), ex);
		}
		return null;
	}

	/**
	 * 检查List长度
	 * 
	 * @param key
	 * @return
	 */
	public long countList(String key) {
		if (key == null) {
			return 0;
		}
		try {
			return connectionCluster.llen(key);
		} catch (Exception ex) {
			logger.error("countList error.", ex);
		}
		return 0;
	}

	/**
	 * 添加到List中（同时设置过期时间）
	 * 
	 * @param key
	 *            key值
	 * @param seconds
	 *            过期时间 单位s
	 * @param value
	 * @return
	 */
	public boolean addList(String key, int seconds, String value) {
		boolean result = addList(key, value);
		if (result) {
			long i = expire(key, seconds);
			return i == 1;
		}
		return false;
	}

	/**
	 * 添加到List
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean addList(String key, String value) {
		if (key == null || value == null) {
			return false;
		}
		try {
			connectionCluster.lpush(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("setList error.", ex);
		}
		return false;
	}

	/**
	 * 添加到List(只新增)
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean addList(String key, List<String> list) {
		if (key == null || list == null || list.size() == 0) {
			return false;
		}
		try {
			connectionCluster.lpush(key, list.toArray(new String[0]));
			return true;
		} catch (Exception ex) {
			logger.error("setList error.", ex);
		}
		return true;
	}

	/**
	 * 获取List
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getList(String key) {
		try {
			return connectionCluster.lrange(key, 0, -1);
		} catch (Exception ex) {
			logger.error("getList error.", ex);
		}
		return null;
	}

	/**
	 * 获取List
	 * 
	 * @param key
	 * @return
	 */
	public List<String> getList(String key, int start, int end) {
		try {
			return connectionCluster.lrange(key, start, end);
		} catch (Exception ex) {
			logger.error("getList error.", ex);
		}
		return null;
	}

	/**
	 * 获取List
	 * 
	 * @param key
	 * @return
	 */
	public Long getListLength(String key) {
		try {
			return connectionCluster.llen(key);
		} catch (Exception ex) {
			logger.error("getList error.", ex);
		}
		return null;
	}

	/**
	 * 设置HashSet对象
	 *
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @param value
	 *            Json String or String value
	 * @return
	 */
	public boolean setHSet(String domain, String key, String value) {
		if (value == null)
			return false;
		try {
			connectionCluster.hset(domain, key, value);
			return true;
		} catch (Exception ex) {
			logger.error("setHSet error.", ex);
		}
		return false;
	}

	/**
	 * 获得HashSet对象
	 *
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @return Json String or String value
	 */
	public String getHSet(String domain, String key) {
		try {
			Object obj = connectionCluster.hget(domain, key);
			if (obj != null) {
				return (String) obj;
			} else {
				return "";
			}

		} catch (Exception ex) {
			logger.error("getHSet error.", ex);
		}
		return null;
	}

	/**
	 * 删除HashSet对象
	 *
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @return 删除的记录数
	 */
	public long delHSet(String domain, String key) {
		long count = 0;
		try {
			count = connectionCluster.hdel(domain, key);
		} catch (Exception ex) {
			logger.error("delHSet error.", ex);
		}
		return count;
	}

	/**
	 * 判断key是否存在
	 *
	 * @param domain
	 *            域名
	 * @param key
	 *            键值
	 * @return
	 */
	public boolean existsHSet(String domain, String key) {
		boolean isExist = false;
		try {
			isExist = connectionCluster.hexists(domain, key);
		} catch (Exception ex) {
			logger.error("existsHSet error.", ex);
		}
		return isExist;
	}

	/**
	 * 返回 domain 指定的哈希集中所有字段的value值
	 *
	 * @param domain
	 * @return
	 */

	public List<String> hvals(String domain) {
		List<String> retList = null;
		try {
			retList = connectionCluster.hvals(domain);
		} catch (Exception ex) {
			logger.error("hvals error.", ex);
		}
		return retList;
	}

	/**
	 * 返回 domain 指定的哈希集中所有字段的key值
	 *
	 * @param domain
	 * @return
	 */

	public Set<String> hkeys(String domain) {
		Set<String> retList = null;
		try {
			retList = (Set<String>) connectionCluster.hkeys(domain);
		} catch (Exception ex) {
			logger.error("hkeys error.", ex);
		}
		return retList;
	}

	/**
	 * 返回 domain 指定的哈希key值总数
	 *
	 * @param domain
	 * @return
	 */
	public long lenHset(String domain) {
		long retList = 0;
		try {
			retList = connectionCluster.hlen(domain);
		} catch (Exception ex) {
			logger.error("hkeys error.", ex);
		}
		return retList;
	}

	/**
	 * 设置排序集合
	 *
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 */
	public boolean setSortedSet(String key, long score, String value) {
		try {
			connectionCluster.zadd(key, score, value);
			return true;
		} catch (Exception ex) {
			logger.error("setSortedSet error.", ex);
		}
		return false;
	}

	/**
	 * 获得排序集合
	 *
	 * @param key
	 * @param startScore
	 * @param endScore
	 * @param orderByDesc
	 * @return
	 */
	// public Set<String> getSoredSet(String key, long startScore, long
	// endScore, boolean orderByDesc)
	// {
	// try {
	// if (orderByDesc) {
	// return jedisCluster.zrevrangeByScore(key, endScore, startScore);
	// } else {
	// return jedisCluster.zrangeByScore(key, startScore, endScore);
	// }
	// } catch (Exception ex) {
	// logger.error("getSoredSet error.", ex);
	// }
	// return null;
	// }

	/**
	 * 计算排序长度
	 *
	 * @param key
	 * @param startScore
	 * @param endScore
	 * @return
	 */
	public long countSoredSet(String key, long startScore, long endScore) {
		try {
			Long count = connectionCluster.zcount(key, startScore, endScore);
			return count == null ? 0L : count;
		} catch (Exception ex) {
			logger.error("countSoredSet error.", ex);
		}
		return 0L;
	}

	/**
	 * 删除排序集合
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean delSortedSet(String key, String value) {
		try {
			long count = connectionCluster.zrem(key, value);
			return count > 0;
		} catch (Exception ex) {
			logger.error("delSortedSet error.", ex);
		}
		return false;
	}

	/**
	 * 获得排序集合
	 *
	 * @param key
	 * @param startRange
	 * @param endRange
	 * @param orderByDesc
	 * @return
	 */
	// public Set<String> getSoredSetByRange(String key, int startRange, int
	// endRange,
	// boolean orderByDesc) {
	// try {
	// if (orderByDesc) {
	// return jedisCluster.zrevrange(key, startRange, endRange);
	// } else {
	// return jedisCluster.zrange(key, startRange, endRange);
	// }
	// } catch (Exception ex) {
	// logger.error("getSoredSetByRange error.", ex);
	// }
	// return null;
	// }

	/**
	 * 获得排序打分
	 *
	 * @param key
	 * @return
	 */
	public Double getScore(String key, String member) {
		try {
			return connectionCluster.zscore(key, member);
		} catch (Exception ex) {
			logger.error("getSoredSet error.", ex);
		}
		return null;
	}

	public boolean set(String key, String value, int second) {
		try {
			connectionCluster.setex(key, second, value);
			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
		}
		return false;
	}

	public boolean set(String key, String value) {

		try {
			connectionCluster.set(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
		}

		return false;
	}

	public boolean setIsOK(String key, String value) {
		try {
			connectionCluster.set(key, value);
			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
		}
		return false;
	}

	public String get(String key, String defaultValue) {
		try {
			return (String) (connectionCluster.get(key) == null ? defaultValue
					: connectionCluster.get(key));
		} catch (Exception ex) {
			logger.error("get error.", ex);
		}
		return defaultValue;
	}

	public String getKey(String key, String defaultValue) {
		try {
			return (String) (connectionCluster.get(key) == null ? defaultValue
					: connectionCluster.get(key));
		} catch (Exception ex) {
			logger.error("get error.", ex);
		}
		return defaultValue;
	}

	public boolean setKey(String key, String value, int second) {
		try {
			connectionCluster.setex(key, second, value);
			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
		}
		return false;
	}

	public boolean del(String key) {
		try {
			connectionCluster.del(key);
			return true;
		} catch (Exception ex) {
			logger.error("del error.", ex);
		}
		return false;
	}

	public Long delKey(String key) {
		try {
			return connectionCluster.del(key);
		} catch (Exception ex) {
			logger.error("del error.", ex);
		}
		return 0L;
	}

	public long incr(String key) {
		try {
			return connectionCluster.incr(key);
		} catch (Exception ex) {
			logger.error("incr error.", ex);
		}
		return 0;
	}

	public long decr(String key) {
		try {
			return connectionCluster.decr(key);
		} catch (Exception ex) {
			logger.error("incr error.", ex);
		}
		return 0;
	}

	/**
	 * @author Alex 加入域内多值存取
	 */
	public boolean hmset(String key, Map<String, String> hash) {
		try {
			connectionCluster.hmset(key, hash);
			return true;
		} catch (Exception ex) {
			logger.error("set error.", ex);
		}
		return false;
	}

	public List<String> hmget(String key, String... fields) {
		try {
			return connectionCluster.hmget(key, fields);
		} catch (Exception ex) {
			logger.error("get error.", ex);
		}
		return new ArrayList<String>();
	}

	public String flushAll() {
		return connectionCluster.flushall();
	}

	public List<String> getKeys(String str) {
		List<String> list = new ArrayList<String>();
		list = connectionCluster.keys(str);
		return list;
	}

	/*
	 * 如果返回true,说明允许当前线程获取锁,允许当前线程做删除锁的操作
	 * 如果返回false,说明不允许当前线程获取锁,当前线程出于等待状态或者直接返回
	 */
	public boolean validateLock(String lockName, String newTime, Long overTime) {
		// ShardedJedis shardedJedis=null;
		try {
			// shardedJedis = shardedJedisPool.getResource();
			String lockTime = (String) connectionCluster.get(lockName);
			if (!StringUtils.isNotBlank(lockTime)) {
				return false;
			}

			Long lockDate = new Long(lockTime);
			Long newDate = new Long(newTime);
			Long over1 = newDate - lockDate;
			if (over1.longValue() < overTime.longValue()) {
				return false;
			}
			String oldTime = (String) connectionCluster.getset(lockName, newTime);
			Long oldDate = new Long(oldTime);
			Long over = newDate - oldDate;
			if (over.longValue() < overTime.longValue()) {
				// 说明刚刚有线程获取了锁
				return false;
			} else {
				// 说明锁超时,在当前线程之前,没有其他线程获取锁,当前线程获取锁成功
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	/*
	 * 获取分布式锁
	 */
	public boolean getLock(String lockName, String time, Long overTime) {
		boolean result = setnx(lockName, time);
		if (result) {
			return validateLock(lockName, time, overTime);
		} else {
			return true;
		}
	}

	/*
	 * 释放锁
	 */
	public boolean releaseLock(String lockName) {
		Long result = delKey(lockName);
		if (result.longValue() == 1L) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setnx(String key, String value) {
		try {
			return connectionCluster.setnx(key, value);
		} catch (Exception ex) {
			logger.error("set error.", ex);
		}
		return false;
	}
}
