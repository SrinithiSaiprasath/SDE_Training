// package Binary Tree Problems;

import java.util.ArrayList;
import java.util.List;

public class DepthBT {
    static int l = 0;

    public static List<Integer> Depth(Node root, List<Integer> res) {
        if (root == null)
            return res;
        Depth(root.left, res);
        Depth(root.right, res);
        res.add(root.data);

        return res;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        List<Integer> res = new ArrayList<>();
        List<Integer> a = Depth(root, res);
        System.out.println("The Depth of the BT is " + a.size());
    }
}

// The Depth of the BT is 5
// O(n)