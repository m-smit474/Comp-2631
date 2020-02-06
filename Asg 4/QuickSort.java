import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int array[] = new int[7];

        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(99);
        }

        System.out.println("This is the original array");
        print(array);

        quickSort(array, 0, array.length);
        System.out.println("This is the sorted array");
        print(array);
    }

    private static void quickSort(int array[], int start, int end) {
        if (end - start <= 1) {
            return;
        }

        int mid = partition(array, start, end);

        quickSort(array, start, mid);
        quickSort(array, mid + 1, end);
    }

    private static int partition(int array[], int start, int end) {
        int pivot = end - 1;
        int hi = end - 2;
        int lo = start;

        while (lo < hi) {

            while (lo < pivot && array[lo] <= array[pivot]) {
                lo++;
            }

            while (hi > start && array[hi] >= array[pivot]) {
                hi--;
            }

            if (lo < hi) {
                swap(array, lo, hi);
            }
        }


        if (pivot != lo) {
            swap(array, lo, pivot);
        }

        return lo;
    }

    private static void swap(int array[], int one, int two) {
        int temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    private static void print(int array[]) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println("\n");
    }

}
