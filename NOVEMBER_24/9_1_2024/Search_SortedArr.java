package Date_9_1_2024;
public class Search_SortedArr {
    public static int search(int[] arr, int key) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == key) {
                return m;
            }

            if (arr[l] <= arr[m]) {
                if (key >= arr[l] && key < arr[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                if (key > arr[m] && key <= arr[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Search_SortedArr solution = new Search_SortedArr();
        int[] arr1 = { 4, 5, 6, 7, 0, 1, 2 };
        int find_el = 12;
        System.out.println("Index of key1: " +search(arr1, find_el));
    }
}