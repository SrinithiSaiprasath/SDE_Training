
//Q2
public class Car {
    String name = "Renault" ; 
    void start(){
        System.out.println("Car started");
    }
    void stop(){
        System.out.println("Car stopped");
    }

    public static void main(String[] args) {
        System.out.println(new Car().name);
    }
}


// OP
// Renault