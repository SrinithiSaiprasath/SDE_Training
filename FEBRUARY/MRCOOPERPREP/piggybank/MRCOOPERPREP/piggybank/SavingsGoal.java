package MRCOOPERPREP.piggybank;

import java.util.* ; 
@SuppressWarnings("unused")

public class SavingsGoal {
    private int id;
    public double targetAmount;
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

    public String getGoalAnalysis() {
        return "Goal: " + targetAmount + ", Saved: " + currentSavings;
    }
}