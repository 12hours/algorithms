package classes;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by nm on 16.5.17.
 */
public class StackArrayImpl<T> implements Stack<T> {
    private T[] array;
    private int nextIndex;


    public StackArrayImpl() {
        this.array = (T[]) new Object[2];
        this.nextIndex = 0;
    }

    public StackArrayImpl(T[] array) {
        this.array = array;
        this.nextIndex = 0;
    }

    public StackArrayImpl(T[] array, int nextIndex) {
        this.array = array;
        this.nextIndex = nextIndex;
    }

    @Override
    public void push(T item) {
        if (nextIndex == this.array.length - 1) {
//            this.array = Arrays.copyOf(this.array, this.array.length * 2);
//            this.array = doubleArray((Class<T>) item.getClass(), this.array.length);
            resize(this.array.length * 2);
        }
        this.array[nextIndex++] = item;

    }

    private T[] doubleArray(Class<T> clazz, int len) {
        T[] arr = (T[]) Array.newInstance(clazz, len * 2);
        for (int i = 0; i < nextIndex; i++) {
            arr[i] = this.array[i];
        }
        return arr;
    }

    private void resize(int len){
        T[] tempArray = (T[]) new Object[len];
        for (int i = 0; i < nextIndex; i++) {
            tempArray[i] = this.array[i];
        }
        this.array = tempArray;
    }


    @Override
    public T pop() {
        if (nextIndex == 0) return null;

        if (nextIndex == this.array.length/4){
//            this.array = doubleArray(this.getClass().getComponentType(), this.array.length/2);
            resize(this.array.length /2);
        }



        T itemToReturn = this.array[--nextIndex];
        this.array[nextIndex] = null;
        return itemToReturn;
    }

    @Override
    public boolean isEmpty() {
        if (nextIndex == 0) return true;
        else return false;
    }

    @Override
    public int size() {
        return 0;
    }

    public static void main(String[] args) {
        Stack<String> stack = new StackArrayImpl<String>(new String[2]);
        stack.push("Hello");
        stack.push("my");
        stack.push("world");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
