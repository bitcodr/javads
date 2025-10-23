package org.algorithm_datastructure;

import java.util.*;

/**
 * Binary Search Tree (BST) Implementation
 *
 * A binary tree where for each node:
 * - All values in left subtree are less than node value
 * - All values in right subtree are greater than node value
 * - Both subtrees are also BSTs
 *
 * Operations:
 * - insert(data): Add node maintaining BST property - O(log n) avg, O(n) worst
 * - delete(data): Remove node maintaining BST property - O(log n) avg, O(n) worst
 * - search(data): Find node with data - O(log n) avg, O(n) worst
 * - findMin(): Find minimum value - O(log n) avg, O(n) worst
 * - findMax(): Find maximum value - O(log n) avg, O(n) worst
 * - inorder(): Returns sorted elements - O(n)
 *
 * Time Complexity: O(log n) average for balanced tree, O(n) for skewed tree
 * Space Complexity: O(n) for storage, O(h) for recursion
 *
 * Applications:
 * - Maintaining sorted data
 * - Efficient searching
 * - Database indexing
 * - Priority queues
 */
public class binary_search_tree {

    /**
     * TreeNode class represents a node in the BST
     */
    static class TreeNode<T extends Comparable<T>> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * BST class implements the binary search tree data structure
     */
    static class BST<T extends Comparable<T>> {
        private TreeNode<T> root;
        private int size;

        /**
         * Constructor - creates empty BST
         */
        public BST() {
            this.root = null;
            this.size = 0;
        }

        /**
         * Inserts a node in the BST
         * Time Complexity: O(log n) average, O(n) worst
         * @param data Data to insert
         */
        public void insert(T data) {
            root = insertHelper(root, data);
            size++;
        }

        private TreeNode<T> insertHelper(TreeNode<T> node, T data) {
            if (node == null) {
                return new TreeNode<>(data);
            }

            if (data.compareTo(node.data) < 0) {
                node.left = insertHelper(node.left, data);
            } else if (data.compareTo(node.data) > 0) {
                node.right = insertHelper(node.right, data);
            }
            // If equal, don't insert (no duplicates)

            return node;
        }

        /**
         * Deletes a node from the BST
         * Time Complexity: O(log n) average, O(n) worst
         * @param data Data to delete
         * @return true if deleted, false if not found
         */
        public boolean delete(T data) {
            int originalSize = size;
            root = deleteHelper(root, data);
            return size < originalSize;
        }

        private TreeNode<T> deleteHelper(TreeNode<T> node, T data) {
            if (node == null) return null;

            if (data.compareTo(node.data) < 0) {
                node.left = deleteHelper(node.left, data);
            } else if (data.compareTo(node.data) > 0) {
                node.right = deleteHelper(node.right, data);
            } else {
                // Node found - delete it
                size--;

                // Case 1: No children (leaf node)
                if (node.left == null && node.right == null) {
                    return null;
                }

                // Case 2: One child
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }

                // Case 3: Two children
                // Find inorder successor (min in right subtree)
                TreeNode<T> successor = findMinNode(node.right);
                node.data = successor.data;
                node.right = deleteHelper(node.right, successor.data);
                size++; // Compensate for extra decrement
            }

            return node;
        }

        /**
         * Searches for a node with given data
         * Time Complexity: O(log n) average, O(n) worst
         * @param data Data to search for
         * @return true if found, false otherwise
         */
        public boolean search(T data) {
            return searchHelper(root, data);
        }

        private boolean searchHelper(TreeNode<T> node, T data) {
            if (node == null) return false;

            if (data.equals(node.data)) return true;

            if (data.compareTo(node.data) < 0) {
                return searchHelper(node.left, data);
            } else {
                return searchHelper(node.right, data);
            }
        }

        /**
         * Finds the minimum value in the BST
         * Time Complexity: O(log n) average, O(n) worst
         * @return Minimum value
         */
        public T findMin() {
            if (root == null) {
                throw new NoSuchElementException("Tree is empty");
            }
            return findMinNode(root).data;
        }

