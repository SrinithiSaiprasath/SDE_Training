import java.util.*;


public class Bubble_Sort{
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1} ;
        System.out.println("The Array is : " + Arrays.toString(arr)); ;
        bubblesort(arr) ; 
        System.out.println("The Sorted Array is : " + Arrays.toString(arr));
    }
    public static void bubblesort(int[] arr){
        int temp ; 
        boolean swapped ; 
        for(int i = 0 ; i < arr.length-1 ; i++){
            swapped = false ; 
            for(int j = 0 ; j < arr.length-1 ; j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j] ; 
                    arr[j] = arr[j+1] ; 
                    arr[j+1] = temp ; 
                    swapped = true ; 
                }
            }
            if(swapped == false){
                break ; 
            }
        }
    }
}