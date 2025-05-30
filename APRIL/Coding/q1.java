import java.util.*;

public class q1 {
    public static void mian(String args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int res = getFirstRepeatingInteger(arr);
        System.out.println(res);
        sc.close();
    }

    public static int getFirstRepeatingInteger(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                return arr[i];
            } else {
                set.add(arr[i]);
            }
        }
        return -1; // Return -1 if no repeating integer is found
    }
}
