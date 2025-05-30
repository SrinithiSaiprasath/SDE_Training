/**1. Given a queue of integers, rearrange	the elements by	interleaving the
 *  first half of the list	with the second half of	the list. For example,
 *  suppose a queue stores the following sequence of values:[11,12,13,14,15,16,17,18,19,20].
 * Consider the two halves of this list: 
 * first half:[11,12,13,14,15]second half: [16,17,18,19,20]. 
 * These are combined in an alternating fashion to form a sequence	of 
 * interleave pairs: the first values from each half	(11 and	16), 
 * then the second values from each half (12	and 17), then the  third values 
 * from each half (13	and 18), and so	on. In each pair, the value from the first half	
 * appears	before the value from the second half.	
 * Thus, after the	call, the queue	stores	the following 
 * values: [11,16,12,17,13,18,14,19,15,20]

Time Complexity: O(n). Space Complexity: O(n). */
public class Q1 {
    public static void interleaveQueue(java.util.Queue<Integer> queue) {
        int size = queue.size();
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        
        // Step 1: Push the first half of the queue into the stack
        for (int i = 0; i < size / 2; i++) {
            stack.push(queue.poll());
        }
        
        // Step 2: Interleave the elements
        while (!stack.isEmpty()) {
            queue.offer(stack.pop()); 
            queue.offer(queue.poll()); 
        }
    }

    public static void main(String[] args) {
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        for (int i = 11; i <= 20; i++) {
            queue.offer(i);
        }
        
        System.out.println("Original Queue: " + queue);
        interleaveQueue(queue);
        System.out.println("Interleaved Queue: " + queue);
    } 
}
