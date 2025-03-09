//piggybank

import java.util.*;
import java.time.LocalDate;
class User{
    private String name ,contact, address;
    private String id;
    private String password;
    private String email ; 
    private static int userCounter = 1000;

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

class SavingsGoal {
        @SuppressWarnings("unused")
        private String id;
        private String name;
        private final double targetAmount;
        private String deadline;
        private double currentSavings;
    
        public SavingsGoal(String id, String name, double targetAmount, String deadline) {
            this.id = id;
            this.name = name;
            this.targetAmount = targetAmount;
            this.deadline = deadline;
            this.currentSavings = 0;
        }
        public double getGoal(){
            return targetAmount;
        }
        public String getName() {
            return name;
        }
        public boolean checkGoalAchieved() {
            if(LocalDate.now().isAfter(LocalDate.parse(deadline)) && currentSavings < targetAmount) {
                return true;
            }
            return false;
            
        }
    
        public void addSavings(double amount) {
            currentSavings += amount;
        }
    
        public double getCurrentSavings() {
            return currentSavings;
        }
        public int getRemainingDays(){
            return LocalDate.now().until(LocalDate.parse(deadline)).getDays() ;
        }
        public String getGoalAnalysis() { 
            return "Goal: " + name +" "+ targetAmount + ", Saved: " + currentSavings + ", Remaining: " + (targetAmount - currentSavings) + " remaining Days:" + getRemainingDays();
        }
        public void withdrawSavings(double amount) {
            if (amount <= currentSavings) {
                currentSavings -= amount;
            }
        }
}



class Budget{
    private String category ; 
    private double expenses;
    private double amount ;
    final public HashMap<String , Double> myexpenses = new HashMap<>();
    String[] ycat= {"Investments " , "Daily Food" ,"Common" ,"Unexpected","Miscellaneous","Other" };
    
    public Budget(String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.expenses = 0;
    }
    public void addExpense(double expense) {
        this.expenses += expense;
    }

    public String getBudgetAnalysis() {
        String rem = "";
        if(amount -expenses < 0){
            rem = "  Crossed Budget by " + -1 * (amount - expenses);
        }
        return "Category: " + category + ", Budget: " + amount + ", Expenses: " + expenses + rem;
    }
}


class Account{
    private String id;
    private String password;
    private double balance;
    private Map<String, Budget> budgets = new HashMap<>();
    private List<SavingsGoal> savingsGoals = new ArrayList<>();
    
    public Account(String id,String password){
   
    this.id = id;
        this.password = password ;
        this.balance = 0.0;
        // System.out.println("Account Created for User: " +id);
    }
    public boolean verifyUser(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }
    public boolean logout(String id){
        return this.id.equals(id) ;
    }
    public void showMySavingsGoals(){
        for(int i = 0 ; i < savingsGoals.size(); i++){
            System.out.println("Goal Name : " +savingsGoals.get(i).getName()+"\n"+ " Current Savings :  "+ savingsGoals.get(i).getCurrentSavings()+"\n" +" Remaining Amount : " + -1*(savingsGoals.get(i).getCurrentSavings() - savingsGoals.get(i).getGoal())+ "\n" +" Deadline Complete " + savingsGoals.get(i).checkGoalAchieved()+ "\n" + " Remaining Days " + savingsGoals.get(i).getRemainingDays());
        }
    }
    
