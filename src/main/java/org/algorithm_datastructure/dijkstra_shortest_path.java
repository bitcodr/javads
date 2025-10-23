package org.algorithm_datastructure;

import java.util.*;

/**
 * Dijkstra's Shortest Path Algorithm
 *
 * Finds the shortest path from a source vertex to all other vertices
 * in a weighted graph with non-negative edge weights.
 *
 * Algorithm:
 * 1. Initialize distances: source = 0, all others = infinity
 * 2. Use priority queue (min heap) to get vertex with minimum distance
 * 3. For each neighbor, if new path is shorter, update distance
 * 4. Repeat until all vertices are processed
 *
 * Time Complexity: O((V + E) log V) with priority queue
 * Space Complexity: O(V)
 *
 * Characteristics:
 * - Works only with non-negative weights
 * - Greedy algorithm
 * - Single-source shortest path
 *
 * Applications:
 * - GPS navigation
 * - Network routing
 * - Google Maps
 * - Flight connections
 */
public class dijkstra_shortest_path {

    /**
     * Edge class represents a weighted edge
     */
    static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    /**
     * Node class for priority queue
     */
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    /**
     * Finds shortest paths from source to all vertices
     * Time Complexity: O((V + E) log V)
     * @param graph Adjacency list representation
     * @param source Source vertex
     * @param V Number of vertices
     * @return Array of shortest distances from source
     */
    public static int[] dijkstra(List<List<Edge>> graph, int source, int V) {
        int[] distances = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (visited[u]) continue;
            visited[u] = true;

            // Explore neighbors
            for (Edge edge : graph.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                // Relaxation
                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.offer(new Node(v, distances[v]));
                }
            }
        }

        return distances;
    }

    /**
     * Finds shortest path with path reconstruction
     * @param graph Adjacency list
     * @param source Source vertex
     * @param destination Destination vertex
     * @param V Number of vertices
     * @return ShortestPathResult containing distance and path
     */
    public static ShortestPathResult dijkstraWithPath(
            List<List<Edge>> graph, int source, int destination, int V) {

        int[] distances = new int[V];
        int[] previous = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        distances[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (visited[u]) continue;
            visited[u] = true;

            if (u == destination) break;

            for (Edge edge : graph.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    previous[v] = u;
                    pq.offer(new Node(v, distances[v]));
                }
            }
        }

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        if (distances[destination] != Integer.MAX_VALUE) {
            int curr = destination;
            while (curr != -1) {
                path.add(0, curr);
                curr = previous[curr];
            }
        }

        return new ShortestPathResult(distances[destination], path);
    }

    /**
     * Result class containing distance and path
     */
    static class ShortestPathResult {
        int distance;
        List<Integer> path;

        ShortestPathResult(int distance, List<Integer> path) {
            this.distance = distance;
            this.path = path;
        }

        @Override
        public String toString() {
            if (distance == Integer.MAX_VALUE) {
                return "No path exists";
            }
            return "Distance: " + distance + ", Path: " + path;
        }
    }

    public static void main(String[] args) {
        System.out.println("Dijkstra's Shortest Path Algorithm Demo\n");

        // Test 1: Basic graph
        System.out.println("Test 1: Basic Weighted Graph");
        int V = 6;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges (u, v, weight)
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 2));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(3, 5));
        graph.get(2).add(new Edge(3, 8));
        graph.get(2).add(new Edge(4, 10));
        graph.get(3).add(new Edge(4, 2));
        graph.get(3).add(new Edge(5, 6));
        graph.get(4).add(new Edge(5, 3));

        System.out.println("Graph edges:");
        System.out.println("0 -> 1 (4), 0 -> 2 (2)");
        System.out.println("1 -> 2 (1), 1 -> 3 (5)");
        System.out.println("2 -> 3 (8), 2 -> 4 (10)");
        System.out.println("3 -> 4 (2), 3 -> 5 (6)");
        System.out.println("4 -> 5 (3)");
        System.out.println();

        int source = 0;
        int[] distances = dijkstra(graph, source, V);

        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To vertex " + i + ": " +
                (distances[i] == Integer.MAX_VALUE ? "∞" : distances[i]));
        }
        System.out.println();

        // Test 2: Path reconstruction
        System.out.println("Test 2: Shortest Path with Route");
        System.out.println("Finding path from 0 to 5:");
        ShortestPathResult result = dijkstraWithPath(graph, 0, 5, V);
        System.out.println(result);
        System.out.println();

        System.out.println("Finding path from 0 to 4:");
        result = dijkstraWithPath(graph, 0, 4, V);
        System.out.println(result);
        System.out.println();

        // Test 3: Disconnected graph
        System.out.println("Test 3: Disconnected Graph");
        int V2 = 5;
        List<List<Edge>> graph2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) {
            graph2.add(new ArrayList<>());
        }

        graph2.get(0).add(new Edge(1, 1));
        graph2.get(1).add(new Edge(2, 2));
        graph2.get(3).add(new Edge(4, 3));

        int[] distances2 = dijkstra(graph2, 0, V2);
        System.out.println("Shortest distances from vertex 0:");
        for (int i = 0; i < V2; i++) {
            System.out.println("To vertex " + i + ": " +
                (distances2[i] == Integer.MAX_VALUE ? "∞ (unreachable)" : distances2[i]));
        }
        System.out.println();

        // Test 4: City network example
        System.out.println("Test 4: City Network");
        int cities = 5;
        List<List<Edge>> cityGraph = new ArrayList<>();
        for (int i = 0; i < cities; i++) {
            cityGraph.add(new ArrayList<>());
        }

        String[] cityNames = {"New York", "Boston", "Chicago", "Denver", "LA"};

        // NY -> Boston (200), NY -> Chicago (800)
        cityGraph.get(0).add(new Edge(1, 200));
        cityGraph.get(0).add(new Edge(2, 800));

        // Boston -> Chicago (500), Boston -> Denver (1500)
        cityGraph.get(1).add(new Edge(2, 500));
        cityGraph.get(1).add(new Edge(3, 1500));

        // Chicago -> Denver (600), Chicago -> LA (1500)
        cityGraph.get(2).add(new Edge(3, 600));
        cityGraph.get(2).add(new Edge(4, 1500));

        // Denver -> LA (800)
        cityGraph.get(3).add(new Edge(4, 800));

        int[] cityDistances = dijkstra(cityGraph, 0, cities);
        System.out.println("Shortest distances from " + cityNames[0] + ":");
        for (int i = 0; i < cities; i++) {
            System.out.println("To " + cityNames[i] + ": " +
                (cityDistances[i] == Integer.MAX_VALUE ? "∞" : cityDistances[i] + " km"));
        }
        System.out.println();

        // Test 5: Single vertex
        System.out.println("Test 5: Single Vertex Graph");
        List<List<Edge>> singleVertex = new ArrayList<>();
        singleVertex.add(new ArrayList<>());

        int[] singleDist = dijkstra(singleVertex, 0, 1);
        System.out.println("Distance from 0 to 0: " + singleDist[0]);
        System.out.println();

        // Test 6: Complete graph (all vertices connected)
        System.out.println("Test 6: Complete Graph");
        int V3 = 4;
        List<List<Edge>> completeGraph = new ArrayList<>();
        for (int i = 0; i < V3; i++) {
            completeGraph.add(new ArrayList<>());
        }

        // Fully connected
        completeGraph.get(0).add(new Edge(1, 1));
        completeGraph.get(0).add(new Edge(2, 4));
        completeGraph.get(0).add(new Edge(3, 5));
        completeGraph.get(1).add(new Edge(2, 2));
        completeGraph.get(1).add(new Edge(3, 3));
        completeGraph.get(2).add(new Edge(3, 1));

        System.out.println("Finding shortest path from 0 to 3:");
        result = dijkstraWithPath(completeGraph, 0, 3, V3);
        System.out.println(result);
    }
}
