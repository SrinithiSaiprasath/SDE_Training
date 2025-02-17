package MRCOOPERPREP.piggybank;

import java.util.* ;
@SuppressWarnings("unused")

public class Expenses {
    private int id;
    public double amount;
    public String category;

    public Expenses(int id, double amount, String category) {
        this.id = id;
        this.amount = amount;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }
}