public class InterpolationSearch {
    public static int interpolationSearch(int[] arr , int low , int high ,int key){
        if (low <= high) {
            int mid = low + ((high - low) / (arr[high] - arr[low]) * (key - arr[low]));
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                return interpolationSearch(arr, mid + 1, high, key);
            } else {
                return interpolationSearch(arr, low, mid - 1, key);
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
        int key = 18;
        int index = interpolationSearch(arr, 0, arr.length - 1, key);
        if (index != -1) {
            System.out.println("Element is present at index " + index);
        } else {
            System.out.println("Element is not present in array");
        }
    }
}
