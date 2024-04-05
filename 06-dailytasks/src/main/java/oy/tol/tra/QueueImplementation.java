package oy.tol.tra;

import java.util.Arrays;

public class QueueImplementation<E> implements QueueInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] itemArray;
    private int capacity;
    private int size = 0;
    private int head = 0;
    private int tail = -1;

    public QueueImplementation() {
        this(DEFAULT_CAPACITY);
    }

    public QueueImplementation(int capacity) {
        if (capacity < 2) {
            throw new IllegalArgumentException("Capacity is too small!");
        }
        this.capacity = capacity;
        itemArray = new Object[capacity];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) {
        if (size == capacity) {
            expandCapacity();
        }
        if (element == null) {
            throw new NullPointerException("Cannot enqueue null element");
        }
        tail = (tail + 1) % capacity;
        itemArray[tail] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        E element = (E) itemArray[head];
        itemArray[head] = null;
        head = (head + 1) % capacity;
        size--;
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return (E) itemArray[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(itemArray, null);
        size = 0;
        head = 0;
        tail = -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int index = head;
        for (int i = 0; i < size; i++) {
            builder.append(itemArray[index]);
            if (i < size - 1) {
                builder.append(", ");
            }
            index = (index + 1) % capacity;
        }
        builder.append("]");
        return builder.toString();
    }

    private void expandCapacity() {
        int newCapacity = capacity * 2 + 1;
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = itemArray[(head + i) % capacity];
        }
        itemArray = newArray;
        head = 0;
        tail = size - 1;
        capacity = newCapacity;
    }
}
