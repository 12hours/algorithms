import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by nm on 10.6.17.
 */
public class SolverTest {
    @Test
    public void testSolvedBoard() throws Exception {
        Solver solver = new Solver(new Board(new int[][] {{1,2,3},{4,5,6},{7,8,0}}));
        System.out.println("Already solved board");
        assertThat(solver.isSolvable(), is(true));
        assertThat(solver.moves(), is(0));
        for (Board b : solver.solution()){
            System.out.println(b);
        }
    }

    @Test
    public void testUnsolvedBoard() throws Exception {
        Solver solver = new Solver(new Board(new int[][] {{8,1,3},{4,0,2},{7,6,5}}));
        System.out.println("Unsolved board test");
        assertThat(solver.isSolvable(), is(true));
        for (Board b : solver.solution()){
            System.out.println(b);
        }
    }

    @Test
    public void testUnsolvableBoard() throws Exception {
        Solver solver = new Solver(new Board(new int[][] {{1,2,3},{4,5,6},{8,7,0}}));
        System.out.println("Unsolvable board test");
        assertThat(solver.isSolvable(), is(false));
        assertThat(solver.moves(), is(-1));
        assertThat(solver.solution(), is(nullValue()));
    }
}