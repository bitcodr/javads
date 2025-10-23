package org.algorithm_datastructure;

import java.util.EmptyStackException;

/**
 * Stack Implementation using Array
 *
 * Stack is a Last-In-First-Out (LIFO) data structure where elements are
 * added and removed from the same end (top).
 *
 * Operations:
 * - push(element): Add element to top - O(1)
 * - pop(): Remove and return top element - O(1)
 * - peek(): Return top element without removing - O(1)
 * - isEmpty(): Check if stack is empty - O(1)
 * - size(): Get number of elements - O(1)
 *
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) where n is the number of elements
 *
 * Applications:
 * - Expression evaluation
 * - Function call stack
 * - Undo operations
 * - Backtracking algorithms
 */
public class stack_implementation {

    static class Stack<T> {
        private static final int DEFAULT_CAPACITY = 10;
        private Object[] elements;
        private int top;
        private int capacity;

        /**
         * Constructor - creates empty stack with default capacity
         */
        public Stack() {
            this(DEFAULT_CAPACITY);
        }

        /**
         * Constructor - creates empty stack with specified capacity
         * @param capacity Initial capacity of the stack
         */
        public Stack(int capacity) {
            this.capacity = capacity;
            this.elements = new Object[capacity];
            this.top = -1;
        }

        /**
         * Pushes an element onto the top of the stack
         * Time Complexity: O(1) amortized (O(n) when resizing)
         * @param element Element to push
         */
        public void push(T element) {
            if (top == capacity - 1) {
                resize();
            }
            elements[++top] = element;
        }

        /**
         * Removes and returns the top element
         * Time Complexity: O(1)
         * @return Top element
         * @throws EmptyStackException if stack is empty
         */
        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T element = (T) elements[top];
            elements[top--] = null; // Help garbage collection
            return element;
        }

        /**
         * Returns the top element without removing it
         * Time Complexity: O(1)
         * @return Top element
         * @throws EmptyStackException if stack is empty
         */
        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return (T) elements[top];
        }

        /**
         * Checks if stack is empty
         * Time Complexity: O(1)
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return top == -1;
        }

        /**
         * Returns the number of elements in stack
         * Time Complexity: O(1)
         * @return Number of elements
         */
        public int size() {
            return top + 1;
        }

        /**
         * Doubles the capacity of the stack
         * Time Complexity: O(n)
         */
        private void resize() {
            capacity *= 2;
            Object[] newElements = new Object[capacity];
            System.arraycopy(elements, 0, newElements, 0, top + 1);
            elements = newElements;
        }

        /**
         * Returns string representation of stack
         * @return String representation
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i <= top; i++) {
                sb.append(elements[i]);
                if (i < top) sb.append(", ");
            }
            sb.append("] <- top");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Stack Implementation Demo\n");

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Operations");
        Stack<Integer> stack = new Stack<>();

        // Push elements
        System.out.println("Pushing elements: 10, 20, 30, 40, 50");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        System.out.println("Stack: " + stack);
        System.out.println("Size: " + stack.size());
        System.out.println();

        // Peek
        System.out.println("Peek: " + stack.peek());
        System.out.println("Stack after peek: " + stack);
        System.out.println();

        // Pop elements
        System.out.println("Popping elements:");
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Stack: " + stack);
        System.out.println("Size: " + stack.size());
        System.out.println();

        // Test 2: String stack
        System.out.println("Test 2: String Stack");
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Hello");
        stringStack.push("World");
        stringStack.push("Stack");
        System.out.println("String stack: " + stringStack);
        System.out.println("Popped: " + stringStack.pop());
        System.out.println("String stack: " + stringStack);
        System.out.println();

        // Test 3: Expression evaluation example
        System.out.println("Test 3: Balanced Parentheses Check");
        System.out.println("Expression: ((()))");
        System.out.println("Balanced: " + isBalanced("((()))"));
        System.out.println("Expression: ((()");
        System.out.println("Balanced: " + isBalanced("((()"));
        System.out.println("Expression: ())");
        System.out.println("Balanced: " + isBalanced("())"));
        System.out.println();

        // Test 4: Stack operations until empty
        System.out.println("Test 4: Empty Stack");
        Stack<Integer> smallStack = new Stack<>(3);
        smallStack.push(1);
        smallStack.push(2);
        smallStack.push(3);
        System.out.println("Stack: " + smallStack);

        System.out.println("Popping all elements:");
        while (!smallStack.isEmpty()) {
            System.out.println("Popped: " + smallStack.pop());
        }
        System.out.println("Is empty: " + smallStack.isEmpty());

        // Test 5: Auto-resize
        System.out.println("\nTest 5: Auto-resize");
        Stack<Integer> resizableStack = new Stack<>(2);
        System.out.println("Initial capacity: 2");
        System.out.println("Pushing 5 elements...");
        for (int i = 1; i <= 5; i++) {
            resizableStack.push(i * 10);
        }
        System.out.println("Stack: " + resizableStack);
        System.out.println("Size: " + resizableStack.size());
    }

    /**
     * Helper method to check balanced parentheses using stack
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
