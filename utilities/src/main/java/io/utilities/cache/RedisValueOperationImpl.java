package io.utilities.cache;

import io.utilities.converter.ConverterStringUntil;
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
        valueOperations.setIfAbsent(key, ConverterStringUntil.converterToString(value));
        LOGGER.info("Push Successful : Key {}", key);
    }

    @Override
    public void pushCache(String key, int time, T value) {
        valueOperations.set(key, ConverterStringUntil.converterToString(value), time, TimeUnit.MILLISECONDS);
        LOGGER.info("Push Successful : Key => {}, Time => {}", key, time);
    }

    @Override
    public void deletedCache(String key) {
        valueOperations.getOperations().delete(key);
        LOGGER.info("Delete Successful {}", key);
    }

    @Override
    public T getValue(String key, Class<T> type) {
        String raw = valueOperations.get(key);
        if (raw == null) {
            return null;
        }
        return ConverterStringUntil.converterStringToObject(raw, type);
    }
}
