package Date_9_1_2024;

public class Max_Subarray_53 {
    public static void main(String[] args){
        int[] nums = {-2,1,-3,4,-1,2,1,-100} ;
        System.out.println(maxSubArray(nums));
    }
    public static int maxSubArray(int[] nums) {
        int n =   nums.length ;
        int max_val = Integer.MIN_VALUE ;
        int sum = 0 ;

        for(int i = 0 ; i < n ;i++){
            sum += nums[i] ;
            max_val = Math.max(max_val, sum);
            if(sum < 0) sum = 0 ;
        }
        return max_val ;
    }
}