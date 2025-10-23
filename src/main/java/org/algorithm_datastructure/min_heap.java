package org.algorithm_datastructure;

import java.util.*;

/**
 * Min Heap Implementation using Array
 *
 * A complete binary tree where every parent node has a value less than
 * or equal to its children. The minimum element is always at the root.
 *
 * Operations:
 * - insert(element): Add element maintaining heap property - O(log n)
 * - extractMin(): Remove and return minimum element - O(log n)
 * - peek(): Get minimum without removing - O(1)
 * - heapify(array): Convert array to heap - O(n)
 * - delete(index): Remove element at index - O(log n)
 *
 * Time Complexity: O(log n) for insert/delete, O(1) for peek
 * Space Complexity: O(n) where n is the number of elements
 *
 * Array representation:
 * - Parent of node at index i is at (i-1)/2
 * - Left child of node at index i is at 2*i + 1
 * - Right child of node at index i is at 2*i + 2
 *
 * Applications:
 * - Priority Queue
 * - Heap Sort
 * - Dijkstra's Algorithm
 * - Finding k smallest elements
 */
public class min_heap {

    static class MinHeap<T extends Comparable<T>> {
        private List<T> heap;

        /**
         * Constructor - creates empty min heap
         */
        public MinHeap() {
            this.heap = new ArrayList<>();
        }

        /**
         * Constructor - creates min heap from array
         * Time Complexity: O(n)
         * @param array Array to heapify
         */
        public MinHeap(T[] array) {
            this.heap = new ArrayList<>(Arrays.asList(array));
            heapify();
        }

        /**
         * Inserts an element into the heap
         * Time Complexity: O(log n)
         * @param element Element to insert
         */
        public void insert(T element) {
            heap.add(element);
            bubbleUp(heap.size() - 1);
        }

        /**
         * Removes and returns the minimum element (root)
         * Time Complexity: O(log n)
         * @return Minimum element
         */
        public T extractMin() {
            if (isEmpty()) {
                throw new NoSuchElementException("Heap is empty");
            }

            T min = heap.get(0);
            T last = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, last);
                bubbleDown(0);
            }

