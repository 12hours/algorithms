package classes;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nm on 18.5.17.
 */
public class StackArrayImplTest {

    @Test
    public void testThreeStrings(){
        Stack<String> stack = new StackArrayImpl<String>();
        stack.push("hello1");
        stack.push("hello2");
        stack.push("hello3");

        assertTrue(stack.pop().equals("hello3"));
        assertTrue(stack.pop().equals("hello2"));
        assertTrue(stack.pop().equals("hello1"));
    }

    @Test
    public void testEmpty(){
        Stack<String> stack = new StackArrayImpl<String>();
        assertTrue(stack.pop() == null);
        assertTrue(stack.pop() == null);
    }

    @Test
    public void testPushLotOfIntegers(){
        Stack<Integer> stack = new StackArrayImpl<>();
        for (int i = 0; i < 65; i++){
            stack.push(new Integer(i));
        }
    }

    @Test
    public void testPushAndPopLotOfIntegers() {
        Stack<Integer> stack = new StackArrayImpl<>();
        for (int i = 0; i <= 65; i++) {
            stack.push(new Integer(i));
        }

        for (int i = 65; i >= 0; i--){
            assertTrue(stack.pop().equals(i));
        }
    }

    @Test
    public void testIfEmpty(){
        Stack<String> stack = new StackArrayImpl<String>();
        assertTrue(stack.isEmpty() == true);
        stack.push("test");
        stack.pop();
        assertTrue(stack.isEmpty() == true);
    }

    @Test
    public void testIfNotEmpty() {
        Stack<String> stack = new StackArrayImpl<String>();
        stack.push("test");
        assertTrue(stack.isEmpty() == false);
    }

}