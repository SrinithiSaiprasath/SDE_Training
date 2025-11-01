import java.util.* ; 
// Post Order : left,right,root

public class postOrder {
    public static void postOrder1(Node root ,List<Integer> list){
            if(root == null){
                return ;
            }
            postOrder1(root.left , list);
            postOrder1(root.right , list);
            list.add(root.data);
            
            System.out.println(list);
        }
    
        public static void maiin(String[] args){
            Node root = new Node(1);
            root.left = new Node(2);
            root.right = new Node(3);
            root.left.left = new Node(4);
            root.left.right = new Node(5);
            root.right.left = new Node(6);
            root.right.right = new Node(7);
            List<Integer> list = new ArrayList<>();
            postOrder1(root , list);
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