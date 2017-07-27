package assignment_three_mwis;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MWIS {    
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 3/assignment_three_mwis/mwis.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int N = in.nextInt();
        int[] queries = {1, 2, 3, 4, 17, 117, 517, 997};
        long[] weights = new long[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.nextLong();
        }
        boolean[] selected = solve(weights);
        for (int i = 0; i < queries.length; i++) {
            out.print(selected[queries[i] - 1] ? 1 : 0);
        }
        out.println();
        out.close();
    }
    
    public static boolean[] solve(long[] sequence) {
        long[] dp = new long[sequence.length];    // the dp array that stores the solutions
        boolean[] selected = new boolean[sequence.length]; // boolean array storing whether that index is selected
        // 2 initial cases
        dp[0] = sequence[0]; // if there is only 1 value, then then that value is the solution
        dp[1] = Long.max(dp[0], sequence[1]); // if there are 2 values, then the maximum of the 2 is the solution
        for (int i = 2; i < sequence.length; i++) { // we start at 2 since 0 and 1 have been solved
            dp[i] = Long.max(dp[i-2] + sequence[i], dp[i-1]);    // the solutions is the maximum of either the value at index i
                                                                    // plus the solution at i-2, but if the solution at i-1 is greater,
                                                                    // then we should skip index i and use i-1 instead
        } // for i
        int ind;
        for (ind = sequence.length - 1; ind >= 2; ind--) {
            if (dp[ind-2] + sequence[ind] > dp[ind-1]) {
                selected[ind] = true;
                ind--;
            }
        }
        if (ind == 1) selected[1] = true;
        else if (ind == 0) selected[0] = true;
        return selected;
    }
}
