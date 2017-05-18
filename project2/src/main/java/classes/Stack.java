package classes;

/**
 * Created by nm on 16.5.17.
 */
public interface Stack<T> {
    void push(T item);
    T pop();
    boolean isEmpty();
    int size();
}


