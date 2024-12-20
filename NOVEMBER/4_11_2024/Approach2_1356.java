import java.util.*;
public class Approach2_1356 {
    public static void main(String[] args){
        int[] arr = {1,2,5,4,741,7,8,9,6,3,1,85,};
        sort_bits(arr);
    }
    public static void sort_bits(int[] arr){
        Map<Integer , List<Integer>> map =  new TreeMap<>();
        for(int n : arr){
            int bit_val = Integer.bitCount(n);
            if(map.containsKey(bit_val)){
                map.get(bit_val).add(n);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(n);
                map.put(bit_val, list);
            }
        }
        int i = 0 ;
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            List<Integer> list = entry.getValue();
            for(int n : list){
                arr[i] = n;
                i++ ;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
