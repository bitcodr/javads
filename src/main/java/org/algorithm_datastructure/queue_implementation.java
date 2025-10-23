package org.algorithm_datastructure;

import java.util.NoSuchElementException;

/**
 * Queue Implementation using Circular Array
 *
 * Queue is a First-In-First-Out (FIFO) data structure where elements are
 * added at the rear and removed from the front.
 *
 * Operations:
 * - enqueue(element): Add element to rear - O(1)
 * - dequeue(): Remove and return front element - O(1)
 * - peek(): Return front element without removing - O(1)
 * - isEmpty(): Check if queue is empty - O(1)
 * - size(): Get number of elements - O(1)
 *
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) where n is the number of elements
 *
 * Applications:
 * - Task scheduling
 * - Breadth-First Search
 * - Request handling
 * - Print queue
 */
public class queue_implementation {

    static class Queue<T> {
        private static final int DEFAULT_CAPACITY = 10;
        private Object[] elements;
        private int front;
        private int rear;
        private int size;
        private int capacity;

        /**
         * Constructor - creates empty queue with default capacity
         */
        public Queue() {
            this(DEFAULT_CAPACITY);
        }

        /**
         * Constructor - creates empty queue with specified capacity
         * @param capacity Initial capacity of the queue
         */
        public Queue(int capacity) {
            this.capacity = capacity;
            this.elements = new Object[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        /**
         * Adds an element to the rear of the queue
         * Time Complexity: O(1) amortized
         * @param element Element to add
         */
        public void enqueue(T element) {
            if (size == capacity) {
                resize();
            }
            rear = (rear + 1) % capacity;
            elements[rear] = element;
            size++;
        }

        /**
         * Removes and returns the front element
         * Time Complexity: O(1)
         * @return Front element
         * @throws NoSuchElementException if queue is empty
         */
        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            T element = (T) elements[front];
            elements[front] = null; // Help garbage collection
            front = (front + 1) % capacity;
            size--;
            return element;
        }

        /**
         * Returns the front element without removing it
         * Time Complexity: O(1)
         * @return Front element
         * @throws NoSuchElementException if queue is empty
         */
        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return (T) elements[front];
        }

        /**
         * Checks if queue is empty
         * Time Complexity: O(1)
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * Returns the number of elements in queue
         * Time Complexity: O(1)
         * @return Number of elements
         */
        public int size() {
            return size;
        }

        /**
         * Doubles the capacity of the queue
         * Time Complexity: O(n)
         */
        private void resize() {
            int newCapacity = capacity * 2;
            Object[] newElements = new Object[newCapacity];

            // Copy elements in correct order
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(front + i) % capacity];
            }

            elements = newElements;
            front = 0;
            rear = size - 1;
            capacity = newCapacity;
        }

        /**
         * Returns string representation of queue
         * @return String representation
         */
        @Override
        public String toString() {
            if (isEmpty()) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                int index = (front + i) % capacity;
                sb.append(elements[index]);
                if (i < size - 1) sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Queue Implementation Demo\n");

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Operations");
        Queue<Integer> queue = new Queue<>();

        // Enqueue elements
        System.out.println("Enqueuing elements: 10, 20, 30, 40, 50");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        System.out.println("Queue: " + queue);
        System.out.println("Size: " + queue.size());
        System.out.println();

        // Peek
        System.out.println("Peek (front): " + queue.peek());
        System.out.println("Queue after peek: " + queue);
        System.out.println();

        // Dequeue elements
        System.out.println("Dequeuing elements:");
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Queue: " + queue);
        System.out.println("Size: " + queue.size());
        System.out.println();

        // Test 2: String queue
        System.out.println("Test 2: String Queue");
        Queue<String> stringQueue = new Queue<>();
        stringQueue.enqueue("First");
        stringQueue.enqueue("Second");
        stringQueue.enqueue("Third");
        System.out.println("String queue: " + stringQueue);
        System.out.println("Dequeued: " + stringQueue.dequeue());
        System.out.println("String queue: " + stringQueue);
        System.out.println();

        // Test 3: Circular behavior
        System.out.println("Test 3: Circular Behavior");
        Queue<Integer> circularQueue = new Queue<>(5);
        System.out.println("Creating queue with capacity 5");
        System.out.println("Enqueuing: 1, 2, 3, 4, 5");
        for (int i = 1; i <= 5; i++) {
            circularQueue.enqueue(i);
        }
        System.out.println("Queue: " + circularQueue);

        System.out.println("Dequeuing 3 elements:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Dequeued: " + circularQueue.dequeue());
        }
        System.out.println("Queue: " + circularQueue);

        System.out.println("Enqueuing: 6, 7, 8 (wraps around)");
        circularQueue.enqueue(6);
        circularQueue.enqueue(7);
        circularQueue.enqueue(8);
        System.out.println("Queue: " + circularQueue);
        System.out.println();

        // Test 4: Process all elements
        System.out.println("Test 4: Process All Elements");
        Queue<String> taskQueue = new Queue<>();
        taskQueue.enqueue("Task 1");
        taskQueue.enqueue("Task 2");
        taskQueue.enqueue("Task 3");
        System.out.println("Task queue: " + taskQueue);

        System.out.println("Processing all tasks:");
        while (!taskQueue.isEmpty()) {
            System.out.println("Processing: " + taskQueue.dequeue());
        }
        System.out.println("Is empty: " + taskQueue.isEmpty());
        System.out.println();

        // Test 5: Auto-resize
        System.out.println("Test 5: Auto-resize");
        Queue<Integer> resizableQueue = new Queue<>(3);
        System.out.println("Initial capacity: 3");
        System.out.println("Enqueuing 7 elements...");
        for (int i = 1; i <= 7; i++) {
            resizableQueue.enqueue(i * 10);
        }
        System.out.println("Queue: " + resizableQueue);
        System.out.println("Size: " + resizableQueue.size());
        System.out.println();

        // Test 6: BFS simulation
        System.out.println("Test 6: BFS Level Traversal Simulation");
        Queue<String> bfsQueue = new Queue<>();
        bfsQueue.enqueue("Level 0: Root");
        bfsQueue.enqueue("Level 1: Left");
        bfsQueue.enqueue("Level 1: Right");
        bfsQueue.enqueue("Level 2: LL");
        bfsQueue.enqueue("Level 2: LR");

        System.out.println("Processing nodes in BFS order:");
        while (!bfsQueue.isEmpty()) {
            System.out.println(bfsQueue.dequeue());
        }
    }
}
