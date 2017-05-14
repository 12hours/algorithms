import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

public class PercolationStats {
    int[] results;

    /**
     * @param n
     * @param trials perform trials independent experiments on an n-by-n grid
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        int fullSize = n * n;

        this.results = new int[trials];
        for (int trialCount = 0; trialCount < trials; trialCount++) {
            System.out.println(String.format("Trial %d", trialCount));
            Percolation perc = new Percolation(n);
            int openCount = 0, i, j;
            while (!perc.percolates()) {
                i = StdRandom.uniform(1, n + 1);
                j = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(i, j)) {
                    perc.open(i, j);
                    openCount++;
                }
            }
            results[trialCount] = openCount;

        }
    }

    /**
     * sample mean of percolation threshold
     *
     * @return
     */
    public double mean() {
        return StdStats.mean(this.results);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev() {
        if (this.results.length == 1) return Double.NaN;
        return StdStats.stddev(this.results);
    }

    /**
     * endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.results.length);
    }

    /**
     * high endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.results.length);
    }

    public static void main(String[] args) {
        PercolationStats perc = new PercolationStats(20, 100);
        System.out.println(String.format("Mean = %s", perc.mean() / 400));
        System.out.println(String.format("StdDev = %s", perc.stddev() / 400));
        System.out.println(String.format("cLo = %s", perc.confidenceLo() / 400));
        System.out.println(String.format("cHi = %s", perc.confidenceHi() / 400));
    }
}