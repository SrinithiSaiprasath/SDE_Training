import java.util.*;

public class MySol{
    static class Node {
        String str1 = "";
        boolean isLocked;
        int id;
        Node parent; 
        int des_locked; 
        ArrayList<Node> child = new ArrayList<>();
        ArrayList<Node> dsc_lockList = new ArrayList<>();

        Node(String s) {
            this.str1 = s;
        }
    }

    static boolean lock(Node node , int id){
        
        if(node.isLocked){
            return false;
        }
        
        if(node.des_locked > 0){
            return false;
        }
        
        Node parent = node.parent;
        while (parent != null) {
            if (parent.isLocked) {
                return false;
            }
            parent = parent.parent;
        }
        
        parent = node.parent;
        while(parent != null){
            parent.des_locked += 1;
            parent.dsc_lockList.add(node);
            parent = parent.parent;
        }
        
        node.isLocked = true;
        node.id = id;
        
        return true;
    }

    static boolean unlock(Node node , int id){
        
        if(!node.isLocked || node.id != id){
            return false;
        }
        
        Node parent = node.parent;
        while(parent != null){
            parent.des_locked -= 1;
            parent.dsc_lockList.remove(node);
            parent = parent.parent;
        }
        node.isLocked = false;
        node.id = 0;

        return true;
        
    }

    static boolean getAllChilds(Node node , ArrayList<Node> a , int id){
        
        if(node == null){
            return true;
        }
        
        if(node.isLocked){
            if(id != node.id)
                return false;
            else
                a.add(node);
        }
        
        if(node.des_locked == 0){
            return true;
        }
        
        // a.add(node);
        for(Node i : node.child){
            boolean ans = getAllChilds(i , a , id);
            if(ans == false) return false;
        }
        
        return true;
        
    }

    static boolean upgrade(Node node , int id){
        
        if(node.isLocked || node.des_locked == 0){
            return false;
        }
        
        // ArrayList<Node> a = new ArrayList<>();
        // boolean can = getAllChilds(node , a , id);
        // if (!can)
        //     return false;
        ArrayList<Node> a = node.dsc_lockList;
        for (Node i : a) {
            // System.out.println(i.str1 + " " + i.isLocked);
            if (i.isLocked && i.id != id) {
                return false;
            }
        }

        int n = a.size() -1;
        Node parent = node.parent;
        //parents
        while (parent != null) {
            parent.des_locked -= n;
            parent.dsc_lockList.removeAll(a);
            parent.dsc_lockList.add(node);
            parent = parent.parent;
        }
        //nodes
        for (Node i : a) {
            i.isLocked = false;
            i.id = 0;
            parent = i.parent;
            while (parent != node) {
                parent.des_locked -= 1;
                parent.dsc_lockList.remove(i);
                parent = parent.parent;
            }
        }
        //node clearance
        node.des_locked = 0;
        node.dsc_lockList.clear();

        node.isLocked = true;
        node.id = id;
        return true;
    }

    public static void main(String[] agrs) {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int k = scn.nextInt();
        int q = scn.nextInt();

        HashMap<String, Node> hash = new HashMap<>();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.next();
        }

        Node root = new Node(arr[0]);
        hash.put(arr[0], root);

        Queue<Node> que = new LinkedList<>();
        que.add(root);

        int index = 1;
        while (que.size() > 0 && index < n) {

            int size = que.size();

            while (size-- > 0) {

                Node rem = que.remove();

                for (int i = 1; i <= k && index < n; i++) {

                    Node newNode = new Node(arr[index]);
                    newNode.parent = rem;
                    hash.put(arr[index], newNode);
                    rem.child.add(newNode);
                    que.add(newNode);
                    index += 1;
                }
            }
        }
        for (int i = 0; i < q; i++) {
            int val = scn.nextInt();
            String str = scn.next();
            int id = scn.nextInt();
            boolean ans = false;
            if (val == 1) {
                ans = lock(hash.get(str), id);
            } else if (val == 2) {
                ans = unlock(hash.get(str), id);
            } else if (val == 3) {
                ans = upgrade(hash.get(str), id);
            }
            System.out.println(ans);
            // displayStat(hash);
        }
        scn.close();
    }
    
    public static void displayStat(HashMap<String, Node> hash) {
        for (String key : hash.keySet()) {
            System.out.println(
                    key + " " + hash.get(key).isLocked + " " + hash.get(key).id + " " + hash.get(key).des_locked);
        }
    }
    
} 
//op


/**7
2
5
World
Asia
Africa
China
India
SouthAfrica
Egypt
1 China 7
true
1 India 7
true
3 Asia 7
true
2 India 7
false
2 Asia 7
true
 */