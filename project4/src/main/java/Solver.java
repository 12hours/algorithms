import edu.princeton.cs.algs4.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class Solver {

    private class BoardItem {

        private Board board;
        private BoardItem prev;
        private int stepCounter;

        public BoardItem(Board board, BoardItem prev, int stepCounter) {
            this.board = board;
            this.prev = prev;
            this.stepCounter = stepCounter;
        }


        public int manhattan() {
            return board.manhattan() + stepCounter;
        }

        public int hamming() {
            return board.hamming() + stepCounter;
        }

        public Board twin() {
            return board.twin();
        }

        public boolean isGoal() {
            return board.isGoal();
        }

        public Iterable<Board> neighbors() {
            return board.neighbors();
        }

        public Board getBoard() {
            return board;
        }

        public int getStepCounter() {
            return stepCounter;
        }

        public BoardItem getPrev() {
            return prev;
        }

    }

    private boolean initSolved;
    private boolean twinSolved;
    private Stack<Board> sequence;

    /**
     * find a solution to the initial board (using the A* algorithm)
     *
     * @param initial initial board
     */
    public Solver(Board initial) {
        BoardItem initBoard = new BoardItem(initial, null, 0);
        BoardItem twinBoard = new BoardItem(initBoard.twin(), null, 0);


        MinPQ<BoardItem> initPQ = new MinPQ<>((o1, o2) -> {
            if (o1.manhattan() < o2.manhattan())
                return -1;
            if (o1.manhattan() > o2.manhattan())
                return 1;
            return 0;
        });
        initPQ.insert(initBoard);

        MinPQ<BoardItem> twinPQ = new MinPQ<>((o1, o2) -> {
            if (o1.manhattan() < o2.manhattan())
                return -1;
            if (o1.manhattan() > o2.manhattan())
                return 1;
            return 0;
        });
        twinPQ.insert(twinBoard);

        while (!initSolved && !twinSolved) {
            initSolved = step(initPQ);
            twinSolved = step(twinPQ);
        }

        // so if board is solvable
        if (initSolved) {
            sequence = getSequence(initPQ);
        }

    }

    private Stack<Board> getSequence(MinPQ<BoardItem> pq) {
        BoardItem lastBoardItem = pq.min();
        Stack<Board> boards = new Stack<>();

        while (lastBoardItem != null) {
            boards.push(lastBoardItem.getBoard());
            lastBoardItem = lastBoardItem.getPrev();
        }
        return boards;
    }

    private boolean step(MinPQ<BoardItem> PQ) {
        if (PQ.isEmpty()){
            return false;
        }
        BoardItem current = PQ.min();
        if (current.isGoal()) {
            return true;
        }

        current = PQ.delMin();
        for (Board neighbor : current.neighbors()) {
            if (current.getPrev() != null && neighbor.equals(current.getPrev().getBoard())) continue;

            BoardItem item = new BoardItem(neighbor, current, current.stepCounter + 1);
            PQ.insert(item);
        }

        return false;
    }

    /**
     * @return is the initial board solvable?
     */
    public boolean isSolvable() {
        return initSolved;
    }

    /**
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        if (twinSolved) return -1;
        return sequence.size() - 1;
    }

    /**
     * @return sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        if (twinSolved) return null;
        return sequence;
    }

    public static void main(String[] args) {
        String filename;
        try {
            FileInputStream inputStream = new FileInputStream("/home/nm/IdeaProjects/algorithms/project4/echo");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // for each command-line argument
            while((filename = bufferedReader.readLine()) != null) {

                // read in the board specified in the filename
                In in = new In("/home/nm/IdeaProjects/algorithms/project4/8puzzle/" + filename);
                int n = in.readInt();
                int[][] tiles = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        tiles[i][j] = in.readInt();
                    }
                }

                // solve the slider puzzle
                Board initial = new Board(tiles);
                Solver solver = new Solver(initial);
                StdOut.println(filename + ": " + solver.moves());
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}