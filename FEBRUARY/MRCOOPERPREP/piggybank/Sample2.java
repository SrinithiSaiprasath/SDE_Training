import java.util.*;

@SuppressWarnings("unused")

class User {
    private String name ,contact, address;
    private String id;
    private String password;
    private static int userCounter = 1000;

    public User(String name, String contact, String address, String password) {
        this.name = name;
        this.id = generateUserId();
        this.contact = contact;
        this.address = address;
        this.password = password;
    }

    private String generateUserId() {
        return "user_" + (userCounter++);
    }

    public String getAccId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}

class SavingsGoal {
    @SuppressWarnings("unused")
    private String id;
    private final double targetAmount;
    private String deadline;
    private double currentSavings;

    public SavingsGoal(String id, double targetAmount, String deadline) {
        this.id = id;
        this.targetAmount = targetAmount;
        this.deadline = deadline;
        this.currentSavings = 0;
    }

    public boolean checkGoalAchieved() {
        return currentSavings >= targetAmount;
    }

    public void addSavings(double amount) {
        currentSavings += amount;
    }

    public double getCurrentSavings() {
        return currentSavings;
    }

    public String getGoalAnalysis() {
        return "Goal: " + targetAmount + ", Saved: " + currentSavings + ", Remaining: " + (targetAmount - currentSavings);
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
        return "Category: " + category + ", Budget: " + amount + ", Expenses: " + expenses + ", Remaining: " + (amount - expenses);
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

    public void deposit(double amount, boolean isSavings) {
        if (isSavings) {
            if (savingsGoals.isEmpty()) {
                System.out.println("No savings goal set!");
                return;
            }
            savingsGoals.get(0).addSavings(amount);
        } else {
            balance += amount;
        }
    }

    public boolean withdraw(double amount, String category ) {
        
        if (amount <= balance) {
            balance -= amount;
            budgets.get(category).addExpense(amount);
            return true;
        }
        return false;
    }
    public boolean withdrawSavings(double amount , boolean isSavings) {
        if (isSavings) {
            // Get the first savings goal and update its savings
            SavingsGoal currentGoal = savingsGoals.get(0);
            double currentSavings = currentGoal.getCurrentSavings();
            if (currentSavings >= amount) {
                currentGoal.withdrawSavings(amount);
                return true;
            }
        }
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
        System.out.println("Current Savings Balance: " + totalSavings);
    }
}

public class Sample2 {
    public static void main(String[] args) {
        User me = new User("Srinithi", "122121121", "add", "abcd");
        Account account = new Account(me.getAccId(), "abcd");
        System.out.println("Your User ID is: " + me.getAccId());    
        //deposit amount in curr account
        account.deposit(50000,false);
        account.deposit(2000,false);
        account.checkCurrentBalance();
        //deposit amount in savings 
        double goal = 20000;
        account.addSavingsGoal(new SavingsGoal(me.getAccId(), goal, null));
        account.deposit(10000 , true);
        account.deposit(5000 ,true);
        account.checkCurrentBalance();
        //add budget
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
        if(isdone) System.out.println("Withdrawn 1");
        else System.out.println("Cannot withdraw"); 
        account.checkCurrentBalance();


        boolean isdone2 = account.withdraw(600, "Food Daily");
        if(isdone2) System.out.println("Withdrawn 2");
        else System.out.println("Cannot withdraw"); 
        account.checkCurrentBalance();

        boolean isdone3 = account.withdraw(600, "Other");
        if(isdone3) System.out.println("Withdrawn 3");
        else System.out.println("Cannot withdraw"); 

        account.checkCurrentBalance();
        account.getBudgetAnalysis();

        double d = 1000;
        boolean a = account.withdrawSavings(d , true);
        account.checkCurrentSavings();
        // account.getBudgetAnalysis();
        System.out.println("end");

    }
}