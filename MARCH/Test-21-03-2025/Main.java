import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

class User {
	String id;
	String password;
	String Name;
	String email;
	Long contact;
	String address;

	public User(String Name, String id, String password, String email, String address, Long contact) {
		this.Name = Name;
		this.id = id;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.address = address;
	}

	public User() {
	}

	String getId() {
		return id;
	}

	String getName() {
		return Name;
	}

	public String getPassword() {
		return password;
	}

	void setpassword(String np) {
		this.password = np;
	}

	Long getContact() {
		return contact;
	}

	String getEmail() {
		return email;
	}
}

class AccountManager {
	List<User> users = new ArrayList<>();
	HashMap<String, User> userMap = new HashMap<>();
	Set<User> loggedInUsers = new HashSet<>();

	public void Register(String name, String id, String password, String Address, String email, Long contact) {
		if (!userMap.keySet().contains(id)) {
			User me = new User(name, id, password, email, Address, contact);
			users.add(me);
			userMap.put(id, me);
		}
	}

	public void login(String id, String password) {
		if (userMap.keySet().contains(id) && userMap.get(id).getPassword().equals(password)) {
			loggedInUsers.add(userMap.get(id));
			System.out.println("Logged In Successfully as " + userMap.get(id).getName());
		} else {
			System.out.println("Invalid Credentials");
		}
	}

	public boolean getUserFromID(String id) {
		return userMap.containsKey(id);
	}
}

interface Tracker {
	public double getBalance();

	public void getAnalysis();

	public void deposit();

	public void withdraw();
}

class Budget {
	String category;
	double amount;
	User user;
	HashMap<String, Double> userBudgets = new HashMap<>();
	HashMap<String, Double> userExpenses = new HashMap<>();

	public Budget() {

	}

	public Budget(String category, double amount) {
		this.category = category;
		this.amount = amount;
	}

	public void createBudget(String category, double amount) {
		userBudgets.put(category, amount);
	}

	public void setBudget(String category, double amount) {
		this.category = category;
		this.amount = amount;
	}

	public void getBudget(String category) {
		System.out.println("Budget for " + category + " is: " + userBudgets.get(category));
	}

	public void getBudget() {
		for (String key : userBudgets.keySet()) {
			System.out.println("Budget for " + key + " is: " + userBudgets.get(key));
		}
	}

	public void getAnalysis() {
		for (String key : userBudgets.keySet()) {
			System.out.println("Budget for " + key + " is: " + userBudgets.get(key) + " and Expenses are: "
					+ userExpenses.get(key));
		}
	}

	public void getAnalysis(String category) {
		System.out.println("Budget for " + category + " is: " + userBudgets.get(category) + " and Expenses are: "
				+ userExpenses.get(category));
	}
}

class Account implements Tracker {
	User user;
	HashMap<User, Double> userAccount = new HashMap<>();
	HashMap<String, Budget> userBudgets = new HashMap<>();
	HashMap<String, Double> userExpenses = new HashMap<>();
	double balance;

	public Account(User user) {
		this.user = user;
		this.balance = 0.0;
		this.userBudgets = new HashMap<>();
		this.userExpenses = new HashMap<>();
	}

	public Account() {

	}

	public void setBalance(double amount) {
		this.balance += amount;
	}

	public double getBalance() {
		System.out.println("Balance: " + userAccount.get(user));
		return balance;
	}

	// Removed duplicate method getBalance()
	public void getAnalysis(String category) {
		System.out.println("Budget for " + category + " is: " + userBudgets.get(category).amount + "  Expenses : "
				+ userExpenses.get(category));
	}

	public void getAnalysis() {
		for (String key : userBudgets.keySet()) {
			System.out.println("Budget for " + key + " is: " + userBudgets.get(key).amount + "  Expenses : "
					+ userExpenses.get(key));
		}
	}

