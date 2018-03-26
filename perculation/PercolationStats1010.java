import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

    
public class PercolationStats1010  {
    private double[] ratio;
    public PercolationStats1010(int n, int runs) {
        if (n <= 0) {
            throw new IllegalArgumentException("n<=0, please enter a posotive integer");
        }
        if (runs <= 0) {
            throw new IllegalArgumentException("illegal number of runs, enter a positive integer");
        }
        ratio = new double[runs];
        for (int  i = 0; i < runs; i++) {
            Percolation percolation = new Percolation(n);
            int cavities = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n +1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    cavities++;
                
                }

            }
            ratio[i] = cavities * 1.0 / (n * n);
        }

    }
    public double mean() {
        return StdStats.mean(ratio);
    }
    public double stddev() {
        return StdStats.stddev(ratio);
    }
    public double confidenceLow() {
        return mean() - 1.96 * stddev() * (Math.sqrt(ratio.length));
    }
    public double confidenceHi() {
        return mean() + 1.96 * stddev() * (Math.sqrt(ratio.length));
    }
    
    public static void main(String[] args) {
        int n = 10;
        int runs = 10;
        PercolationStats stats = new PercolationStats(n, runs);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLow() + ", " + stats.confidenceHi());
   
    }

}
    
    
    