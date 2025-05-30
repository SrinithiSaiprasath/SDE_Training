import java.util.*;

public class q3 {

    public static void findDuplicates(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        boolean hasDuplicates = false;

        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey());
                hasDuplicates = true;
            }
        }

        if (!hasDuplicates) {
            System.out.println("No duplicates found");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        findDuplicates(arr);
        scanner.close();
    }
}