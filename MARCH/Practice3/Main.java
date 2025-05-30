package MARCH.Practice3;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

class User {
    public static final Scanner sc = new Scanner(System.in);
    private String name;
    private String id;
    private String email;
    private String password;
    private String phone;
    private String address;

    public User(String name, String id, String email, String password, String phone, String address) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

}

class Auth {
    public static final Scanner sc = new Scanner(System.in);
    HashMap<String, User> users = new HashMap<>();

    public void register() {
        System.out.println("Enter the name : ");
        String name = sc.next();
        System.out.println("Enter the id : ");
        String id = sc.next();
        if (users.containsKey(id)) {
            System.out.println("User ID already exists");
            return;
        }
        System.out.println("Enter the email : ");
        String email = sc.next();
        System.out.println("Enter the password : ");
        String password = sc.next();
        System.out.println("Enter the phone : ");
        String phone = sc.next();
        System.out.println("Enter the address : ");
        String address = sc.next();
        User user = new User(name, id, email, password, phone, address);

        users.put(id, user);
        System.out.println("User registered successfully");
    }

    public User login() {

        System.out.println("Enter the id : ");
        String id = sc.next();
        System.out.println("Enter the password : ");
        String password = sc.next();
        if (users.containsKey(id)) {
            User user = users.get(id);
            if (user.getPassword().equals(password)) {
                System.out.println("Login successful");
                return user;
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("User not found");
        }
        return null;
    }

    public void verifyYourself() {
        System.out.println("Verification Process");
        System.out.println("Enter the id : ");
        String id = sc.next();
        if (users.containsKey(id)) {
            User user = users.get(id);
            System.out.println("Enter the email : ");
            String email = sc.next();
            if (user.getEmail().equals(email) && verifyMail(email)) {
                System.out.println("Verification successful");
            } else {
                System.out.println("Invalid email kindly register again");
                users.remove(id);
                register();
            }
        } else {
            System.out.println("User not found");
        }
    }

    public boolean verifyMail(String mail) {
        for (User user : users.values()) {
            if (user.getEmail().equals(mail)) {
                return true;
            }
        }
        return false;
    }
}

class SavingsGoal implements TrackerOps {
    private String name;
    private String description;
    private double goal;
    private String deadline; // format => dd/mm/yyyy
    private double currentAmount;
    List<SavingsGoal> goals = new ArrayList<>();

    public SavingsGoal(String name, String description, double goal, String deadline) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.deadline = deadline; // format => dd/mm/yyyy
        this.currentAmount = 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getGoal() {
        return goal;
    }

    public String getDeadline() {
        return deadline;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public int getNoOfDaysLeft() {
        String[] date = getDeadline().split("/");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
                Integer.parseInt(date[0]), 0, 0);
        return now.compareTo(end);
    }

    public void deposit() {
        System.out.println("Enter the Savings Challenge Name: ");
        String name = sc.next();
        for (SavingsGoal goal : goals) {
            if (goal.getName().equals(name)) {
                System.out.println("Enter the amount to deposit : ");
                double amount = sc.nextDouble();
                goal.currentAmount += amount;
                System.out.println(
                        "Amount deposited successfully" + "\n" + "Current balance : " + goal.currentAmount);
                return;
            }
        }

    }

    public void withdraw() {
        System.out.println("Enter the Savings Challenge Name: ");
        String name = sc.next();
        for (SavingsGoal goal : goals) {
            if (goal.getName().equals(name)) {
                System.out.println("Enter the amount to withdraw : ");
                double amount = sc.nextDouble();
                if (amount > goal.currentAmount) {
                    System.out.println("Insufficient balance");
                    return;
                }
                goal.currentAmount -= amount;
                System.out.println(
                        "Amount withdrawn successfully" + "\n" + "Current balance : " + goal.currentAmount);
                return;
            }
        }
    }

    public void getAnalytics() {
        System.out.println("Enter Savings Goal Name to get Analytics : ");
        String name = sc.next();
        if (!name.equalsIgnoreCase("all")) {
            for (SavingsGoal goal : goals) {
                if (goal.getName().equals(name)) {
                    System.out.println("Name : " + goal.getName() + "\n" + "Description : "
                            + goal.getDescription() + "\n" + "Goal : " + goal.getGoal() + "\n" + "Deadline : "
                            + goal.getDeadline() + "\n" + "Current Amount : " + goal.getCurrentAmount() + "\n"
                            + "No of days left : " + goal.getNoOfDaysLeft());
                    return;
                }
            }
        } else {
            for (SavingsGoal goal : goals) {
                System.out.println("Name : " + goal.getName() + "\n" + "Description : "
                        + goal.getDescription() + "\n" + "Goal : " + goal.getGoal() + "\n" + "Deadline : "
                        + goal.getDeadline() + "\n" + "Current Amount : " + goal.getCurrentAmount() + "\n"
                        + "No of days left : " + goal.getNoOfDaysLeft());
            }
        }
    }

