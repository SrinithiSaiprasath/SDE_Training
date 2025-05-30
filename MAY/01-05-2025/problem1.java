import java.util.PriorityQueue;

public class problem1 {
    public static void main(String[] args) {
        // int t = 1 ; 
        int[] arr = {4,3,2,6} ;
        System.out.println(getSequenceSum(arr));

    }
    public static int getSequenceSum(int[] n){
        int res =  0 ;
        PriorityQueue<Integer> prq = new PriorityQueue<>();
        for (int i = 0; i < n.length; i++) {
            prq.add(n[i]);
        }
        while (prq.size() > 1) {
            int a = prq.poll();
            int b = prq.poll();
            res += a + b;
            prq.add(a + b);
        }
        return res ;
    }
}
// Time complexity: O(nlogn) where n is the length of the array
// Space complexity: O(n) for the priority queue    