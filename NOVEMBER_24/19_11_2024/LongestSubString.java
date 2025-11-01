public class LongestSubString {
    public static int lengthOfLongestSubstring(String s) {
        int[] index = new int[128];
        int maxLength = 0, start = 0;

        for (int end = 0; end < s.length(); end++) {
            start = Math.max(start, index[s.charAt(end)]);
            maxLength = Math.max(maxLength, end - start + 1);
            index[s.charAt(end)] = end + 1;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s)); // Output: 3
    }
    
    
}
