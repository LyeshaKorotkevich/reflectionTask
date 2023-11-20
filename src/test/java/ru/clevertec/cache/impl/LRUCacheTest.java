package ru.clevertec.cache.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.cache.Cache;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    private Cache<Integer, String> cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache<>(3);
    }

    @Test
    void newlyCreatedCacheIsEmpty() {
        assertTrue(cache.get(1).isEmpty());
    }

    @Test
    void canPutAndGetElement() {
        // given
        String expected = "One";
        cache.put(1, "One");

        // when
        String actual = cache.get(1).get();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void puttingSameKeyUpdatesValue() {
        // given
        String expected = "New One";
        cache.put(1, "One");
        cache.put(1, "New One");

        // when
        String actual = cache.get(1).get();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void evictsLeastRecentlyUsedElementWhenFull() {
        // given
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.put(4, "Four");

        // when
        Optional<String> actual = cache.get(1);

        // then
        assertTrue(actual.isEmpty()); // Asserting that key 2 is evicted
    }

    @Test
    void canRemoveElementFromCache() {
        // given
        cache.put(1, "One");

        // when
        cache.remove(1);

        // then
        assertTrue(cache.get(1).isEmpty());
    }
}
