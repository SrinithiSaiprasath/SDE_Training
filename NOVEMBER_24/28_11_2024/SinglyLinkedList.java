public class SinglyLinkedList{

    public static void main(String[] args) {
        Node head = new Node(10) ;
        Node second = new Node(20) ;
        Node third = new Node(30) ;
        head.next = second ;
        second.next = third ;
        printlist(head) ;
    }

    public static void printlist(Node root){
        while(root != null){
            System.out.println(root.data);
            root = root.next ;
        } 
    }
}