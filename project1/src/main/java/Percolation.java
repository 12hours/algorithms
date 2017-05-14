import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *
 */
public class Percolation implements PercolationInterface {

    protected class Grid {
        private int[][] grid;
        private int size;

        public Grid(int n) {
            if (n < 0)
                throw new IllegalArgumentException();
            this.size = n;
            this.grid = new int[this.size][this.size];
        }

        public void set(int i, int j, int value) {
            if (i >= this.size || j >= this.size || i < 0 || j < 0)
                throw new IndexOutOfBoundsException();

            this.grid[i][j] = value;
        }

        public int get(int i, int j) {
            if (i >= this.size || j >= this.size || i < 0 || j < 0)
                throw new IndexOutOfBoundsException();

            return this.grid[i][j];
        }

        public int getSize() {
            return this.size;
        }
    }

    protected class GridExtended extends Grid {
        // Handles coordinates as 1...N

        public GridExtended(int N) {
            super(N);
        }

        // this functions takes coordinates in inner format
        private int convertToOneD(int i, int j) {
            if (i == -1 || j == -1)
                return -1;
            return (i * getSize()) + j + 1;
        }

        // this public converter takes coordinates in outer format
        public int convert2Dto1D(int i, int j) {
            if (i < 1 || j < 1)
                throw new IllegalArgumentException();
            i = convertToInnerCoordinate(i);
            j = convertToInnerCoordinate(j);
            return convertToOneD(i, j);
        }

        public int[] convert1Dto2D(int c) {
            int[] xy = new int[2];
            xy[0] = (c - 1) / getSize() + 1;
            xy[1] = (c - 1) % getSize() + 1;
            return xy;
        }

        private int convertToInnerCoordinate(int x) {
            return x - 1;
        }

        /*
         * Coordinates should be set in outer format
         */
        @Override
        public void set(int i, int j, int val) {
            i = convertToInnerCoordinate(i);
            j = convertToInnerCoordinate(j);
            super.set(i, j, val);
        }

        /*
         * Coordinates should be set in outer format
         */
        @Override
        public int get(int i, int j) {
            i = convertToInnerCoordinate(i);
            j = convertToInnerCoordinate(j);
            return super.get(i, j);
        }

        // coordinatase must be in outer {1...N} format
        // returns 1D format
        public int getLeft(int i, int j) {
            i = convertToInnerCoordinate(i);
            j = convertToInnerCoordinate(j);
            int[] left = {-1, -1};
            if (j > 0) {
                left[0] = i;
                left[1] = j - 1;
            }
            return convertToOneD(left[0], left[1]);
        }

        public int getRight(int i, int j) {
            i = convertToInnerCoordinate(i);
            j = convertToInnerCoordinate(j);
            int[] right = {-1, -1};
            if (j < getSize() - 1) {
                right[0] = i;
                right[1] = j + 1;
            }
            return convertToOneD(right[0], right[1]);
        }

        public int getTop(int i, int j) {
            i = convertToInnerCoordinate(i);
            j = convertToInnerCoordinate(j);
            int[] top = {-1, -1};
            if (i > 0) {
                top[0] = i - 1;
                top[1] = j;
            }
            return convertToOneD(top[0], top[1]);
        }

        public int getBot(int i, int j) {
            i = convertToInnerCoordinate(i);
            j = convertToInnerCoordinate(j);
            int[] bot = {-1, -1};
            if (i < getSize() - 1) {
                bot[0] = i + 1;
                bot[1] = j;
            }
            return convertToOneD(bot[0], bot[1]);
        }
    }

    // ------------------------------------- //
    private final int TOP_VIRT;
    private final int BOT_VIRT;
    private int openCount = 0;
    private GridExtended grid;
    private WeightedQuickUnionUF uf;

    /**
     * @param n size of grid side
     */
    public Percolation(int n) {
        int fullSize = n * n;
        TOP_VIRT = 0;
        BOT_VIRT = fullSize + 1;

        this.grid = new GridExtended(n);
        this.uf = new WeightedQuickUnionUF((n * n) + 2);
    }

    /**
     * Opens site with given coordinates
     *
     * @param row row number
     * @param col column number
     */
    @Override
    public void open(int row, int col) {
        this.grid.set(row, col, 1);
        this.openCount++;

        int current = this.grid.convert2Dto1D(row, col);
        int top = this.grid.getTop(row, col);
        int bot = this.grid.getBot(row, col);
        int left = this.grid.getLeft(row, col);
        int right = this.grid.getRight(row, col);

        if (top == -1) {
            uf.union(current, TOP_VIRT);
        } else {
            int[] coordinates = this.grid.convert1Dto2D(top);
            if (this.isOpen(coordinates[0], coordinates[1])) {
                uf.union(current, top);
            }
        }

        if (bot == -1) {
            uf.union(current, BOT_VIRT);
        } else {
            int[] coordinates = this.grid.convert1Dto2D(bot);
            if (this.isOpen(coordinates[0], coordinates[1])) {
                uf.union(current, bot);
            }
        }

        if (left != -1) {
            int[] coordinates = this.grid.convert1Dto2D(left);
            if (this.isOpen(coordinates[0], coordinates[1]))
                uf.union(current, left);
        }

        if (right != -1) {
            int[] coordinates = this.grid.convert1Dto2D(right);
            if (this.isOpen(coordinates[0], coordinates[1]))
                uf.union(current, right);
        }
    }

    /**
     * @param row number
     * @param col number
     * @return if this site is open
     */
    @Override
    public boolean isOpen(int row, int col) {
        return this.grid.get(row, col) == 1;
    }

    /**
     * @param row row number
     * @param col column number
     * @return if this site is part of full set
     */
    @Override
    public boolean isFull(int row, int col) {
        int current = this.grid.convert2Dto1D(row, col);
        return this.uf.connected(current, TOP_VIRT);
    }

    /**
     * @return number of open sites in the grid
     */
    @Override
    public int numberOfOpenSites() {
        return this.openCount;
    }

    /**
     * @return if grid percolates or not
     */
    @Override
    public boolean percolates() {
        return this.uf.connected(TOP_VIRT, BOT_VIRT);
    }

}
