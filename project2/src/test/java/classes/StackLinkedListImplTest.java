package classes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nm on 16.5.17.
 */
public class StackLinkedListImplTest {

    @Test
    public void test1(){
        Stack<String> stack = new StackLinkedListImpl<>();
        stack.push("Hello!");
        stack.push("My");
        stack.push("World!");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}