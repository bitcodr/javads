package org.algorithm_datastructure;

/**
 * Doubly Linked List Implementation
 *
 * A linear data structure where each node contains data and references to both
 * the next and previous nodes in the sequence.
 *
 * Operations:
 * - insertAtBeginning(data): Add node at start - O(1)
 * - insertAtEnd(data): Add node at end - O(1) with tail pointer
 * - insertAt(position, data): Add node at position - O(n)
 * - deleteFromBeginning(): Remove first node - O(1)
 * - deleteFromEnd(): Remove last node - O(1) with tail pointer
 * - deleteAt(position): Remove node at position - O(n)
 * - search(data): Find node with data - O(n)
 * - reverse(): Reverse the list - O(n)
 *
 * Time Complexity: O(1) for insertion/deletion at both ends, O(n) for middle
 * Space Complexity: O(n) where n is the number of nodes
 *
 * Advantages over singly linked list:
 * - Can traverse in both directions
 * - Deletion is easier (no need to track previous node)
 * - O(1) insertion/deletion at end with tail pointer
 *
 * Applications:
 * - Browser history (forward/back)
 * - LRU cache
 * - Undo/Redo functionality
 * - Music player (next/previous song)
 */
public class doubly_linked_list {

    /**
     * Node class represents a single node in the doubly linked list
     */
    static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * DoublyLinkedList class implements the doubly linked list data structure
     */
    static class DoublyLinkedList<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;

        /**
         * Constructor - creates empty list
         */
        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        /**
         * Inserts a node at the beginning
         * Time Complexity: O(1)
         * @param data Data to insert
         */
        public void insertAtBeginning(T data) {
            Node<T> newNode = new Node<>(data);

            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            size++;
        }

        /**
         * Inserts a node at the end
         * Time Complexity: O(1)
         * @param data Data to insert
         */
        public void insertAtEnd(T data) {
            Node<T> newNode = new Node<>(data);

            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
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

            if (position == size) {
                insertAtEnd(data);
                return;
            }

            Node<T> newNode = new Node<>(data);
            Node<T> current = head;

            for (int i = 0; i < position; i++) {
                current = current.next;
            }

            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
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

            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
            return data;
        }

        /**
         * Deletes the last node
         * Time Complexity: O(1)
         * @return Data of deleted node
         */
        public T deleteFromEnd() {
            if (tail == null) {
                throw new IllegalStateException("List is empty");
            }

            T data = tail.data;

            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
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

            if (position == size - 1) {
                return deleteFromEnd();
            }

            Node<T> current = head;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }

            T data = current.data;
            current.prev.next = current.next;
            current.next.prev = current.prev;
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
            Node<T> current = head;
            Node<T> temp = null;

            while (current != null) {
                temp = current.prev;
                current.prev = current.next;
                current.next = temp;
                current = current.prev;
            }

            if (temp != null) {
                tail = head;
                head = temp.prev;
            }
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
         * Returns string representation (forward)
         * @return String representation
         */
        @Override
        public String toString() {
            return toStringForward();
        }

        /**
         * Returns string representation traversing forward
         * @return String representation
         */
        public String toStringForward() {
            if (head == null) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder("[");
            Node<T> current = head;

            while (current != null) {
                sb.append(current.data);
                if (current.next != null) {
                    sb.append(" <-> ");
                }
                current = current.next;
            }

            sb.append("]");
            return sb.toString();
        }

        /**
         * Returns string representation traversing backward
         * @return String representation
         */
        public String toStringBackward() {
            if (tail == null) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder("[");
            Node<T> current = tail;

            while (current != null) {
                sb.append(current.data);
                if (current.prev != null) {
                    sb.append(" <-> ");
                }
                current = current.prev;
            }

            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("Doubly Linked List Implementation Demo\n");

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Operations");
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        System.out.println("Inserting at end: 10, 20, 30");
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        System.out.println("List (forward): " + list.toStringForward());
        System.out.println("List (backward): " + list.toStringBackward());
        System.out.println("Size: " + list.size());
        System.out.println();

        System.out.println("Inserting at beginning: 5");
        list.insertAtBeginning(5);
        System.out.println("List (forward): " + list.toStringForward());
        System.out.println();

        // Test 2: Insert at position
        System.out.println("Test 2: Insert at Position");
        System.out.println("Inserting 15 at position 2");
        list.insertAt(2, 15);
        System.out.println("List (forward): " + list.toStringForward());
        System.out.println("List (backward): " + list.toStringBackward());
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
        DoublyLinkedList<Integer> reverseList = new DoublyLinkedList<>();
        reverseList.insertAtEnd(1);
        reverseList.insertAtEnd(2);
        reverseList.insertAtEnd(3);
        reverseList.insertAtEnd(4);
        reverseList.insertAtEnd(5);
        System.out.println("Original (forward): " + reverseList.toStringForward());
        System.out.println("Original (backward): " + reverseList.toStringBackward());
        reverseList.reverse();
        System.out.println("Reversed (forward): " + reverseList.toStringForward());
        System.out.println("Reversed (backward): " + reverseList.toStringBackward());
        System.out.println();

        // Test 6: Bidirectional traversal
        System.out.println("Test 6: Bidirectional Traversal");
        DoublyLinkedList<String> stringList = new DoublyLinkedList<>();
        stringList.insertAtEnd("Apple");
        stringList.insertAtEnd("Banana");
        stringList.insertAtEnd("Cherry");
        stringList.insertAtEnd("Date");
        System.out.println("Forward: " + stringList.toStringForward());
        System.out.println("Backward: " + stringList.toStringBackward());
        System.out.println();

        // Test 7: Browser history simulation
        System.out.println("Test 7: Browser History Simulation");
        DoublyLinkedList<String> browserHistory = new DoublyLinkedList<>();
        browserHistory.insertAtEnd("google.com");
        browserHistory.insertAtEnd("github.com");
        browserHistory.insertAtEnd("stackoverflow.com");
        browserHistory.insertAtEnd("leetcode.com");
        System.out.println("History (newest to oldest): " + browserHistory.toStringBackward());
        System.out.println("History (oldest to newest): " + browserHistory.toStringForward());
        System.out.println("Going back: " + browserHistory.deleteFromEnd());
        System.out.println("Current history: " + browserHistory);
    }
}
