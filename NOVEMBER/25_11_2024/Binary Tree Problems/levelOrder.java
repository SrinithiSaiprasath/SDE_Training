import java.util.*;

public class levelOrder {
    public static List<List<Integer>> levelOrder1(Node root) {
        
        List<List<Integer>> res = new ArrayList<>() ; 
        if(root == null) return res ;

        Queue<Node> q = new LinkedList<>() ;
        q.add(root) ;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> list = new ArrayList<>() ;
            for(int i = 0 ; i < size ; i++){
                Node curr = q.remove() ;
                list.add(curr.data) ;
                if(curr.left != null) q.add(curr.left) ;
                if(curr.right != null) q.add(curr.right) ;
            }
            res.add(list) ;
        }
        return res ;
    }

    public static int getLevel(Node root){
        List<List<Integer>> res = levelOrder1(root);
        return res.size() - 1;
    } 
    public static void main(String[] args){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println(levelOrder1(root));
        System.out.println("The number of Levels are " + getLevel(root));
    
    }
}


//     [[1], 
//    [2, 3], 
// [4, 5, 6, 7]]
// The number of Levels are 2