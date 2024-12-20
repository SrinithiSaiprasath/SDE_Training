package Date_9_1_2024;

import java.util.Arrays;

public class Longest_Common_Prefix {
    public static String Longestcp(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "-1";
        }
        Arrays.sort(arr);
        String first = arr[0];
        String last = arr[arr.length - 1];
        int prefixlen = 0;
        while (prefixlen < first.length() && prefixlen < last.length()
                && first.charAt(prefixlen) == last.charAt(prefixlen)) {
            prefixlen++;
        }
        String cp = first.substring(0, prefixlen);
        return cp.isEmpty() ? "-1" : cp;
    }

    public static void main(String[] args) {
        String[] arr1 = { "a", "aab", "azz" };
        System.out.println(Longestcp(arr1));
    }
}
