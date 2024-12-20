class Human{ 
    private int age;  //when age is private it gives an error
    private String name;

    public int getAge(){
        return age;
    }

    void setAge(int age){
        this.age = age;
    }
    void setName(String name){
        this.name = name; 
    }
    public String getName(){
        return name;
    }

}

public class Encapsulation{
    public static void main(String[] args){
        Human h = new Human();
        // h.age = 20 ;
        // h.name = "John" ;
        System.out.println(h.getAge());  //when age is private it gives an error
        System.out.println(h.getName());
    }
}