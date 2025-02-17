package MRCOOPERPREP.piggybank;

import java.util.* ;
import MRCOOPERPREP.piggybank.*;
// @SuppressWarnings("unused")

public class Account {
    private String id;
    private String password;
    private double balance;
    public List<Expenses> expensesList = new ArrayList<>();
    public List<Budget> budgetList = new ArrayList<>();
    public List<SavingsGoal> savingsGoals = new ArrayList<>();
    public Map<String, Account> accounts = new HashMap<>(); // HashMap to store accounts

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
        this.balance = 0;
    }

    public  boolean verifyUser(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addSavingsGoal(SavingsGoal goal) {
        savingsGoals.add(goal);
    }

    public void addBudget(Budget budget) {
        budgetList.add(budget);
    }

    public void addExpense(Expenses expense) {
        expensesList.add(expense);
        for (Budget budget : budgetList) {
            if (budget.category.equals(expense.category)) {
                budget.addExpense(expense.amount);
            }
        }
    }

    public void checkCurrentSavings() {
        double totalSaved = 0;
        for (SavingsGoal goal : savingsGoals) {
            System.out.println(goal.getGoalAnalysis());
            totalSaved += goal.checkGoalAchieved() ? goal.targetAmount : 0;
        }
        System.out.println("Overall Savings: " + totalSaved);
    }

    public void getBudgetAnalysis() {
        for (Budget budget : budgetList) {
            System.out.println(budget.getBudgetAnalysis());
        }
    }
    public static String[] create_account(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your contact:");
        String contact = scanner.nextLine();
        System.out.println("Enter your address:");
        String address = scanner.nextLine();
        System.out.println("Set your password:");
        String password = scanner.nextLine();
        return new String[]{name, contact, address, password};
        
    }
}