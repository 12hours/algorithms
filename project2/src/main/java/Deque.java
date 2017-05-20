import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node{
        Node next;
        Node prev;
        Item item;
    }

    private Node firstNode;
    private Node lastNode;
    private int size;

    /**
     * construct an empty deque
     */
    public Deque() {
        this.firstNode = null;
        this.lastNode = null;
    }

    /**
     * @return is the deque empty?
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @return the number of items on the deque
     */
    public int size() {
        return this.size;
    }

    /**
     * @param item
     * add the item to the front
     */
    public void addFirst(Item item) {
        if (item == null){
            throw new java.lang.NullPointerException();
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = this.firstNode;
        newNode.prev = null;
        this.firstNode = newNode;
        if (isEmpty()){
            this.lastNode = newNode;
        }
        this.size++;
    }

    /**
     * @param item
     * add the item to the end
     */
    public void addLast(Item item) {
        if (item == null){
            throw new java.lang.NullPointerException();
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        newNode.prev = lastNode;
        this.lastNode = newNode;
        if (isEmpty()){
            this.firstNode = newNode;
        }
        this.size++;
    }

    /**
     * @return
     * remove and return the item from the front
     */
    public Item removeFirst() {
        if (isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        Node nodeToRemove = this.firstNode;
        this.firstNode = nodeToRemove.next;
        this.size--;
        return nodeToRemove.item;
    }

    /**
     * @return
     * remove and return the item from the end
     */
    public Item removeLast() {
        if (isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        Node nodeToRemove = this.lastNode;
        this.lastNode = nodeToRemove.prev;
        this.size--;
        return nodeToRemove.item;
    }

    /**
     * @return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        Iterator<Item> iter = new Iterator<Item>() {
            private Node current = firstNode;


            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Node next = current;
                current = current.next;
                return next.item;
            }
        };
        return iter;
    }

    public static void main(String[] args) {
    }
}