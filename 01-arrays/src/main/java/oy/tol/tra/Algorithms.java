package oy.tol.tra;

public class Algorithms {

    public static <T> void reverse(T[] array) {
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        int n = array.length;
        for (int j = 1; j < n; j++) {
            T key = array[j];
            int i = j - 1;
            while (i >= 0 && array[i].compareTo(key) > 0) {
                array[i + 1] = array[i];
                i = i - 1;
            }
            array[i + 1] = key;
        }
    }
}
