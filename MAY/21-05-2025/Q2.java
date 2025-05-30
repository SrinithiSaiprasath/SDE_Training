/** 2. Given two integers n	and k, return all possible combinations	of k numbers out
 *   of 1...n. For example, if n = 4 and k = 2,	a solution is:
 *  [[2,4],[3,4],[2,3],[1,2],[1,3],
[1,4],].*/

import java.util.* ;
public class Q2 {
    public static void combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        System.out.println(result);
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, int start, int n, int k) {
        if (tempList.size() == k) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i <= n; i++) {
            tempList.add(i);
            backtrack(result, tempList, i + 1, n, k);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        combine(4, 2); // Example usage
    }
}


/*3. Can we reverse the string without using any temporary variable? */
//using recursion we can reverse the string with no temp variable 
//the recursion stack acts as a temporary variable because of which the temp var is not needed 