	public void deposit(double amount) {
		userAccount.putIfAbsent(user, 0.0);
		userAccount.put(user, userAccount.get(user) + amount);
		System.out.println("Amount Deposited Successfully");
	}

	public void withdraw(double amount, String category) {
		if (!userAccount.containsKey(user)) {
			System.out.println("User account not found.");
			return;
		}

		if (!userBudgets.containsKey(category)) {
			System.out.println("Invalid category.");
			return;
		}

		double currentBalance = userAccount.get(user);
		if (userAccount.get(user) < amount) {
			System.out.println("Insufficient Balance.");
			return;
		}
		userAccount.put(user, currentBalance - amount);
		userExpenses.put(category, userExpenses.getOrDefault(category, 0.0) + amount);
		System.out.println("Amount Withdrawn Successfully.");
	}

	public void withdraw() {
	}

	public void deposit() {
	}
}

class SavingGoal implements Tracker {
	String name;
	String GoalName;
	double GoalAmount;
	String Date;
	double currentBalance;
	String id;
	HashMap<String, SavingGoal> userGoals = new HashMap<>();
	HashMap<String, HashMap<String, SavingGoal>> AllSavingGoals = new HashMap<>();

	public SavingGoal() {

	}

	public SavingGoal(String GoalName, double GoalAmount, String Date) {
		this.GoalName = GoalName;
		this.GoalAmount = GoalAmount;
		this.currentBalance = 0;
		this.Date = Date;
	}

	public String getSavingGoalName() {
		return GoalName;
	}

	public double getSavingGoalAmount() {
		return GoalAmount;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public String getDate() {
		return Date;
	}

	public SavingGoal getGoalFromName(String GoalName) {
		return userGoals.get(GoalName);
	}

	public void createSavinGoal(String GoalName, double GoalAmount, String Date) {
		SavingGoal sg = new SavingGoal(GoalName, GoalAmount, Date);
		userGoals.put(GoalName, sg);
		AllSavingGoals.put(id, userGoals);
	}

	public void deleteSavingGoal(String GoalName) {
		if (userGoals.containsKey(GoalName)) {
			userGoals.remove(GoalName);
			AllSavingGoals.put(id, userGoals);
		}
	}

	public void deposit(String GoalName, double amount) {
		if (userGoals.containsKey(GoalName)) {
			userGoals.get(GoalName).currentBalance += amount;
			AllSavingGoals.put(id, userGoals);
		}
	}

	public void withdraw(String GoalName, double amount) {
		if (userGoals.containsKey(GoalName)) {
			userGoals.get(GoalName).currentBalance -= amount;
			AllSavingGoals.put(id, userGoals);
		}

	}

	public void getBalance(String GoalName) {
		if (userGoals.containsKey(GoalName)) {
			System.out.println(
					"Current Balance in Goal Name: " + GoalName + ": " + userGoals.get(GoalName).currentBalance);
		}
	}

	public void getAnalysis() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Assuming the date is in "yyyy-MM-dd"
																					// format
		for (String key : userGoals.keySet()) {
			SavingGoal goal = userGoals.get(key);
			LocalDate goalDate = LocalDate.parse(goal.getDate(), formatter); // Parse the goal date
			LocalDate today = LocalDate.now(); // Get today's date
			int daysLeft = today.until(goalDate).getDays(); // Calculate days left

			System.out.println("Goal Name: " + key +
					" Goal Amount: " + goal.GoalAmount +
					" Current Balance: " + goal.currentBalance +
					" No of Days Left: " + (daysLeft >= 0 ? daysLeft : "Goal date has passed"));
		}
	}

	public void getAnalysis(String GoalName) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Assuming the date is in "yyyy-MM-dd"
																					// format
		SavingGoal goal = userGoals.get(GoalName);
		LocalDate goalDate = LocalDate.parse(goal.getDate(), formatter); // Parse the goal date
		LocalDate today = LocalDate.now(); // Get today's date
		int daysLeft = today.until(goalDate).getDays(); // Calculate days left

