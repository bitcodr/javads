# Contributing to Java Algorithms and Data Structures

Thank you for your interest in contributing to this project! This guide will help you add new algorithms and data structures to the repository.

## Code Style Guidelines

### File Naming Convention

- Use lowercase with underscores: `algorithm_name.java`
- Class names should match file names
- Example: `binary_search_tree.java` contains class `binary_search_tree`

### Code Structure

Each implementation file should follow this structure:

```java
package org.algorithm_datastructure;

import java.util.*;

/**
 * Algorithm/Data Structure Name
 *
 * Brief description of what it does and how it works.
 *
 * Operations (for data structures):
 * - operation1(): Description - O(complexity)
 * - operation2(): Description - O(complexity)
 *
 * Algorithm steps (for algorithms):
 * 1. Step one
 * 2. Step two
 * ...
 *
 * Time Complexity: O(...)
 * Space Complexity: O(...)
 *
 * Applications:
 * - Application 1
 * - Application 2
 * - Application 3
 */
public class algorithm_name {

    /**
     * Main method description
     * Time Complexity: O(...)
     * Space Complexity: O(...)
     * @param param1 Description
     * @return Return value description
     */
    public static ReturnType methodName(ParamType param1) {
        // Implementation with inline comments
    }

    public static void main(String[] args) {
        System.out.println("Algorithm Name Demo\n");

        // Test 1: Basic example
        System.out.println("Test 1: Description");
        // Test implementation
        System.out.println();

        // Test 2: Edge cases
        System.out.println("Test 2: Edge Cases");
        // Test implementation
        System.out.println();

        // Additional tests...
    }
}
```

## Documentation Requirements

### Class-Level Documentation

Every class should include:
1. **Name**: Clear, descriptive name
2. **Description**: What the algorithm/data structure does
3. **How it works**: Brief explanation of the approach
4. **Complexity**: Time and space complexity
5. **Applications**: Real-world use cases

### Method-Level Documentation

Every public method should include:
1. **JavaDoc comment**: Brief description
2. **Complexity annotations**: Time and space complexity
3. **Parameters**: `@param` tags with descriptions
4. **Return value**: `@return` tag with description
5. **Exceptions**: `@throws` if applicable

### Test Cases

Include diverse test cases in the `main` method:
1. **Basic example**: Simple case demonstrating core functionality
2. **Multiple scenarios**: Different input types and sizes
3. **Edge cases**: Empty inputs, single elements, boundary conditions
4. **Performance test**: For complex algorithms (optional)
5. **Real-world example**: Practical application demonstration

## Adding New Implementations

### 1. Choose Your Algorithm/Data Structure

Make sure it's not already implemented. Check existing files:
```bash
ls src/main/java/org/algorithm_datastructure/
```

### 2. Create the File

```bash
touch src/main/java/org/algorithm_datastructure/your_algorithm.java
```

### 3. Implement Following the Template

Use the structure shown above, ensuring:
- Clear variable names
- Inline comments for complex logic
- Comprehensive JavaDoc
- Multiple test cases

### 4. Test Your Implementation

```bash
javac src/main/java/org/algorithm_datastructure/your_algorithm.java
java org.algorithm_datastructure.your_algorithm
```

### 5. Update README.md

Add your implementation to the appropriate section in README.md.

## Code Quality Checklist

Before submitting:

- [ ] Code compiles without errors
- [ ] All test cases pass
- [ ] JavaDoc is complete and accurate
- [ ] Complexity analysis is included
- [ ] Code is well-formatted
- [ ] Variable names are descriptive
- [ ] No hardcoded values (use constants)
- [ ] Edge cases are handled
- [ ] Comments explain complex logic
- [ ] main() method demonstrates usage

## Common Algorithms to Implement

### Sorting
- Insertion Sort
- Shell Sort
- Bucket Sort
- Tim Sort

### Graph Algorithms
- Bellman-Ford
- Floyd-Warshall
- Topological Sort
- Tarjan's Algorithm
- Prim's MST

### String Algorithms
- Rabin-Karp
- Boyer-Moore
- Aho-Corasick
- Z-Algorithm

### Dynamic Programming
- Matrix Chain Multiplication
- Partition Problem
- Word Break
- Palindrome Partitioning

### Tree Algorithms
- AVL Tree
- Red-Black Tree
- B-Tree
- Segment Tree
- Fenwick Tree

### Advanced Data Structures
- Skip List
- Bloom Filter
- Suffix Array
- Disjoint Set Union

## Examples of Good Implementations

Look at these files as references:
- `fibonacci_dp.java` - Multiple approaches with comparison
- `dijkstra_shortest_path.java` - Graph algorithm with path reconstruction
- `kmp_pattern_matching.java` - String algorithm with detailed examples
- `binary_search_tree.java` - Complete data structure implementation

## Questions?

If you have questions or need help, please:
1. Check existing implementations for reference
2. Review this guide thoroughly
3. Open an issue for discussion

## License

By contributing, you agree that your contributions will be licensed under the same license as the project.

---

Happy coding! 🚀
