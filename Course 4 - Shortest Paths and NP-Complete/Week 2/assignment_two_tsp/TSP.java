package assignment_two_tsp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class TSP { 
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 2/assignment_two_tsp/tsp.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int N = in.nextInt();
        Point2D[] points = new Point2D[N];
        double[][] dist = new double[N][N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point2D(in.nextDouble(), in.nextDouble());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = points[i].distanceTo(points[j]);
            }
        }
        HamiltonianCycle tsp = new HamiltonianCycle(dist);
        out.println((int) tsp.length());
        out.close();
    }
}
