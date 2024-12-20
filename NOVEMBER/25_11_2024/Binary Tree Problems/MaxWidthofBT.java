// package Binary Tree Problems;
import java.util.*;
public class MaxWidthofBT {
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
    public static int widthofBT(Node root) {
        if(root == null) return 0;
        int maxWidth = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            maxWidth = Math.max(maxWidth , size);
            for(int i = 0 ; i < size ; i++){
                Node curr = q.remove();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }
        return maxWidth ;
    }
    
    public static int getWidthwithLevel(Node root){
        int res = getLevel(root);
        return (int) Math.pow(2,res) ; 
    }
        
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println("Width of BT is " + widthofBT(root));
        System.out.println("Width of BT is " + getWidthwithLevel(root));
    }
}
// Width of BT is 4
// Width of BT is 4