package assignment_one_jobs;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Jobs2 {
    private static class Job implements Comparable<Job> {
        private int weight;
        private int length;
        private double value;
        
        public Job(int weight, int length) {
            this.weight = weight;
            this.length = length;
            this.value = (double) weight / length;
        }

        @Override
        public int compareTo(Job j) {
            if (value != j.value) return new Double(value).compareTo(j.value);
            return weight - j.weight;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 1/assignment_one_jobs/jobs.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int N = in.nextInt();
        Job[] jobs = new Job[N];
        for (int i = 0; i < N; i++) {
            jobs[i] = new Job(in.nextInt(), in.nextInt());
        }
        Arrays.sort(jobs);
        long totalLen = 0;
        long totalSumWeighted = 0;
        for (int i = N - 1; i >= 0; i--) {
            totalLen += jobs[i].length;
            totalSumWeighted += totalLen * jobs[i].weight;
        }
        out.println(totalSumWeighted);
        out.close();
    }
}
