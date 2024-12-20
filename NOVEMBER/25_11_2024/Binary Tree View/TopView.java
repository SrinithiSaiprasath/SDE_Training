import java.util.*;
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

public class TopView {
    public static List<Integer> topView(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<Pair<Node, Integer>> q = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        q.add(new Pair<>(root, 0)); 

        while (!q.isEmpty()) {
            Pair<Node, Integer> p = q.poll();
            Node curr = p.getKey();
            int line = p.getValue();

            map.putIfAbsent(line, curr.data);

            if (curr.left != null) {
                q.add(new Pair<>(curr.left, line - 1));
            }
            if (curr.right != null) {
                q.add(new Pair<>(curr.right, line + 1));
            }
        }

        ans.addAll(map.values());
        return ans;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<Integer> result = topView(root);
        System.out.println(result); 
    }
}


// [1, 2, 3, 4, 7]