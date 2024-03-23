package oy.tol.tra;


public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {



    private Pair<K, V>[] entries = null;
    private int count = 0;
    private int collisionCount = 0;
    private int maxProbingSteps = 0;
    private int reallocationCount = 0;
    private static final double LOAD_FACTOR = 0.45;
    private static final int DEFAULT_CAPACITY = 20;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        // Assuming capacity means the count of elements to add, so multiplying by fill factor.
        entries = (Pair<K, V>[]) new Pair[(int) ((double) capacity * (1.0 + LOAD_FACTOR))];
        reallocationCount = 0;
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
    }

    @Override
    public int size() {
        return count;
    }

    /**
     * Prints out the statistics of the hash table.
     * Here you should print out member variable information which tell something
     * about your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class (int counters) in add() whenever a collision
     * happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function is not good.
     */
    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hash table load factor is %.2f%n", LOAD_FACTOR));
        builder.append(String.format("Hash table capacity is %d%n", entries.length));
        builder.append(String.format("Current fill rate is %.2f%%%n", (count / (double)entries.length) * 100.0));
        builder.append(String.format("Hash table had %d collisions when filling the hash table.%n", collisionCount));
        builder.append(String.format("Hash table had to probe %d times in the worst case.%n", maxProbingSteps));
        builder.append(String.format("Hash table had to reallocate %d times.%n", reallocationCount));
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        int index = key.hashCode() % entries.length;
        int originalIndex = index;
        boolean isNewAddition = false;
        int steps = 0;
        while (entries[index] != null && !entries[index].getKey().equals(key)) {
            index = (index + 1) % entries.length;
            steps++;
            if (index == originalIndex) {
                throw new OutOfMemoryError("Hash table is full.");
            }
        }

        if (entries[index] == null) {
            count++;
            isNewAddition = true;
        }

        entries[index] = new Pair<>(key, value);
        maxProbingSteps = Math.max(maxProbingSteps, steps);

        if (steps > 0) collisionCount++;

        if (((double) count / entries.length) > LOAD_FACTOR) {
            reallocate((int) (entries.length * (1.0 / LOAD_FACTOR)));
        }

        return isNewAddition;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");

        int index = key.hashCode() % entries.length;
        int originalIndex = index;

        while (entries[index] != null && !entries[index].getKey().equals(key)) {
            index = (index + 1) % entries.length;
            if (index == originalIndex) {
                return null;
            }
        }

        return entries[index] != null ? entries[index].getValue() : null;
    }



    @SuppressWarnings("unchecked")
    @Override
    public Pair<K, V>[] toSortedArray() {
        Pair<K, V>[] pairs = (Pair<K, V>[]) new Pair[count];
        int index = 0;
        for (Pair<K, V> entry : entries) {
            if (entry != null) {
                pairs[index++] = entry;
            }
        }

        quickSort(pairs, 0, count - 1);
        return pairs;
    }


    private void quickSort(Pair<K, V>[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Pair<K, V>[] arr, int low, int high) {
        Pair<K, V> pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].getKey().compareTo(pivot.getKey()) <= 0) {
                i++;
                Pair<K, V> temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Pair<K, V> temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }









    @SuppressWarnings("unchecked")
    private void reallocate(int newSize) throws OutOfMemoryError {
        if (newSize < DEFAULT_CAPACITY) {
            newSize =        DEFAULT_CAPACITY;
        }
        reallocationCount++;
        Pair<K, V>[] oldEntries = entries;
        this.entries = (Pair<K, V>[]) new Pair[(int)((double)newSize * (1.0 + LOAD_FACTOR))];
        count = 0;
        collisionCount = 0;
        maxProbingSteps = 0;
        for (int index = 0; index < oldEntries.length; index++) {
            if (oldEntries[index] != null) {
                add(oldEntries[index].getKey(), oldEntries[index].getValue());
            }
        }
    }

    @Override
    public void compress() throws OutOfMemoryError {
        int newCapacity = (int)(count * (1.0 / LOAD_FACTOR));
        if (newCapacity < entries.length) {
            reallocate(newCapacity);
        }
    }









}

