public class BubbleSort{
    public static void main(String[] args) {
        int[] arr = {20, 9 , 15, 8 ,6,3};
        int[] sortedArray = bubble_sort(arr) ;

        System.out.println("Sorted Array: ");
        for (int num : sortedArray) {   
            System.out.print(num + " ");
        }
    }

    static public int[] bubble_sort(int[] nums){
        int n = nums.length ; 
        for(int i = n- 1 ; i >= 0 ; i--){
            boolean swapped = false ; 
            System.out.println("Pass " + (n - i) + ": ");
            for(int  j = 0 ; j < i ; j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j] ; 
                    nums[j] = nums[j+1] ; 
                    nums[j+1] = temp ; 
                    swapped = true ; 
                    System.out.println("Swapped " + nums[j] + " and " + nums[j+1]);
                }
            }
            if(!swapped){
                break ; 
            }
        }
        return nums ;
    }
}

//TC : O(N**2)
//SC : O(1)