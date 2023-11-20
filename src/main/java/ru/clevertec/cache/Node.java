package ru.clevertec.cache;

import lombok.Data;

/**
 * Класс, представляющий узел двусвязного списка.
 *
 * @param <K> Тип ключа узла.
 * @param <V> Тип значения узла.
 */
@Data
public class Node<K, V> {
    private K key;
    private V value;
    private int frequency;
    private Node<K, V> next;
    private Node<K, V> prev;

    public Node(K key, V value, int frequency) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }
}
