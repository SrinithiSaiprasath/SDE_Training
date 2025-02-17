package MRCOOPERPREP.piggybank;

// package MRCOOPERPREP.piggybankapp;
import java.util.*;
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
                System.out.println("1. Deposit\n2. Withdraw\n3. Add Budget\n4. Add Expense\n5. Check Budget Analysis\n6. Set Savings Goal\n7. Check Savings Analysis\n8. Exit \");
                System.out.println("Enter your choice:");
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.println("Enter deposit amount:");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.println("Enter withdrawal amount:");
                        double withdrawAmount = scanner.nextDouble();
                        if (!account.withdraw(withdrawAmount)) {
                            System.out.println("Insufficient funds!");
                        }
                        break;
                    case 3:
                        System.out.println("Enter budget category:");
                        scanner.nextLine();
                        String category = scanner.nextLine();
                        System.out.println("Enter budget amount:");
                        double budgetAmount = scanner.nextDouble();
                        account.addBudget(new Budget(1, budgetAmount, category));
                        break;
                    case 4:
                        System.out.println("Enter expense category:");
                        scanner.nextLine();
                        String expCategory = scanner.nextLine();
                        System.out.println("Enter expense amount:");
                        double expAmount = scanner.nextDouble();
                        account.addExpense(new Expenses(1, expAmount, expCategory));
                        break;
                    case 5:
                        account.getBudgetAnalysis();
                        break;
                    case 6:
                        System.out.println("Enter target savings amount:");
                        double targetAmount = scanner.nextDouble();
                        account.addSavingsGoal(new SavingsGoal(1, targetAmount, "N/A"));
                        break;
                    case 7:
                        account.checkCurrentSavings();
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
