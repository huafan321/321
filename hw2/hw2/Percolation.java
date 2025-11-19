package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int length;
    private boolean[][] grid;
    private int openSitesNum;
    private WeightedQuickUnionUF connected;
    private WeightedQuickUnionUF ufFull;
    private int virtualTop;
    private int virtualBottom;
    public Percolation(int N){ // create N-by-N grid, with all sites initially blocked
        if (N <= 0) throw new IllegalArgumentException();
        else {
            length = N;
            grid = new boolean[N][N];
            connected = new WeightedQuickUnionUF(N*N + 2);
            ufFull = new WeightedQuickUnionUF(N*N +1);
            virtualTop = N*N;
            virtualBottom = N*N + 1;
        }
    }
    public void open(int row, int col)       // open the site (row, col) if it is not open already
    {
        int id = row*length + col;
        if((row < 0 || row > length -1 ) || (col < 0 || col > length - 1)) throw new IndexOutOfBoundsException();
        if (!isOpen(row,col)){
            grid[row][col] = true;
            if (row == 0) {
                ufFull.union(id, virtualTop);
                connected.union(id,virtualTop);
            }
            if (row == length - 1) {
                connected.union(id,virtualBottom);
            }
            openSitesNum += 1;
            checkConnected(row,col);
        }
    }

    public boolean isOpen(int row, int col)  // is the site (row, col) open?
    {
        if((row < 0 || row > length -1 ) || (col < 0 || col > length - 1)) throw new IndexOutOfBoundsException();
        return grid[row][col];
    }
    public boolean isFull(int row, int col){// is the site (row, col) full?
        int id = row*length + col;
        return ufFull.find(id) == ufFull.find(virtualTop);
    }
    public int numberOfOpenSites() { // number of open sites
        return openSitesNum;
    }
    public boolean percolates(){
        return connected.find(virtualTop) == connected.find(virtualBottom);
    }              // does the system percolate?
    private void checkConnected(int row,int col){
        int id = row * length + col;
        // 上
        if(row > 0 && grid[row - 1][col]) {
            connected.union(id, id - length);
            ufFull.union(id,id - length);
        }
        // 下
        if(row < length - 1 && grid[row + 1][col]) {
            connected.union(id, id + length);
            ufFull.union(id,id + length);
        }
        // 左
        if(col > 0 && grid[row][col - 1]) {
            connected.union(id, id - 1);
            ufFull.union(id,id - 1);
        }
        // 右
        if(col < length - 1 && grid[row][col + 1]) {
            connected.union(id, id + 1);
            ufFull.union(id,id+1);
        }
    }
}
