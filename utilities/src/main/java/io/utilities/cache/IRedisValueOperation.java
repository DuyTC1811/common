package io.utilities.cache;

public interface IRedisValueOperation<T> {
    void pushCache(String key, T value);

    void pushCache(String key, int time, T value);

    void deletedCache(String key);

    T getValue(String key);
}
