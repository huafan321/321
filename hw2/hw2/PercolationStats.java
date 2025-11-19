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
        return 1.0 * p.numberOfOpenSites() /n*n;
    }

    public static void main(String[] args) {
        int N = 20; // 网格大小 20x20
        int T = 30; // 实验次数

        // 创建一个 PercolationFactory
        PercolationFactory pf = new PercolationFactory();

        // 创建 PercolationStats 对象，进行 T 次实验
        PercolationStats stats = new PercolationStats(N, T, pf);

        // 打印结果
        System.out.printf("mean                    = %.5f%n", stats.mean());
        System.out.printf("stddev                  = %.5f%n", stats.stddev());
        System.out.printf("95%% confidence interval = [%.5f, %.5f]%n",
                stats.confidenceLow(), stats.confidenceHigh());
    }
}
