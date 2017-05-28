package sorts;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by nm on 23.5.17.
 */
public class MergeSortSimpleTest {
    int[] array;

    @Before
    public void setUp() throws Exception {
        array = new int[] {4,3,5,7,1,6,2,9,0,8};
    }

    @Test
    public void test() throws Exception {
        MergeSortSimple.sort(array);
        System.out.println(Arrays.toString(array));
    }
}