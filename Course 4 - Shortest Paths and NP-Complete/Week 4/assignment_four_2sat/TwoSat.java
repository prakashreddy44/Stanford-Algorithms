package assignment_four_2sat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class TwoSat { 
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        String[] inputFiles = {"Week 4/assignment_four_2sat/2sat1.txt",
                               "Week 4/assignment_four_2sat/2sat2.txt",
                               "Week 4/assignment_four_2sat/2sat3.txt",
                               "Week 4/assignment_four_2sat/2sat4.txt",
                               "Week 4/assignment_four_2sat/2sat5.txt",
                               "Week 4/assignment_four_2sat/2sat6.txt"};
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        tLoop: for (int t = 0; t < 6; t++) {
            Reader in = new Reader(inputFiles[t]);
            int N = in.nextInt();
            Digraph G = new Digraph(N * 2);
            for (int i = 0; i < N; i++) {
                int a = toPoint(in.nextInt());
                int b = toPoint(in.nextInt());
                G.addEdge(a ^ 1, b);
                G.addEdge(b ^ 1, a);
            }
            TarjanSCC scc = new TarjanSCC(G);
            for (int i = 0; i < N; i++) {
                if (scc.stronglyConnected(i * 2, i * 2 + 1)) {
                    out.print(0);
                    continue tLoop;
                }
            }
            out.print(1);
        }
        out.println();
        out.close();
    }
    
    private static int toPoint(int i) {
        return i < 0 ? (-i - 1) * 2 + 1 : (i - 1) * 2;
    }
}
