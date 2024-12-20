import java.util.*;

public class groupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "nat","bat"};
        List<List<String>> result = groupAnagrams(str);
        for (List<String> list : result) {
            System.out.println(list);
        }
    }
}
