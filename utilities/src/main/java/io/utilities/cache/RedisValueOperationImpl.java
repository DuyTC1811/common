package io.utilities.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisValueOperationImpl<T> implements IRedisValueOperation<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisValueOperationImpl.class);
    private final ValueOperations<String, String> valueOperations;

    public RedisValueOperationImpl(final RedisTemplate<String, String> template) {
        this.valueOperations = template.opsForValue();
    }

    @Override
    public void pushCache(String key, T value) {
        valueOperations.setIfAbsent(key, value.toString());
        LOGGER.info("Push Successful : Key {}, Data {}", key, value);
    }

    @Override
    public void pushCache(String key, int time, T value) {
        valueOperations.set(key, value.toString(), time, TimeUnit.MILLISECONDS);
        LOGGER.info("Push Successful : Key => {}, Time => {}, Data => {}", key, time, value);
    }

    @Override
    public void deletedCache(String key) {
        valueOperations.getOperations().delete(key);
        LOGGER.info("Delete Successful {}", key);
    }

    @Override
    public T getValue(String key) {
        return (T) valueOperations.get(key);
    }
}
