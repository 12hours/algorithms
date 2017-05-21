import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nm on 21.5.17.
 */
public class RandomizedQueueTest {
    private RandomizedQueue<Integer> queue;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    @Before
    public void setUp() throws Exception {
        queue = new RandomizedQueue<>();
    }

    @Test
    public void emptyIsEmptyTest() throws Exception {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void emptyIsNotEmptyTest() throws Exception {
        queue.enqueue(42);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void sizeTest() throws Exception {
        assertTrue(queue.size() == 0);

        queue.enqueue(41);
        queue.enqueue(42);
        queue.enqueue(43);
        assertTrue(queue.size() == 3);

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.size() == 0);
    }

    @Test
    public void sampleTest() throws Exception {
        queue.enqueue(42);

        assertTrue(queue.sample() == 42);
        assertTrue(queue.isEmpty() == false);
        assertTrue(queue.size() == 1);
    }

    @Test
    public void manySampleTest() throws Exception {
        queue.enqueue(41);
        queue.enqueue(42);
        queue.enqueue(43);

        Integer sample = queue.sample();
        assertTrue(sample == 41 || sample == 42 || sample == 43);

    }

    @Test(expected = java.lang.NullPointerException.class)
    public void assNull() throws Exception {
        queue.enqueue(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void dequeueFromEmpty() throws Exception {
        queue.dequeue();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void sampleFromEmpty() throws Exception {
        queue.sample();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void endedIteratorAccessTest() throws Exception {
        for (int i = 1; i < 11; i++){
            queue.enqueue(i);
        }

        Iterator<Integer> iter = queue.iterator();
        while (iter.hasNext()){
            iter.next();
        }
        iter.next();
    }

    @Test
    public void iterator() throws Exception {
        Iterator<Integer> iter = queue.iterator();
        assertTrue(false);
    }
}