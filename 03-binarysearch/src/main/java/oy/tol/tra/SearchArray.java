package oy.tol.tra;

/**
 * For searching a number from an array of integers.
 *
 * @author Antti Juustila
 * @version 1.0
 */
public class SearchArray {

    private SearchArray() {
        // Empty
    }

    /**
     * Finds a number from the specified array using linear search, -1 if one could not be found.
     * @param value The value to find.
     * @param array The array to search.
     * @param fromIndex The index to start searching from.
     * @param toIndex The index to search to in the array.
     * @return The index of the number in the array, -1 if not found.
     */
    public static int slowLinearSearch(Integer value, Integer [] array, int fromIndex, int toIndex) {
        return linearSearch(value, array, fromIndex, toIndex);
    }

    /**
     * Finds a String from the specified array using linear search, -1 if one could not be found.
     * @param value The value to find.
     * @param array The array to search.
     * @param fromIndex The index to start searching from.
     * @param toIndex The index to search to in the array.
     * @return The index of the number in the array, -1 if not found.
     */
    public static int slowLinearSearch(String value, String [] array, int fromIndex, int toIndex) {
        return linearSearch(value, array, fromIndex, toIndex);
    }

    private static <T> int linearSearch(T value, T[] array, int fromIndex, int toIndex) {
        for (int index = fromIndex; index < toIndex; index++) {
            if (array[index].equals(value)) {
                return index;
            }
        }
        return -1;
    }
}
