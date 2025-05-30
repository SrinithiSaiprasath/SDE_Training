/**2. Given an integer k and a queue of integers,
 *  how do you reverse the order of the first k elements of the queue, 
 * leaving the other elements in the same relative order? 
 * For  example, if	k=4 and	queue has the elements [10,	20,	30,	40,	50,	60,	70,	80,	90];
 * 	the	output should	be	[40,	30,	20,	10,	50,	60,	70,	80,	90]

Time Complexity: O(n). Space Complexity: O(n). */
import java.util.* ; 
public class Q2 {
    public static void reverseFirstKElements(Queue<Integer> queue, int k) {
        if (k <= 0 || k > queue.size()) {
            return; // Invalid k
        }
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < k; i++) {
            stack.push(queue.poll());
        }
        
        while (!stack.isEmpty()) {
            queue.offer(stack.pop());
        }
        
        for (int i = 0; i < queue.size() - k; i++) {
            queue.offer(queue.poll());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 10; i <= 90; i += 10) {
            queue.offer(i);
        }
        
        System.out.println("Original Queue: " + queue);
        reverseFirstKElements(queue, 4);
        System.out.println("Queue after reversing first 4 elements: " + queue);
    }
}
