package Date_9_1_2024;

public class Trapping_RainWater {
    static int Trap_Rain_Water(int[] arr) {
        int n = arr.length;
        int[] start = new int[n];
        int[] right = new int[n];
        int res = 0;
        start[0] = arr[0];
        for (int i = 1; i < n; i++)
            start[i] = Math.max(start[i - 1], arr[i]);
        right[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--)
            right[i] = Math.max(right[i + 1], arr[i]);
        for (int i = 1; i < n - 1; i++) {
            int minOf2 = Math.min(start[i - 1], right[i + 1]);
            if (minOf2 > arr[i]) {
                res += minOf2 - arr[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 1,0,0,1,0,1,1,4,2 };
        System.out.println(Trap_Rain_Water(arr));
    }
}
