package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {

    private Object[] elements;
    private int capacity;
    private int topIndex = -1;
    private static final int DEFAULT_CAPACITY = 10;

    public StackImplementation() throws StackAllocationException {
        this(DEFAULT_CAPACITY);
    }

    public StackImplementation(int capacity) throws StackAllocationException {
        if (capacity < 2) {
            throw new StackAllocationException("Capacity must be at least 2.");
        }

        try {
            elements = new Object[capacity];
            this.capacity = capacity;
        } catch (Exception e) {
            throw new StackAllocationException("Failed to allocate stack memory.");
        }
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {
        if (topIndex + 1 >= capacity) {
            // If the stack is full, double its capacity.
            int newCapacity = capacity * 2;
            try {
                Object[] newArray = new Object[newCapacity];
                System.arraycopy(elements, 0, newArray, 0, capacity);
                elements = newArray;
                capacity = newCapacity;
            } catch (Exception e) {
                throw new StackAllocationException("Failed to reallocate stack memory.");
            }
        }

        if (element == null) {
            throw new NullPointerException("Cannot push null element onto the stack.");
        }

        topIndex++;
        elements[topIndex] = element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Cannot pop from an empty stack.");
        }

        E poppedElement = (E) elements[topIndex];
        topIndex--;

        return poppedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Cannot peek into an empty stack.");
        }

        return (E) elements[topIndex];
    }

    @Override
    public int size() {
        return topIndex + 1;
    }

    @Override
    public void clear() {
        topIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i <= topIndex; i++) {
            builder.append(elements[i].toString());
            if (i < topIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
