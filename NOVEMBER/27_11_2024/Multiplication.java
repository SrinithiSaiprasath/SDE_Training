public class Multiplication {
    int a ;
    int b ;
    int c ; 
    int d ;
    Multiplication(int p , int q){
        a =p ;
        b = q ;
        int ab = a*b ; 
        System.out.println("Product of " + a + " and " + b + " is " + ab) ;
    }
    void Multiply(int x , int y){
        c = x ; 
        d = y ;
        int cd = c*d ;
        System.out.println("Product of " + c + " and " + d + " is " + cd) ;
    }
    public static void main(String[] args) {
        new Multiplication(25,25).Multiply(10,20);
        new Multiplication(20,20).Multiply(30,30);
    }
}

// Product of 25 and 25 is 625
// Product of 10 and 20 is 200
// Product of 20 and 20 is 400
// Product of 30 and 30 is 900
