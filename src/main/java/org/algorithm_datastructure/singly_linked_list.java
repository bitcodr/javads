package org.algorithm_datastructure;

/**
 * Singly Linked List Implementation
 *
 * A linear data structure where each element (node) contains data and a reference
 * to the next node in the sequence.
 *
 * Operations:
 * - insertAtBeginning(data): Add node at start - O(1)
 * - insertAtEnd(data): Add node at end - O(n)
 * - insertAt(position, data): Add node at position - O(n)
 * - deleteFromBeginning(): Remove first node - O(1)
 * - deleteFromEnd(): Remove last node - O(n)
 * - deleteAt(position): Remove node at position - O(n)
 * - search(data): Find node with data - O(n)
 * - reverse(): Reverse the list - O(n)
 *
 * Time Complexity: O(1) for insertion/deletion at beginning, O(n) for others
 * Space Complexity: O(n) where n is the number of nodes
 *
 * Applications:
 * - Implementation of stacks and queues
 * - Dynamic memory allocation
 * - Polynomial arithmetic
 * - Undo functionality
 */
public class singly_linked_list {

    /**
     * Node class represents a single node in the linked list
     */
    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * SinglyLinkedList class implements the linked list data structure
     */
    static class SinglyLinkedList<T> {
        private Node<T> head;
        private int size;

        /**
         * Constructor - creates empty list
         */
        public SinglyLinkedList() {
            this.head = null;
            this.size = 0;
        }

        /**
         * Inserts a node at the beginning
         * Time Complexity: O(1)
         * @param data Data to insert
         */
        public void insertAtBeginning(T data) {
            Node<T> newNode = new Node<>(data);
            newNode.next = head;
            head = newNode;
            size++;
        }

        /**
         * Inserts a node at the end
         * Time Complexity: O(n)
         * @param data Data to insert
         */
        public void insertAtEnd(T data) {
            Node<T> newNode = new Node<>(data);

            if (head == null) {
                head = newNode;
                size++;
                return;
            }

            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            size++;
        }

        /**
         * Inserts a node at specified position (0-indexed)
         * Time Complexity: O(n)
         * @param position Position to insert at
         * @param data Data to insert
         */
        public void insertAt(int position, T data) {
            if (position < 0 || position > size) {
                throw new IndexOutOfBoundsException("Invalid position");
            }

            if (position == 0) {
                insertAtBeginning(data);
                return;
            }

            Node<T> newNode = new Node<>(data);
            Node<T> current = head;

            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
            size++;
        }

        /**
         * Deletes the first node
         * Time Complexity: O(1)
         * @return Data of deleted node
         */
        public T deleteFromBeginning() {
            if (head == null) {
                throw new IllegalStateException("List is empty");
            }

            T data = head.data;
            head = head.next;
            size--;
            return data;
        }

        /**
         * Deletes the last node
         * Time Complexity: O(n)
         * @return Data of deleted node
         */
        public T deleteFromEnd() {
            if (head == null) {
                throw new IllegalStateException("List is empty");
            }

            if (head.next == null) {
                T data = head.data;
                head = null;
                size--;
                return data;
            }

            Node<T> current = head;
            while (current.next.next != null) {
                current = current.next;
            }

            T data = current.next.data;
            current.next = null;
            size--;
            return data;
        }

        /**
         * Deletes node at specified position
         * Time Complexity: O(n)
         * @param position Position to delete from
         * @return Data of deleted node
         */
        public T deleteAt(int position) {
            if (position < 0 || position >= size) {
                throw new IndexOutOfBoundsException("Invalid position");
            }

            if (position == 0) {
                return deleteFromBeginning();
            }

            Node<T> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }

            T data = current.next.data;
            current.next = current.next.next;
            size--;
            return data;
        }

