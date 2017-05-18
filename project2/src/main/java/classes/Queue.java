package classes;

/**
 * Created by nm on 18.5.17.
 */
public interface Queue<T> {
    T dequeue();
    void enqueue(T item);
    boolean isEmpty();
    int size();
}
