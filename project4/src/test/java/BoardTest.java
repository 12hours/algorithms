import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nm on 9.6.17.
 */
public class BoardTest {

    @Test
    public void testHammingHeuristic() throws Exception {
        Board board1 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertThat(board1.hamming(), is(0));

        Board board2 = new Board(new int[][]{{3, 1, 2}, {4, 5, 6}, {7, 8, 9}});
        assertThat(board2.hamming(), is(3));

        Board board3 = new Board(new int[][]{{3, 5, 6}, {4, 1, 2}, {7, 8, 9}});
        assertThat(board3.hamming(), is(5));
    }

    @Test
    public void testManhattanHeuristic() throws Exception {
        Board board1 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertThat(board1.manhattan(), is(0));

        Board board2 = new Board(new int[][]{{4, 2, 3}, {1, 5, 6}, {7, 8, 9}});
        assertThat(board2.manhattan(), is(2));

        Board board3 = new Board(new int[][]{{8, 2, 3}, {4, 5, 6}, {7, 1, 9}});
        assertThat(board3.manhattan(), is(6));
    }

    @Test
    public void testIfSolved() throws Exception {
        Board board1 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        assertThat(board1.isGoal(), is(true));

        Board board2 = new Board(new int[][]{{0, 2, 3}, {4, 5, 6}, {7, 8, 1}});
        assertThat(board2.isGoal(), is(false));
    }

    @Test
    public void testTwin() throws Exception {
        Board board1 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        Board twin = board1.twin();

        System.out.println("Original " + board1);
        System.out.println("Twin " + twin);

    }

    @Test
    public void testNeighbors() throws Exception {
        Board board1 = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        Set<Board> neighbors = (Set<Board>) board1.neighbors();
        System.out.println("Initial " + board1 + "\n" + "Neighbors:\n");

        for (Board b : neighbors){
            System.out.println(b);
        }
        assertTrue(neighbors.size() == 2);

        Board board2 = new Board(new int[][]{{1, 2, 3}, {4, 0, 5}, {6, 7, 8}});
        Set<Board> neighbors2 = (Set<Board>) board2.neighbors();
        System.out.println("Initial " + board2 + "\n" + "Neighbors:\n");

        for (Board b : neighbors2){
            System.out.println(b);
        }
        assertThat(neighbors2.size(),  is(4));

        Board board3 = new Board(new int[][]{{1, 2, 0}, {3, 4, 5}, {6, 7, 8}});
        Set<Board> neighbors3 = (Set<Board>) board3.neighbors();
        System.out.println("Initial " + board3 + "\n" + "Neighbors:\n");

        for (Board b : neighbors3){
            System.out.println(b);
        }
        assertTrue(neighbors3.size() == 2);


    }
}