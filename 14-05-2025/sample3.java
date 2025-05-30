public class sample3 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // System.out.println("Hello World");
        C myc = new C();
        myc.methodA(5) ; 
}
}

interface A{
    int methodA(int a );
}

interface B{
    void methodA();
}

class C implements A , B{
    public int methodA(int a ){
        System.out.println("A,A");
        return 0 ; 
    }
    public void methodA(){
        System.out.println("B,A");
    }
}
