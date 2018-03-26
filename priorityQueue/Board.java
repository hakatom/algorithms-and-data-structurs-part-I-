import java.util.LinkedList;
import java.util.List;

public class Board {


    private final int[][] blocks;
    private final int n;
    private int blankRow;
    private int blankCol;
    public Board(int[][] blocks) {
        if (blocks == null ) {
            throw new NullPointerException();
        }
        this.blocks = copyOf(blocks);
        n = blocks.length;
        blankRow = -1;
        blankCol = -1;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] == 0) {
                    blankRow = row;
                    blankCol = col;
                    return;
                }
            }
        }
     }          // construct a board from an n-by-n array of blocks

    /*
    * Board dimention n
    */
    public int dimension(){
        return n;
    }                 
    /*
    * number of blocks out of place
     */
    public int hamming() {
        int result = 0;
        for (int row=0; row<n; row++) {
            for (int col = 0; col<n; col++) {
                if  (row == blankRow && col == blankCol) {
                    continue;
                 }
                 if (manhattan(row, col) != 0){
                     result++;
                 }
            }
        }
        return result;
    }
    /**
     * sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        int result = 0;
        for (int row =0; row<n; row++) {
            for (int col =0; col < n; col++) {
                if (row == blankRow && col == blankCol) {
                    continue;
                }
                result += manhattan(row, col);
            }
        }
        return  result;
    }               

    private int manhattan(int row, int col) {
        int destVal = blocks[row][col] - 1;
        int destRow = destVal / n;
        int destCol = destVal % n;
        return Math.abs(destRow-row) + Math.abs(destCol-col);
    }

    /**
     * is this boaar the goal board
     */
    public boolean isGoal() {
        return hamming() == 0;
    }
    /**
     *  // a board that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
        int[][] cloned = copyOf(blocks);
        if (blankRow != 0) {
            swap(cloned, 0, 0, 0, 1);
        } else {
            swap (cloned, 1, 0, 1, 1);
        }
        return new Board(cloned);
    }                  
    /**
     * does this board equal y?
     */
    private void swap(int[][] v, int rowA, int colA, int rowB, int colB) {
        int swap = v[rowA][colA];
        v[rowA][colA] = v[rowB][colB];
        v[rowB][colB] = swap;
    }
    

    public boolean equals(Object y) {
        if ( y == this) {
            return true;
        }
        if ( y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (this.blankCol != that.blankCol) {
            return false;
        }
        if (this.blankRow != that.blankRow) {
            return false;
        }
        if (this.n != that.n) {
            return false;
        }
        for (int row =0; row<n; row++) {
            for (int col = 0; col<n; col++) {
                if (this.blocks[row][col]!=that.blocks[row][col]) {
                    return false;
                }
            }
        }
        return true;

    }

    private int[][] copyOf(int[][] matrix) {
        int[][] clone = new int[matrix.length][];
        for (int row = 0; row< matrix.length; row++) {
            clone[row] = matrix[row].clone();
        }
        return clone;
    }
    /*
         // all neighboring boards
    */
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();

        if (blankRow > 0) {
            int[][] north = copyOf(blocks);
            swap(north, blankRow, blankCol, blankRow - 1, blankCol);
            neighbors.add(new Board(north));

        }
        if (blankRow < n -1) {
            int[][] south = copyOf(blocks);
            swap(south, blankRow , blankCol, blankRow + 1, blankCol);
            neighbors.add(new Board(south));
        }
        if (blankCol > 0) {
            int[][] west = copyOf(blocks);
            swap(west, blankRow, blankCol, blankRow, blankCol -1);
            neighbors.add(new Board(west));

        }
        if (blankCol < n - 1) {
            int[][] east = copyOf(blocks);
            swap(east, blankRow, blankCol, blankRow, blankCol + 1);
            neighbors.add(new Board(east));
        }
        return neighbors;
    }
    /**
     * string reresentation of this bard
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                builder.append(String.format("%2d ", blocks[row][col]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {}
}