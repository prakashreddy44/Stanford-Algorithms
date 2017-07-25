package assignment_two_clustering;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Clustering1 { 
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 2/assignment_two_clustering/clustering1.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int V = in.nextInt();
        final int K = 4;
        UF uf = new UF(V);
        ArrayList<WeightedEdge> edges = new ArrayList<WeightedEdge>();
        String line;
        while ((line = in.nextLine()) != null) {
            String[] arr = line.split(" ");
            edges.add(new WeightedEdge(Integer.parseInt(arr[0]) - 1, Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[2])));
        }
        Collections.sort(edges);
        for (WeightedEdge e : edges) {
            uf.union(e.either(), e.other(e.either()));
            if (uf.count() == K) break;
        }
        int max = Integer.MAX_VALUE;
        for (WeightedEdge e : edges) {
            if (!uf.connected(e.either(), e.other(e.either()))) {
                max = Math.min(max, (int) (e.weight()));
            }
        }
        out.println(max);
        out.close();
    }
}
