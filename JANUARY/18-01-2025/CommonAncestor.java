import java.util.* ; 

//You are given a forest with n nodes each node is given an integer value from 0 to n1. 
//You are also given noes x1 and x2 .You have to find the nearest common ancestor of x1 and x2. 
//If there are multiple common ancestors then return the one with the smallest value. 
//If there is no common ancestor then return -1.

class Node{
    int val ; 
    Node parent ; 
    public Node(int val){
        this.val = val ; 
        this.parent = null ; 
    }
}
public class CommonAncestor {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in) ;
        // System.err.println("Enter the number of test cases") ;
        int tc = 1;
        while(tc -->0){

            // System.err.println("Enter the number of nodes") ;
            int n = 23 ;
            // int[] arr = new int[n] ; 
            int[] arr = {4, 4, 1, 4, 13 ,8, 8, 8 ,0, 8, 14, 9, 15, 11, -1, 10, 15, 22, 22, 22 ,22, 22, 21};
            // System.err.println("Enter the values of nodes") ;
            // for(int i = 0 ; i < n ; i++){
            // arr[i] = sc.nextInt() ; 
            // }

            //Form Tree
            Node[] nodes = new Node[n] ; 
            for(int i = 0 ; i < n ; i++){
            nodes[i] = new Node(i) ;
            }
            for(int i = 0 ; i < n ; i++){
            if(arr[i] != -1){
                nodes[i].parent = nodes[arr[i]] ; 
            }
            }
            // System.err.println("Enter the values of x1") ;
            int x1 = 9 ;
            // System.err.println("Enter the values of x2") ;
            int x2 = 2;
            Node ancestor = nearestCommonAncestor(nodes[x1] , nodes[x2]) ; 
            if(ancestor == null){
            System.out.println("-1") ;
            }else{
            System.out.println("The nearest common ancestor is " + ancestor.val) ; 
            }
        }
        sc.close();
    }

    static Node nearestCommonAncestor(Node x1 ,Node x2){
        Set<Node> ans = new HashSet<>(); 
        while(x1 != null){
            ans.add(x1) ; 
            x1 = x1.parent ; 
        }
        while(x2 != null){
            if(ans.contains(x2)){
                return x2 ; 
            }
            x2 = x2.parent ; 
        }
        return null ;
    }
    
}
