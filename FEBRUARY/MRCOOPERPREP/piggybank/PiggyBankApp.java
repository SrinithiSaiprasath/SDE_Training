import java.util.*;

class User {
    public String name, contact, address;
    public String id;
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
    private int id;
    private double targetAmount;
    private String deadline;
    private double currentSavings;

    public SavingsGoal(int id, double targetAmount, String deadline) {
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
}

class Budget {
    private String category;
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
    public String id;
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

    public boolean withdraw(double amount, String category) {

        if (amount <= balance) {
            balance -= amount;
            budgets.get(category).addExpense(amount);
            return true;
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

public class PiggyBankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your contact:");
        String contact = scanner.nextLine();
        System.out.println("Enter your address:");
        String address = scanner.nextLine();
        System.out.println("Set your password:");
        String password = scanner.nextLine();
        
        User user = new User(name, contact, address, password);
        Account account = new Account(user.getAccId(), password);
        
        System.out.println("Your User ID is: " + user.getAccId());
        
        System.out.println("Enter User ID to login:");
        String enteredId = scanner.nextLine();
        System.out.println("Enter Password:");
        String enteredPassword = scanner.nextLine();
        
        if (account.verifyUser(enteredId, enteredPassword)) {
            System.out.println("Login successful!");
            
            while (true) {
                System.out.println("1. Deposit\n2. Withdraw\n3. Add Budget\n4. Check Budget Analysis\n5. Set Savings Goal\n6. Check Savings Analysis\n7. Check Current Balance\n8. Exit");
                System.out.println("Enter your choice:");
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.println("Enter deposit amount:");
                        double depositAmount = scanner.nextDouble();
                        System.out.println("Is this for savings? (true/false):");
                        boolean isSavings = scanner.nextBoolean();
                        account.deposit(depositAmount, isSavings);
                        break;
                    case 2:
                        System.out.println("Enter withdrawal amount:");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Enter category: " + Budget.CATEGORIES );
                        String category = scanner.next() ; 
                        if (!account.withdraw(withdrawAmount, category)) {
                            System.out.println("Insufficient funds!");
                        }
                        break;
                    case 3:
                        System.out.println("Select a budget category: " + Budget.CATEGORIES);
                        scanner.nextLine();
                        String budgetCategory = scanner.nextLine();
                        System.out.println("Enter budget amount:");
                        double budgetAmount = scanner.nextDouble();
                        account.addBudget(budgetCategory, budgetAmount);
                        break;
                    case 4:
                        account.getBudgetAnalysis();
                        break;
                    case 5:
                        System.out.println("Enter target savings amount:");
                        double targetAmount = scanner.nextDouble();
                        account.addSavingsGoal(new SavingsGoal(1, targetAmount, "N/A"));
                        break;
                    case 6:
                        account.checkCurrentSavings();
                        break;
                    case 7:
                        account.checkCurrentBalance();
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Invalid credentials. Exiting.");
        }
        scanner.close();
    }
}