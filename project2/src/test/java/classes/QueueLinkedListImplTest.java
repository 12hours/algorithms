package classes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nm on 18.5.17.
 */
public class QueueLinkedListImplTest {
    @Test
    public void testIfEmpty(){
        Queue<Integer> q = new QueueLinkedListImpl<>();
        assertTrue(q.isEmpty());
    }

    @Test
    public void testIfNotEmpty(){
        Queue<Integer> q = new QueueLinkedListImpl<>();
        q.enqueue(22);
        assertFalse(q.isEmpty());
    }

    @Test
    public void testPushLotOfItems(){
        Queue<Integer> q = new QueueLinkedListImpl<>();
        for (int i = 0; i < 128; i++){
            q.enqueue(i);
        }
    }

    @Test
    public void testPushAndPopLotOfItems(){
        Queue<Integer> q = new QueueLinkedListImpl<>();
        for (int i = 0; i < 128; i++){
            q.enqueue(i);
        }

        for (int i = 0; i < 128; i++){
            assertTrue(q.dequeue().equals(i));
        }
    }

    @Test
    public void testPopFromEmpty(){
        Queue<Integer> q = new QueueLinkedListImpl<>();
        assertTrue(q.dequeue() == null);
    }

    @Test
    public void testPopFromEmpty2(){
        Queue<Integer> q = new QueueLinkedListImpl<>();
        for (int i = 0; i < 10; i++){
            q.enqueue(i);
        }
        for (int i = 0; i < 10; i++){
            q.dequeue();
        }
        assertTrue(q.dequeue() == null);
    }

}