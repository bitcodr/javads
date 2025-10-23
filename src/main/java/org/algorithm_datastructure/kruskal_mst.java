package org.algorithm_datastructure;

import java.util.*;

/**
 * Kruskal's Minimum Spanning Tree Algorithm
 *
 * Finds a minimum spanning tree for a connected weighted graph.
 * A spanning tree connects all vertices with minimum total edge weight.
 *
 * Algorithm:
 * 1. Sort all edges by weight
 * 2. Initialize disjoint sets for each vertex
 * 3. For each edge, if it connects two different sets:
 *    - Add edge to MST
 *    - Union the two sets
 * 4. Stop when MST has V-1 edges
 *
 * Time Complexity: O(E log E) or O(E log V)
 * Space Complexity: O(V + E)
 *
 * Applications:
 * - Network design (cables, roads)
 * - Clustering
 * - Approximation algorithms
 * - Circuit design
 */
public class kruskal_mst {

    /**
     * Edge class represents a weighted edge
     */
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public String toString() {
            return src + " -- " + dest + " (weight: " + weight + ")";
        }
    }

    /**
     * Union-Find data structure for cycle detection
     */
    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;  // Same set, would create cycle
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }

    /**
     * Finds minimum spanning tree using Kruskal's algorithm
     * Time Complexity: O(E log E)
     * @param V Number of vertices
     * @param edges List of all edges
     * @return List of edges in MST
     */
    public static List<Edge> kruskalMST(int V, List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();

        // Sort edges by weight
        Collections.sort(edges);

        // Initialize Union-Find
        UnionFind uf = new UnionFind(V);

        // Process edges in sorted order
        for (Edge edge : edges) {
            // If adding this edge doesn't create a cycle
            if (uf.union(edge.src, edge.dest)) {
                mst.add(edge);

                // MST complete when we have V-1 edges
                if (mst.size() == V - 1) {
                    break;
                }
            }
        }

        return mst;
    }

    /**
     * Calculates total weight of MST
     * @param mst List of edges in MST
     * @return Total weight
     */
    public static int getMSTWeight(List<Edge> mst) {
        int totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.weight;
        }
        return totalWeight;
    }

    public static void main(String[] args) {
        System.out.println("Kruskal's Minimum Spanning Tree Algorithm Demo\n");

        // Test 1: Basic graph
        System.out.println("Test 1: Basic Graph");
        int V1 = 4;
        List<Edge> edges1 = new ArrayList<>();

        edges1.add(new Edge(0, 1, 10));
        edges1.add(new Edge(0, 2, 6));
        edges1.add(new Edge(0, 3, 5));
        edges1.add(new Edge(1, 3, 15));
        edges1.add(new Edge(2, 3, 4));

        System.out.println("Graph with " + V1 + " vertices:");
        System.out.println("Edges:");
        for (Edge e : edges1) {
            System.out.println("  " + e);
        }

        List<Edge> mst1 = kruskalMST(V1, edges1);
        System.out.println("\nMinimum Spanning Tree:");
        for (Edge e : mst1) {
            System.out.println("  " + e);
        }
        System.out.println("Total weight: " + getMSTWeight(mst1));
        System.out.println();

        // Test 2: Larger graph
        System.out.println("Test 2: Larger Graph");
        int V2 = 9;
        List<Edge> edges2 = new ArrayList<>();

        edges2.add(new Edge(0, 1, 4));
        edges2.add(new Edge(0, 7, 8));
        edges2.add(new Edge(1, 2, 8));
        edges2.add(new Edge(1, 7, 11));
        edges2.add(new Edge(2, 3, 7));
        edges2.add(new Edge(2, 5, 4));
        edges2.add(new Edge(2, 8, 2));
        edges2.add(new Edge(3, 4, 9));
        edges2.add(new Edge(3, 5, 14));
        edges2.add(new Edge(4, 5, 10));
        edges2.add(new Edge(5, 6, 2));
        edges2.add(new Edge(6, 7, 1));
        edges2.add(new Edge(6, 8, 6));
        edges2.add(new Edge(7, 8, 7));

        System.out.println("Graph with " + V2 + " vertices and " +
            edges2.size() + " edges");

        List<Edge> mst2 = kruskalMST(V2, edges2);
        System.out.println("\nMinimum Spanning Tree (8 edges):");
        for (Edge e : mst2) {
            System.out.println("  " + e);
        }
        System.out.println("Total weight: " + getMSTWeight(mst2));
        System.out.println();

        // Test 3: Complete graph
        System.out.println("Test 3: Complete Graph K4");
        int V3 = 4;
        List<Edge> edges3 = new ArrayList<>();

        edges3.add(new Edge(0, 1, 1));
        edges3.add(new Edge(0, 2, 2));
        edges3.add(new Edge(0, 3, 3));
        edges3.add(new Edge(1, 2, 4));
        edges3.add(new Edge(1, 3, 5));
        edges3.add(new Edge(2, 3, 6));

        System.out.println("Complete graph with " + V3 + " vertices:");

        List<Edge> mst3 = kruskalMST(V3, edges3);
        System.out.println("\nMST edges:");
        for (Edge e : mst3) {
            System.out.println("  " + e);
        }
        System.out.println("Total weight: " + getMSTWeight(mst3));
        System.out.println("(Should be 1 + 2 + 3 = 6)");
        System.out.println();

        // Test 4: City network
        System.out.println("Test 4: City Network Example");
        String[] cities = {"A", "B", "C", "D", "E"};
        int V4 = 5;
        List<Edge> edges4 = new ArrayList<>();

        edges4.add(new Edge(0, 1, 2));   // A-B: 2km
        edges4.add(new Edge(0, 3, 6));   // A-D: 6km
        edges4.add(new Edge(1, 2, 3));   // B-C: 3km
        edges4.add(new Edge(1, 3, 8));   // B-D: 8km
        edges4.add(new Edge(1, 4, 5));   // B-E: 5km
        edges4.add(new Edge(2, 4, 7));   // C-E: 7km
        edges4.add(new Edge(3, 4, 9));   // D-E: 9km

        System.out.println("Connecting cities with cables:");
        System.out.println("Cities: A, B, C, D, E");

        List<Edge> mst4 = kruskalMST(V4, edges4);
        System.out.println("\nOptimal cable layout:");
        for (Edge e : mst4) {
            System.out.println("  " + cities[e.src] + " -- " +
                cities[e.dest] + ": " + e.weight + " km");
        }
        System.out.println("Total cable length: " + getMSTWeight(mst4) + " km");
        System.out.println();

        // Test 5: Simple linear graph
        System.out.println("Test 5: Linear Graph");
        int V5 = 4;
        List<Edge> edges5 = new ArrayList<>();

        edges5.add(new Edge(0, 1, 1));
        edges5.add(new Edge(1, 2, 2));
        edges5.add(new Edge(2, 3, 3));

        List<Edge> mst5 = kruskalMST(V5, edges5);
        System.out.println("Linear graph MST (all edges included):");
        for (Edge e : mst5) {
            System.out.println("  " + e);
        }
        System.out.println("Total weight: " + getMSTWeight(mst5));
    }
}