            return min;
        }

        /**
         * Returns the minimum element without removing it
         * Time Complexity: O(1)
         * @return Minimum element
         */
        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Heap is empty");
            }
            return heap.get(0);
        }

        /**
         * Deletes element at given index
         * Time Complexity: O(log n)
         * @param index Index to delete from
         */
        public void delete(int index) {
            if (index < 0 || index >= heap.size()) {
                throw new IndexOutOfBoundsException("Invalid index");
            }

            T last = heap.remove(heap.size() - 1);

            if (index < heap.size()) {
                heap.set(index, last);
                bubbleDown(index);
                bubbleUp(index);
            }
        }

        /**
         * Converts array to heap
         * Time Complexity: O(n)
         */
        private void heapify() {
            for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
                bubbleDown(i);
            }
        }

        /**
         * Moves element up to maintain heap property
         * Time Complexity: O(log n)
         * @param index Index of element to bubble up
         */
        private void bubbleUp(int index) {
            int parent = (index - 1) / 2;

            while (index > 0 && heap.get(index).compareTo(heap.get(parent)) < 0) {
                swap(index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        }

        /**
         * Moves element down to maintain heap property
         * Time Complexity: O(log n)
         * @param index Index of element to bubble down
         */
        private void bubbleDown(int index) {
            int size = heap.size();

            while (true) {
                int smallest = index;
                int left = 2 * index + 1;
                int right = 2 * index + 2;

                if (left < size && heap.get(left).compareTo(heap.get(smallest)) < 0) {
                    smallest = left;
                }

                if (right < size && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                    smallest = right;
                }

                if (smallest == index) {
                    break;
                }

                swap(index, smallest);
                index = smallest;
            }
        }

        /**
         * Swaps two elements in the heap
         * @param i First index
         * @param j Second index
         */
        private void swap(int i, int j) {
            T temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        /**
         * Checks if heap is empty
         * Time Complexity: O(1)
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return heap.isEmpty();
        }

        /**
         * Returns the size of the heap
         * Time Complexity: O(1)
         * @return Number of elements
         */
        public int size() {
            return heap.size();
        }

        /**
         * Returns string representation of heap
         * @return String representation
         */
        @Override
        public String toString() {
            return heap.toString();
        }

        /**
         * Returns heap as array for visualization
         * @return Heap array
         */
        public List<T> toList() {
            return new ArrayList<>(heap);
        }
    }

    public static void main(String[] args) {
        System.out.println("Min Heap Implementation Demo\n");

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Operations");
        MinHeap<Integer> minHeap = new MinHeap<>();

        System.out.println("Inserting: 10, 5, 20, 1, 15, 30");
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(20);
        minHeap.insert(1);
        minHeap.insert(15);
        minHeap.insert(30);

        System.out.println("Heap: " + minHeap);
        System.out.println("Size: " + minHeap.size());
        System.out.println("Min (peek): " + minHeap.peek());
        System.out.println();

        // Test 2: Extract min
        System.out.println("Test 2: Extract Min");
        System.out.println("Extracting min: " + minHeap.extractMin());
        System.out.println("Heap after extraction: " + minHeap);
        System.out.println("New min: " + minHeap.peek());
        System.out.println();

        // Test 3: Build heap from array
        System.out.println("Test 3: Build Heap from Array");
        Integer[] arr = {40, 10, 30, 50, 20, 15, 5};
        System.out.println("Array: " + Arrays.toString(arr));
        MinHeap<Integer> heapFromArray = new MinHeap<>(arr);
        System.out.println("Min Heap: " + heapFromArray);
        System.out.println("Min: " + heapFromArray.peek());
        System.out.println();

        // Test 4: Extract all elements (should come out sorted)
        System.out.println("Test 4: Extract All (Heap Sort)");
        MinHeap<Integer> sortHeap = new MinHeap<>();
        int[] unsorted = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Inserting: " + Arrays.toString(unsorted));
        for (int num : unsorted) {
            sortHeap.insert(num);
        }

        System.out.print("Extracting in sorted order: ");
        List<Integer> sorted = new ArrayList<>();
        while (!sortHeap.isEmpty()) {
            sorted.add(sortHeap.extractMin());
        }
        System.out.println(sorted);
        System.out.println();

        // Test 5: Priority queue simulation
        System.out.println("Test 5: Priority Queue Simulation");
        MinHeap<Integer> priorityQueue = new MinHeap<>();
        System.out.println("Adding tasks with priorities: 5, 2, 8, 1, 9");
        priorityQueue.insert(5);
        priorityQueue.insert(2);
        priorityQueue.insert(8);
        priorityQueue.insert(1);
        priorityQueue.insert(9);

        System.out.println("Processing tasks in priority order:");
        System.out.println("Task priority: " + priorityQueue.extractMin());
        System.out.println("Task priority: " + priorityQueue.extractMin());
        System.out.println("Task priority: " + priorityQueue.extractMin());
        System.out.println("Remaining tasks: " + priorityQueue);
        System.out.println();

        // Test 6: String heap
        System.out.println("Test 6: String Min Heap");
        MinHeap<String> stringHeap = new MinHeap<>();
        stringHeap.insert("Grape");
        stringHeap.insert("Apple");
        stringHeap.insert("Orange");
        stringHeap.insert("Banana");

        System.out.println("String heap: " + stringHeap);
        System.out.println("Min (alphabetically first): " + stringHeap.peek());
        System.out.println();

        // Test 7: Finding k smallest elements
        System.out.println("Test 7: Find 3 Smallest Elements");
        MinHeap<Integer> kSmallest = new MinHeap<>();
        int[] numbers = {7, 10, 4, 3, 20, 15};
        System.out.println("Array: " + Arrays.toString(numbers));

        for (int num : numbers) {
            kSmallest.insert(num);
        }

        System.out.print("3 smallest elements: ");
        for (int i = 0; i < 3 && !kSmallest.isEmpty(); i++) {
            System.out.print(kSmallest.extractMin() + " ");
        }
        System.out.println("\n");

        // Test 8: Delete operation
        System.out.println("Test 8: Delete Operation");
        MinHeap<Integer> deleteHeap = new MinHeap<>();
        for (int i = 1; i <= 7; i++) {
            deleteHeap.insert(i);
        }
        System.out.println("Heap: " + deleteHeap);
        System.out.println("Deleting element at index 2");
        deleteHeap.delete(2);
        System.out.println("Heap after deletion: " + deleteHeap);
    }
}
