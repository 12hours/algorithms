import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by nm on 19.5.17.
 */
public class DequeTest {
    @Test
    public void isEmpty() throws Exception {
        Deque<Integer> deq = new Deque<>();
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
        Deque<Integer> deq = new Deque<>();
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
    public void addFirst() throws Exception {
        Deque<Integer> deq = new Deque<>();
        deq.addFirst(128);
        deq.addFirst(256);
        deq.addFirst(512);

        assertTrue(deq.removeFirst() == 512);
        assertTrue(deq.removeFirst() == 256);
        assertTrue(deq.removeFirst() == 128);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void addFirstNull() {
        Deque<Integer> deq = new Deque<>();
        deq.addFirst(null);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void addLastNull() {
        Deque<Integer> deq = new Deque<>();
        deq.addLast(null);
    }

    @Test
    public void addLast() throws Exception {
        Deque<Integer> deq = new Deque<>();
        deq.addLast(128);
        deq.addLast(256);
        deq.addLast(512);

        assertTrue(deq.removeLast() == 512);
        assertTrue(deq.removeLast() == 256);
        assertTrue(deq.removeLast() == 128);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void removeFirst() throws Exception {
        Deque<Integer> deq = new Deque<>();
        deq.removeFirst();

    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void removeLast() throws Exception {
        Deque<Integer> deq = new Deque<>();
        deq.removeLast();
    }

    @Test
    public void iterator() throws Exception {
        Deque<Integer> deq = new Deque<>();

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
}