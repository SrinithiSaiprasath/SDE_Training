
public class Sample3 {
    public static void notmain(int N , int k , int[] money) {
        int maxfollowers = 0  ; 
        for(int val : money){
            if(val == k){
                maxfollowers++ ; 
            }
        } 
        System.out.println(maxfollowers) ;
    }    
}

