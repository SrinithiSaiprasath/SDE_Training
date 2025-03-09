// package SDE_Training-main.FEBRUARY.MRCOOPERPREP.PiggyBankApp;
// package MRCOOPERPREP.piggybank;
import java.time.LocalDate;
import java.util.*;

@SuppressWarnings("unused")
class User {
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
    }

    private String generateUserId() {
        return "user_" + (userCounter++);
    }

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

class Budget {
    private final String category;
    private double amount;
    private double expenses;
    public static final List<String> CATEGORIES = Arrays.asList("Investments", "Shopping", "Food Daily", "Common", "Unexpected", "Other");

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
            rem = "Crossed Budget by " + -1 * (amount - expenses);
        }
        return "Category: " + category + ", Budget: " + amount + ", Expenses: " + expenses + rem;
    }
}

class Account {
    private String id;
    private String password;
    private double balance;
    private Map<String, Budget> budgets = new HashMap<>();
    private List<SavingsGoal> savingsGoals = new ArrayList<>();

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
        this.balance = 0;
    }

    public boolean verifyUser(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }
    public void showMySavingsGoals(){
        for(int i = 0 ; i < savingsGoals.size(); i++){
            System.out.println("Goal Name : " +savingsGoals.get(i).getName()+"\n"+ " Current Savings :  "+ savingsGoals.get(i).getCurrentSavings()+"\n" +" Remaining Amount : " + (savingsGoals.get(i).getCurrentSavings() - savingsGoals.get(i).getCurrentSavings())+ "\n" +" Deadline Complete " + savingsGoals.get(i).checkGoalAchieved()+ "\n" + " Remaining Days " + savingsGoals.get(i).getRemainingDays());
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
            System.out.println("Amount " + amount+ " withdrawn successfully from " +category); 
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
                    System.out.println("Amount " + amount + " withdrawn successfully from savings goal! " + goalName + " Remaining Amount : " + (goal.getCurrentSavings() - amount));
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

public class PiggyBankApp {
    public static void main(String[] args) {
        //register the user
        User me = new User("Srinithi", "122121121", "add","srinithi@gmail.com", "abcd");
        Account account = new Account(me.getAccId(), "abcd");

        //user verified
        System.out.println("Your User ID is: " + me.getAccId());    
        System.out.println("Your Password is: " + me.getPassword());
        System.out.println("Your changed password is: " + me.setPassWord("efgh"));

        //deposit amount in main account 
        account.deposit(50000,false ); //check if the amount is to be added in savings 
        account.deposit(2000,false); //if savings if false then it directly sums to the total balance of the main acc

        //check account balance
        account.checkCurrentBalance();

        //Create a saving goal and add amount in the saving goal
        double goal = 20000;
        account.addSavingsGoal(new SavingsGoal(me.getAccId(),"S1", goal, "2025-04-04"));
        account.deposit(10000 , true);
        account.deposit(5000 ,true);

        //create another saving goal and add amount in the saving goal
        double goal1 = 25000;
        account.addSavingsGoal(new SavingsGoal(me.getAccId(),"S2", goal1, "2025-04-04"));
        account.deposit(10000 , true);
        account.deposit(5000 ,true);

        //show all my saving goals and its data
        account.showMySavingsGoals();

        //check current savings
        account.checkCurrentBalance();
        account.checkCurrentSavings();

        //Creating a budget and adding amount to the budget
        String[] categories = {"Investments", "Shopping", "Food Daily", "Common", "Unexpected", "Other"};
        account.addBudget("Investments", 5000);
        account.addBudget("Shopping", 5000);
        account.addBudget("Food Daily", 1000);
        account.addBudget("Common", 2000);
        account.addBudget("Unexpected", 2000);
        account.addBudget("Other",1000);
        account.checkCurrentBalance();
        account.checkCurrentSavings();
        account.getBudgetAnalysis();


        //withdraw from curr account
        boolean isdone = account.withdraw(6000, "Investments");
        // if(isdone) System.out.println("Amount is Withdrawn ");
        // else System.out.println("Cannot withdraw Amount "); 
        account.checkCurrentBalance();


        boolean isdone2 = account.withdraw(600, "Food Daily");
        // if(isdone2) System.out.println("Withdrawn 2");
        // else System.out.println("Cannot withdraw"); 
        account.checkCurrentBalance();

        boolean isdone3 = account.withdraw(600, "Other");
        // if(isdone3) System.out.println("Withdrawn");
        // else System.out.println("Cannot withdraw"); 

        account.checkCurrentBalance();
        account.getBudgetAnalysis();
        //show my saving goals 
        account.showMySavingsGoals();

        //withdraw from savings
        double d = 1000;
        boolean a = account.withdrawSavings(d,"S1");
        account.checkCurrentSavings();
        // account.getBudgetAnalysis();
        System.out.println("Transaction Complete");

        
    }
}
