public class q3 {
    public static void main(String[] args){
        int[] r  = new int[500];
        int[] res = return_prime(28, r) ; 
        int sum  =30 ; 
        for(int i = 0 ; i < res.length; i++ ){
            int j = 1 ; 
            if(res[i] + res[j] == sum){
                System.out.println(res[i]  + "+" + res[j] + "=" + sum );
                break ; 
            }
            else {
                j++ ; 
            }
        }
        }
    

    static int[] return_prime(int num , int[] res){
        int j = 0 ;
        int it;
        for(it=2; it <= num ; it++){
            if(check_prime(it)) {
                res[j] = it;
                j++ ; 
            }
        }
        return res ; 
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
