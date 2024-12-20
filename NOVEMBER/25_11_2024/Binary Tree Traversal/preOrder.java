// preorder is : root , left ,right
import java.util.*;

public class preOrder {
    public static void preOrder1(Node root , List<Integer> list){
        if(root == null){
            return ; 
        }
        list.add(root.data) ; 
        preOrder1(root.left, list);
        preOrder1(root.right ,list);

        System.out.println(root.data);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>() ;
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        preOrder1(root, list);
        System.out.println(list);
    }
}
//Output
// 4
// 5
// 2
// 6
// 7
// 3
// 1