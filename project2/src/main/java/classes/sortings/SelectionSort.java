package classes.sortings;

/**
 * Created by nm on 20.5.17.
 */
public class SelectionSort implements IntArraySort {

    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++){
            int minimal = i;
            for(int j = i+1; j < array.length; j++){
                if (array[j] < array[minimal]){
                    minimal = j;
                }
            }
            swap(array, i, minimal);
        }
    }
}
