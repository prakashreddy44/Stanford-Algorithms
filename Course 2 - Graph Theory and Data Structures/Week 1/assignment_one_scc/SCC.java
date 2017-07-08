package assignment_one_scc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class SCC {    
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 1/assignment_one_scc/SCC.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        final int V = 875714;
        Digraph G = new Digraph(V);
        String line;
        while ((line = in.nextLine()) != null) {
            G.addEdge(Integer.parseInt(line.split(" ")[0]) - 1, Integer.parseInt(line.split(" ")[1]) - 1);
        }
        TarjanSCC scc = new TarjanSCC(G);
        int[] size = new int[V];
        for (int i = 0; i < V; i++) {
            size[scc.id(i)]++;
        }
        int[] best5 = new int[5];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < 5; j++) {
                if (size[i] > best5[j]) {
                    for (int k = 4; k > j; k--) {
                        best5[k] = best5[k - 1];
                    }
                    best5[j] = size[i];
                    break;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            if (i != 0) out.print(",");
            out.print(best5[i]);
        }
        out.println();
        out.close();
    }
}
