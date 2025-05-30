import java.util.*;
import java.io.*;

class Node {
    String name;
    int lockedBy = -1;
    boolean isLocked = false;
    Set<Node> descLocks = new HashSet<>();
    List<Node> children = new ArrayList<>();
    Node parent;

    Node(String name) {
        this.name = name;
    }
}

public class NewTree {
    HashMap<String, Node> map = new HashMap<>();

    NewTree(String[] names, int m) {
        int n = names.length;
        for (String node : names) {
            map.put(node, new Node(node));
        }
        int ind = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(map.get(names[ind++]));

        while (!q.isEmpty() && ind < n) {
            Node curr = q.poll();
            for (int i = 0; i < m && ind < n; i++) {
                Node child = map.get(names[ind++]);
                child.parent = curr;
                curr.children.add(child);
                q.add(child);
            }
        }
    }

    public boolean lock(String name, int uid) {
        Node node = map.get(name);
        if (node.isLocked || !node.descLocks.isEmpty()) return false;

        Node curr = node.parent;
        while (curr != null) {
            if (curr.isLocked) return false;
            curr = curr.parent;
        }

        node.lockedBy = uid;
        node.isLocked = true;

        curr = node.parent;
        while (curr != null) {
            curr.descLocks.add(node);
            curr = curr.parent;
        }

        return true;
    }

    public boolean unlock(String name, int uid) {
        Node node = map.get(name);
        if (!node.isLocked || node.lockedBy != uid) return false;

        node.isLocked = false;
        node.lockedBy = -1;

        Node curr = node.parent;
        while (curr != null) {
            curr.descLocks.remove(node);
            curr = curr.parent;
        }

        return true;
    }


    public boolean upgrade(String name, int uid) {
        Node node = map.get(name);

        if (node.isLocked || node.descLocks.isEmpty()) return false;

        Node curr = node.parent;
        while (curr != null) {
            if (curr.isLocked) return false;
            curr = curr.parent;
        }

        for (Node lockedNode : node.descLocks) {
            if (lockedNode.isLocked && lockedNode.lockedBy != uid){//Changes
                return false;
            }
        }

        //Changes done
        for (Node lockedNode : node.descLocks) {
            if (lockedNode.isLocked){
                lockedNode.isLocked = false;
                lockedNode.lockedBy = -1;
            }
        }

        node.isLocked = true;
        node.lockedBy = uid;

        curr = node;
        curr.descLocks = new HashSet<>();
        curr = curr.parent;

        while(curr != null) {
            curr.descLocks = new HashSet<>();
            curr.descLocks.add(node);
            curr = curr.parent;
        }

        return true;
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        int q = sc.nextInt();
//        sc.nextLine();
//
//
//        String[] names = new String[n];
//        for (int i = 0; i < n; i++) {
//            names[i] = sc.next();
//        }
//
//        NewTree tree = new NewTree(names, m);
//
//
//        for (int i = 0; i < q; i++) {
//            int type = sc.nextInt();
//            String name = sc.next();
//            int uid = sc.nextInt();
//
//            boolean result = false;
//            if (type == 1) {
//                result = tree.lock(name, uid);
//            } else if (type == 2) {
//                result = tree.unlock(name, uid);
//            } else if (type == 3) {
//                result = tree.upgrade(name, uid);
//            }
//
//            System.out.println(result);
//        }
//
//        sc.close();
//    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\DELL\\eclipse-workspace\\TC\\new_large_testcase.txt"));

            int n = sc.nextInt();
            int m = sc.nextInt();
            int q = sc.nextInt();
            sc.nextLine();

            String[] nodeNames = new String[n];
            for (int i = 0; i < n; i++) {
                nodeNames[i] = sc.nextLine();
            }

            NewTree tree = new NewTree(nodeNames, m);

            List<String> queries = new ArrayList<>();
            for (int i = 0; i < q; i++) {
                queries.add(sc.nextLine());
            }

            sc.close(); // Close the file after reading

            long startTime = System.nanoTime();

            for (String query : queries) {
                String[] parts = query.split(" ");
                int choice = Integer.parseInt(parts[0]);
                String name = parts[1];
                int uid = Integer.parseInt(parts[2]);
                boolean result = false;

                if (choice == 1) {
                    result = tree.lock(name, uid);
                } else if (choice == 2) {
                    result = tree.unlock(name, uid);
                } else if (choice == 3) {
                    result = tree.upgrade(name, uid);
                }

                // Store results but don't print inside the timed block
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // Convert nanoseconds to milliseconds

            System.out.println("Execution Time: " + duration + " ms");

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }
    }
}