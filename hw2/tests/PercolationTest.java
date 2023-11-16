import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class PercolationTest {
    private int[][] getState(int N, Percolation p) {
        int[][] state = new int[5][5];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = open + full;
            }
        }
        return state;
    }
    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(5);
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        int[][] expectedState = {
                {0, 3, 0, 0, 0},
                {3, 3, 0, 0, 0},
                {3, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void yourTestHere() {
        // TODO: write some more tests
        int N = 5;
        Percolation p = new Percolation(5);
        System.out.println(p);
    }

    @Test
    public void isOpenTest() {
        int N = 5;
        Percolation p = new Percolation(5);
        p.open(0,0);
        p.open(4,4);
        assertThat(p.isOpen(0,0)).isTrue();
        assertThat(p.isOpen(4,4)).isTrue();
    }

    @Test
    public void numberOfOpenSitesTest() {
        int N = 5;
        Percolation p = new Percolation(5);
        p.open(0,0);
        p.open(4,4);
        assertThat(p.numberOfOpenSites()).isEqualTo(2);

    }

    @Test
    public void disjointSetTest() {
        int N = 5;
        Percolation p = new Percolation(5);
        System.out.println(p.uf.find(24));

    }

    @Test
    public void xyTo1DTest(){
        int N = 5;
        Percolation p = new Percolation(5);
        assertThat(p.sites[3][2].oneD).isEqualTo(17);
    }

    @Test
    public void connectTest(){
        int N = 5;
        Percolation p = new Percolation(5);
        p.open(0,0);
        p.open(1, 0);
        assertThat(p.uf.connected(0,5)).isTrue();
    }

    @Test
    public void percolateTest(){
        int N = 5;
        Percolation p = new Percolation(5);
        p.open(0,0);
        p.open(1, 0);
    }

}
