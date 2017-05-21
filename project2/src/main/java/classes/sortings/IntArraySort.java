package classes.sortings;

/**
 * Created by nm on 20.5.17.
 */
public interface IntArraySort {
    default void swap(int[] array, int index_a, int index_b){
        int temp = array[index_a];
        array[index_a] = array[index_b];
        array[index_b] = temp;
    }

    void sort(int[] array);

}
