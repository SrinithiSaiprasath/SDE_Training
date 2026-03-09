public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {20, 9 , 15, 8 ,6,3};
        int[] sortedArray = insertionSort(arr) ; 

        System.out.println("Sorted Array: ");
        for (int num : sortedArray) {   
            System.out.print(num + " ");
        }
    }


    public static int[] insertionSort(int[] nums){
        int n =  nums.length ; 
        
        for(int i = 1; i < n ; i++){
            int j = i- 1 ; 
            int check = nums[i]; 
            System.out.println("check value "+ check ) ; 
            while(j>= 0 && check <nums[j]){
                System.out.println(nums[j] + " swapped "+ nums[j+1]) ; 
                nums[j+1] = nums[j] ; 
                j-- ; 
            }
            nums[j+1] = check ; 
        }
        return nums ;
    }
}
