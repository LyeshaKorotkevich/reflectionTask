package ru.clevertec.cache.impl;

import ru.clevertec.cache.Cache;
import ru.clevertec.cache.DoublyLinkedList;
import ru.clevertec.cache.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Реализация кэша со стратегией вытеснения "Наиболее давно используемый" (LRU - Least Recently Used).
 *
 * @param <K> Тип ключей в кэше.
 * @param <V> Тип значений в кэше.
 */
public class LRUCache<K, V> implements Cache<K, V> {
    /**
     * Размер кэша по умолчанию.
     */
    public static final Integer DEFAULT_SIZE = 20;

    /**
     * Размер кэша.
     */
    private final Integer size;

    /**
     * Хранилище элементов кэша.
     */
    private Map<K, Node<K, V>> cacheMap;

    /**
     * Двусвязный список для отслеживания порядка использования элементов.
     */
    private DoublyLinkedList<K, V> linkedList;

    /**
     * Конструктор LRU-кэша с размером по умолчанию.
     */
    public LRUCache() {
        this(DEFAULT_SIZE);
    }

    /**
     * Конструктор LRU-кэша с заданным размером.
     *
     * @param cacheSize Максимальное количество элементов, которые может содержать кэш.
     */
    public LRUCache(Integer cacheSize) {
        this.size = cacheSize;
        this.clean();
    }

    @Override
    public void clean() {
        cacheMap = new HashMap<>();
        linkedList = new DoublyLinkedList<>();
    }

    @Override
    public Optional<V> get(K key) {
        if (cacheMap.containsKey(key)) {
            Node<K, V> node = cacheMap.get(key);
            linkedList.removeNode(node);
            linkedList.addToTail(node);
            return Optional.of(node.getValue());
        }
        return Optional.empty();
    }

    /**
     * Связывает указанное значение с указанным ключом в кэше.
     * Если кэш уже содержит ключ, значение будет обновлено.
     * Если кэш заполнен, может произойти вытеснение самой давно использованной записи.
     *
     * @param key   Ключ, с которым ассоциируется указанное значение.
     * @param value Значение, которое будет ассоциировано с указанным ключом.
     */
    @Override
    public void put(K key, V value) {
        if (cacheMap.containsKey(key)) {
            Node<K, V> item = cacheMap.get(key);
            item.setValue(value);

            linkedList.removeNode(item);
            linkedList.addToTail(item);
        } else {
            if (cacheMap.size() >= size) {
                cacheMap.remove(linkedList.getHead().getKey());
                linkedList.removeNode(linkedList.getHead());
            }

            Node<K, V> node = new Node<>(key, value, 1);
            linkedList.addToTail(node);
            cacheMap.put(key, node);
        }
    }

    @Override
    public void remove(K key) {
        if (cacheMap.containsKey(key)) {
            Node<K, V> nodeToRemove = cacheMap.get(key);
            linkedList.removeNode(nodeToRemove);
            cacheMap.remove(key);
        }
    }
}
