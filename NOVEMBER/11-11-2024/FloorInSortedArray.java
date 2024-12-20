public class FloorInSortedArray {
    public static int findFloor(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                result = arr[mid];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 8, 10, 12, 14, 19};
        int target = 5;
        System.out.println("Floor of " + target + ": " + findFloor(arr, target));
    }
}
