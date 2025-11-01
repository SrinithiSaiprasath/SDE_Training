//Prime numbers in a given range 


public class q1{
    public static void main(String[] args){
        int num = 20 ; 
        int[] res = new int[num] ; 
        return_prime(num, res);
    }

    static void  return_prime(int num , int[] res){
        int j = 0 ;
        int it;
        for(it=2; it <= num ; it++){
            if(check_prime(it)) {
                res[j] = it;
                j++ ; 
            }
        }
        for(int i : res){
            if(i != 0 )System.out.println(i);
        } 
    }

    public static boolean check_prime(int num){
        boolean flag = true ; 
        for(int i  = 2 ; i <= num/2 ; i ++){
            if(num%i == 0){
                flag = false ; 
                return flag ; 
            }
        }
        return flag ; 
    }
}