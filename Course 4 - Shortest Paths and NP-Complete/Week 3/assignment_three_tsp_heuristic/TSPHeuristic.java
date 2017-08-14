package assignment_three_tsp_heuristic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;

public class TSPHeuristic { 
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 3/assignment_three_tsp_heuristic/nn.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int N = in.nextInt();
        Point2D[] points = new Point2D[N];
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < N; i++) {
            points[in.nextInt() - 1] = new Point2D(in.nextDouble(), in.nextDouble());
        }
        int cur = 0;
        for (int i = 1; i < N; i++) {
            set.add(i);
        }
        double ans = 0;
        while (!set.isEmpty()) {
            double minDist = Double.POSITIVE_INFINITY;
            int minIndex = -1;
            for (int i : set) {
                double dist = points[cur].distanceTo(points[i]);
                if (dist < minDist) {
                    minDist = dist;
                    minIndex = i;
                } else if (dist == minDist) {
                    minIndex = Math.min(minIndex, i);
                }
            }
            ans += minDist;
            cur = minIndex;
            set.remove(minIndex);
        }
        ans += points[cur].distanceTo(points[0]);
        out.println((long) ans);
        out.close();
    }
}
