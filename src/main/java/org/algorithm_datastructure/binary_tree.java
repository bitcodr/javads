package org.algorithm_datastructure;

import java.util.*;

/**
 * Binary Tree Implementation
 *
 * A hierarchical tree data structure where each node has at most two children:
 * left and right.
 *
 * Operations:
 * - insert(data): Add node in level order - O(n)
 * - inorder(): Traverse left-root-right - O(n)
 * - preorder(): Traverse root-left-right - O(n)
 * - postorder(): Traverse left-right-root - O(n)
 * - levelOrder(): Traverse level by level - O(n)
 * - height(): Get tree height - O(n)
 * - size(): Get number of nodes - O(n)
 * - search(data): Find node with data - O(n)
 *
 * Time Complexity: O(n) for most operations
 * Space Complexity: O(n) for storage, O(h) for recursion where h is height
 *
 * Applications:
 * - Expression trees
 * - Huffman coding
 * - Decision trees
 * - File system hierarchy
 */
public class binary_tree {

    /**
     * TreeNode class represents a node in the binary tree
     */
    static class TreeNode<T> {
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
     * BinaryTree class implements the binary tree data structure
     */
    static class BinaryTree<T> {
        private TreeNode<T> root;

        /**
         * Constructor - creates empty tree
         */
        public BinaryTree() {
            this.root = null;
        }

        /**
         * Constructor - creates tree with root
         */
        public BinaryTree(T data) {
            this.root = new TreeNode<>(data);
        }

        /**
         * Inserts a node in level order
         * Time Complexity: O(n)
         * @param data Data to insert
         */
        public void insert(T data) {
            TreeNode<T> newNode = new TreeNode<>(data);

            if (root == null) {
                root = newNode;
                return;
            }

            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode<T> current = queue.poll();

                if (current.left == null) {
                    current.left = newNode;
                    return;
                }
                queue.offer(current.left);

                if (current.right == null) {
                    current.right = newNode;
                    return;
                }
                queue.offer(current.right);
            }
        }

        /**
         * Inorder traversal: Left -> Root -> Right
         * Time Complexity: O(n)
         * @return List of elements in inorder
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
         * Preorder traversal: Root -> Left -> Right
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
         * Postorder traversal: Left -> Right -> Root
         * Time Complexity: O(n)
         * @return List of elements in postorder
         */
        public List<T> postorder() {
            List<T> result = new ArrayList<>();
            postorderHelper(root, result);
            return result;
        }

        private void postorderHelper(TreeNode<T> node, List<T> result) {
            if (node == null) return;
            postorderHelper(node.left, result);
            postorderHelper(node.right, result);
            result.add(node.data);
        }

        /**
         * Level order traversal (BFS)
         * Time Complexity: O(n)
         * @return List of elements in level order
         */
        public List<T> levelOrder() {
            List<T> result = new ArrayList<>();
            if (root == null) return result;

            Queue<TreeNode<T>> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode<T> current = queue.poll();
                result.add(current.data);

                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            return result;
        }

        /**
         * Gets the height of the tree
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
         * Gets the number of nodes in the tree
         * Time Complexity: O(n)
         * @return Number of nodes
         */
        public int size() {
            return sizeHelper(root);
        }

        private int sizeHelper(TreeNode<T> node) {
            if (node == null) return 0;
            return 1 + sizeHelper(node.left) + sizeHelper(node.right);
        }

        /**
         * Searches for a node with given data
         * Time Complexity: O(n)
         * @param data Data to search for
         * @return true if found, false otherwise
         */
        public boolean search(T data) {
            return searchHelper(root, data);
        }

        private boolean searchHelper(TreeNode<T> node, T data) {
            if (node == null) return false;
            if (node.data.equals(data)) return true;
            return searchHelper(node.left, data) || searchHelper(node.right, data);
        }

        /**
         * Checks if tree is empty
         * Time Complexity: O(1)
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return root == null;
        }

        /**
         * Gets the root data
         * @return Root data
         */
        public T getRoot() {
            return root != null ? root.data : null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Binary Tree Implementation Demo\n");

        // Test 1: Build a binary tree
        System.out.println("Test 1: Build Binary Tree");
        BinaryTree<Integer> tree = new BinaryTree<>();

        System.out.println("Inserting: 1, 2, 3, 4, 5, 6, 7");
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

        System.out.println("Tree structure:");
        System.out.println("       1");
        System.out.println("      / \\");
        System.out.println("     2   3");
        System.out.println("    / \\ / \\");
        System.out.println("   4  5 6  7");
        System.out.println();

        // Test 2: Traversals
        System.out.println("Test 2: Tree Traversals");
        System.out.println("Inorder (L-Root-R): " + tree.inorder());
        System.out.println("Preorder (Root-L-R): " + tree.preorder());
        System.out.println("Postorder (L-R-Root): " + tree.postorder());
        System.out.println("Level Order: " + tree.levelOrder());
        System.out.println();

        // Test 3: Tree properties
        System.out.println("Test 3: Tree Properties");
        System.out.println("Height: " + tree.height());
        System.out.println("Size: " + tree.size());
        System.out.println("Root: " + tree.getRoot());
        System.out.println("Is empty: " + tree.isEmpty());
        System.out.println();

        // Test 4: Search
        System.out.println("Test 4: Search Operations");
        System.out.println("Search for 5: " + tree.search(5));
        System.out.println("Search for 10: " + tree.search(10));
        System.out.println();

        // Test 5: String tree
        System.out.println("Test 5: String Binary Tree");
        BinaryTree<String> stringTree = new BinaryTree<>();
        stringTree.insert("A");
        stringTree.insert("B");
        stringTree.insert("C");
        stringTree.insert("D");
        stringTree.insert("E");

        System.out.println("String tree structure:");
        System.out.println("       A");
        System.out.println("      / \\");
        System.out.println("     B   C");
        System.out.println("    / \\");
        System.out.println("   D   E");
        System.out.println();

        System.out.println("Traversals:");
        System.out.println("Inorder: " + stringTree.inorder());
        System.out.println("Preorder: " + stringTree.preorder());
        System.out.println("Postorder: " + stringTree.postorder());
        System.out.println("Level Order: " + stringTree.levelOrder());
        System.out.println();

        // Test 6: Small tree
        System.out.println("Test 6: Small Tree");
        BinaryTree<Integer> smallTree = new BinaryTree<>(10);
        smallTree.insert(20);
        smallTree.insert(30);

        System.out.println("Tree structure:");
        System.out.println("    10");
        System.out.println("   / \\");
        System.out.println("  20  30");
        System.out.println();

        System.out.println("Height: " + smallTree.height());
        System.out.println("Size: " + smallTree.size());
        System.out.println("Level Order: " + smallTree.levelOrder());
    }
}
