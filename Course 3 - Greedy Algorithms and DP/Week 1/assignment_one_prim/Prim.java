package assignment_one_prim;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Prim { 
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 1/assignment_one_prim/edges.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int V = in.nextInt();
        int E = in.nextInt();
        WeightedGraph G = new WeightedGraph(V);
        for (int i = 0; i < E; i++) {
            G.addEdge(new WeightedEdge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }
        PrimMST mst = new PrimMST(G);
        out.println((int) mst.weight());
        out.close();
    }
}
