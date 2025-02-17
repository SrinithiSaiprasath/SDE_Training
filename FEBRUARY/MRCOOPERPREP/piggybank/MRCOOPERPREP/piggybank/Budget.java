package MRCOOPERPREP.piggybank;

import java.util.*;
@SuppressWarnings("unused")

public class Budget {
    private int id;
    private double amount;
    public String category;
    private double expenses;

    public Budget(int id, double amount, String category) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.expenses = 0;
    }

    public void updateBudget(double amount) {
        this.amount += amount;
    }

    public void addExpense(double expense) {
        this.expenses += expense;
    }

    public String getBudgetAnalysis() {
        return "Category: " + category + ", Budget: " + amount + ", Expenses: " + expenses;
    }

    public double getRemainingBudget() {
        return amount - expenses;
    }
}