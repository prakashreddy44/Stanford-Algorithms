package assignment_two_clustering;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.HashMap;

public class ClusteringBig { 
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 2/assignment_two_clustering/clustering_big.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int V = in.nextInt();
        int size = in.nextInt();
        UF uf = new UF(V);
        HashMap<BitSet, Integer> map = new HashMap<BitSet, Integer>();
        for (int i = 0; i < V; i++) {
            BitSet b = new BitSet(size);
            for (int j = 0; j < size; j++) {
                if (in.nextInt() == 1) b.set(j);
            }
            if (map.containsKey(b)) uf.union(map.get(b), i);
            map.put(b, i);
        }
        for (BitSet b : map.keySet()) {
            int v = map.get(b);
            for (int i = 0; i < size; i++) {
                b.flip(i);
                if (map.containsKey(b)) uf.union(map.get(b), v);
                for (int j = i + 1; j < size; j++) {
                    b.flip(j);
                    if (map.containsKey(b)) uf.union(map.get(b), v);
                    b.flip(j);
                }
                b.flip(i);
            }
        }
        out.println(uf.count());
        out.close();
    }
}
