//RECURSIVE APPROACH
public class Approach1_509{
    public static void main(String[] args){
        int fib_num = 3; 
        System.out.println(find_fib(fib_num));
    }
    public static int find_fib(int num){
        if(num == 0 || num == 1){
            return num;
        }
        return find_fib(num-1) + find_fib(num-2);
    }
}