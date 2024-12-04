import java.util.*;

public class KLargestNumbers {
    public static List<Integer> findKLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest element
            }
        }

        List<Integer> result = new ArrayList<>(minHeap);
        Collections.sort(result, Collections.reverseOrder()); // Optional
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 3;
        System.out.println("K-Largest Numbers: " + findKLargest(nums, k));
    }
}
