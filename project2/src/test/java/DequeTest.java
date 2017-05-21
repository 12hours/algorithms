import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by nm on 19.5.17.
 */
public class DequeTest {
    private Deque<Integer> deq;

    @Before
    public void setUp() throws Exception {
        deq = new Deque<>();

    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(deq.isEmpty());

        deq.addFirst(42);
        assertFalse(deq.isEmpty());

        deq.removeFirst();
        assertTrue(deq.isEmpty());

        deq.addLast(22);
        assertFalse(deq.isEmpty());

        deq.removeLast();
        assertTrue(deq.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertTrue(deq.size() == 0);

        deq.addFirst(42);
        assertTrue(deq.size() == 1);

        deq.addLast(42);
        assertTrue(deq.size() == 2);

        deq.removeFirst();
        assertTrue(deq.size() == 1);

        deq.removeLast();
        assertTrue(deq.size() == 0);
    }

    @Test
    public void addFirstRemoveFirst() throws Exception {
        deq.addFirst(128);
        deq.addFirst(256);
        deq.addFirst(512);

        assertTrue(deq.removeFirst() == 512);
        assertTrue(deq.removeFirst() == 256);
        assertTrue(deq.removeFirst() == 128);
    }


    @Test
    public void addFirstRemoveLast() throws Exception {
        deq.addFirst(128);
        deq.addFirst(256);
        deq.addFirst(512);

        assertTrue(deq.removeLast() == 128);
        assertTrue(deq.removeLast() == 256);
        assertTrue(deq.removeLast() == 512);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void addFirstNull() {
        deq.addFirst(null);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void addLastNull() {
        deq.addLast(null);
    }

    @Test
    public void addLastRemoveLast() throws Exception {
        deq.addLast(128);
        deq.addLast(256);
        deq.addLast(512);

        assertTrue(deq.removeLast() == 512);
        assertTrue(deq.removeLast() == 256);
        assertTrue(deq.removeLast() == 128);
    }

    @Test
    public void addLastRemoveFirst() throws Exception {
        deq.addLast(128);
        deq.addLast(256);
        deq.addLast(512);

        assertTrue(deq.removeFirst() == 128);
        assertTrue(deq.removeFirst() == 256);
        assertTrue(deq.removeFirst() == 512);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void removeFirstFromEmpty() throws Exception {
        deq.removeFirst();

    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void removeLastFromEmpty() throws Exception {
        deq.removeLast();
    }

    @Test
    public void singleIterator() throws Exception {

        for (int i = 42; i > 0; i--){
            deq.addFirst(i);
        }

        Iterator<Integer> iter = deq.iterator();

        for (int i = 1; iter.hasNext(); i++){
            Integer item = iter.next();
//            System.out.println(String.format("expected = %s, got = %s", i, item));
            assertTrue(item.equals(i));
        }


    }

    @Test
    public void multipleIterators() throws Exception {
        for (int i = 42; i > 0; i--){
            deq.addFirst(i);
        }

        Iterator<Integer> iter1 = deq.iterator();
        for (int i = 1; iter1.hasNext(); i++){
            Integer item1 = iter1.next();
            assertTrue(item1.equals(i));

            Iterator<Integer> iter2 = deq.iterator();
            for (int j = 1; iter2.hasNext(); j++){
                Integer item2 = iter2.next();
                assertTrue(item2.equals(j));
            }

        }
    }

    @Test
    public void emptyToNonEmptyToEmpty() throws Exception {
        deq.addLast(128);
        deq.addFirst(256);
        deq.addLast(512);

        assertTrue(deq.size() != 0);
        assertTrue(deq.isEmpty() == false);

        assertTrue(deq.removeFirst() == 256);
        assertTrue(deq.removeLast() == 512);
        assertTrue(deq.removeFirst() == 128);

        assertTrue(deq.size() == 0);
        assertTrue(deq.isEmpty());

        deq.addLast(12);
        deq.addFirst(25);
        deq.addLast(51);

        assertTrue(deq.size() != 0);
        assertTrue(deq.isEmpty() == false);

        assertTrue(deq.removeFirst() == 25);
        assertTrue(deq.removeLast() == 51);
        assertTrue(deq.removeFirst() == 12);

        assertTrue(deq.size() == 0);
        assertTrue(deq.isEmpty());

    }
}