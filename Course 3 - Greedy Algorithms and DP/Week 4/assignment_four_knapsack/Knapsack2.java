package assignment_four_knapsack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Knapsack2 {    
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 4/assignment_four_knapsack/knapsack_big.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int M = in.nextInt();
        int N = in.nextInt();
        int[] value = new int[N];
        int[] weight = new int[N];
        for (int i = 0; i < N; i++) {
            value[i] = in.nextInt();
            weight[i] = in.nextInt();
        }
        out.println(solve(M, weight, value));
        out.close();
    }
    
    public static int solve(int M, int[] weight, int[] value) {
        int[] dp = new int[M + 1];
        for (int i = 0; i < value.length; i++) {
            for (int j = M; j >= 0; j--) {
                if (j - weight[i] >= 0) dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[M];
    }
}