        private TreeNode<T> findMinNode(TreeNode<T> node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        /**
         * Finds the maximum value in the BST
         * Time Complexity: O(log n) average, O(n) worst
         * @return Maximum value
         */
        public T findMax() {
            if (root == null) {
                throw new NoSuchElementException("Tree is empty");
            }
            TreeNode<T> current = root;
            while (current.right != null) {
                current = current.right;
            }
            return current.data;
        }

        /**
         * Inorder traversal returns sorted elements
         * Time Complexity: O(n)
         * @return List of elements in sorted order
         */
        public List<T> inorder() {
            List<T> result = new ArrayList<>();
            inorderHelper(root, result);
            return result;
        }

        private void inorderHelper(TreeNode<T> node, List<T> result) {
            if (node == null) return;
            inorderHelper(node.left, result);
            result.add(node.data);
            inorderHelper(node.right, result);
        }

        /**
         * Preorder traversal
         * Time Complexity: O(n)
         * @return List of elements in preorder
         */
        public List<T> preorder() {
            List<T> result = new ArrayList<>();
            preorderHelper(root, result);
            return result;
        }

        private void preorderHelper(TreeNode<T> node, List<T> result) {
            if (node == null) return;
            result.add(node.data);
            preorderHelper(node.left, result);
            preorderHelper(node.right, result);
        }

        /**
         * Gets the height of the BST
         * Time Complexity: O(n)
         * @return Height of tree
         */
        public int height() {
            return heightHelper(root);
        }

        private int heightHelper(TreeNode<T> node) {
            if (node == null) return 0;
            return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
        }

        /**
         * Gets the number of nodes in the BST
         * Time Complexity: O(1)
         * @return Number of nodes
         */
        public int size() {
            return size;
        }

        /**
         * Checks if BST is empty
         * Time Complexity: O(1)
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return root == null;
        }

        /**
         * Checks if tree is a valid BST
         * Time Complexity: O(n)
         * @return true if valid BST, false otherwise
         */
        public boolean isValidBST() {
            return isValidBSTHelper(root, null, null);
        }

        private boolean isValidBSTHelper(TreeNode<T> node, T min, T max) {
            if (node == null) return true;

            if ((min != null && node.data.compareTo(min) <= 0) ||
                (max != null && node.data.compareTo(max) >= 0)) {
                return false;
            }

            return isValidBSTHelper(node.left, min, node.data) &&
                   isValidBSTHelper(node.right, node.data, max);
        }
    }

    public static void main(String[] args) {
        System.out.println("Binary Search Tree Implementation Demo\n");

        // Test 1: Build a BST
        System.out.println("Test 1: Build BST");
        BST<Integer> bst = new BST<>();

        System.out.println("Inserting: 50, 30, 70, 20, 40, 60, 80");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Tree structure:");
        System.out.println("       50");
        System.out.println("      /  \\");
        System.out.println("    30    70");
        System.out.println("   / \\   / \\");
        System.out.println("  20 40 60 80");
        System.out.println();

        // Test 2: Traversals (Inorder gives sorted)
        System.out.println("Test 2: Traversals");
        System.out.println("Inorder (sorted): " + bst.inorder());
        System.out.println("Preorder: " + bst.preorder());
        System.out.println();

        // Test 3: Search operations
        System.out.println("Test 3: Search Operations");
        System.out.println("Search for 40: " + bst.search(40));
        System.out.println("Search for 25: " + bst.search(25));
        System.out.println("Search for 80: " + bst.search(80));
        System.out.println();

        // Test 4: Min and Max
        System.out.println("Test 4: Min and Max");
        System.out.println("Minimum value: " + bst.findMin());
        System.out.println("Maximum value: " + bst.findMax());
        System.out.println();

        // Test 5: Tree properties
        System.out.println("Test 5: Tree Properties");
        System.out.println("Height: " + bst.height());
        System.out.println("Size: " + bst.size());
        System.out.println("Is valid BST: " + bst.isValidBST());
        System.out.println("Is empty: " + bst.isEmpty());
        System.out.println();

        // Test 6: Delete operations
        System.out.println("Test 6: Delete Operations");
        System.out.println("Inorder before deletion: " + bst.inorder());

        System.out.println("Deleting 20 (leaf node): " + bst.delete(20));
        System.out.println("Inorder after deleting 20: " + bst.inorder());

        System.out.println("Deleting 30 (node with two children): " + bst.delete(30));
        System.out.println("Inorder after deleting 30: " + bst.inorder());

        System.out.println("Deleting 50 (root): " + bst.delete(50));
        System.out.println("Inorder after deleting 50: " + bst.inorder());

        System.out.println("Deleting 100 (not present): " + bst.delete(100));
        System.out.println();

        // Test 7: String BST
        System.out.println("Test 7: String BST");
        BST<String> stringBST = new BST<>();
        stringBST.insert("Dog");
        stringBST.insert("Cat");
        stringBST.insert("Elephant");
        stringBST.insert("Bear");
        stringBST.insert("Fox");

        System.out.println("Inorder (alphabetically sorted): " + stringBST.inorder());
        System.out.println("Min: " + stringBST.findMin());
        System.out.println("Max: " + stringBST.findMax());
        System.out.println("Search for 'Cat': " + stringBST.search("Cat"));
        System.out.println();

        // Test 8: Sorted insertion
        System.out.println("Test 8: Build from Sorted Array");
        BST<Integer> skewedBST = new BST<>();
        System.out.println("Inserting sorted values: 1, 2, 3, 4, 5 (creates skewed tree)");
        for (int i = 1; i <= 5; i++) {
            skewedBST.insert(i);
        }
        System.out.println("Inorder: " + skewedBST.inorder());
        System.out.println("Height (skewed): " + skewedBST.height());
        System.out.println("Note: Height is " + skewedBST.height() +
            " for " + skewedBST.size() + " nodes (degrades to linked list)");
    }
}
