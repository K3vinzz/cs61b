import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    Site[][] sites;
    WeightedQuickUnionUF uf;
    int openNum;
    public class Site{
        int row;
        int col;
        int oneD;
        boolean isOpen;
        boolean isFull;
        public Site(int r, int c){
            this.row = r;
            this.col = c;
            this.oneD = xyTo1D(r,c);
            this.isOpen = false;
            this.isFull = false;
        }
    }

    public Percolation(int N) {
        sites = new Site[N][N];
        for (int r = 0; r < N; r += 1){
            for (int c = 0; c < N; c += 1){
                sites[r][c] = new Site(r, c);
            }
        }
        openNum = 0;
        uf = new WeightedQuickUnionUF(N * N);
    }

    public void open(int row, int col) {
        sites[row][col].isOpen = true;
        openNum += 1;
        connect(xyTo1D(row,col));
    }

    public boolean isOpen(int row, int col) {
        return sites[row][col].isOpen;
    }

    public boolean isFull(int row, int col) {
        return sites[row][col].isFull;
    }

    public int numberOfOpenSites() {
        return openNum;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    public int xyTo1D(int r, int c){
        return r * 5 + c;
    }

    /** helper function to check if is connected */
    public void connect(int i){
        int t = i - 5;
        int b = i + 5;
        int l = i - 1;
        int r = i + 1;
        if (t >= 0){
            if(sites[t / 5][t % 5].isOpen){
                uf.union(i, t);
//                System.out.println("union top");
            }
        }
        if (l >= 0 && (l % 5) != 4){
            if(sites[l / 5][l % 5].isOpen){
                uf.union(i, l);
//                System.out.println("union left");

            }
        }
        if ((r % 5) != 0){
            if(sites[r / 5][r % 5].isOpen){
                uf.union(i, r);
//                System.out.println("union right");

            }
        }
        if (b < 25){
            if(sites[b / 5][b % 5].isOpen){
                uf.union(i, b);
//                System.out.println("union bottom");

            }
        }
    }

}
