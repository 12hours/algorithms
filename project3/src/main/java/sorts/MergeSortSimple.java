package sorts;

/**
 * Created by nm on 23.5.17.
 */
public class MergeSortSimple {

    public static void sort(int[] array) {
        int[] aux = new int[array.length];
        sort(array, aux, 0, array.length - 1);
    }

    private static boolean less(int i, int i1) {
        return i < i1;
    }

    private static void sort(int[] array, int[] aux, int lo, int hi) {
        if (hi <= lo){
            return;
        }

        int mid = lo + (hi - lo)/2;
        sort(array, aux, lo, mid);
        sort(array, aux, mid+1, hi);
        merge(array, aux, lo, mid, hi);
    }

    public static void merge(int[] a, int[] aux, int lo, int mid, int hi){

        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++){
            if      (i > mid)               a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }

    }

    public static void main(String[] args) {

    }
}
