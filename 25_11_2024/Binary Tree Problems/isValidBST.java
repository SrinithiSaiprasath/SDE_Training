// package Binary Tree Problems;

public class isValidBST {
    public static boolean isBST(Node root) {
        
        if(root == null || root.left == null && root.right == null) return true ;
        if(root.left != null && root.left.data >= root.data) return false ;
        if(root.right != null && root.right.data <= root.data) return false ;
        
        return isBST(root.left) && isBST(root.right) ;
    }
    public static void main(String[] args){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        System.out.println("Statement : The given Binary Tree is a Binary Search Tree :" + isBST(root));
    }
}

//Statement : The given Binary Tree is a Binary Search Tree :false
//O(N) 