		System.out.println("Goal Name: " + GoalName +
				" Goal Amount: " + goal.GoalAmount +
				" Current Balance: " + goal.currentBalance +
				" No of Days Left: " + (daysLeft >= 0 ? daysLeft : "Goal date has passed"));
	}

	public double getBalance() {
		for (String key : userGoals.keySet()) {
			SavingGoal goal = userGoals.get(key);
			System.out.println("Goal Name: " + key +
					" Goal Amount: " + goal.GoalAmount +
					" Current Balance: " + goal.currentBalance +
					" Goal Date: " + goal.Date);
		}
		return 0.0;
	}

	public void withdraw() {
	}

	public void deposit() {
	}
}

class SavingChallenge implements Tracker {
	String name;
	String ChallengeName;
	double ChallengeAmount;
	String Date;
	double currentBalance;
	String id;
	HashMap<String, SavingGoal> userChallenges = new HashMap<>();
	HashMap<String, HashMap<String, SavingGoal>> AllChallengeGoals = new HashMap<>();

	public SavingChallenge() {

	}

	public SavingChallenge(String GoalName, double GoalAmount, String Date) {
		this.ChallengeName = GoalName;
		this.ChallengeAmount = GoalAmount;
		this.currentBalance = 0;
		this.Date = Date;
	}

	public String getSavingGoalName() {
		return ChallengeName;
	}

	public double getSavingGoalAmount() {
		return ChallengeAmount;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public String getDate() {
		return Date;
	}

	public double getBalance() {
		for (String key : userChallenges.keySet()) {
			SavingGoal goal = userChallenges.get(key);
			System.out.println("Goal Name: " + key +
					" Goal Amount: " + goal.GoalAmount +
					" Current Balance: " + goal.currentBalance +
					" Goal Date: " + goal.Date);
		}
		return 0.0;
	}

	public double getChallengeBalance(String ChallengeName) {
		if (userChallenges.containsKey(ChallengeName)) {
			System.out.println("Current Balance in Goal Name: " + ChallengeName + ": "
					+ userChallenges.get(ChallengeName).currentBalance);
		}
		return 0.0;
	}

	public void getAnalysis() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Assuming the date is in "yyyy-MM-dd"
																					// format
		for (String key : userChallenges.keySet()) {
			SavingGoal goal = userChallenges.get(key);
			LocalDate goalDate = LocalDate.parse(goal.getDate(), formatter); // Parse the goal date
			LocalDate today = LocalDate.now(); // Get today's date
			int daysLeft = today.until(goalDate).getDays(); // Calculate days left

			System.out.println("Goal Name: " + key +
					" Goal Amount: " + goal.GoalAmount +
					" Current Balance: " + goal.currentBalance +
					" No of Days Left: " + (daysLeft >= 0 ? daysLeft : "Goal date has passed"));
		}
	}

	public void getAnalysis(String ChallengeName) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Assuming the date is in "yyyy-MM-dd"
																					// format
		SavingGoal goal = userChallenges.get(ChallengeName);
		LocalDate goalDate = LocalDate.parse(goal.getDate(), formatter); // Parse the goal date
		LocalDate today = LocalDate.now(); // Get today's date
		int daysLeft = today.until(goalDate).getDays(); // Calculate days left

