package org.algorithm_datastructure;

import java.util.*;

/**
 * Trie (Prefix Tree) Implementation
 *
 * A tree-like data structure used for efficient string search operations.
 * Each node represents a character, and paths from root represent strings.
 *
 * Operations:
 * - insert(word): Add word to trie - O(m) where m is word length
 * - search(word): Check if word exists - O(m)
 * - startsWith(prefix): Check if prefix exists - O(m)
 * - delete(word): Remove word from trie - O(m)
 * - getAllWords(): Get all words in trie - O(n) where n is total characters
 * - countWords(): Count words with given prefix - O(m + k) where k is matches
 *
 * Time Complexity: O(m) for most operations where m is word length
 * Space Complexity: O(ALPHABET_SIZE * N * M) worst case
 *
 * Applications:
 * - Autocomplete
 * - Spell checker
 * - IP routing
 * - Dictionary implementation
 */
public class trie_implementation {

    /**
     * TrieNode represents a single node in the trie
     */
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    /**
     * Trie class implements the prefix tree data structure
     */
    static class Trie {
        private TrieNode root;
        private int wordCount;

        /**
         * Constructor - creates empty trie
         */
        public Trie() {
            root = new TrieNode();
            wordCount = 0;
        }

        /**
         * Inserts a word into the trie
         * Time Complexity: O(m) where m is word length
         * @param word Word to insert
         */
        public void insert(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }

            TrieNode current = root;

            for (char ch : word.toCharArray()) {
                current.children.putIfAbsent(ch, new TrieNode());
                current = current.children.get(ch);
            }

            if (!current.isEndOfWord) {
                current.isEndOfWord = true;
                wordCount++;
            }
        }

        /**
         * Searches for a word in the trie
         * Time Complexity: O(m) where m is word length
         * @param word Word to search for
         * @return true if word exists, false otherwise
         */
        public boolean search(String word) {
            TrieNode node = searchNode(word);
            return node != null && node.isEndOfWord;
        }

        /**
         * Checks if any word starts with given prefix
         * Time Complexity: O(m) where m is prefix length
         * @param prefix Prefix to check
         * @return true if prefix exists, false otherwise
         */
        public boolean startsWith(String prefix) {
            return searchNode(prefix) != null;
        }

        /**
         * Helper method to search for a node
         * @param str String to search for
         * @return Node if found, null otherwise
         */
        private TrieNode searchNode(String str) {
            TrieNode current = root;

            for (char ch : str.toCharArray()) {
                TrieNode node = current.children.get(ch);
                if (node == null) {
                    return null;
                }
                current = node;
            }

            return current;
        }

        /**
         * Deletes a word from the trie
         * Time Complexity: O(m) where m is word length
         * @param word Word to delete
         * @return true if word was deleted, false if not found
         */
        public boolean delete(String word) {
            if (!search(word)) {
                return false;
            }

            deleteHelper(root, word, 0);
            wordCount--;
            return true;
        }

        private boolean deleteHelper(TrieNode current, String word, int index) {
            if (index == word.length()) {
                current.isEndOfWord = false;
                return current.children.isEmpty();
            }

            char ch = word.charAt(index);
            TrieNode node = current.children.get(ch);

            if (node == null) {
                return false;
            }

            boolean shouldDeleteChild = deleteHelper(node, word, index + 1);

            if (shouldDeleteChild) {
                current.children.remove(ch);
                return current.children.isEmpty() && !current.isEndOfWord;
            }

            return false;
        }

        /**
         * Gets all words in the trie
         * Time Complexity: O(n) where n is total characters
         * @return List of all words
         */
        public List<String> getAllWords() {
            List<String> words = new ArrayList<>();
            getAllWordsHelper(root, "", words);
            return words;
        }