        /**
         * Searches for a node with given data
         * Time Complexity: O(n)
         * @param data Data to search for
         * @return Position of node, or -1 if not found
         */
        public int search(T data) {
            Node<T> current = head;
            int position = 0;

            while (current != null) {
                if (current.data.equals(data)) {
                    return position;
                }
                current = current.next;
                position++;
            }

            return -1;
        }

        /**
         * Reverses the linked list
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public void reverse() {
            Node<T> prev = null;
            Node<T> current = head;
            Node<T> next = null;

            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            head = prev;
        }

        /**
         * Gets the middle element
         * Time Complexity: O(n)
         * @return Data of middle node
         */
        public T getMiddle() {
            if (head == null) {
                throw new IllegalStateException("List is empty");
            }

            Node<T> slow = head;
            Node<T> fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow.data;
        }

        /**
         * Checks if list is empty
         * Time Complexity: O(1)
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return head == null;
        }

        /**
         * Returns the size of the list
         * Time Complexity: O(1)
         * @return Number of nodes
         */
        public int size() {
            return size;
        }

        /**
         * Returns string representation of the list
         * @return String representation
         */
        @Override
        public String toString() {
            if (head == null) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder("[");
            Node<T> current = head;

            while (current != null) {
                sb.append(current.data);
                if (current.next != null) {
                    sb.append(" -> ");
                }
                current = current.next;
            }

            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Singly Linked List Implementation Demo\n");

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Operations");
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        System.out.println("Inserting at end: 10, 20, 30");
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        System.out.println("List: " + list);
        System.out.println("Size: " + list.size());
        System.out.println();

        System.out.println("Inserting at beginning: 5");
        list.insertAtBeginning(5);
        System.out.println("List: " + list);
        System.out.println();

        // Test 2: Insert at position
        System.out.println("Test 2: Insert at Position");
        System.out.println("Inserting 15 at position 2");
        list.insertAt(2, 15);
        System.out.println("List: " + list);
        System.out.println();

        // Test 3: Search
        System.out.println("Test 3: Search");
        System.out.println("Searching for 15: Position " + list.search(15));
        System.out.println("Searching for 100: Position " + list.search(100));
        System.out.println();

        // Test 4: Delete operations
        System.out.println("Test 4: Delete Operations");
        System.out.println("Deleting from beginning: " + list.deleteFromBeginning());
        System.out.println("List: " + list);
        System.out.println("Deleting from end: " + list.deleteFromEnd());
        System.out.println("List: " + list);
        System.out.println("Deleting at position 1: " + list.deleteAt(1));
        System.out.println("List: " + list);
        System.out.println();

        // Test 5: Reverse
        System.out.println("Test 5: Reverse List");
        SinglyLinkedList<Integer> reverseList = new SinglyLinkedList<>();
        reverseList.insertAtEnd(1);
        reverseList.insertAtEnd(2);
        reverseList.insertAtEnd(3);
        reverseList.insertAtEnd(4);
        reverseList.insertAtEnd(5);
        System.out.println("Original list: " + reverseList);
        reverseList.reverse();
        System.out.println("Reversed list: " + reverseList);
        System.out.println();

        // Test 6: Get middle
        System.out.println("Test 6: Get Middle Element");
        SinglyLinkedList<String> middleList = new SinglyLinkedList<>();
        middleList.insertAtEnd("A");
        middleList.insertAtEnd("B");
        middleList.insertAtEnd("C");
        middleList.insertAtEnd("D");
        middleList.insertAtEnd("E");
        System.out.println("List: " + middleList);
        System.out.println("Middle element: " + middleList.getMiddle());
        System.out.println();

        // Test 7: String list
        System.out.println("Test 7: String Linked List");
        SinglyLinkedList<String> stringList = new SinglyLinkedList<>();
        stringList.insertAtEnd("Apple");
        stringList.insertAtEnd("Banana");
        stringList.insertAtEnd("Cherry");
        System.out.println("String list: " + stringList);
        System.out.println("Size: " + stringList.size());
    }
}
