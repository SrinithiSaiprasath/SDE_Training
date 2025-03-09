import java.util.*;

public class TreeOfSpace{
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

	public static boolean upgrade(Node node, int id){
		if(node.isLocked || node.descendantsLocked == 0 || node.ancestorsLocked > 0){
			return false;
		}

		ArrayList<Node> list = new ArrayList<>();

		boolean allChilds = getAllChilds(list,id,node);
		if(!allChilds){
			return false;
		}

		ancestorCount(node, 1);
		for(Node i : list){
			unlock(i,id);
		}

		node.isLocked = true;
		node.id = id;
		return true;
	}

	public static boolean getAllChilds(ArrayList<Node> list, int id, Node node){
		if(node == null){
			return true;
		}

		if(node.isLocked){
			if(node.id!=id){
				return false;
			}

			list.add(node);
			return true;
		}

		if(node.descendantsLocked == 0){
			return true;
		}

		for(Node child : node.children){
			if(!getAllChilds(list, id, child)){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		Scanner sc=  new Scanner(System.in);
		System.out.println("Enter n");

		// int n = sc.nextInt();
		int n = 7 ;

		System.out.println("Enter m");
		// int k = sc.nextInt();
		int k = 2 ;

		System.out.println("Enter array elements");
		String[] strArray = {"World" , "Asia" ,"Africa", "China", "India" , "SouthAfrica" ,"Egypt"};
        
		// for (int i = 0; i < n; i++) {
		// }

		Node root = new Node();
		HashMap<String, Node> map = new HashMap<>();
		map.put(strArray[0],root);
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);

		int index = 1;
		while(!queue.isEmpty() && index < n) {
			int size = queue.size();
			while(size-- > 0){
				Node removedNode = queue.poll();
				for(int i = 1; i<=k && index < n;i++){
					Node newNode = new Node();
					newNode.parent = removedNode;
					map.put(strArray[index], newNode);
					removedNode.children.add(newNode);
					queue.add(newNode);
					index+=1;
				}
			}
		}

		// queries =  [‘1 China 9’, ‘1 India 9’, ‘3 Asia 9’, ‘2 India 9’, ‘2 Asia 9’]

		System.out.println("Enter no.of queries");

		// int q = sc.nextInt();
        int q = 5;
		for(int i = 0; i < q; i++){
			int val = sc.nextInt();
			String s = sc.next();
			int id = sc.nextInt();
			boolean result = false;

			if(val == 1){            
				result = lock(map.get(s), id);
			}
			else if(val == 2){
				result = unlock(map.get(s), id);
			}
			else if(val == 3){
				result = upgrade(map.get(s), id);
			}
		
			System.out.print(result);
		}
	}

	
}