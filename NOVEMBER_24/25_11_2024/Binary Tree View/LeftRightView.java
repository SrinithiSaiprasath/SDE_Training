// package Tree View;
import java.util.* ; 
public class LeftRightView{
    public static void LeftRec(Node root , int level , List<Integer> list){
        if(root == null){
            return ;
        }
        if(level == list.size()){
            list.add(root.data);
        }
        System.out.println(level ); 

        LeftRec(root.left , level+1 , list);
        LeftRec(root.right , level+1 , list);
    }

    public static void RightRec(Node root , int level , List<Integer> list){
        if(root == null){
            return ;
        }
        if(level == list.size()){
            list.add(root.data);
        }
        System.out.println(level);
        RightRec(root.right , level+1 , list);
        RightRec(root.left , level+1 , list);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();

        LeftRec(root , 0 , list);
        RightRec(root , 0 , list1);
        System.out.println("Left View "  + list);
        System.out.println("Right View " + list1);
    }
}

// Left View [1, 2, 4]
// Right View [1, 3, 7]