package ru.clevertec.cache.factory;

import ru.clevertec.cache.Cache;
import ru.clevertec.cache.impl.LFUCache;
import ru.clevertec.cache.impl.LRUCache;
import ru.clevertec.util.YamlReader;

public class CacheFactoryImpl<K, V> implements CacheFactory<K, V>{
    private static final String ALGORITHM_FROM_FILE = YamlReader.getAlgorithm();
    private static final Integer MAX_SIZE_FROM_FILE = YamlReader.getMaxSize();
    private Cache<K, V> cache = null;

    @Override
    public Cache<K, V> createCache() {
        switch (ALGORITHM_FROM_FILE) {
            case "LRU" -> cache = new LRUCache<>(MAX_SIZE_FROM_FILE);
            case "LFU" -> cache = new LFUCache<>(MAX_SIZE_FROM_FILE);
        }
        return cache;
    }
}
