package Date_9_1_2024;

import java.util.Arrays;

public class Chocolate_Distribution {
    public static int find_Chocolate_diff(int[] packets, int child) {
        int totp = packets.length;
        if (child == 0 || totp == 0 || child > totp) {
            return 0;
        }
        Arrays.sort(packets);
        int minp = Integer.MAX_VALUE;
        for (int i = 0; i <= totp - child; i++) {
            int currp = packets[i + child - 1] - packets[i];
            minp = Math.min(minp, currp);
        }
        return minp;
    }

    public static void main(String[] args) {
        int[] p = { 7, 3, 2, 4, 9, 12, 56 };
        int child = 3;
        System.out.println(find_Chocolate_diff(p, child));
    }
}
