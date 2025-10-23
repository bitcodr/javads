package org.algorithm_datastructure;

import java.util.*;

/**
 * Graph Implementation using Adjacency List
 *
 * A graph is a collection of vertices (nodes) and edges connecting them.
 * This implementation uses an adjacency list for efficient space usage.
 *
 * Operations:
 * - addVertex(vertex): Add a vertex - O(1)
 * - addEdge(from, to): Add edge (directed) - O(1)
 * - addUndirectedEdge(v1, v2): Add undirected edge - O(1)
 * - removeVertex(vertex): Remove vertex - O(V + E)
 * - removeEdge(from, to): Remove edge - O(E)
 * - hasEdge(from, to): Check if edge exists - O(1) average
 * - getNeighbors(vertex): Get adjacent vertices - O(1)
 * - dfs(): Depth-first search - O(V + E)
 * - bfs(): Breadth-first search - O(V + E)
 *
 * Time Complexity: Most operations O(1), traversals O(V + E)
 * Space Complexity: O(V + E)
 *
 * Applications:
 * - Social networks
 * - Maps and navigation
 * - Network routing
 * - Dependency resolution
 */
public class graph_implementation {

    /**
     * Graph class using adjacency list
     */
    static class Graph<T> {
        private Map<T, List<T>> adjacencyList;
        private boolean isDirected;

        /**
         * Constructor - creates empty graph
         * @param isDirected true for directed graph, false for undirected
         */
        public Graph(boolean isDirected) {
            this.adjacencyList = new HashMap<>();
            this.isDirected = isDirected;
        }

        /**
         * Adds a vertex to the graph
         * Time Complexity: O(1)
         * @param vertex Vertex to add
         */
        public void addVertex(T vertex) {
            adjacencyList.putIfAbsent(vertex, new ArrayList<>());
        }

        /**
         * Adds a directed edge from source to destination
         * Time Complexity: O(1)
         * @param from Source vertex
         * @param to Destination vertex
         */
        public void addEdge(T from, T to) {
            addVertex(from);
            addVertex(to);
            adjacencyList.get(from).add(to);
        }

        /**
         * Adds an undirected edge between two vertices
         * Time Complexity: O(1)
         * @param v1 First vertex
         * @param v2 Second vertex
         */
        public void addUndirectedEdge(T v1, T v2) {
            addEdge(v1, v2);
            addEdge(v2, v1);
        }

        /**
         * Removes a vertex and all its edges
         * Time Complexity: O(V + E)
         * @param vertex Vertex to remove
         */
        public void removeVertex(T vertex) {
            // Remove all edges to this vertex
            for (List<T> neighbors : adjacencyList.values()) {
                neighbors.remove(vertex);
            }
            // Remove the vertex itself
            adjacencyList.remove(vertex);
        }

        /**
         * Removes an edge between two vertices
         * Time Complexity: O(E) for vertex
         * @param from Source vertex
         * @param to Destination vertex
         */
        public void removeEdge(T from, T to) {
            List<T> neighbors = adjacencyList.get(from);
            if (neighbors != null) {
                neighbors.remove(to);
            }

            if (!isDirected) {
                neighbors = adjacencyList.get(to);
                if (neighbors != null) {
                    neighbors.remove(from);
                }
            }
        }

        /**
         * Checks if an edge exists
         * Time Complexity: O(1) average
         * @param from Source vertex
         * @param to Destination vertex
         * @return true if edge exists
         */
        public boolean hasEdge(T from, T to) {
            List<T> neighbors = adjacencyList.get(from);
            return neighbors != null && neighbors.contains(to);
        }

        /**
         * Gets neighbors of a vertex
         * Time Complexity: O(1)
         * @param vertex Vertex to get neighbors of
         * @return List of neighbors
         */
        public List<T> getNeighbors(T vertex) {
            return adjacencyList.getOrDefault(vertex, new ArrayList<>());
        }

        /**
         * Gets all vertices in the graph
         * Time Complexity: O(1)
         * @return Set of all vertices
         */
        public Set<T> getVertices() {
            return adjacencyList.keySet();
        }

        /**
         * Performs depth-first search from a starting vertex
         * Time Complexity: O(V + E)
         * @param start Starting vertex
         * @return List of vertices in DFS order
         */
        public List<T> dfs(T start) {
            List<T> result = new ArrayList<>();
            Set<T> visited = new HashSet<>();
            dfsHelper(start, visited, result);
            return result;
        }

