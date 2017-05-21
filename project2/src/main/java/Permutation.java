import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by nm on 21.5.17.
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        int numberToRead = Integer.decode(args[0]);
        while (numberToRead > 0){
            String input = StdIn.readString();
            queue.enqueue(input);
            numberToRead--;
        }

        for (String str: queue){
            StdOut.println(str);
        }
    }
}
