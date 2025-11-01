class Something {
    static int a = 10 ;
    int b = 20 ;
}
public class VariableScope{
    public static void main(String[] args) {
        Something s1 = new Something();
        s1.a = 25 ; 
        s1.b =  35 ; 
        Something s2 = new Something() ;
        System.out.println("s1 a " + s1.a);
        System.out.println("s1 b " + s1.b);
        System.out.println("s2 a " + s2.a);
        System.out.println("s2 b " + s2.b);
    }
}