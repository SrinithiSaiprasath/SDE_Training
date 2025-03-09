//health tracker
class User{
    private String id = "User_";
    private int usercount = 0;
    private String name ;
    private String phonenumber;
    private String email;
    private String password ;

    public User(String name,String phonenumber, String email, String password){
        this.name = name;
        this.phonenumber= phonenumber;
        this.email = email;
        this.password = password;
        this.id = generateID();
        System.out.println("User created for: "+name + " as " + this.id);
    }
    public String generateID(){return id+usercount++;}
    public String getName(){return this.name;}
    public String getId(){return this.id;}
    public String getPasword(){return this.password;}
    public String setPassword(String password){return this.password = password;}
}

public interface HabitTracker {
    public void addHabit(String habit);
    public void removeHabit(String habit);
    public void updateHabit(String habit);
    public void getHabits();
}

public class SampleTest2Impl implements HabitTracker {
    public static void main(String[] args){

        
    }
    
}