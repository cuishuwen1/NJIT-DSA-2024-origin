package oy.tol.tra;

public class Algorithms {


    public static <T> void reverse(T[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
    }


    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int begin, int end) {
        if (begin < end) {
            int pivotIndex = partition(array, begin, end);
            quickSort(array, begin, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int begin, int end) {
        T pivot = array[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }


    public static <T extends Comparable<T>> int binarySearch(T value, T[] array, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return -1;
        }

        int mid = (fromIndex + toIndex) / 2;

        if (value.compareTo(array[mid]) == 0) {
            return mid;
        } else if (value.compareTo(array[mid]) < 0) {
            return binarySearch(value, array, fromIndex, mid - 1);
        } else {
            return binarySearch(value, array, mid + 1, toIndex);
        }
    }
}
