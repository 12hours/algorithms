package classes;

/**
 * Created by nm on 16.5.17.
 */
public class StackLinkedListImpl<T> implements Stack<T> {
    private class Item<T> {
        private Item<T> next;
        private T item;

        public Item<T> getNext() {
            return next;
        }

        public T getItem() {
            return item;
        }

        public Item(T item, Item<T> next) {
            this.item = item;
            this.next = next;
        }

    }

    Item<T> firstItem;

    public StackLinkedListImpl() {
        firstItem = null;
    }

    @Override
    public void push(T item) {
        Item<T> newItem = new Item<T>(item, firstItem);
        firstItem = newItem;
    }

    @Override
    public T pop() {
        if (isEmpty())
            return null;
        Item<T> itemToReturn = firstItem;
        firstItem = itemToReturn.getNext();

        return itemToReturn.getItem();
    }

    @Override
    public boolean isEmpty() {
        return firstItem == null;
    }

    @Override
    public int size() {
        return 0;
    }
}
