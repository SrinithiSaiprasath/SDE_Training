//Armstrong number between a range 
import java.util.*;
public class q2 {
    public static void  main(String[] args){
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1 ); 
        res.add(5) ; 
        res.add(10) ; 
        res.add(153) ; 

        ArrayList<Integer> r = new ArrayList<>() ; 
        r = return_armstrong(150 , 153 ) ; 
        for(Integer i : r ){
            System.out.println(i);
        }
    }

    public static boolean check_armstrong(int num){
        int power = Integer.toString(num).length() ;
        int temp = num ; 
        int sum = 0 ;
        while(temp > 0){
            int rem = temp%10 ; 
            sum += Math.pow(rem ,power);
            temp = temp/10 ; 
        }
        if(sum == num){
            return true ; 
        }
        return false;
    }

    public static ArrayList<Integer> return_armstrong(int start , int stop){
        ArrayList<Integer> res = new ArrayList<>() ; 
        for(int i = start ;i <  stop + 1 ; i++){
            if(check_armstrong(i)){
                res.add(i) ; 
            }
        }
        return res ; 
        
    }
}
