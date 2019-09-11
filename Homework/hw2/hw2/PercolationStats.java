package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int Tnum;
    private double[] statistics;

    public PercolationStats(int N, int T, hw2.PercolationFactory pf){
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T<= 0){
            throw new IllegalArgumentException("A number N and T needs to bigger than 0. ");
        }

        Tnum = T;
        statistics = new double[T];

        for (int i = 0; i < statistics.length; i += 1) {
            int numberofOpenSite = 0;
            hw2.Percolation newpf = pf.make(N);
            while (!newpf.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                if (!newpf.isOpen(x, y)) {
                    newpf.open(x, y);
                    numberofOpenSite += 1;
                }
            }
            statistics[i] = (double) numberofOpenSite / (N * N);
        }
    }

    private double sum(double[] t){
        double total = 0;

        for (int i = 0; i < t.length; i+=1){
            total = total + t[i];
        }
        return total;
    }

    public double mean(){
        // sample mean of percolation threshold
        //return StdStats.mean(statistics)
        double totalsum = sum(statistics);
        double anumber =  totalsum/Tnum;
        return anumber;
    }

    public double stddev(){
        // sample standard deviation of percolation threshold
        //return StdStats.stddev(statistics);
        double[] newarray = new double[Tnum];
        for(int i = 0; i < statistics.length; i += 1){
            newarray[i] = Math.pow((statistics[i] - this.mean()),2);
        }
        double astddev = (sum(newarray) / (Tnum-1));
        return Math.sqrt(astddev);
    }

    public double confidenceLow(){
    // low endpoint of 95% confidence interval
        return this.mean() - (1.96 * this.stddev()/Math.sqrt(Tnum));
    }
    public double confidenceHigh(){
    // high endpoint of 95% confidence interval
        return this.mean() + (1.96 * this.stddev()/Math.sqrt(Tnum));
    }

    public static void main(String[] arg){
        hw2.PercolationFactory na = new hw2.PercolationFactory();
        PercolationStats aa = new PercolationStats(20, 204, na);
        System.out.println(aa.mean());
        System.out.println(aa.stddev());
        System.out.println(aa.confidenceHigh());
        System.out.println(aa.confidenceLow());
    }
}
