package org.algorithm_datastructure;

/**
 * Union-Find (Disjoint Set) Implementation
 *
 * A data structure that keeps track of elements partitioned into disjoint sets.
 * Supports two main operations: union (merge sets) and find (which set element belongs to).
 *
 * Optimizations:
 * - Path compression: Makes tree flatter during find
 * - Union by rank: Attaches smaller tree under larger tree
 *
 * Operations:
 * - makeSet(n): Initialize n disjoint sets - O(n)
 * - find(x): Find representative of set containing x - O(α(n))
 * - union(x, y): Merge sets containing x and y - O(α(n))
 * - connected(x, y): Check if x and y are in same set - O(α(n))
 *
 * α(n) is inverse Ackermann function, effectively O(1) for practical purposes
 *
 * Time Complexity: Nearly O(1) amortized for find and union
 * Space Complexity: O(n)
 *
 * Applications:
 * - Kruskal's MST algorithm
 * - Network connectivity
 * - Image processing (connected components)
 * - Least common ancestor
 */
public class union_find {

    static class UnionFind {
        private int[] parent;  // parent[i] is parent of i
        private int[] rank;    // rank[i] is approximate depth of tree rooted at i
        private int numSets;   // number of disjoint sets

        /**
         * Constructor - creates n disjoint sets
         * Time Complexity: O(n)
         * @param n Number of elements
         */
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            numSets = n;

