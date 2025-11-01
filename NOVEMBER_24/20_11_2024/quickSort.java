class quickSort{
    public static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int pivot_value = partition(arr, low, high);
            quickSort(arr, low, pivot_value-1);
            quickSort(arr, pivot_value+1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7, 3};
        quickSort(arr, 0, arr.length-1);
        for(int i = 0 ; i < arr.length ; i++){
            System.out.println(arr[i]);
        }
    }
}