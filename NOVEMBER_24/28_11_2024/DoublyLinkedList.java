class Node {
    int data ;
    Node prev ; 
    Node next ; 
    Node(int data ){
        this.data = data ;
        this.prev = null ;
        this.next = null ;
    }
}
public class DoublyLinkedList{
    static void forwardTraversal(Node head) {
        Node curr = head;
        if (head != null) {
            do {
                System.out.print(curr.data + " ");
                curr = curr.next;
            } while (curr != head);
        }
        System.out.println();
    }
    static void backwardTraversal(Node head) {
        Node curr = head.prev;
        if (head != null) {
            do {
                System.out.print(curr.data + " ");
                curr = curr.prev;
            } while (curr.next != head);
        }
        System.out.println();
    }
    public static void main(String[] args){
        Node head = new Node (1) ; 
        Node head1 = new Node (10) ; 
        Node head2 = new Node (20) ; 
        Node head3 = new Node (30) ; 
        Node head4 = new Node (40) ;
        head.next = head1 ; 
        head1.next = head2 ; 
        head2.next = head3 ; 
        head3.next = head4 ; 
        head4.next = null ;
        System.out.println("Forward Traversal:");
        forwardTraversal(head);
        System.out.println("Backward Traversal:");
        backwardTraversal(head);
    }
}
