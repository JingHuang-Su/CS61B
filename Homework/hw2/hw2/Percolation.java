package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufOnlyTpS;
    private boolean[][] grid;
    private int topsite = 0;
    private int bottomsite;
    private int size;
    private int opensitenum = 0;
    //Helper Method for connect
    //xyTo1D(2, 4) = 14
    private int xyTo1D(int row, int col){
        return (row * size) + col + 1;
    }
    //Helper Method for check connected
    private void checkup(int row, int col){
        int up = row - 1;
        int x = xyTo1D(up, col);
        int y = xyTo1D(row, col);

        try {
            if (isOpen(up, col)) {
                uf.union(x, y);
                ufOnlyTpS.union(x, y);
            }
        }catch (IndexOutOfBoundsException ex) {
            return;
        }
    }
    private void checkdown(int row, int col){
        int down = row + 1;
        int x = xyTo1D(down, col);
        int y = xyTo1D(row, col);

        try {
            if (isOpen(down, col)) {
                uf.union(x, y);
                ufOnlyTpS.union(x, y);
            }
        }catch (IndexOutOfBoundsException ex) {
            return;
        }
    }
    private void checkright(int row, int col){
        int right = col + 1;
        int x = xyTo1D(row, right);
        int y = xyTo1D(row, col);

        try {
            if (isOpen(right, col)) {
                uf.union(x, y);
                ufOnlyTpS.union(x, y);
            }
        }catch (IndexOutOfBoundsException ex) {
            return;
        }
    }
    private void checkleft(int row, int col){
        int left = col - 1;
        int x = xyTo1D(row, left);
        int y = xyTo1D(row, col);

        try {
            if (isOpen(left, col)) {
                uf.union(x, y);
                ufOnlyTpS.union(x, y);
            }
        }catch (IndexOutOfBoundsException ex) {
            return;
        }

    }

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException("A number N need to bigger than 0 (N>0)");
        }

        size = N;
        bottomsite = N*N + 1;
        /** for ufOnlyTps N*N +1
             0
           //|\\
          O OOO O
         */

        uf = new WeightedQuickUnionUF(N * N + 2);
        ufOnlyTpS = new WeightedQuickUnionUF(N * N + 1);

        grid = new boolean[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; i < N; i += 1) {
                grid[i][j] = false;
            }
        }
    }



    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (row < 0 || col < 0 || row > size-1 || col > size-1){
            throw new IllegalArgumentException("A number N need to bigger than 0 and smaller than N-1");
        }else if (isOpen(row, col)){
            return;
        }


        grid[row][col] = true;
        opensitenum += 1;


        int numforconnect = xyTo1D(row, col);

        if (row == 0){
            uf.union(topsite, numforconnect);
            ufOnlyTpS.union(topsite, numforconnect);
        }

        if (row == size - 1){
            uf.union(bottomsite, numforconnect);
        }

        checkup(row, col);
        checkdown(row, col);
        checkright(row, col);
        checkleft(row, col);

    }

    public boolean isOpen(int row, int col){
        // is the site (row, col) open?
        /*
        if (row < 0 || col < 0 || row > size-1 || col > size-1) {
            throw new IllegalArgumentException("A number N need to bigger than 0 and smaller than N-1");
        }
        */
        return grid[row][col];

    }


    public boolean isFull(int row, int col){
        // is the site (row, col) full?
        if (row < 0 || col < 0 || row > size-1 || col > size-1) {
            throw new IllegalArgumentException("A number N need to bigger than 0 and smaller than N-1");
        }
        int x = xyTo1D(row, col);
        return ufOnlyTpS.connected(topsite, x);
    }

    public int numberOfOpenSites(){
        // number of open sites
        return opensitenum;
    }


    public boolean percolates(){
        // does the system percolate?
        return uf.connected(topsite, bottomsite);
    }

    public static void main(String[] args) {
        // use for unit testing (not required, but keep this here for the autograder)
        Percolation po = new Percolation(5);
        po.open(3, 4);
        po.open(2, 4);
        po.open(2, 2);
        po.open(2, 3);
        po.open(0, 2);
        po.open(1, 2);
        System.out.println(po.isFull(2, 2));
        System.out.println(po.numberOfOpenSites());
        po.open(4, 4);
        System.out.println(po.percolates());
    }
}