            // Initially, each element is its own parent (separate set)
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        /**
         * Finds the representative (root) of the set containing x
         * Uses path compression for optimization
         * Time Complexity: O(α(n)) amortized
         * @param x Element to find
         * @return Representative of the set
         */
        public int find(int x) {
            if (parent[x] != x) {
                // Path compression: make parent point directly to root
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * Merges the sets containing x and y
         * Uses union by rank for optimization
         * Time Complexity: O(α(n)) amortized
         * @param x First element
         * @param y Second element
         * @return true if sets were merged, false if already in same set
         */
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            // Already in same set
            if (rootX == rootY) {
                return false;
            }

            // Union by rank: attach smaller tree under larger tree
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            numSets--;
            return true;
        }

        /**
         * Checks if two elements are in the same set
         * Time Complexity: O(α(n)) amortized
         * @param x First element
         * @param y Second element
         * @return true if in same set, false otherwise
         */
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        /**
         * Returns the number of disjoint sets
         * Time Complexity: O(1)
         * @return Number of disjoint sets
         */
        public int getNumSets() {
            return numSets;
        }

        /**
         * Returns the size of the set containing x
         * Time Complexity: O(n)
         * @param x Element
         * @return Size of set containing x
         */
        public int getSetSize(int x) {
            int root = find(x);
            int size = 0;

            for (int i = 0; i < parent.length; i++) {
                if (find(i) == root) {
                    size++;
                }
            }

            return size;
        }

        /**
         * Returns string representation showing parent array
         * @return String representation
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Parent: [");
            for (int i = 0; i < parent.length; i++) {
                sb.append(parent[i]);
                if (i < parent.length - 1) sb.append(", ");
            }
            sb.append("]\n");

            sb.append("Rank: [");
            for (int i = 0; i < rank.length; i++) {
                sb.append(rank[i]);
                if (i < rank.length - 1) sb.append(", ");
            }
            sb.append("]");

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Union-Find (Disjoint Set) Implementation Demo\n");

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Union and Find");
        UnionFind uf = new UnionFind(10);

        System.out.println("Initial state (10 disjoint sets):");
        System.out.println("Number of sets: " + uf.getNumSets());
        System.out.println();

        System.out.println("Performing unions:");
        System.out.println("Union(0, 1): " + uf.union(0, 1));
        System.out.println("Union(2, 3): " + uf.union(2, 3));
        System.out.println("Union(4, 5): " + uf.union(4, 5));
        System.out.println("Number of sets: " + uf.getNumSets());
        System.out.println();

        // Test 2: Check connectivity
        System.out.println("Test 2: Check Connectivity");
        System.out.println("Connected(0, 1): " + uf.connected(0, 1));
        System.out.println("Connected(0, 2): " + uf.connected(0, 2));
        System.out.println("Connected(2, 3): " + uf.connected(2, 3));
        System.out.println();

        // Test 3: Merge larger sets
        System.out.println("Test 3: Merge Sets");
        System.out.println("Union(0, 2): " + uf.union(0, 2));
        System.out.println("Now 0, 1, 2, 3 are connected");
        System.out.println("Connected(0, 3): " + uf.connected(0, 3));
        System.out.println("Connected(1, 3): " + uf.connected(1, 3));
        System.out.println("Number of sets: " + uf.getNumSets());
        System.out.println();

        // Test 4: Attempt duplicate union
        System.out.println("Test 4: Duplicate Union");
        System.out.println("Union(0, 1) again: " + uf.union(0, 1));
        System.out.println("(Returns false - already in same set)");
        System.out.println("Number of sets: " + uf.getNumSets());
        System.out.println();

        // Test 5: Network connectivity example
        System.out.println("Test 5: Network Connectivity Example");
        UnionFind network = new UnionFind(6);

        System.out.println("6 computers, initially disconnected");
        System.out.println("Number of networks: " + network.getNumSets());

        System.out.println("\nConnecting computers:");
        System.out.println("Connect 0-1");
        network.union(0, 1);
        System.out.println("Connect 1-2");
        network.union(1, 2);
        System.out.println("Connect 3-4");
        network.union(3, 4);

        System.out.println("\nNumber of networks: " + network.getNumSets());
        System.out.println("Computer 0 can reach computer 2: " + network.connected(0, 2));
        System.out.println("Computer 0 can reach computer 3: " + network.connected(0, 3));

        System.out.println("\nConnect 2-3");
        network.union(2, 3);
        System.out.println("Number of networks: " + network.getNumSets());
        System.out.println("Computer 0 can reach computer 4: " + network.connected(0, 4));
        System.out.println();

        // Test 6: Cycle detection in graph
        System.out.println("Test 6: Cycle Detection in Graph");
        UnionFind cycleDetect = new UnionFind(4);

        int[][] edges = {{0, 1}, {1, 2}, {2, 3}};
        boolean hasCycle = false;

        System.out.println("Adding edges to graph:");
        for (int[] edge : edges) {
            System.out.println("Edge " + edge[0] + "-" + edge[1]);
            if (!cycleDetect.union(edge[0], edge[1])) {
                hasCycle = true;
                System.out.println("Cycle detected!");
                break;
            }
        }

        if (!hasCycle) {
            System.out.println("No cycle detected so far");
            System.out.println("\nAdding edge 3-0 (creates cycle):");
            if (!cycleDetect.union(3, 0)) {
                System.out.println("Cycle detected!");
            }
        }
        System.out.println();

        // Test 7: Set size
        System.out.println("Test 7: Set Sizes");
        UnionFind sizeTester = new UnionFind(8);
        sizeTester.union(0, 1);
        sizeTester.union(1, 2);
        sizeTester.union(3, 4);

        System.out.println("After unions: (0-1-2) and (3-4)");
        System.out.println("Size of set containing 0: " + sizeTester.getSetSize(0));
        System.out.println("Size of set containing 3: " + sizeTester.getSetSize(3));
        System.out.println("Size of set containing 5: " + sizeTester.getSetSize(5));
        System.out.println();

        // Test 8: Find representatives
        System.out.println("Test 8: Find Representatives");
        UnionFind findTest = new UnionFind(5);
        findTest.union(0, 1);
        findTest.union(1, 2);

        System.out.println("After unions: 0-1-2");
        System.out.println("Representative of 0: " + findTest.find(0));
        System.out.println("Representative of 1: " + findTest.find(1));
        System.out.println("Representative of 2: " + findTest.find(2));
        System.out.println("Representative of 3: " + findTest.find(3));
        System.out.println("(All elements in set have same representative)");
    }
}
