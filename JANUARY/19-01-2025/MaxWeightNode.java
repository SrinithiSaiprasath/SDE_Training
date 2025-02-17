//Maximum weight of all nodes 
import java.util.* ; 
public class MaxWeightNode {
    public static void main(String[] args){
        int[] arr = {1, 2, -1, 1, 2};
        int result = solution1(arr) ; 
        int result2 = solution2(arr) ;
        System.out.println(result ) ;
        System.out.println(result2) ;
     }
        
    public static int solution1(int[] arr){
        int ans=Integer.MIN_VALUE;
        int result=-1;
        int[] weight = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            int source=i;
            int dest=arr[i];
            if(dest!=-1){
                weight[dest]+=source;
                if(ans<=weight[dest]){
                    ans=Math.max(ans,weight[dest]);
                    result=dest;
                }
            }
            }
        
        if(ans!=Integer.MIN_VALUE)
            return result;
        return -1;
    }

    public static int solution2(int[] arr){
        int[] temp = new int[arr.length] ;
        for(int i = 0 ; i < arr.length ;i++){
            if(arr[i] != -1){
                temp[arr[i]] += i ; 
            }
        }    
        int ans= 0 ;
        int max = Integer.MIN_VALUE ;
        for(int i = 0 ; i < temp.length ; i++){
            if(temp[i] > max){
                max = temp[i] ; 
                ans = i ; 
            }
        }
        if(max != Integer.MIN_VALUE){
           return(ans) ; 
        }else{
            return (-1) ; 
        }
    }
}
