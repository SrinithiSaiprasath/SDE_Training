package OCTOBER_25;

class Node{
    int value ; 
    int priority ;
    Node next ; 
    Node(int value , int priority){
        this.value = value ; 
        this.priority = priority ; 
        this.next = null ;
    }

    static int peek(Node head){return head.value ; }
    static Node pop(Node head){head = head.next ; return head ;}

    static Node push(Node head ,int value , int priority){
        Node start =  head ; 
        Node newNode = new Node(value , priority) ; 

        if(newNode.priority < start.priority) {
            newNode.next = head ;
            head = newNode ;
        }
        else{
            while(start.next != null && start.next.priority <= newNode.priority){
                start = start.next ; 
            }
            start.next = newNode ; 
            newNode.next = null ; 
        } 
        return head ; 
    }


    static Node push(Node head ,Node val){
        Node start =  head ; 
        Node newNode = val ; 

        if(newNode.priority < start.priority) {
            newNode.next = head ;
            head = newNode ;
        }
        else{
            while(start.next != null && start.next.priority <= newNode.priority){
                start = start.next ; 
            }
            start.next = newNode ; 
            newNode.next = null ; 
        } 
        return head ; 
    }

}


public class PriorityQueueImpl {
    public static void main(String args[]) { 
        Node n = null ; 
        Node n1 =  new Node(4,1) ; 
        Node n2 = new Node(5,2) ;
        Node n3 = new Node(6,3) ; 
        Node n4 = new Node(23,6) ; 
        Node n5 = new Node(9,5) ; 

        n = Node.push(n1 , n2) ;
        n = Node.push(n1 , n3 ) ; 
        n = Node.push(n1 , n5) ; 
        n = Node.push(n4 , n2) ;

        while(n==null){
            System.out.println(Node.peek(n)) ;
            n = Node.pop(n) ;  
        }
    } 
}
