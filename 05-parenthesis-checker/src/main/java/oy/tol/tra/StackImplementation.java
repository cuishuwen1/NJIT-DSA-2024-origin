package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 *
 * Note that you need to implement constructor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with a value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with the given size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

    private Object[] elements;
    private int capacity;
    private int currentIndex = -1;
    private static final int DEFAULT_CAPACITY = 10;

    /** Implement so that
     * - if the size is less than 2, throw StackAllocationException
     * - if the allocation of the array throws with Java exception,
     *   throw StackAllocationException.
     * @param capacity The capacity of the stack.
     * @throws StackAllocationException If cannot allocate room for the internal array.
     */
    public StackImplementation(int capacity) throws StackAllocationException {
        if(capacity < 2){
            throw new StackAllocationException("Capacity must be at least 2.");
        }
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.currentIndex = -1;
    }

    /**
     * Allocates a stack with a default capacity.
     * @throws StackAllocationException
     */
    public StackImplementation() throws StackAllocationException {

        this(DEFAULT_CAPACITY);
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Null element cannot be added to the stack.");
        }
        if (currentIndex + 1 == capacity) {
            try{
                int newCapacity = capacity() * 2;
                Object[] newArray = new Object[newCapacity];
                for (int i = 0; i < capacity; i++) {
                    newArray[i] = elements[i];
                }
                capacity = newCapacity;
                elements = newArray;
            }catch (OutOfMemoryError e){
                throw new StackAllocationException("Stack is full.");
            }
        }
        elements[++currentIndex] = element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Stack is empty.");
        }
        return (E) elements[currentIndex--];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Stack is empty.");
        }
        return (E) elements[currentIndex];
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public void clear() {
        currentIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (var index = 0; index <= currentIndex; index++) {
            builder.append(elements[index].toString());
            if (index < currentIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
