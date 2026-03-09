//recursion approach
/**Top down approach where we get break the bigger problem into smaller problem and solev them first 
 * And eventually solve the bigger one
*/
public class fibonacci_V1 {
    public static void main(String[] args){
        int n = 5 ; 
        System.out.println(fibonacci(n));
    }

    public static int fibonacci(int n){
        if(n<= 1) return n ; 
        return fibonacci(n-1) + fibonacci(n -2) ;  
    }
}


// Fib :  0 1 1 2 3 5 8
// IDX :  0 1 2 3 4 5 6