package classes;

/**
 * Created by nm on 18.5.17.
 */
public class QueueLinkedListImpl<T> implements Queue<T> {
    private class Node {
        T item;
        Node next;
    }

    Node firstNode;
    Node lastNode;

    public QueueLinkedListImpl() {
        this.firstNode = null;
        this.lastNode = null;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) return null;

        Node nodeToRemove = this.firstNode;
        this.firstNode = nodeToRemove.next;

        return nodeToRemove.item;
    }

    @Override
    public void enqueue(T item) {

        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;

        if (isEmpty()){
            this.firstNode = newNode;
            this.lastNode = newNode;
        } else {
            this.lastNode.next = newNode;
            this.lastNode = newNode;
        }
    }


    @Override
    public boolean isEmpty() {
        return this.firstNode == null;
    }

    @Override
    public int size() {
        return 0;
    }
}
