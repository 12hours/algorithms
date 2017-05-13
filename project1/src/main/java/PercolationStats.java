public class PercolationStats {

    /**
     * @param n
     * @param trials
     * perform trials independent experiments on an n-by-n grid
     */
    public PercolationStats(int n, int trials) {
	if (n <= 0 || trials <= 0)
	    throw new IllegalArgumentException();
    }

    /**
     * sample mean of percolation threshold
     * 
     * @return
     */
    public double mean() {
	return 0;
    }

    /**
     * sample standard deviation of percolation threshold
     * 
     * @return
     */
    public double stddev() {
	return 0;
    }

    /**
     * endpoint of 95% confidence interval
     * 
     * @return
     */
    public double confidenceLo() {
	return 0;
    }

    /**
     * high endpoint of 95% confidence interval
     * 
     * @return
     */
    public double confidenceHi() {
	return 0;
    }

    public static void main(String[] args) {
    }
}