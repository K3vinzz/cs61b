import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    public class Site{
        public int row, col, xy;
        public boolean isOpen;
        public Site(int r, int c){
            row = r;
            col = c;
            xy = r * size + c;
            isOpen = false;
        }
    }
    private int size;
    private Site sites[][];
    private Site virtualTop, virtualBot;
    private WeightedQuickUnionUF uf, uf2;
    private int openCount;

    public Percolation(int N) {
        size = N;
        sites = new Site[N][N];
        openCount = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);
        virtualTop = new Site(size, 0); // size = 5 , xy = 25
        virtualBot = new Site(size, 1); // xy = 26
        for (int r = 0; r < N; r += 1){
            for (int c = 0; c < N; c += 1){
                sites[r][c] = new Site(r, c);
            }
        }
    }

    public void open(int row, int col) {
        sites[row][col].isOpen = true;
        openCount += 1;
        // vertical check
        if (row == 0){
            uf.union(sites[row][col].xy, virtualTop.xy);
            uf2.union(sites[row][col].xy, virtualTop.xy);
            if (sites[row + 1][col].isOpen){
                uf.union(sites[row][col].xy, sites[row + 1][col].xy);
                uf2.union(sites[row][col].xy, sites[row + 1][col].xy);
            }
        } else if (row == size - 1) {
            uf .union(sites[row][col].xy, virtualBot.xy);
            if (sites[row - 1][col].isOpen){
                uf.union(sites[row][col].xy, sites[row - 1][col].xy);
                uf2.union(sites[row][col].xy, sites[row - 1][col].xy);

            }
        } else {
            if (sites[row - 1][col].isOpen){
                uf.union(sites[row][col].xy, sites[row - 1][col].xy);
                uf2.union(sites[row][col].xy, sites[row - 1][col].xy);

            }
            if (sites[row + 1][col].isOpen){
                uf.union(sites[row][col].xy, sites[row + 1][col].xy);
                uf2.union(sites[row][col].xy, sites[row + 1][col].xy);

            }
        }

        // horizontal check
        if (col > 0 && sites[row][col - 1].isOpen){
            uf.union(sites[row][col].xy, sites[row][col - 1].xy);
            uf2.union(sites[row][col].xy, sites[row][col - 1].xy);

        }
        if (col < size - 1 && sites[row][col + 1].isOpen){
            uf.union(sites[row][col].xy, sites[row][col + 1].xy);
            uf2.union(sites[row][col].xy, sites[row][col + 1].xy);
        }
    }

    public boolean isOpen(int row, int col) {
        return sites[row][col].isOpen;
    }

    public boolean isFull(int row, int col) {
        return uf2.connected(sites[row][col].xy, virtualTop.xy) ;
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return uf.connected(virtualTop.xy, virtualBot.xy);
    }


}
