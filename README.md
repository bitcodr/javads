# Java Algorithms and Data Structures

A comprehensive collection of fundamental algorithms and data structures implemented in Java for educational purposes and interview preparation.

## Table of Contents

- [Overview](#overview)
- [Getting Started](#getting-started)
- [Data Structures](#data-structures)
- [Algorithms](#algorithms)
  - [Sorting](#sorting)
  - [Searching](#searching)
  - [Array Problems](#array-problems)
  - [String Problems](#string-problems)
  - [Graph Algorithms](#graph-algorithms)
  - [Dynamic Programming](#dynamic-programming)
  - [Tree Algorithms](#tree-algorithms)
  - [Backtracking](#backtracking)
  - [Matrix Problems](#matrix-problems)
  - [Interval Problems](#interval-problems)
- [Building and Running](#building-and-running)
- [Contributing](#contributing)
- [License](#license)

## Overview

This repository contains implementations of classic algorithms and data structures in Java. Each implementation includes:
- Clear problem descriptions
- Time and space complexity analysis
- Working test cases
- Clean, readable code

## Getting Started

### Prerequisites

- Java 8 or higher
- Gradle (wrapper included)

### Clone the Repository

```bash
git clone <repository-url>
cd javads
```

### Build the Project

```bash
./gradlew build
```

### Run Individual Algorithms

Each algorithm can be run independently:

```bash
./gradlew run --args="<ClassName>"
```

Or compile and run directly:

```bash
javac src/main/java/org/algorithm_datastructure/<filename>.java
java org.algorithm_datastructure.<classname>
```

## Data Structures

### Fundamental Data Structures

| Data Structure | File | Description |
|----------------|------|-------------|
| Stack | `stack_implementation.java` | LIFO data structure with push/pop operations |
| Queue | `queue_implementation.java` | FIFO data structure with enqueue/dequeue operations |
| Singly Linked List | `singly_linked_list.java` | Linear data structure with nodes pointing to next |
| Doubly Linked List | `doubly_linked_list.java` | Linear data structure with bidirectional pointers |
| Binary Tree | `binary_tree.java` | Hierarchical tree structure |
| Binary Search Tree | `binary_search_tree.java` | Binary tree with ordering property |
| Min Heap | `min_heap.java` | Complete binary tree with min-heap property |
| Max Heap | `max_heap.java` | Complete binary tree with max-heap property |
| Hash Table | `hash_table_implementation.java` | Hash-based key-value store |
| Graph | `graph_implementation.java` | Graph with adjacency list representation |
| Trie | `trie_implementation.java` | Prefix tree for string operations |
| Union-Find | `union_find.java` | Disjoint set data structure |

### Advanced Data Structures

| Data Structure | File | Description |
|----------------|------|-------------|
| LRU Cache | `lru_cache.java` | Least Recently Used cache with O(1) operations |
| Token Bucket Rate Limiter | `token_bucket_rate_limiter.java` | Rate limiting algorithm |

## Algorithms

### Sorting

| Algorithm | File | Time Complexity | Space Complexity |
|-----------|------|----------------|------------------|
| Bubble Sort | `sort_bubble.java` | O(n²) | O(1) |
| Selection Sort | `sort_selection.java` | O(n²) | O(1) |
| Merge Sort | `sort_merge.java` | O(n log n) | O(n) |
| Quick Sort | `sort_quick.java` | O(n log n) avg | O(log n) |
| Counting Sort | `counting_sort.java` | O(n + k) | O(k) |
| Heap Sort | `heap_sort.java` | O(n log n) | O(1) |
| Radix Sort | `radix_sort.java` | O(d × n) | O(n + k) |
| Dutch National Flag | `dutch_national_flag_problem.java` | O(n) | O(1) |

### Searching

| Algorithm | File | Time Complexity | Space Complexity |
|-----------|------|----------------|------------------|
| Binary Search | `search_binary.java` | O(log n) | O(1) |

### Array Problems

| Problem | File | Time Complexity |
|---------|------|----------------|
| Two Sum | `two_sum.java` | O(n) |
| Pair Target Sum | `pair_targetsum.java` | O(n) |
| Good Pairs | `good_pairs.java` | O(n²) |
| Pairs Division | `pairs_division.java` | O(n) |
| Triplet Sum to Zero | `triplet_sum_to_zero.java` | O(n²) |
| Unique Triplets | `unique_triplets.java` | O(n²) |
| Triplet Smaller Sum | `triplet_smaller_sum.java` | O(n²) |
| Triplet Sum Close to Target | `triplet_sum_close_to_target.java` | O(n²) |
| Quadruple Sum to Target | `quadruple_sum_to_target.java` | O(n³) |
| Contains Duplicate | `contains_dupplicate.java` | O(n) |
| Remove Duplicate | `remove_dupplicate.java` | O(n) |
| Unique Integer in Array | `unique_integet_in_array.java` | O(n) |
| Maximum Sum Subarray | `maximum_sum_subarray.java` | O(n) |
| Subarray Less Than Target | `subarray_less_than_target.java` | O(n) |
| Product of Array | `product_of_array.java` | O(n) |
| Squaring Numbers | `squaring_number.java` | O(n) |
| Best Time to Buy/Sell Stock | `best_time_to_buy_sell.java` | O(n) |

### String Problems

| Problem | File | Time Complexity |
|---------|------|----------------|
| Anagram Check | `anagram.java` | O(n) |
| Palindrome Check | `palindrome.java` | O(n) |
| Reverse String | `reverse_string.java` | O(n) |
| Caesar Encryption | `caesar_encryption.java` | O(n) |
| Replace Vowels | `replace_vowels.java` | O(n) |
| Pangram Check | `pangram.java` | O(n) |
| Compare String Contains Char | `compare_string_contains_char.java` | O(n) |
| Shortest Distance | `shortest_distance.java` | O(n) |
| Valid Parentheses | `valid_parentheses.java` | O(n) |
| KMP Pattern Matching | `kmp_pattern_matching.java` | O(n + m) |
| Rabin-Karp Pattern Matching | `rabin_karp.java` | O(n + m) |

### Graph Algorithms

| Algorithm | File | Time Complexity | Description |
|-----------|------|----------------|-------------|
| Depth First Search | `graph_dfs.java` | O(V + E) | DFS traversal |
| Breadth First Search | `graph_bfs.java` | O(V + E) | BFS traversal |
| Dijkstra's Algorithm | `dijkstra_shortest_path.java` | O((V + E) log V) | Shortest path |
| Kruskal's Algorithm | `kruskal_mst.java` | O(E log E) | Minimum spanning tree |
| Prim's Algorithm | `prim_mst.java` | O(E log V) | Minimum spanning tree |
| Number of Islands | `number_of_islands.java` | O(m × n) | Count islands in grid |

### Dynamic Programming

| Problem | File | Time Complexity | Description |
|---------|------|----------------|-------------|
| Fibonacci | `fibonacci_dp.java` | O(n) | nth Fibonacci number |
| 0/1 Knapsack | `knapsack_01.java` | O(n × W) | 0/1 knapsack problem |
| Unbounded Knapsack | `knapsack_unbounded.java` | O(n × W) | Unbounded knapsack |
| Longest Common Subsequence | `longest_common_subsequence.java` | O(m × n) | LCS of two strings |
| Longest Increasing Subsequence | `longest_increasing_subsequence.java` | O(n²) or O(n log n) | LIS in array |
| Coin Change | `coin_change.java` | O(n × amount) | Min coins for amount |
| Edit Distance | `edit_distance.java` | O(m × n) | Levenshtein distance |
| House Robber | `house_robber.java` | O(n) | Maximum robbery amount |
| Climbing Stairs | `climbing_stairs.java` | O(n) | Number of ways to climb |

### Tree Algorithms

| Algorithm | File | Time Complexity | Description |
|-----------|------|----------------|-------------|
| Inorder Traversal | `tree_traversal_inorder.java` | O(n) | Left-Root-Right |
| Preorder Traversal | `tree_traversal_preorder.java` | O(n) | Root-Left-Right |
| Postorder Traversal | `tree_traversal_postorder.java` | O(n) | Left-Right-Root |
| Level Order Traversal | `tree_level_order.java` | O(n) | BFS traversal |
| Max Depth | `tree_max_depth.java` | O(n) | Maximum depth of tree |

### Backtracking

| Problem | File | Time Complexity | Description |
|---------|------|----------------|-------------|
| N-Queens | `n_queens.java` | O(n!) | N-Queens puzzle |
| Sudoku Solver | `sudoku_solver.java` | O(9^m) | Solve Sudoku puzzle |
| Permutations | `permutations.java` | O(n!) | All permutations |
| Combinations | `combinations.java` | O(2^n) | All combinations |

### Matrix Problems

| Problem | File | Time Complexity |
|---------|------|----------------|
| Matrix Diagonal Difference | `matrix_diagonals_difference.java` | O(n) |

### Interval Problems

| Problem | File | Time Complexity |
|---------|------|----------------|
| Merge Intervals | `merge_interval.java` | O(n log n) |
| Minimum Window Sort | `minimum_window_sort.java` | O(n) |

## Building and Running

### Build the Project

```bash
./gradlew build
```

### Run Tests

```bash
./gradlew test
```

### Clean Build Artifacts

```bash
./gradlew clean
```

## Project Structure

```
javads/
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── algorithm_datastructure/
│                   ├── [data structure files]
│                   └── [algorithm files]
├── build.gradle
├── settings.gradle
├── gradlew
└── README.md
```

## Complexity Guide

### Time Complexity Classes

- **O(1)** - Constant time
- **O(log n)** - Logarithmic time
- **O(n)** - Linear time
- **O(n log n)** - Linearithmic time
- **O(n²)** - Quadratic time
- **O(n³)** - Cubic time
- **O(2^n)** - Exponential time
- **O(n!)** - Factorial time

### Space Complexity

Most algorithms include space complexity analysis in their comments.

## Contributing

Contributions are welcome! Please follow these guidelines:

1. Follow Java naming conventions
2. Include time and space complexity analysis
3. Add test cases in the main method
4. Document your code with clear comments
5. Ensure code is well-formatted and readable

## License

This project is for educational purposes.

## Resources

- [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)
- [VisuAlgo](https://visualgo.net/) - Algorithm visualizations
- [LeetCode](https://leetcode.com/) - Practice problems
- [GeeksforGeeks](https://www.geeksforgeeks.org/) - Algorithm tutorials

## Contact

For questions or suggestions, please open an issue in the repository.
