package assignment_three_median;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Median {
    private static MaxPQ<Integer> lo;
    private static MinPQ<Integer> hi;
    private static int median;
    
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 3/assignment_three_median/Median.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        final int N = 10000;
        lo = new MaxPQ<Integer>(N);
        hi = new MinPQ<Integer>(N);
        int sum = 0;
        for (int i = 0; i < N; i++) {
            update(in.nextInt());
            sum = (sum + median) % N;
        }
        out.println(sum);
        out.close();
    }
    
    private static void update(int x) {
        if (lo.size() < hi.size()) {
            if (x < median) {
                lo.insert(x);
            } else {
                lo.insert(hi.delMin());
                hi.insert(x);
            }
            median = lo.max();
        } else if (lo.size() > hi.size()) {
            if (x < median) {
                hi.insert(lo.delMax());
                lo.insert(x);
            } else {
                hi.insert(x);
            }
            median = lo.max();
        } else {
            if (x < median) {
                lo.insert(x);
                median = lo.max();
            } else {
                hi.insert(x);
                median = hi.min();
            }
        }
    }
}
