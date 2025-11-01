// package Binary Tree Problems;
import java.util.*;
public class RootToNode {
    public static boolean rootToNode(Node root , int target , List<Integer> res){
        if(root==null) return false ;
        else{
            res.add(root.data) ; 
        }
        if(root.data==target) return true;
        if(rootToNode(root.left, target,res) || rootToNode(root.right, target, res)){
            return true ; 
        }
        res.remove(res.size() - 1) ;
        return false;
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        List<Integer> res = new ArrayList<>();
        int target = 8; 
        rootToNode(root, target, res);
        System.out.println("Path to " + target + " is " + res);
    }
}

// Path to 8 is [1, 2, 4, 8]
//O(N)