		System.out.println("Goal Name: " + ChallengeName +
				" Goal Amount: " + goal.GoalAmount +
				" Current Balance: " + goal.currentBalance +
				" No of Days Left: " + (daysLeft >= 0 ? daysLeft : "Goal date has passed"));
	}

	public void deposit(String ChallengeName, double amount) {
		if (userChallenges.containsKey(ChallengeName)) {
			userChallenges.get(ChallengeName).currentBalance += amount;
			AllChallengeGoals.put(id, userChallenges);
		}
	}

	public void withdraw(String ChallengeName, double amount) {
		if (userChallenges.containsKey(ChallengeName)) {
			userChallenges.get(ChallengeName).currentBalance -= amount;
			AllChallengeGoals.put(id, userChallenges);
		}
	}

	public void createSavinChallenge(String ChallengeName, double ChallengeAmount, String Date) {
		SavingGoal sg = new SavingGoal(ChallengeName, ChallengeAmount, Date);
		userChallenges.put(ChallengeName, sg);
		AllChallengeGoals.put(id, userChallenges);
	}

	public void deleteSavingChallenge(String ChallengeName) {
		if (userChallenges.containsKey(ChallengeName)) {
			userChallenges.remove(ChallengeName);
			AllChallengeGoals.put(id, userChallenges);
		}
	}

	public void getBalance(String ChallengeName) {
		if (userChallenges.containsKey(ChallengeName)) {
			System.out.println("Current Balance in Goal Name: " + ChallengeName + ": "
					+ userChallenges.get(ChallengeName).currentBalance);
		}
	}

	public void withdraw() {
	}

	public void deposit() {
	}
}

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static AccountManager am = new AccountManager();

	public static void RegisterOperations() {
		System.out.println("Enter Name: ");
		String name = sc.next();
		System.out.println("Enter ID: ");
		String id = sc.next();
		System.out.println("Enter Password: ");
		String password = sc.next();
		System.out.println("Enter Email: ");
		String email = sc.next();
		System.out.println("Enter Address: ");
		String address = sc.next();
		System.out.println("Enter Contact: ");
		Long contact = sc.nextLong();
		am.Register(name, id, password, email, address, contact);
		System.out.println("Registered Successfully as " + name);
	}

	public static void LoginOperations() {
		System.out.println("Enter ID: ");
		String id = sc.next();
		System.out.println("Enter Password: ");
		String password = sc.next();
		am.login(id, password);
		System.out.println("Logged In Successfully as:" + am.loggedInUsers);
		System.out.println("1. Account");
		System.out.println("2. Budget");
		System.out.println("3. Saving Goal");
		System.out.println("4. Saving Challenge");
		System.out.println("5. Exit");
	}

	public static void AccountOperations() {

		User loggedInUser = am.loggedInUsers.iterator().next();
		Account acc = new Account(loggedInUser);
		while (true) {
			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. Get Balance");
			System.out.println("4. Get Analysis");
			System.out.println("5. Exit");
			int c = Main.sc.nextInt();
			switch (c) {
				case 1:
					System.out.println("Enter the amount to deposit: ");
					double amount = sc.nextDouble();
					acc.deposit(amount);
					break;
				case 2:
					System.out.println("Enter the amount to withdraw: ");
					double amount1 = sc.nextDouble();
					System.out.println("Enter the category: ");
					String catogory = sc.next();
					acc.withdraw(amount1, catogory);
					break;
				case 3:
					acc.getBalance();
					break;
				case 4:
					acc.getAnalysis();
					break;
				case 5:
					return;
				default:
					break;
			}

		}
		// }
	}

	public static void BudgetOperations() {
		Budget budget = new Budget();
		while (true) {
			System.out.println("1. Create Budget");
			System.out.println("2. Set Budget");
			System.out.println("3. Get Budget");
			System.out.println("4. Get Analysis");
			System.out.println("5. Exit");
			int c = sc.nextInt();
			switch (c) {
				case 1:
					System.out.println("Enter the category: ");
					String category = sc.next();
					System.out.println("Enter the amount: ");
					double amount = sc.nextDouble();
					budget.createBudget(category, amount);
					break;
				case 2:
					System.out.println("Enter the category: ");
					String category1 = sc.next();
					System.out.println("Enter the amount: ");
					double amount1 = sc.nextDouble();
					budget.setBudget(category1, amount1);
					break;
				case 3:
					System.out.println("Enter the category: ");
					String category2 = sc.next();
					budget.getBudget(category2);
					break;
				case 4:
					budget.getAnalysis();
					break;
				case 5:
					return;
				default:
					break;
			}
		}
	}

	public static void SavingGoalOperations() {
		SavingGoal sg = new SavingGoal();
		while (true) {
			System.out.println("1. Create Saving Goal");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Get Balance");
			System.out.println("5. Get Analysis");
			System.out.println("6. Exit");
			int c = sc.nextInt();
			switch (c) {
				case 1:
					System.out.println("Enter the Goal Name: ");
					String GoalName = sc.next();
					System.out.println("Enter the Goal Amount: ");
					double GoalAmount = sc.nextDouble();
					System.out.println("Enter the Date: ");
					String Date = sc.next();
					sg.createSavinGoal(GoalName, GoalAmount, Date);
					break;
				case 2:
					System.out.println("Enter the Goal Name: ");
					String GoalName1 = sc.next();
					System.out.println("Enter the amount to deposit: ");
					double amount = sc.nextDouble();
					sg.deposit(GoalName1, amount);
					break;
				case 3:
					System.out.println("Enter the Goal Name: ");
					String GoalName2 = sc.next();
					System.out.println("Enter the amount to withdraw: ");
					double amount1 = sc.nextDouble();
					sg.withdraw(GoalName2, amount1);
					break;
				case 4:
					System.out.println("Enter the Goal Name: ");
					String GoalName3 = sc.next();
					sg.getBalance(GoalName3);
					break;
				case 5:
					sg.getAnalysis();
					break;
				case 6:
					return;
				default:
					break;
			}
		}
	}

	public static void SavingChallengeOperations() {
		// Scanner sc = new Scanner(System.in);
		SavingChallenge savingChallenge = new SavingChallenge();
		while (true) {
			System.out.println("1. Create Saving Challenge");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Get Balance");
			System.out.println("5. Get Analysis");
			System.out.println("6. Exit");
			int c = sc.nextInt();
			switch (c) {
				case 1:
					System.out.println("Enter the Challenge Name: ");
					String ChallengeName = sc.next();
					System.out.println("Enter the Challenge Amount: ");
					double ChallengeAmount = sc.nextDouble();
					System.out.println("Enter the Date: ");
					String Date = sc.next();
					savingChallenge.createSavinChallenge(ChallengeName, ChallengeAmount, Date);
					break;
				case 2:
					System.out.println("Enter the Challenge Name: ");
					String ChallengeName1 = sc.next();
					System.out.println("Enter the amount to deposit: ");
					double amount = sc.nextDouble();
					savingChallenge.deposit(ChallengeName1, amount);
					break;
				case 3:
					System.out.println("Enter the Challenge Name: ");
					String ChallengeName2 = sc.next();
					System.out.println("Enter the amount to withdraw: ");
					double amount1 = sc.nextDouble();
					savingChallenge.withdraw(ChallengeName2, amount1);
					break;
				case 4:
					System.out.println("Enter the Challenge Name: ");
					String ChallengeName3 = sc.next();
					savingChallenge.getBalance(ChallengeName3);
					break;
				case 5:
					savingChallenge.getAnalysis();
					break;
				case 6:
					return;
				default:
					break;
			}
		}
	}

	public static void main(String[] args) {
		AccountManager am = new AccountManager();
		while (true) {
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			int choice = sc.nextInt();
			switch (choice) {
				case 1:
					RegisterOperations();
					break;
				case 2:
					LoginOperations();
					int c = sc.nextInt();
					switch (c) {
						case 1:
							AccountOperations();
							// LoginOperations();
							// break;
						case 2:
							BudgetOperations();
							// LoginOperations();
							// break;
						case 3:
							SavingGoalOperations();
							// LoginOperations();
							// break;
						case 4:
							SavingChallengeOperations();
							// LoginOperations();
							// break;
						case 5:
							break;
						default:
							System.out.println("Invalid Choice");
							// break;
					}
				case 3:
					System.exit(0);
					break;
			}
		}
	}

}
