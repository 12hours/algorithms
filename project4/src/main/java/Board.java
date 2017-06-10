import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by nm on 8.6.17.
 */
public class Board {
    private int[][] boardBlocks;
    private int N;

    /**
     * construct a boardBlocks from an n-by-n array of blocks
     *
     * @param blocks blocks[i][j] = block in row i, column j
     */
    public Board(int[][] blocks) {
        this.boardBlocks = blocks;
        N = blocks.length;
    }

    /**
     * @return boardBlocks dimension n
     */
    public int dimension() {
        return N;
    }

    /**
     * @return number of blocks out of place
     */
    public int hamming() {
        int outNumber = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (boardBlocks[i][j] != N* i + j + 1) {
                    outNumber++;
                }
            }
        }
        return outNumber;
    }

    /**
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int dist = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int number = boardBlocks[i][j];
                int targetI = (number - 1) / N;
                int targetJ = (number - 1) % N;

                dist += Math.abs(i - targetI);
                dist += Math.abs(j - targetJ);
            }
        }

        return dist;
    }

    /**
     * @return is this boardBlocks the goal boardBlocks?
     */
    public boolean isGoal() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!(i == N - 1 && j == N - 1)) {
                    if (boardBlocks[i][j] != (i * N + j + 1))
                        return false;
                } else {
                    if (boardBlocks[i][j] != 0)
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * @return a boardBlocks that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
        int[][] twinBlocks = cloneBlocks(boardBlocks);

        int i1, j1, i2, j2;
        do {
            i1 = StdRandom.uniform(0, N);
            j1 = StdRandom.uniform(0, N);
            i2 = StdRandom.uniform(0, N);
            j2 = StdRandom.uniform(0, N);
        } while (twinBlocks[i1][j1] == 0 || twinBlocks[i2][j2] == 0 || twinBlocks[i1][j1] == twinBlocks[i2][j2]);

        swap(twinBlocks, i1, j1, i2, j2);

        return new Board(twinBlocks);
    }

    private static int[][] cloneBlocks(int[][] blocks) {
        int[][] twinBlocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                twinBlocks[i][j] = blocks[i][j];
            }
        }
        return twinBlocks;
    }

    private static void swap(int[][] blocks, int i1, int j1, int i2, int j2) {
        int temp = blocks[i1][j1];
        blocks[i1][j1] = blocks[i2][j2];
        blocks[i2][j2] = temp;
    }

    /**
     * @param y another boardBlocks
     * @return does this boardBlocks equal y?
     */

    public boolean equals(Object y) {
        Board otherBoard = (Board) y;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (otherBoard.boardBlocks[i][j] != this.boardBlocks[i][j])
                    return false;
            }
        }
        return true;
    }

    /**
     * @return all neighboring boards
     */
    public Iterable<Board> neighbors() {
        Set<Board> boardSet = new HashSet<>();

        int i0 = -1, j0 = -1;
        findZeroLoop:
        {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (boardBlocks[i][j] == 0) {
                        i0 = i;
                        j0 = j;
                        break findZeroLoop;
                    }
                }
            }
        }
        if (i0 == -1 || j0 == -1)   throw new RuntimeException("Illegal boardBlocks - ZERO cell not found");

        Board top, bot, left, right;

        // TOP
        if (i0 - 1 >= 0){
            int[][] topBlocks = cloneBlocks(boardBlocks);
            swap(topBlocks, i0, j0, i0 - 1, j0);
            top = new Board(topBlocks);
            boardSet.add(top);
        }

        // BOT
        if (i0 + 1 < N){
            int[][] botBlocks = cloneBlocks(boardBlocks);
            swap(botBlocks, i0, j0, i0 + 1, j0);
            bot = new Board(botBlocks);
            boardSet.add(bot);
        }

        // LEFT
        if (j0 - 1 >= 0){
            int[][] leftBlocks = cloneBlocks(boardBlocks);
            swap(leftBlocks, i0, j0, i0, j0 - 1);
            left = new Board(leftBlocks);
            boardSet.add(left);
        }

        // RIGHT
        if (j0 + 1 < N){
            int[][] rightBlocks = cloneBlocks(boardBlocks);
            swap(rightBlocks, i0, j0, i0, j0 + 1);
            right = new Board(rightBlocks);
            boardSet.add(right);
        }

        return boardSet;
    }

    /**
     * @return string representation of this boardBlocks (in the output format specified below)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", boardBlocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
    }

}
