import java.util.*  ; 
//inorder: Left,Root,Right


public class inOrder {
    public static void inOrder1(Node root, List<Integer> list){
        if(root ==  null){
            return ;
        }
        
        inOrder1(root.left , list);
        list.add(root.data);
        inOrder1(root.right , list);

        System.out.println(list);
    }

    public static void main(String[] args) {
        Node root =  new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        List<Integer> list = new ArrayList<>();
        inOrder1(root , list);
    }
}

//Output
// [4]
// [4, 2, 5]
// [4, 2, 5]
// [4, 2, 5, 1, 6]
// [4, 2, 5, 1, 6, 3, 7]
// [4, 2, 5, 1, 6, 3, 7]
// [4, 2, 5, 1, 6, 3, 7]