package org.algorithm_datastructure;

import java.util.*;

/**
 * Max Heap Implementation using Array
 *
 * A complete binary tree where every parent node has a value greater than
 * or equal to its children. The maximum element is always at the root.
 *
 * Operations:
 * - insert(element): Add element maintaining heap property - O(log n)
 * - extractMax(): Remove and return maximum element - O(log n)
 * - peek(): Get maximum without removing - O(1)
 * - heapify(array): Convert array to heap - O(n)
 *
 * Time Complexity: O(log n) for insert/delete, O(1) for peek
 * Space Complexity: O(n)
 *
 * Applications:
 * - Priority Queue (highest priority first)
 * - Heap Sort (descending order)
 * - Finding k largest elements
 */
public class max_heap {

    static class MaxHeap<T extends Comparable<T>> {
        private List<T> heap;

        public MaxHeap() {
            this.heap = new ArrayList<>();
        }

        public MaxHeap(T[] array) {
            this.heap = new ArrayList<>(Arrays.asList(array));
            heapify();
        }

        public void insert(T element) {
            heap.add(element);
            bubbleUp(heap.size() - 1);
        }

        public T extractMax() {
            if (isEmpty()) {
                throw new NoSuchElementException("Heap is empty");
            }

            T max = heap.get(0);
            T last = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, last);
                bubbleDown(0);
            }

            return max;
        }

        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Heap is empty");
            }
            return heap.get(0);
        }

        private void heapify() {
            for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
                bubbleDown(i);
            }
        }

        private void bubbleUp(int index) {
            int parent = (index - 1) / 2;

            while (index > 0 && heap.get(index).compareTo(heap.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        }

        private void bubbleDown(int index) {
            int size = heap.size();

            while (true) {
                int largest = index;
                int left = 2 * index + 1;
                int right = 2 * index + 2;

                if (left < size && heap.get(left).compareTo(heap.get(largest)) > 0) {
                    largest = left;
                }

                if (right < size && heap.get(right).compareTo(heap.get(largest)) > 0) {
                    largest = right;
                }

                if (largest == index) {
                    break;
                }

                swap(index, largest);
                index = largest;
            }
        }

        private void swap(int i, int j) {
            T temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        public int size() {
            return heap.size();
        }

        @Override
        public String toString() {
            return heap.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Max Heap Implementation Demo\n");

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Operations");
        MaxHeap<Integer> maxHeap = new MaxHeap<>();

        System.out.println("Inserting: 10, 5, 20, 1, 15, 30");
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(20);
        maxHeap.insert(1);
        maxHeap.insert(15);
        maxHeap.insert(30);

        System.out.println("Heap: " + maxHeap);
        System.out.println("Max (peek): " + maxHeap.peek());
        System.out.println();

        // Test 2: Extract max
        System.out.println("Test 2: Extract Max");
        System.out.println("Extracting max: " + maxHeap.extractMax());
        System.out.println("Heap after extraction: " + maxHeap);
        System.out.println("New max: " + maxHeap.peek());
        System.out.println();

        // Test 3: Extract all (descending order)
        System.out.println("Test 3: Extract All (Descending Order)");
        MaxHeap<Integer> sortHeap = new MaxHeap<>();
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Inserting: " + Arrays.toString(numbers));
        for (int num : numbers) {
            sortHeap.insert(num);
        }

        System.out.print("Extracting in descending order: ");
        while (!sortHeap.isEmpty()) {
            System.out.print(sortHeap.extractMax() + " ");
        }
        System.out.println("\n");

        // Test 4: Find k largest elements
        System.out.println("Test 4: Find 3 Largest Elements");
        MaxHeap<Integer> kLargest = new MaxHeap<>();
        int[] arr = {7, 10, 4, 3, 20, 15};
        System.out.println("Array: " + Arrays.toString(arr));

        for (int num : arr) {
            kLargest.insert(num);
        }

        System.out.print("3 largest elements: ");
        for (int i = 0; i < 3 && !kLargest.isEmpty(); i++) {
            System.out.print(kLargest.extractMax() + " ");
        }
        System.out.println();
    }
}
