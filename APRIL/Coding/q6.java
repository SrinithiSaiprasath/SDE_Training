
public class q6 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node insertionSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node sorted = null;

        while (head != null) {
            Node current = head;
            head = head.next;

            if (sorted == null || sorted.data >= current.data) {
                current.next = sorted;
                sorted = current;
            } else {
                Node temp = sorted;
                while (temp.next != null && temp.next.data < current.data) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }
        }

        return sorted;
    }

   

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println();
            sc.close();
            return  ;
        }

        Node head = new Node(sc.nextInt());
        Node current = head;

        for (int i = 1; i < n; i++) {
            current.next = new Node(sc.nextInt());
            current = current.next;
        }

        head = insertionSort(head);
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }

        System.out.println();

        sc.close();
    }
}
