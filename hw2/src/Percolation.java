import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.

    public class Site {
        int row, col, xy;
        boolean isOpen;
        public Site(int x, int y){
            row = x;
            col = y;
            xy = xyTo1d(x, y);
        }
    }
    public Percolation(int N) {
        // TODO: Fill in this constructor.
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return 0;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

    /* Helper function to convert x, y to 1-d */
    public int xyTo1d(int x, int y){
        return x * 5 + y;
    }

}