        private void getAllWordsHelper(TrieNode node, String prefix, List<String> words) {
            if (node.isEndOfWord) {
                words.add(prefix);
            }

            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                getAllWordsHelper(entry.getValue(), prefix + entry.getKey(), words);
            }
        }

        /**
         * Gets all words with given prefix
         * Time Complexity: O(m + k) where m is prefix length, k is matches
         * @param prefix Prefix to search for
         * @return List of words with given prefix
         */
        public List<String> getAllWordsWithPrefix(String prefix) {
            List<String> words = new ArrayList<>();
            TrieNode node = searchNode(prefix);

            if (node != null) {
                getAllWordsHelper(node, prefix, words);
            }

            return words;
        }

        /**
         * Counts words with given prefix
         * Time Complexity: O(m + k)
         * @param prefix Prefix to count
         * @return Number of words with prefix
         */
        public int countWordsWithPrefix(String prefix) {
            return getAllWordsWithPrefix(prefix).size();
        }

        /**
         * Returns total number of words in trie
         * Time Complexity: O(1)
         * @return Number of words
         */
        public int size() {
            return wordCount;
        }

        /**
         * Checks if trie is empty
         * Time Complexity: O(1)
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return wordCount == 0;
        }

        /**
         * Finds longest common prefix among all words
         * Time Complexity: O(n * m)
         * @return Longest common prefix
         */
        public String longestCommonPrefix() {
            StringBuilder prefix = new StringBuilder();
            TrieNode current = root;

            while (current.children.size() == 1 && !current.isEndOfWord) {
                Map.Entry<Character, TrieNode> entry = current.children.entrySet().iterator().next();
                prefix.append(entry.getKey());
                current = entry.getValue();
            }

            return prefix.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Trie (Prefix Tree) Implementation Demo\n");

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Insert and Search");
        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("apply");
        trie.insert("banana");

        System.out.println("Inserted: apple, app, application, apply, banana");
        System.out.println("Search 'apple': " + trie.search("apple"));
        System.out.println("Search 'app': " + trie.search("app"));
        System.out.println("Search 'appl': " + trie.search("appl"));
        System.out.println("Search 'banana': " + trie.search("banana"));
        System.out.println("Total words: " + trie.size());
        System.out.println();

        // Test 2: StartsWith (prefix search)
        System.out.println("Test 2: Prefix Search");
        System.out.println("Starts with 'app': " + trie.startsWith("app"));
        System.out.println("Starts with 'appl': " + trie.startsWith("appl"));
        System.out.println("Starts with 'ban': " + trie.startsWith("ban"));
        System.out.println("Starts with 'cat': " + trie.startsWith("cat"));
        System.out.println();

        // Test 3: Get all words
        System.out.println("Test 3: Get All Words");
        System.out.println("All words: " + trie.getAllWords());
        System.out.println();

        // Test 4: Autocomplete
        System.out.println("Test 4: Autocomplete (words with prefix)");
        System.out.println("Words starting with 'app': " + trie.getAllWordsWithPrefix("app"));
        System.out.println("Words starting with 'appl': " + trie.getAllWordsWithPrefix("appl"));
        System.out.println("Words starting with 'b': " + trie.getAllWordsWithPrefix("b"));
        System.out.println();

        // Test 5: Count words with prefix
        System.out.println("Test 5: Count Words with Prefix");
        System.out.println("Count of words with 'app': " + trie.countWordsWithPrefix("app"));
        System.out.println("Count of words with 'appl': " + trie.countWordsWithPrefix("appl"));
        System.out.println();

        // Test 6: Delete
        System.out.println("Test 6: Delete Operation");
        System.out.println("All words before deletion: " + trie.getAllWords());
        System.out.println("Deleting 'app': " + trie.delete("app"));
        System.out.println("All words after deletion: " + trie.getAllWords());
        System.out.println("Search 'app': " + trie.search("app"));
        System.out.println("Search 'apple': " + trie.search("apple"));
        System.out.println("Size: " + trie.size());
        System.out.println();

        // Test 7: Dictionary simulation
        System.out.println("Test 7: Dictionary/Spell Checker");
        Trie dictionary = new Trie();
        String[] words = {"cat", "car", "card", "care", "careful", "dog", "dodge"};

        System.out.println("Building dictionary with: " + Arrays.toString(words));
        for (String word : words) {
            dictionary.insert(word);
        }

        String[] queries = {"cat", "cats", "car", "card", "careful", "dog"};
        System.out.println("\nSpell check:");
        for (String query : queries) {
            System.out.println(query + ": " + (dictionary.search(query) ? "✓ correct" : "✗ not found"));
        }
        System.out.println();

        // Test 8: Longest common prefix
        System.out.println("Test 8: Longest Common Prefix");
        Trie prefixTrie = new Trie();
        prefixTrie.insert("flower");
        prefixTrie.insert("flow");
        prefixTrie.insert("flight");

        System.out.println("Words: flower, flow, flight");
        System.out.println("Longest common prefix: '" + prefixTrie.longestCommonPrefix() + "'");
        System.out.println();

        // Test 9: Empty trie
        System.out.println("Test 9: Empty Trie");
        Trie emptyTrie = new Trie();
        System.out.println("Is empty: " + emptyTrie.isEmpty());
        System.out.println("Size: " + emptyTrie.size());
        System.out.println("Search 'test': " + emptyTrie.search("test"));
    }
}
