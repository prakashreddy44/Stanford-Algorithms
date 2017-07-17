package assignment_two_dijkstra;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Dijkstra {
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 2/assignment_two_dijkstra/dijkstraData.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        final int V = 200;
        final int S = 1;
        final int[] DEST = {7, 37, 59, 82, 99, 115, 133, 165, 188, 197};
        WeightedGraph G = new WeightedGraph(V);
        for (int i = 0; i < V; i++) {
            String[] line = in.nextLine().split("\\s+");
            int v = Integer.parseInt(line[0]) - 1;
            for (int j = 1; j < line.length; j++) {
                String[] tuple = line[j].split(",");
                int w = Integer.parseInt(tuple[0]) - 1;
                int weight = Integer.parseInt(tuple[1]);
                G.addEdge(new WeightedEdge(v, w, weight));
            }
        }
        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(G, S - 1);
        for (int i = 0; i < DEST.length; i++) {
            if (i != 0) out.print(",");
            out.print((int) (sp.distTo(DEST[i] - 1)));
        }
        out.println();
        out.close();
    }
}
