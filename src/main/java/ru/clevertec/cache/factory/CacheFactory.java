package ru.clevertec.cache.factory;

import ru.clevertec.cache.Cache;

public interface CacheFactory<K, V> {
    Cache<K, V> createCache();
}
