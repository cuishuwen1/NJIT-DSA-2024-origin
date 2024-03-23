package oy.tol.tra;

public class Algorithms {
    public static <T>void exchange(T[] arr, int idx1, int idx2){
        T tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    public static <T extends Comparable<T>> void sort(T[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    exchange(arr, j, j + 1);
                }
            }
        }
    }

    public static <T> void reverse(T[] arr){
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            exchange(arr, left, right);
            left++;
            right--;
        }
    }

    public static <T extends Comparable<T>> int binarySearch(T value, T[] arr, int fromIdx, int toIdx) {
        int mid;
        while (fromIdx <= toIdx) {
            mid = fromIdx + (toIdx - fromIdx) / 2;
            if (value.compareTo(arr[mid]) > 0) {
                fromIdx = mid + 1;
            } else if (value.compareTo(arr[mid]) < 0) {
                toIdx = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static <E extends Comparable<E>> void quickSort(E[] arr) {
        quickSortRec(arr, 0, arr.length - 1);
    }

    public static <E extends Comparable<E>> void quickSortRec(E[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int pivot = partition(arr, begin, end);
        quickSortRec(arr, begin, pivot - 1);
        quickSortRec(arr, pivot + 1, end);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int begin, int end) {
        E pivot = arr[begin];
        int left = begin;
        int right = end;
        while (left != right) {
            while ((left < right) && arr[right].compareTo(pivot) > 0) {
                right--;
            }
            while ((left < right) && arr[left].compareTo(pivot) <= 0) {
                left++;
            }
            if (left < right) {
                exchange(arr, left, right);
            }
        }
        arr[begin] = arr[left];
        arr[left] = pivot;
        return left;
    }
}
