package classes.sortings;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by nm on 20.5.17.
 */
public class InsertionSortTest {

    static IntArraySort sortClass;

    @BeforeClass
    public static void setSortingClass(){
        sortClass = new InsertionSort();
    }
    private int[] testArray;

    @Before
    public void setTestArray(){
        testArray = new int[] {2, 8, 1, 10, 5, 7, 3, 0, 9, 6, 4};
    }

    @Test
    public void testSort(){
        sortClass.sort(testArray);
        System.out.println(Arrays.toString(testArray));
    }
}