package assignment_one_apsp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class APSP { 
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        String[] inputFiles = {"Week 1/assignment_one_apsp/g1.txt", "Week 1/assignment_one_apsp/g2.txt", "Week 1/assignment_one_apsp/g3.txt"};
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        double minDist = Double.POSITIVE_INFINITY;
        for (int t = 0; t < 3; t++) {
            Reader in = new Reader(inputFiles[t]);
            int V = in.nextInt();
            int E = in.nextInt();
            WeightedDigraph G = new WeightedDigraph(V);
            for (int i = 0; i < E; i++) {
                G.addEdge(new DirectedWeightedEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
            }
            JohnsonAPSP sp = new JohnsonAPSP(G);
            if (!sp.hasNegativeCycle()) {
                for (int v = 0; v < V; v++) {
                    for (int w = 0; w < V; w++) {
                        minDist = Math.min(minDist, sp.dist(v, w));
                    }
                }
            }
        }
        out.println(minDist == Double.POSITIVE_INFINITY ? "NULL" : (long) minDist);
        out.close();
    }
}
