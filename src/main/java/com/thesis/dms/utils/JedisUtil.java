package com.thesis.dms.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class JedisUtil {

    private final JedisPool jedisPool;

    public String getDataRedisByKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                return jedis.get(key);
            }
            // Return empty string mean key doesn't exist in Redis
            log.warn("Redis is not contain key={}", key);
            return "";
        } catch (Exception e) {
            log.error("Cannot get data with key {} from Redis", key, e);
            return null;
        }
    }
    public void cacheRedisData(String key, long timeLast, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.psetex(key, timeLast, value);
            log.debug("Set key={} in Redis with value={}, status={}", key, value, result);
        } catch (Exception e) {
            log.error("Cannot insert data into Redis with key={} with value: {}", key, value, e);
        }
    }

    public void removeCachedData(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        } catch (Exception e) {
            log.error("Delete redis data with key {} FAILED ", key, e);
        }
    }

    public void removeCachedData(Set<String> keys) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(keys.toArray(new String[keys.size()]));
        } catch (Exception e) {
            log.error("Delete redis data with Key Set [{}] FAILED ", keys, e);
        }
    }

    public void cacheMapInRedis(String key, HashMap<String,String> value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hmset(key, value);
        } catch (Exception e) {
            log.error("Set map redis data with key {} FAILED ", key, e);
        }
    }

    public Map<String,String> getMapDataInRedis(String key) {
        Map<String,String> data = new HashMap<>();
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            log.error("Get map redis data with key {} FAILED ", key, e);
            return data;
        }
    }
    public void cacheHashFieldValue (String key, String field, String value) {
        try (Jedis jedis = jedisPool.getResource()){
            jedis.hset(key, field, value);
        } catch (Exception e) {
            log.error("Set field value Redis data with key {}, field {} FAILED", key, field, e);
        }
    }

    public Set<String> getAllKeyByPattern(String pattern) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys(pattern);
        } catch (Exception e) {
            log.error("Cannot get data with pattern {} from Redis", pattern, e);
            return null;
        }
    }

    public String getValueByKeyAndField(String key, String field) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(key, field);
        } catch (Exception e) {
            log.error("Cannot get data with key {}, field {} from Redis", key, field, e);
            return null;
        }
    }
}
