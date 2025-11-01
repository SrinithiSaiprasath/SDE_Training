public class Approach2_509 {
    public static void main(String[] args){
        int fib_num = 13 ; 
        System.out.println(find_fib(fib_num));
    }
    public static int find_fib(int num){
        if(num == 0 || num == 1){
            return num;
        }
        int a = 0 ; 
        int b = 1 ; 
        int sum = 0 ;
        for(int i = 2 ; i < num + 1 ; i++){
            sum = a + b ; 
            a = b ; 
            b = sum ; 
        }
        return sum;
    }
}
