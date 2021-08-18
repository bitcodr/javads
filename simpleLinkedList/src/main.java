import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {
        int a = 1;
        int b = 6;
        int c = 9;
        int d = 3;

        Node head = new Node(c);
        Node next1 = new Node(a);
        Node next2 = new Node(b);
        Node next3 = new Node(d);

        head.next = next1;
        next1.next = next2;
        next2.next = next3;

        List<Node> linkedList = new ArrayList<>();
        linkedList.add(head);
        linkedList.add(next1);
        linkedList.add(next2);
        linkedList.add(next3);

        linkedList.stream().map(x -> x.data).forEach(System.out::println);
        System.out.println("Number of nodes: " + numberOfNodes(head));
    }


    public static int numberOfNodes(Node head) {
        int x = 1;
        Node current = head;

        while (current.next != null) {
            current = current.next;
            x += 1;
        }

        return x;
    }
}
