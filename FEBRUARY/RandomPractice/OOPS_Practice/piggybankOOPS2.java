import java.util.*;
// import javax.xml.namespace.QName;

abstract class Auth{
    final HashMap<String,String> users = new HashMap<>();
    public void login(String name, String password){
        if(users.keySet().contains(name) && users.get(name).equals(password)){
            System.out.println("User Logged in Successfully");
        }else{
            System.out.println("User not found");
        }
    }
    public void logout(String name){
        if(users.keySet().contains(name)){
            System.out.println("User Logged out Successfully");
        }else{
            System.out.println("User not found");
        }
    }

    public User register(String name, String contact, String address, String email , String password){
        users.put(email,password);
        return new User(name,contact,address,email,password);
    }
}

abstract class GoalSetter{
    String name;
    double goal;
    double progress;
    HashMap<String,Double> ExpensesGoal = new HashMap<>();
    HashMap<String,Double> ExpensesProgress = new HashMap<>();
    HashMap<String,Double> SavingsGoal = new HashMap<>();
    HashMap<String,Double> SavingsProgress = new HashMap<>();
    public String getName() {return name;}
    public double getGoal() {return goal;}
    public double getProgress() {return progress;}
    public void updateProgress(double progress) {this.progress = progress;}
}

class Expenses extends GoalSetter{
    
    public Expenses(String name, double goal){
        this.name = name;
        this.goal = goal;
        this.progress = 0;
        ExpensesGoal.put(name,goal);
    }
    public void updateProgress(double amount){
        if(progress + amount <= goal){
            progress += amount;
            System.out.println("Progress updated in Expenses: " + name + " completed by: " + (progress / goal) * 100 + "%");
        }
        if(progress >= goal){
            System.out.println("Expenses Completed: " + name);
        }
    }
    public void viewReport(){
        System.out.println("--------------------REPORT--------------------");
        System.out.println("Expenses: " + name + " completed by " + (progress / goal) * 100 + "%");
        System.out.println("----------------------------------------------");
    }
}

class SavingsGoals extends GoalSetter{
    // HashMap<String,Double> mysavingGoals =new HashMap<>();
    // HashMap<String,Double> mysavingGoals =new HashMap<>();

    public SavingsGoals(String name, double goal){
        this.name = name;
        this.goal = goal;
        this.progress = 0;
        SavingsGoal.put(name,goal);
        SavingsProgress.put(name,0.0);
    }
    public void updateProgress(double amount){
        if(progress + amount <= goal){
            progress += amount;
            System.out.println("Progress updated in SavingsGoals: " + name + " completed by: " + (progress / goal) * 100 + "%");
        }
        if(progress >= goal){
            System.out.println("SavingsGoals Completed: " + name);
        }
    }
    public void viewReport(){
        System.out.println("--------------------REPORT--------------------");
        System.out.println("SavingsGoals: " + name + " completed by " + (progress / goal) * 100 + "%");
        System.out.println("----------------------------------------------");
    }
}


// abstract class 
class User extends Auth {
    private String name ,contact, address;
    private String id;
    private String password;
    private String email ; 
    private static int userCounter = 1000;
    public User(){
        this.id = generateUserId();
        System.out.println("User Created for user: " +  this.id);
    }
    public User(String name, String contact, String address, String email , String password) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.email = email ;
        this.password = password;
        this.id = generateUserId();
        System.out.println("User Created for user: " +  this.id);
    }
    private String generateUserId(){return "user_" + (userCounter++);}
    public String getAccId() {return id;}
    public String getPassword() {return password;}
    public String setPassWord(String password) { return this.password = password;}



}

public class piggybankOOPS2 {
    public static void main(String[] args) {
        User user = new User();
        User me = user.register("John Doe", "1234567890", "123", "Baker Street", "12345");
        // User me = new User();
        // me.register("John Doe", "1234567890", "123, Baker Street", "
        // me.login("John Doe", "1234567890");'
        // me.logout("John Doe");'
    }
}
