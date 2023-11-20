package ru.clevertec.cache;

import lombok.Getter;

@Getter
public class DoublyLinkedList<K, V> {
    private Node<K, V> head;
    private Node<K, V> tail;

    /**
     * Добавляет указанный узел в конец списка.
     * Обновляет ссылки узлов для поддержания структуры двусвязного списка.
     *
     * @param node Узел, который нужно добавить в конец списка.
     */
    public void addToTail(Node<K, V> node) {
        if (tail != null) {
            tail.setNext(node);
        }

        node.setPrev(tail);
        node.setNext(null);
        tail = node;

        if (head == null) {
            head = tail;
        }
    }

    /**
     * Удаляет указанный узел из списка.
     * Обновляет ссылки предыдущего и следующего узлов при необходимости.
     *
     * @param node Узел, который нужно удалить.
     */
    public void removeNode(Node<K, V> node) {

        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            head = node.getNext();
        }

        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            tail = node.getPrev();
        }
    }

    /**
     * Добавляет указанный узел в зависимости от частоты.
     * Обновляет ссылки узлов для поддержания структуры двусвязного списка.
     *
     * @param node Узел, который нужно добавить в конец списка.
     */
    public void addNodeWithUpdatedFrequency(Node<K, V> node) {

        if (tail != null && head != null) {
            Node<K, V> temp = head;
            while (temp != null) {
                if (temp.getFrequency() > node.getFrequency()) {
                    if (temp == head) {
                        node.setNext(temp);
                        temp.setPrev(node);
                        head = node;
                        break;
                    } else {
                        node.setNext(temp);
                        node.setPrev(temp.getPrev());
                        temp.getPrev().setNext(node);
                        node.setPrev(temp.getPrev());
                        break;
                    }
                } else {
                    temp = temp.getNext();
                    if (temp == null) {
                        tail.setNext(node);
                        node.setPrev(tail);
                        node.setNext(null);
                        tail = node;
                        break;
                    }
                }
            }
        } else {
            tail = node;
            head = tail;
        }
    }

}

