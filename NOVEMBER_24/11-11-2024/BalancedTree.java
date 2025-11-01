class TreeNode {
    int val ;
    TreeNode left ; 
    TreeNode right ;
    TreeNode (int val){
        this.val = val ;
        left = null ;
        right = null ;
    }
}
public class BalancedTree{
    public static int checkHeight(TreeNode root){
            if(root == null) return 0 ;
            int leftheight = checkHeight(root.left);
            int rightheight = checkHeight(root.right);
            if(leftheight == -1 || rightheight == -1) return -1 ;
            if(Math.abs(leftheight - rightheight) > 1) return -1 ;
            return 1 + Math.max(leftheight, rightheight) ;
        }
    public static void main(String[] args){
        TreeNode root = new TreeNode(1) ;
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);
        System.out.println(checkHeight(root) != -1) ;
    }
}