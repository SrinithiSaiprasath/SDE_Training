package Date_9_1_2024;

public class longest_Palindrome {
    static boolean check_palindorme(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }

    static String longest_palindorme(String s) {
        int n = s.length();
        int maxLen = 1, start = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (check_palindorme(s, i, j) && (j - i + 1) > maxLen) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String s = "malayalamaaaaaa";
        System.out.println(longest_palindorme(s));
    }
}