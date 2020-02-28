package web.boot.action.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author z
 */
@Service
public class RedisService<String, T extends Serializable> {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;

    public void set(String key, T value, Class<T> tClass) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(tClass));
        ValueOperations<String, T> vo = redisTemplate.opsForValue();
        vo.set(key, value);
    }

    public void set(String key, T value, Class<T> tClass, Long time, TimeUnit t) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(tClass));
        ValueOperations<String, T> vo = redisTemplate.opsForValue();
        vo.set(key, value, time, t);
    }

    public T get(String key) {
        ValueOperations<String, T> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
}
