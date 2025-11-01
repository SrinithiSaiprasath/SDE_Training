package Date_9_1_2024;
import java.util.*;
public class Merge_Overlapping_Interval {
    public static List<Integer> merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        List<List<Integer>> list = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                if (intervals[i][1] >= end) {
                    end = intervals[i][1];
                }
                if (intervals[i][0] <= start) {
                    start = intervals[i][0];
                }

            }

            else {
                List<Integer> ans = new ArrayList<>();

                ans.add(start);
                ans.add(end);
                list.add(ans);
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(start);
        ans.add(end);
        list.add(ans);
        
        return ans;
    }
    public static void main(String[] args) {
        int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        System.out.println(merge(intervals));
    }

}
