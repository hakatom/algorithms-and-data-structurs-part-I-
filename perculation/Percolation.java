import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * Algorithms pt 1 week 1 - percolation
 * Percolation.java
 * givan a lattice of n by n site with a brandom sidtribution of cavities,
 * what is the ratio of opened to closed sites in which there is one continous 
 * cavity from the top to the bottom site?
 * this program can simulate many systems  - chemical (perculation of a solvent through a filtering colmn)
 * electrical - opened sites are conducntoing, blocked are isolators etc
 *  
 * @submitted by Doron Vaida
 */

public class Percolation {

    
    private final WeightedQuickUnionUF quf;
    private final WeightedQuickUnionUF btmquf;
    private final boolean[] isOpen;
    private final int topSite;
    private final int btmSite;
    private final int n;
    private int vacant = 0;  // number of vacant sites
    
    /**
     * constroctor, create a grid of n by n blocked sites
     * n - user defined bumber of sites
     **/
    public Percolation(int n) { 
        
        if (n <= 0) {
            throw new IllegalArgumentException("n must be a positive integer");
        }
        this.n = n;
        topSite = 0;
        btmSite = n * n + 1 ;
        quf = new WeightedQuickUnionUF(n * n + 1);
        btmquf = new WeightedQuickUnionUF(n * n + 2);
        isOpen = new boolean[ n * n + 2 ];
        isOpen[topSite] = true;
        isOpen[btmSite] = true;
    
    }
    /**
     * indexOf - converts each site from two dimentional notation to a single simentional notation
     * or, more simly - assuming that the firs tsite is top left and the last is at the bottom ledt, it
     * return the number of the site
     * params - row, col
     * 
     */
    private int indexOf(int row, int col) {
        if (row < 1 || row > n) {
            throw new IndexOutOfBoundsException();
        }
        if (col < 1 || col > n) {
            throw new IndexOutOfBoundsException();
        }
        
        return ((row - 1) * n + col);
    }
    
    /* unite- preforms weighted quick union on two open sites, we assume the fiest site is opened
     * 
     */
    private void unite(int rowA, int colA, int rowB, int colB) {
        
        if (0 < rowB && rowB <= n && 0 < colB && colB <= n && isOpen(rowB, colB)) {
            btmquf.union(indexOf(rowA, colA), indexOf(rowB, colB));
            
            quf.union(indexOf(rowA, colA), indexOf(rowB, colB));

        }
    }
         
    /* opens a site , and then preform union on each neighboring site 
     * parameters - row, col
     * it also incerements vacant, the current number of opened sites, whitch initially is 0
     */ 
    
    public void open(int row, int col) {
        int currentIndex = indexOf(row, col);
        if (isOpen[currentIndex]) {
            return;
        }
        isOpen[currentIndex] = true;
        vacant += 1;
        if (row == 1) {
            btmquf.union(currentIndex, topSite);
            quf.union(currentIndex, topSite);
        }
        if (row == n) {
            btmquf.union(currentIndex, btmSite);
        }
        unite(row, col, row, col + 1);
        unite(row, col, row, col - 1);
        unite(row, col, row + 1, col);
        unite(row, col, row - 1, col);
    }
    /*
     * isOpen : returns is the site at row,col open? 
     */
    
    public boolean isOpen(int row, int col) {
       
        return isOpen[indexOf(row, col)];
    
    } 
    /* isFull - is the cavity at row, col vacant?
     * 
     */
    public boolean isFull(int row, int col) {
        
        return quf.connected(topSite, indexOf(row, col));
    }
    
    /* percolates does the system percolates?
     * 
     */
    public boolean percolates() { 
       

        return btmquf.connected(topSite, btmSite);
    } 
    /* returns the number of open sites in the system
     * */
    public int numberOfOpenSites() {
    return vacant;
    }

    public static void main(String[] args) {
        StdOut.println("Please run PercolationStats instead.");
    }


}