package assignment_three_median;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import assignment_one_scc.Reader;

public class MedianBST {
    private static AVLTreeSet<Integer> tree;
    
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 3/assignment_three_median/Median.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        final int N = 10000;
        tree = new AVLTreeSet<Integer>();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            tree.add(in.nextInt());
            int median = tree.select((tree.size() - 1) / 2);
            sum = (sum + median) % N;
        }
        out.println(sum);
        out.close();
    }
}
