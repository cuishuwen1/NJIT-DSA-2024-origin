package oy.tol.tra;

/**
 * For searching a number from an array of integers or a string from an array of strings.
 *
 * @author Antti Juustila
 * @version 1.1
 */
public class SearchArray {

    private SearchArray() {

    }

    /**
     * Finds a number from the specified array using binary search, -1 if one could not be found.
     *
     * @param <T>      The type of elements in the array, must implement Comparable interface.
     * @param aValue   The value to find.
     * @param fromArray The array to search.
     * @param fromIndex The index to start searching from.
     * @param toIndex  The index to search to in the array.
     * @return The index of the number in the array, -1 if not found.
     */
    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = fromArray[mid];
            int cmp = midVal.compareTo(aValue);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Finds a number from the specified array using linear search, -1 if one could not be found.
     *
     * @param aValue    The value to find.
     * @param fromArray The array to search.
     * @param fromIndex The index to start searching from.
     * @param toIndex   The index to search to in the array.
     * @return The index of the number in the array, -1 if not found.
     */
    public static int slowLinearSearch(Integer aValue, Integer[] fromArray, int fromIndex, int toIndex) {
        for (int index = fromIndex; index < toIndex; index++) {
            if (fromArray[index].equals(aValue)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Finds a String from the specified array using linear search, -1 if one could not be found.
     *
     * @param aValue    The value to find.
     * @param fromArray The array to search.
     * @param fromIndex The index to start searching from.
     * @param toIndex   The index to search to in the array.
     * @return The index of the number in the array, -1 if not found.
     */
    public static int slowLinearSearch(String aValue, String[] fromArray, int fromIndex, int toIndex) {
        for (int index = fromIndex; index < toIndex; index++) {
            if (fromArray[index].equals(aValue)) {
                return index;
            }
        }
        return -1;
    }
}

