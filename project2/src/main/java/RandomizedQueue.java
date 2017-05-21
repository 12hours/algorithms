import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int size;
    private int next_empty;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
        size = 0;
        next_empty = 0;
    }

    /**
     * @return is the queue empty?
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @return return the number of items on the queue
     */
    public int size() {
        return size;
    }

    /**
     * @param item add the item
     */
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        if (this.next_empty == this.array.length || this.size < this.array.length / 4) {
            resize(this.size * 2);
            this.next_empty = this.size + 1;
        }
        this.array[this.next_empty] = item;
        this.size++;
        this.next_empty++;

    }

    private void resize(int new_size) {
        Item[] new_array = (Item[]) new Object[new_size];
        for (int i = 0, j = 0; i < this.next_empty; i++) {
            if (this.array[i] != null) {
                new_array[j] = this.array[i];
                j++;
            }
        }
        this.array = new_array;
    }


    /**
     * @return remove and return a random item
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int random_index;
        do {
            random_index = StdRandom.uniform(0, this.next_empty);
        } while (this.array[random_index] == null);
        Item item_to_return = this.array[random_index];
        this.array[random_index] = null;
        this.size--;
        return item_to_return;
    }

    /**
     * @return (but do not remove) a random item
     */
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        int random_index;
        do {
            random_index = StdRandom.uniform(0, this.next_empty);
        } while (this.array[random_index] == null);

        return this.array[random_index];
    }

    /**
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        Item[] indicies = (Item[]) new Object[this.size];
        for (int i = 0, j = 0; i < this.next_empty; i++) {
            if (this.array[i] != null) {
                indicies[j] = this.array[i];
                j++;
            }
        }
        StdRandom.shuffle(indicies);

        Iterator<Item> iterator = new Iterator<Item>() {
            private Item[] randomItems = indicies;
            private int next = 0;

            @Override
            public boolean hasNext() {
                return next < randomItems.length;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return randomItems[next++];
            }
        };
        return iterator;
    }

    public static <T> void main(String[] args) {
    }

}