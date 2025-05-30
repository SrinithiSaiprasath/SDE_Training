public class q7 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node reverseKGroup(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        Node dummy = new Node(0);
        dummy.next = head;
        Node prevGroupEnd = dummy;

        while (true) {
            Node groupStart = prevGroupEnd.next;
            Node groupEnd = prevGroupEnd;

            for (int i = 0; i < k && groupEnd != null; i++) {
                groupEnd = groupEnd.next;
            }

            if (groupEnd == null) {
                break; 
            }

            Node nextGroupStart = groupEnd.next;
            groupEnd.next = null;

            prevGroupEnd.next = reverseList(groupStart);
            groupStart.next = nextGroupStart;

            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        // Read the linked list
        String[] values = sc.nextLine().split(" ");
        Node head = null, tail = null;
        for (String value : values) {
            Node newNode = new Node(Integer.parseInt(value));
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int k = sc.nextInt();

        head = reverseKGroup(head, k);

        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();

        sc.close();
    }
}
