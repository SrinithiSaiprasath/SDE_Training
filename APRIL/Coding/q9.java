import java.util.*;

public class q9 {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static TreeNode constructTree(int[] nodes) {
        if (nodes.length == 0) return null;

        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < nodes.length) {
            TreeNode current = queue.poll();

            if (i < nodes.length) {
                current.left = new TreeNode(nodes[i++]);
                queue.add(current.left);
            }
            if (i < nodes.length) {
                current.right = new TreeNode(nodes[i++]);
                queue.add(current.right);
            }
        }
        return root;
    }

    public static int findHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(findHeight(root.left), findHeight(root.right));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = sc.nextInt();
        }
        TreeNode root = constructTree(nodes);
        System.out.println(findHeight(root));
        sc.close();
    }
}