        private void dfsHelper(T vertex, Set<T> visited, List<T> result) {
            visited.add(vertex);
            result.add(vertex);

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    dfsHelper(neighbor, visited, result);
                }
            }
        }

        /**
         * Performs breadth-first search from a starting vertex
         * Time Complexity: O(V + E)
         * @param start Starting vertex
         * @return List of vertices in BFS order
         */
        public List<T> bfs(T start) {
            List<T> result = new ArrayList<>();
            Set<T> visited = new HashSet<>();
            Queue<T> queue = new LinkedList<>();

            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                T vertex = queue.poll();
                result.add(vertex);

                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }

            return result;
        }

        /**
         * Checks if graph has a cycle (for directed graphs)
         * Time Complexity: O(V + E)
         * @return true if cycle exists
         */
        public boolean hasCycle() {
            Set<T> visited = new HashSet<>();
            Set<T> recursionStack = new HashSet<>();

            for (T vertex : adjacencyList.keySet()) {
                if (hasCycleHelper(vertex, visited, recursionStack)) {
                    return true;
                }
            }

            return false;
        }

        private boolean hasCycleHelper(T vertex, Set<T> visited, Set<T> recursionStack) {
            if (recursionStack.contains(vertex)) {
                return true;
            }

            if (visited.contains(vertex)) {
                return false;
            }

            visited.add(vertex);
            recursionStack.add(vertex);

            for (T neighbor : getNeighbors(vertex)) {
                if (hasCycleHelper(neighbor, visited, recursionStack)) {
                    return true;
                }
            }

            recursionStack.remove(vertex);
            return false;
        }

        /**
         * Returns the number of vertices
         * @return Number of vertices
         */
        public int getVertexCount() {
            return adjacencyList.size();
        }

        /**
         * Returns the number of edges
         * @return Number of edges
         */
        public int getEdgeCount() {
            int count = 0;
            for (List<T> neighbors : adjacencyList.values()) {
                count += neighbors.size();
            }
            return isDirected ? count : count / 2;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
                sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Graph Implementation Demo\n");

        // Test 1: Directed graph
        System.out.println("Test 1: Directed Graph");
        Graph<String> directedGraph = new Graph<>(true);

        directedGraph.addEdge("A", "B");
        directedGraph.addEdge("A", "C");
        directedGraph.addEdge("B", "D");
        directedGraph.addEdge("C", "D");
        directedGraph.addEdge("D", "E");

        System.out.println("Graph structure:");
        System.out.println(directedGraph);

        System.out.println("Vertices: " + directedGraph.getVertexCount());
        System.out.println("Edges: " + directedGraph.getEdgeCount());
        System.out.println();

        // Test 2: DFS and BFS
        System.out.println("Test 2: Graph Traversals");
        System.out.println("DFS from A: " + directedGraph.dfs("A"));
        System.out.println("BFS from A: " + directedGraph.bfs("A"));
        System.out.println();

        // Test 3: Undirected graph
        System.out.println("Test 3: Undirected Graph");
        Graph<Integer> undirectedGraph = new Graph<>(false);

        undirectedGraph.addUndirectedEdge(1, 2);
        undirectedGraph.addUndirectedEdge(1, 3);
        undirectedGraph.addUndirectedEdge(2, 4);
        undirectedGraph.addUndirectedEdge(3, 4);
        undirectedGraph.addUndirectedEdge(4, 5);

        System.out.println("Graph structure:");
        System.out.println(undirectedGraph);

        System.out.println("DFS from 1: " + undirectedGraph.dfs(1));
        System.out.println("BFS from 1: " + undirectedGraph.bfs(1));
        System.out.println();

        // Test 4: Check neighbors
        System.out.println("Test 4: Get Neighbors");
        System.out.println("Neighbors of 1: " + undirectedGraph.getNeighbors(1));
        System.out.println("Neighbors of 4: " + undirectedGraph.getNeighbors(4));
        System.out.println();

        // Test 5: Has edge
        System.out.println("Test 5: Edge Existence");
        System.out.println("Has edge 1->2: " + undirectedGraph.hasEdge(1, 2));
        System.out.println("Has edge 1->5: " + undirectedGraph.hasEdge(1, 5));
        System.out.println();

        // Test 6: Cycle detection
        System.out.println("Test 6: Cycle Detection");
        Graph<String> cyclicGraph = new Graph<>(true);
        cyclicGraph.addEdge("A", "B");
        cyclicGraph.addEdge("B", "C");
        cyclicGraph.addEdge("C", "A");

        System.out.println("Cyclic graph:");
        System.out.println(cyclicGraph);
        System.out.println("Has cycle: " + cyclicGraph.hasCycle());

        Graph<String> acyclicGraph = new Graph<>(true);
        acyclicGraph.addEdge("A", "B");
        acyclicGraph.addEdge("B", "C");
        acyclicGraph.addEdge("A", "C");

        System.out.println("\nAcyclic graph:");
        System.out.println(acyclicGraph);
        System.out.println("Has cycle: " + acyclicGraph.hasCycle());
        System.out.println();

        // Test 7: Remove operations
        System.out.println("Test 7: Remove Operations");
        Graph<String> removeGraph = new Graph<>(true);
        removeGraph.addEdge("A", "B");
        removeGraph.addEdge("A", "C");
        removeGraph.addEdge("B", "C");

        System.out.println("Original graph:");
        System.out.println(removeGraph);

        removeGraph.removeEdge("A", "B");
        System.out.println("After removing edge A->B:");
        System.out.println(removeGraph);

        removeGraph.removeVertex("C");
        System.out.println("After removing vertex C:");
        System.out.println(removeGraph);
    }
}
