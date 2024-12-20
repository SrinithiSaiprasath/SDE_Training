package Date_9_1_2024;

public class Max_Prod_subarray_152 {
    public static void main(String[] args){
        int[] arr = {2,3,-2,4};
        System.out.println(maxSubArray(arr));
    }
    public static int maxSubArray(int[] nums) {
        int n =   nums.length ;
        if(n == 1) return nums[0];
        int max_val = Integer.MIN_VALUE ;
        int prod = nums[0] ;
        for(int i = 1 ; i < n ;i++){
            prod *= nums[i] ;
            max_val = Math.max(max_val, prod);
            if(prod < 0) prod = 1 ;
        }
        return max_val ;
    }
}
