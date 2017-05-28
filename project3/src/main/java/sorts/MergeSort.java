package sorts;

/**
 * Created by nm on 24.5.17.
 */
public class MergeSort {
    public static void sort(int[] array){
        sort(array, new int[array.length], 0, array.length - 1);
    }

    private static void sort(int[] array, int[] aux, int lo, int hi) {
        if (lo >= hi)   return;

        int mid = lo + (hi - lo)/2;

        sort(array, aux, lo, mid);
        sort(array, aux, mid+1, hi);
        merge(array, aux, lo, mid, hi);

    }

    public static void merge(int[] array, int[] aux, int lo, int mid, int hi){
        for (int i = lo; i <= hi; i++){
            aux[i] = array[i];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            if      (i > mid)           array[k] = aux[j++];
            else if (j > hi)            array[k] = aux[i++];
            else if (aux[i] <= aux[j])  array[k] = aux[i++];
            else                        array[k] = aux[j++];
        }
    }
}
