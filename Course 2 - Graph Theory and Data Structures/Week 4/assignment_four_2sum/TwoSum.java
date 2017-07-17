package assignment_four_2sum;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

public class TwoSum {
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 4/assignment_four_2sum/algo1-programming_prob-2sum.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        final int N = 1000000;
        final int MINN = -10000;
        final int MAXN = 10000;
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextLong();
        }
        
        Arrays.sort(arr);
        boolean[] valid = new boolean[MAXN - MINN + 1];
        int j1 = 0;
        while (j1 < N && arr[0] + arr[j1] < MINN) {
            j1++;
        }
        int j2 = N - 1;
        outer: for (int i = 0; i < N; i++) {
            while (arr[i] + arr[j2] > MAXN) {
                j2--;
                if (j2 <= i) break outer;
            }
            while (j1 - 1 >= 0 && arr[i] + arr[j1 - 1] >= MINN) {
                j1--;
            }
            for (int j = j1; j <= j2; j++) {
                valid[(int) (arr[j] + arr[i] + MAXN)] = true;
            }
        }
        
        int count = 0;
        for (int i = 0; i < valid.length; i++) {
            count += valid[i] ? 1 : 0;
        }
        out.println(count);
        out.close();
    }
}
