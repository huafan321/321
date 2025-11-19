package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] xt;

    public PercolationStats(int N, int T, PercolationFactory pf)// perform T independent experiments on an N-by-N grid
    {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        xt = new double[T];
        for(int i = 0; i < T; i++){
           Percolation p =  pf.make(N);
           xt[i] = carryout(p,N);
        }
    }
    public double mean(){
        return StdStats.mean(xt);
    }                                     // sample mean of percolation threshold
    public double stddev(){
        return StdStats.stddev(xt);
    }                                  // sample standard deviation of percolation threshold
    public double confidenceLow(){
        double avg = StdStats.mean(xt);
        double theta = StdStats.stddev(xt);
        return avg - 1.96*theta/Math.sqrt(xt.length);
    }
    public double confidenceHigh(){
        double avg = StdStats.mean(xt);
        double theta = StdStats.stddev(xt);
        return avg + 1.96*theta/Math.sqrt(xt.length);
    }                                 // high endpoint of 95% confidence interval

    private double carryout(Percolation p, int n){
        while(!p.percolates()){
            int row = StdRandom.uniform(n);
            int col = StdRandom.uniform(n);
            p.open(row,col);
        }
        return 1.0 * p.numberOfOpenSites() /(n*n);
    }
}