    public double getBalance() {
        System.out.println("Enter the Savings Challenge Name: ");
        String name = sc.next();
        if (!name.equalsIgnoreCase("all")) {
            for (SavingsGoal goal : goals) {
                if (goal.getName().equals(name)) {
                    System.out.println("Challenge Name : " + goal.getName() + "\n" + "Current Balance : "
                            + goal.currentAmount);
                    return goal.currentAmount;
                }
            }
        } else {
            double total = 0;
            for (SavingsGoal goal : goals) {
                System.out.println("Challenge Name : " + goal.getName() + "\n" + "Current Balance : "
                        + goal.currentAmount);
                total += goal.currentAmount;
            }
            return total;
        }
        return 0.0;

    }
}

class SavingsChallenge implements TrackerOps {
    private String name;
    private String description;
    private double challenge;
    private int deadline; // count days
    private double currentAmount;
    private String takenDate;
    
    public static final List<SavingsChallenge> definedChallenges = new ArrayList<>();

    public SavingsChallenge(String name, String description, double challenge, int deadline) {
        this.name = name;
        this.description = description;
        this.challenge = challenge;
        this.deadline = deadline;
        this.currentAmount = 0;
        this.takenDate = LocalDate.now().toString();
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getChallenge() { return challenge; }
    public int getDeadline() { return deadline; }
    public double getCurrentAmount() { return currentAmount; }
    public String getTakenDate() { return takenDate; }

    public int getNoOfDaysLeft() {
        LocalDate takenDate = LocalDate.parse(this.takenDate);
        LocalDate expectedDeadline = takenDate.plusDays(deadline);
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expectedDeadline);
    }

    public void createChallenges() {
        System.out.print("Enter the Savings Challenge Name: ");
        String name = sc.next();
        System.out.print("Enter the Savings Challenge Description: ");
        String description = sc.next();
        System.out.print("Enter the Savings Challenge Amount: ");
        String amount = sc.next();
        System.out.print("Enter the Savings Challenge Deadline: ");
        int deadline = sc.nextInt();

        definedChallenges.add(new SavingsChallenge(name, description, Double.parseDouble(amount), deadline));
        System.out.println("Savings Challenge added successfully!");
    }

    public void viewAllChallenges() {
        if (definedChallenges.isEmpty()) {
            System.out.println("No challenges available.");
            return;
        }
        definedChallenges.forEach(challenge -> System.out.println(
                "Name: " + challenge.name + "\nDescription: " + challenge.description +
                "\nGoal: " + challenge.challenge + "\nDeadline: " + challenge.deadline + " days\n"));
    }

    public void deposit() {
        System.out.print("Enter the Savings Challenge Name: ");
        String name = sc.next();
        Optional<SavingsChallenge> challenge = definedChallenges.stream()
                .filter(ch -> ch.name.equals(name))
                .findFirst();

        if (challenge.isPresent()) {
            System.out.print("Enter the amount to deposit: ");
            double amount = sc.nextDouble();
            challenge.get().currentAmount += amount;
            System.out.println("Amount deposited successfully!\nCurrent balance: " + challenge.get().currentAmount);
        } else {
            System.out.println("Challenge not found.");
        }
    }

    public void withdraw() {
        System.out.print("Enter the Savings Challenge Name: ");
        String name = sc.next();
        Optional<SavingsChallenge> challenge = definedChallenges.stream()
                .filter(ch -> ch.name.equals(name))
                .findFirst();

        if (challenge.isPresent()) {
            System.out.print("Enter the amount to withdraw: ");
            double amount = sc.nextDouble();
            if (amount > challenge.get().currentAmount) {
                System.out.println("Insufficient balance.");
            } else {
                challenge.get().currentAmount -= amount;
                System.out.println("Amount withdrawn successfully!\nCurrent balance: " + challenge.get().currentAmount);
            }
        } else {
            System.out.println("Challenge not found.");
        }
    }

    public void getAnalytics() {
        System.out.print("Enter Savings Challenge Name (or 'all' to view all): ");
        String name = sc.next();
        
        if (name.equalsIgnoreCase("all")) {
            definedChallenges.forEach(this::printChallengeDetails);
        } else {
            definedChallenges.stream()
                    .filter(ch -> ch.name.equals(name))
                    .findFirst()
                    .ifPresentOrElse(this::printChallengeDetails, () -> System.out.println("Challenge not found."));
        }
    }

    public double getBalance() {
        System.out.print("Enter the Savings Challenge Name (or 'all' to view total balance): ");
        String name = sc.next();

        if (name.equalsIgnoreCase("all")) {
            double total = definedChallenges.stream().mapToDouble(ch -> ch.currentAmount).sum();
            System.out.println("Total Balance across all challenges: " + total);
            return total;
        }

        return definedChallenges.stream()
                .filter(ch -> ch.name.equals(name))
                .findFirst()
                .map(ch -> {
                    System.out.println("Challenge Name: " + ch.name + "\nCurrent Balance: " + ch.currentAmount);
                    return ch.currentAmount;
                })
                .orElse(0.0);
    }

