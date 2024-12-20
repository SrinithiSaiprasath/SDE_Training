package Date_9_1_2024;

public class Check_Anagram {
    public static boolean CheckAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] charCount = new int[26];
        for (char c : s1.toCharArray()) {
            charCount[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            charCount[c - 'a']--;
        }
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "king";
        String s2 = "burgerking";
        System.out.println(CheckAnagram(s1,s2));
    }
}
