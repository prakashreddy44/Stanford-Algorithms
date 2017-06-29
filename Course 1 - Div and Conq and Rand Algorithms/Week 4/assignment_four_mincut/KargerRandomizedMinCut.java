package assignment_four_mincut;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KargerRandomizedMinCut {
    private ArrayList<Edge> allEdges;
    private ArrayList<Edge> cutEdges;
    private static int globalMinEdges;
    
    /**
     * Computes the minimum cut from this graph after a specified number of trials.
     * 
     * @param G the graph
     * @param trials number of trials to perform
     */
    public KargerRandomizedMinCut(Graph G, int trials) {
        allEdges = new ArrayList<Edge>(G.E());
        cutEdges = new ArrayList<Edge>();
        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (int w: G.adj(v)) {
                if (v < w) {
                    allEdges.add(new Edge(v, w));
                }
                // only add one copy of each self loop (self loops will be consecutive)
                else if (v == w) {
                    if (selfLoops % 2 == 0) {
                        allEdges.add(new Edge(v, w));
                        selfLoops++;
                    }
                }
            }
        }
        globalMinEdges = Integer.MAX_VALUE;
        for (int i = 0; i < trials; i++) {
            int localMinEdges = 0;
            ArrayList<Edge> localCutEdges = new ArrayList<Edge>();
            UF uf = new UF(G.V());
            contract(G, uf);
            for (Edge e: allEdges) {
                if (!uf.connected(e.v, e.w)) {
                    localMinEdges++;
                    localCutEdges.add(e);
                }
            }
            if (localMinEdges < globalMinEdges) {
                globalMinEdges = localMinEdges;
                cutEdges = (ArrayList<Edge>) localCutEdges.clone();
            }
        }
    }
    
    private void contract(Graph G, UF uf) {
        int curSize = allEdges.size();
        int nodesLeft = G.V();
        while (nodesLeft > 2) {
            int ran = (int) (Math.random() * curSize);
            Edge e = allEdges.get(ran);
            if (!uf.connected(e.v, e.w)) {
                uf.union(e.v, e.w);
                nodesLeft--;
            }
            Edge temp = allEdges.get(--curSize);
            allEdges.set(curSize, e);
            allEdges.set(ran, temp);
        }
    }
    
    /**
     * Returns the number of cut edges.
     * 
     * @return the number of cut edges
     */
    public int minCut() {
        return globalMinEdges;
    }
    
    /**
     * Returns all the cut edges from the graph.
     * 
     * @return all the cut edges from the graph, as an iterable
     */
    public Iterable<Edge> cutEdges() {
        return cutEdges;
    }
    
    private class Edge {
        private int v;
        private int w;
        
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
        
        /**
         * Returns either endpoint of this edge.
         *
         * @return either endpoint of this edge
         */
        public int either() {
            return v;
        }

        /**
         * Returns the endpoint of this edge that is different from the given vertex.
         *
         * @param  vertex one endpoint of this edge
         * @return the other endpoint of this edge
         * @throws IllegalArgumentException if the vertex is not one of the
         *         endpoints of this edge
         */
        public int other(int vertex) {
            if      (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("Illegal endpoint");
        }
        
        @Override
        public int hashCode() {
            return 31 * Math.min(v, w) + Math.max(v, w);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof Edge)) return false;
            Edge e = (Edge) o;
            return (v == e.v && w == e.w) || (v == e.w && w == e.v);
        }
    }
    
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 4/assignment_four_mincut/kargerMinCut.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        final int V = 200;
        final int NUM_OF_TRIALS = 10000;
        
        Graph G = new Graph(V);
        for (int i = 0; i < V; i++) {
            String[] line = in.nextLine().split("\\s+");
            int v = Integer.parseInt(line[0]) - 1;
            for (int j = 1; j < line.length; j++) {
                int w = Integer.parseInt(line[j]) - 1;
                if (v < w) G.addEdge(v, w);
            }
        }
        KargerRandomizedMinCut minCut = new KargerRandomizedMinCut(G, NUM_OF_TRIALS);
        out.println(minCut.minCut());
        out.close();
    }
}
