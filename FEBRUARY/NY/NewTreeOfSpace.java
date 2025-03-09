import java.util.*;

public class NewTreeOfSpace{
	static class Node{
		boolean isLocked;
		Node parent;
		int id;
		int ancestorsLocked;
		int descendantsLocked;
		ArrayList<Node> children = new ArrayList<>();
	}

	public static boolean lock(Node node,int id){
		if(node.isLocked || node.ancestorsLocked > 0 || node.descendantsLocked > 0){
			return false;
		}

		Node current = node;
		while(current!=null){
			current.descendantsLocked += 1;
			current = current.parent;
		}

		ancestorCount(node,1);
		node.isLocked = true;
		node.id = id;
		return true;
	}

	public static void ancestorCount(Node node,int val){
		if(node == null){
			return;
		}

		node.ancestorsLocked += val;
		for(Node child : node.children){
			child.ancestorsLocked += val;
		}
	}

	public static boolean unlock(Node node,int id){
		if(!node.isLocked || node.id !=id){
			return false;
		}

		Node current = node;
		while(current!=null){
			current.descendantsLocked -= 1;
			current = current.parent;
		}

		ancestorCount(node,-1);
		node.isLocked = false;
		node.id = 0;
		return true;
	}


}