    private void printChallengeDetails(SavingsChallenge challenge) {
        System.out.println("Name: " + challenge.name + "\nDescription: " + challenge.description +
                "\nGoal: " + challenge.challenge + "\nDeadline: " + challenge.deadline + " days" +
                "\nCurrent Amount: " + challenge.currentAmount +
                "\nDays Left: " + challenge.getNoOfDaysLeft() + "\n");
    }
}

class Account implements TrackerOps {

    User user;
    List<SavingsGoal> mygoals = new ArrayList<>();
    List<SavingsChallenge> mychallenges = new ArrayList<>();
    private double currentBalance;

    public Account(User user) {
        this.user = user;
        this.currentBalance = 0;
        this.mygoals = new ArrayList<>();
        this.mychallenges = new ArrayList<>();
    }

    public double getBalance() {
        System.out.println("Current balance : " + currentBalance);
        return currentBalance;
    }

    public void deposit() {
        System.out.println("Enter the amount to deposit : ");
        String amount = sc.next();
        currentBalance += Double.parseDouble(amount);
        System.out.println("Amount deposited successfully" + "\n" + "Current balance : " + currentBalance);
    }

    public void withdraw() {
        System.out.println("Enter the amount to withdraw : ");
        String amount = sc.next();        
        if (Double.parseDouble(amount) > currentBalance) {
            System.out.println("Insufficient balance");
            return;
        }
        currentBalance -= Double.parseDouble(amount);
        System.out.println("Amount withdrawn successfully" + "\n" + "Current balance : " + currentBalance);
    }

    public void addSavingsGoal() {
        System.out.println("Enter the Savings Goal Name: ");
        String name = sc.next();
        System.out.println("Enter the Savings Goal Description: ");
        String description = sc.next();
        System.out.println("Enter the Savings Goal Amount: ");
        String amount = sc.next();

        System.out.println("Enter the Savings Goal Deadline: ");
        String deadline = sc.next();
        SavingsGoal goal = new SavingsGoal(name, description, Double.parseDouble(amount), deadline);
        mygoals.add(goal);
        System.out.println("Savings Goal added successfully");
    }

    public void viewSavingChallenge() {
        for (SavingsGoal goal : mygoals) {
            System.out.println("Name : " + goal.getName() + "\n" + "Description : "
                    + goal.getDescription() + "\n" + "Goal : " + goal.getGoal() + "\n" + "Deadline : "
                    + goal.getDeadline() + "\n" + "Current Amount : " + goal.getCurrentAmount() + "\n"
                    + "No of days left : " + goal.getNoOfDaysLeft());
        }
    }

    public void addSavingChallenge() {
        System.out.println("Enter the Savings Challenge Name: ");
        String name = sc.next();
        for (SavingsChallenge challenge : SavingsChallenge.definedChallenges) {
            if (challenge.getName().equals(name)) {
                mychallenges.add(challenge);
                System.out.println("Savings Challenge added successfully");
                return;
            }
        }
    }

    public void getAnalytics() {
        System.out.println("Get the analytics for:  \n   1. Saving Goals \n 2.Saving Challenges  \n 3.Exit");
        System.out.println("Enter your choice : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                for (SavingsGoal goal : mygoals) {
                    goal.getAnalytics();
                }
                break;
            case 2:
                for (SavingsChallenge challenge : mychallenges) {
                    challenge.getAnalytics();
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}

interface TrackerOps {
    Scanner sc = new Scanner(System.in);

    public double getBalance();

    public void deposit();

    public void withdraw();

    public void getAnalytics();
}

public class Main {
    public static void main(String[] args) {
        Auth auth = new Auth();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Register \n 3.Login \n4.Exit");
            System.out.println("Enter your choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    auth.register();
                    auth.verifyYourself();
                    break;
                case 2:
                    User user = auth.login();
                    if (user != null) {
                        Account acc = new Account(user);
                        loginOperations(acc);
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            sc.close();
        }
         // closing the scanner
    }

    public static void loginOperations(Account account) {
        Scanner sc = new Scanner(System.in);
        // Account account = new Account(auth.login());
        System.out.println(
                "1.Deposit \n 2.Withdraw \n 3.Add Savings Goal \n 4.View Savings Goal \n 5.Add Savings Challenge \n 6.View Savings Challenge \n 7.Get Analytics \n 8.Exit");
        System.out.println("Enter your choice : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                account.deposit();
                break;
            case 2:
                account.withdraw();
                break;
            case 3:
                account.addSavingsGoal();
                break;
            case 4:
                account.viewSavingChallenge();
                break;
            case 5:
                account.addSavingChallenge();
                break;
            case 6:
                account.viewSavingChallenge();
                break;
            case 7:
                account.getAnalytics();
                break;
            case 8:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        sc.close();
    }
}

