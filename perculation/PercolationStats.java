/* PercolationStats.java
 * Call Percolation.java with user defined parameters and
 * return  the average ratio of open sited and additional 
 * statistical information
 */
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
    
    
public class PercolationStats  {
    private double[] ratio;
    /* preform a user defined number of runs of Percolation, the dimantion of
     * the simulation, n , is also user defined
     */
    public PercolationStats(int n, int runs) {
        if (n <= 0) {
            throw new IllegalArgumentException("n<=0, please enter a posotive integer");
        }
        if (runs <= 0) {
            throw new IllegalArgumentException("illegal number of runs, enter a positive integer");
        }
        ratio = new double[runs];
        for (int  i = 0; i < runs; i++) {
            Percolation percolation = new Percolation(n);
            //int cavities = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    
                
                }

            }
            ratio[i] = (percolation.numberOfOpenSites()) * 1.0 / (n * n);
            
        }

    }
    
    /* return the mean percolation ratio
     * 
     */
    public double mean() {
        return StdStats.mean(ratio);
    }
    
    /* return the standart deviation of the ratio
     * 
     */
    public double stddev() {
        return StdStats.stddev(ratio);
    }
    /* low 95% confedence interval
     * */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() * (Math.sqrt(ratio.length));
    }
    /* high 95% confidence interval
     * */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() * (Math.sqrt(ratio.length));
    }
    
    /* call Percolation with user defined input, return statistics
     * */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int runs = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, runs);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
   
    }

}
    
    
    