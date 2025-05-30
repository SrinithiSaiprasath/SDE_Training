
class Animal{
    private String name ;
    private String familyname;
    private String ancestors ; 
    public Animal(String name){
        this.name= name ;
        this.familyname = "" ; 
        this.ancestors = " " ; 
    }
    public String getName(){return name ; }
    public String getFamilyName(){return familyname ; }

    public void eat(){
        System.out.println( name + " eats");
    }
    public void sleep(){
        System.out.println(  name + "  sleeps");
    }
    public String getAncestors(){
        return ancestors ; 
    }
}

class Lion extends Animal{
    public Lion(String name){
        super(name) ; 
    }

}


public class Sample2 {
    public static void main(String[] args) {
        Animal me = new Lion("Sher");
        me.eat();
        me.sleep();
        Animal me2 = new Animal("Cheetha") ; 
        me2.getAncestors();
        me2.getFamilyName() ; 
        me2.getName() ; 
        me2.eat(); 
        me2.sleep();
    }
}
