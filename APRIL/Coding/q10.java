import java.util.*;

public class q10 {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static TreeNode insertLevelOrder(int[] arr, int i, int n) {
        TreeNode root = null;
        if (i < n) {
            root = new TreeNode(arr[i]);
            root.left = insertLevelOrder(arr, 2 * i + 1, n);
            root.right = insertLevelOrder(arr, 2 * i + 2, n);
        }
        return root;
    }

    public static boolean searchBST(TreeNode root, int key) {
        if (root == null) {
            return false;
        }
        if (root.val == key) {
            return true;
        }
        return key < root.val ? searchBST(root.left, key) : searchBST(root.right,
         key);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int searchKey = sc.nextInt();
        TreeNode root = insertLevelOrder(arr, 0, n);
        if (searchBST(root, searchKey)) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }
        sc.close();
    }
}