    public void deposit(double amount, boolean isSavings) {
        if (isSavings) {
            if (savingsGoals.isEmpty()) {
            System.out.println("No savings goal set!");
            return;
            }
            for(int i = 0 ; i < savingsGoals.size(); i++){
                System.out.println("Saving Goals Set :  " +savingsGoals.get(i).getName());
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name of the savings goal to deposit:");
            String goalName = scanner.nextLine();
            boolean goalFound = false;
            for (SavingsGoal goal : savingsGoals) {
                if (goal.getName().equals(goalName)) {
                goal.addSavings(amount);
                goalFound = true;
                break;
            }
            }
            if (!goalFound) {
            System.out.println("Savings goal not found!");
            }
        } else {
            balance += amount;
        }
    }

    public boolean withdraw(double amount, String category) {
        if (amount <= balance) {
            balance -= amount;
            budgets.get(category).addExpense(amount);
            System.out.println("Amount " + amount+ " withdrawn successfully for " +category); 
            return true;
        }
        return false;
    }

    public boolean withdrawSavings(double amount, String goalName) {
        for (SavingsGoal goal : savingsGoals) {
            if (goal.getName().equals(goalName)) {
                double currentSavings = goal.getCurrentSavings();
                if (currentSavings >= amount) {
                    goal.withdrawSavings(amount);
                    System.out.println("Amount " + amount + " withdrawn successfully from savings goal! " + goalName + " Remaining Amount : " + (goal.getGoal() - goal.getCurrentSavings())); //savingsGoals.get(i).getCurrentSavings() - savingsGoals.get(i).getGoal()
                    return true;
                }
            }
        }
        System.out.println("Insufficient savings in the goal!");
        return false;
    }

    public void addBudget(String category, double amount) {
        budgets.put(category, new Budget(category, amount));
    }

    public void addSavingsGoal(SavingsGoal goal) {
        savingsGoals.add(goal);
    }

    public void checkCurrentSavings() {
        for (SavingsGoal goal : savingsGoals) {
            System.out.println("Savings Goal Name: " + goal.getName());
            System.out.println(goal.getGoalAnalysis());
        }
    }

    public void getBudgetAnalysis() {
        for (Budget budget : budgets.values()) {
            System.out.println(budget.getBudgetAnalysis());
        }
    }

    public void checkCurrentBalance() {
        System.out.println("Current Account Balance: " + balance);
        double totalSavings = savingsGoals.stream().mapToDouble(SavingsGoal::getCurrentSavings).sum();
        // System.out.println("Current Savings Balance: " + totalSavings);
        for (SavingsGoal goal : savingsGoals) {
            System.out.println("Savings Goal: " + goal.getName() + ", Savings Balance: " + goal.getCurrentSavings());
        }
    }
}

public class PiggyBankApplication{
    public static void main(String args[]){
        HashMap<String,String> activeUsers = new HashMap<>();
        User me = new User("Srinithi","123456789", "add","abcd","abcd");
        Account me_acc = new Account(me.getAccId(),"abcd");
        if(me_acc.verifyUser(me.getAccId(), "abcd")){
            System.out.println("Logged in");
            activeUsers.put(me.getAccId(), "abcd");
        }

        me_acc.deposit(5000.0,false);
        me_acc.deposit(6000, false);

        me_acc.checkCurrentBalance();
        me_acc.addSavingsGoal(new SavingsGoal(me.getAccId() , "Car" , 200000.0 , "2025-08-03"));
        me_acc.deposit(50000, true);
        me_acc.checkCurrentSavings();

        me_acc.addBudget("Investments", 6000.0);
        me_acc.addBudget("Shopping", 4000.0);
        me_acc.addBudget("Food Daily", 1000.0);
        me_acc.addBudget("Common", 1000.0);
        me_acc.addBudget("Unexpected", 1000.0);
        me_acc.addBudget("Other", 1000.0);

        me_acc.withdraw(2000.0, "Other");
        me_acc.withdraw(2000.0, "Common");
        me_acc.checkCurrentBalance();

        me_acc.withdrawSavings(5000.0, "Car");
        me_acc.checkCurrentSavings();

        me_acc.getBudgetAnalysis();
        me_acc.showMySavingsGoals();
        me_acc.checkCurrentBalance();

        if(me_acc.logout(me.getAccId()) && activeUsers.containsKey(me.getAccId())){
            System.out.println("Logged Out");
        }
    }
}
