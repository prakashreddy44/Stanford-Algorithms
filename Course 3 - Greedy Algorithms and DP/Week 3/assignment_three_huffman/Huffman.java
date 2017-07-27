package assignment_three_huffman;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Huffman { 
    public static class Node implements Comparable<Node> {
        long weight;
        int min, max;
        
        public Node(long weight, int min, int max) {
            this.weight = weight;
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(Node n) {
            return new Long(weight).compareTo(n.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        // Reader in = new Reader(System.in);
        Reader in = new Reader("Week 3/assignment_three_huffman/huffman.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int N = in.nextInt();
        MinPQ<Node> pq = new MinPQ<Node>(N);
        for (int i = 0; i < N; i++) {
            pq.insert(new Node(in.nextLong(), 0, 0));
        }
        Node node1 = null;
        Node node2 = null;
        while (!pq.isEmpty()) {
            node1 = pq.delMin();
            if (pq.isEmpty()) break;
            node2 = pq.delMin();
            pq.insert(new Node(node1.weight + node2.weight, 1 + Math.min(node1.min, node2.min), 1 + Math.max(node1.max, node2.max)));
        }
        out.println(node1.max);
        out.println(node1.min);
        out.close();
    }
}
