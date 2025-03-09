import java.time.LocalDate;
import java.util.*;

class User {
    private static int userCount = 0;
    private String id;
    private String name;
    private String email;
    private String password;
    private int badges = 0;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = "User_" + (userCount++);
        System.out.println("User created: " + name + " (ID: " + this.id + ")");
    }

    public String getId() { return id; }
    public String getPassword() { return password; }
    public void addBadge() { badges++; }
    public int getBadges() { return badges; }
}

class UserManager {
    private HashMap<String, User> users = new HashMap<>();
    private HashMap<String, Integer> activeList = new HashMap<>();

    public User register(String name, String email, String password) {
        User user = new User(name, email, password);
        users.put(name, user);
        return user;
    }

    public boolean login(String name, String password) {
        if (users.containsKey(name) && users.get(name).getPassword().equals(password)) {
            activeList.put(name, activeList.getOrDefault(name, 0) + 1);
            return true;
        }
        return false;
    }
    
    public boolean logout(String name) {
        if (activeList.containsKey(name)) {
            activeList.remove(name);
            System.out.println("User Logged Out");
            return true;
        }
        System.out.println("User Not Found");
        return false;
    }
    
    public void listUsers() {
        System.out.println("Registered Users:");
        for (String name : users.keySet()) {
            System.out.println(name + " (ID: " + users.get(name).getId() + ", Badges: " + users.get(name).getBadges() + ")");
        }
    }

    public User getUser(String name) {
        return users.get(name);
    }
}
class Habit {
    private String habitName;
    private double progress;
    private LocalDate date;

    public Habit(String habitName, double progress) {
        this.habitName = habitName;
        this.progress = progress;
        this.date = LocalDate.now();
    }

    public String getHabitName() { return habitName; }
    public double getProgress() { return progress; }
    public LocalDate getDate() { return date; }
    public void addProgress(double amount) { progress += amount; }
}

class HabitTracker {
    // Map<UserId, Map<Date, Map<HabitName, DailyProgress>>>
    private Map<String, Map<LocalDate, Map<String, Habit>>> userHabitProgress;
    private Map<String, Map<String, Double>> userHabitGoals;

    public HabitTracker() {
        userHabitProgress = new HashMap<>();
        userHabitGoals = new HashMap<>();
    }

    public void addHabit(String userId, String habitName, double goal) {
        userHabitGoals.computeIfAbsent(userId, k -> new HashMap<>()).put(habitName, goal);
        System.out.println("Habit '" + habitName + "' added for user " + userId + " with goal: " + goal);
    }

    public void updateProgress(String userId, String habitName, double progress) {
        LocalDate today = LocalDate.now();
        
        if (!userHabitGoals.containsKey(userId) || !userHabitGoals.get(userId).containsKey(habitName)) {
            System.out.println("Habit not found for user");
            return;
        }

        Map<LocalDate, Map<String, Habit>> userProgress = 
            userHabitProgress.computeIfAbsent(userId, k -> new HashMap<>());
        Map<String, Habit> dailyProgress = 
            userProgress.computeIfAbsent(today, k -> new HashMap<>());

        Habit habitProgress = dailyProgress.get(habitName);
        if (habitProgress == null) {
            habitProgress = new Habit(habitName, progress);
            dailyProgress.put(habitName, habitProgress);
        } else {
            habitProgress.addProgress(progress);
        }

        double goal = userHabitGoals.get(userId).get(habitName);
        System.out.printf("Progress updated for %s: %.1f/%.1f (%.1f%%)\n", 
            habitName, habitProgress.getProgress(), goal, 
            (habitProgress.getProgress() / goal) * 100);
    }

    public void viewMyHabits(String userId) {
        System.out.println("\nMy Habits:");
        System.out.println("----------------------------------------");
        Map<String, Double> habits = userHabitGoals.get(userId);
        
        if (habits == null || habits.isEmpty()) {
            System.out.println("No habits found.");
            return;
        }
    
        for (Map.Entry<String, Double> habit : habits.entrySet()) {
            System.out.println("Habit: " + habit.getKey());
            System.out.println("Daily Goal: " + habit.getValue());
            System.out.println("----------------------------------------");
        }
    }

    public void getDailyReport(String userId) {
        LocalDate today = LocalDate.now();
        System.out.println("\nDaily Habit Report for " + userId + " - " + today);
        System.out.println("----------------------------------------");

        Map<String, Double> goals = userHabitGoals.get(userId);
        if (goals == null) {
            System.out.println("No habits found for user");
            return;
        }

        Map<LocalDate, Map<String, Habit>> userProgress = userHabitProgress.get(userId);
        Map<String, Habit> todayProgress = userProgress != null ? userProgress.get(today) : null;

        for (Map.Entry<String, Double> habitGoal : goals.entrySet()) {
            String habitName = habitGoal.getKey();
            double goal = habitGoal.getValue();
            Habit progress = todayProgress != null ? todayProgress.get(habitName) : null;
            double currentProgress = progress != null ? progress.getProgress() : 0.0;

            System.out.printf("Habit: %s\n", habitName);
            System.out.printf("Progress: %.1f/%.1f (%.1f%%)\n", 
                currentProgress, goal, (currentProgress / goal) * 100);
            System.out.println("----------------------------------------");
        }
    }

    public Map<String, Double> getHabitGoals(String userId) {
        //get all the habits and goals taken by user

        return userHabitGoals.getOrDefault(userId, new HashMap<>());
        
    }
}
class Journal {
    private Map<String, Map<LocalDate, List<JournalEntry>>> userJournals;

    public Journal() {
        userJournals = new HashMap<>();
    }

    static class JournalEntry {
        private String title;
        private String content;
        private LocalDate date;

        public JournalEntry(String title, String content) {
            this.title = title;
            this.content = content;
            this.date = LocalDate.now();
        }
    }

    public void addJournalEntry(String userId, String title, String content) {
        userJournals.computeIfAbsent(userId, k -> new HashMap<>())
                   .computeIfAbsent(LocalDate.now(), k -> new ArrayList<>())
                   .add(new JournalEntry(title, content));
        System.out.println("Journal entry added successfully!");
    }

    public void viewJournals(String userId) {
        if (!userJournals.containsKey(userId)) {
            System.out.println("No journals found for user.");
            return;
        }

        System.out.println("\nMy Journals:");
        System.out.println("----------------------------------------");
        Map<LocalDate, List<JournalEntry>> userEntries = userJournals.get(userId);
        
        // Sort dates in reverse order (newest first)
        List<LocalDate> sortedDates = new ArrayList<>(userEntries.keySet());
        Collections.sort(sortedDates, Collections.reverseOrder());

        for (LocalDate date : sortedDates) {
            System.out.println("\nDate: " + date);
            System.out.println("----------------------------------------");
            List<JournalEntry> entries = userEntries.get(date);
            for (JournalEntry entry : entries) {
                System.out.println("Title: " + entry.title);
                System.out.println("Content: " + entry.content);
                System.out.println("----------------------------------------");
            }
        }
    }
}

class Message {
    private String from;
    private String to;
    private String content;
    private LocalDate date;

    public Message(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = LocalDate.now();
    }

    public String getFrom() {
        return from;
    }
    public String getTo(){
        return to;
    }
    public String getContent(){
        return content;
    }
    public LocalDate getDate(){
        return date;
    }
}

class SocialNetwork {
    private Map<String, Set<String>> friendsList = new HashMap<>();
    private Map<String, List<Message>> userMessages = new HashMap<>();

    public void addFriend(String userId, String friendId) {
        friendsList.computeIfAbsent(userId, k -> new HashSet<>()).add(friendId);
        friendsList.computeIfAbsent(friendId, k -> new HashSet<>()).add(userId);
        System.out.println(userId + " and " + friendId + " are now friends!");
    }

    public void sendMessage(String from, String to, String content) {
        if (friendsList.containsKey(from) && friendsList.get(from).contains(to)) {
            Message message = new Message(from, to, content);
            userMessages.computeIfAbsent(to, k -> new ArrayList<>()).add(message);
            System.out.println("Message sent to " + to);
        } else {
            System.out.println("You can only message friends. Add " + to + " as a friend first.");
        }
    }

    public void viewFriends(String userId) {
        System.out.println("\nFriends List for " + userId + ":");
        System.out.println("----------------------------------------");
        Set<String> friends = friendsList.getOrDefault(userId, new HashSet<>());
        if (friends.isEmpty()) {
            System.out.println("No friends added yet.");
        } else {
            friends.forEach(friend -> System.out.println("- " + friend));
        }
    }

    public void viewMessages(String userId) {
        System.out.println("\nMessages for " + userId + ":");
        System.out.println("----------------------------------------");
        List<Message> messages = userMessages.getOrDefault(userId, new ArrayList<>());
        if (messages.isEmpty()) {
            System.out.println("No messages.");
        } else {
            for (Message msg : messages) {
                System.out.println("From: " + msg.getFrom());
                System.out.println("To: " + msg.getTo());
                System.out.println("Date: " + msg.getDate());
                System.out.println("Message: " + msg.getContent());
                System.out.println("----------------------------------------");
            }
        }
    }
}
class Challenge {
    private String name;
    private double goal;
    private HashMap<String, Double> activeChallenges = new HashMap<>();
    private HashMap<String, Integer> medals = new HashMap<>();

    public String getName() {
        return name;
    }
    public Challenge(String name, double goal) {
        this.name = name;
        this.goal = goal;
        System.out.println("Challenge Name: " + name + " Goal: " + goal);
    }

    public void takeChallenge(String userId) {
        activeChallenges.put(userId, 0.0);
        System.out.println("Challenge taken by " + userId);
    }
    
    public void updateChallenge(String userId, double progress, UserManager userManager) {
        if (activeChallenges.containsKey(userId)) {
            double newProgress = activeChallenges.get(userId) + progress;
            activeChallenges.put(userId, newProgress);
            System.out.println("Challenge for " + userId + " updated by " + progress);
            if (newProgress >= goal) {
                System.out.println("Challenge completed by " + userId + "! Medal awarded.");
                medals.put(userId, medals.getOrDefault(userId, 0) + 1);
                activeChallenges.remove(userId);
                User user = userManager.getUser(userId);
                if (user != null) {
                    user.addBadge();
                    System.out.println("Badge awarded to " + userId);
                }
            }
        }
    }

    public int getMedals(String userId) {
        return medals.getOrDefault(userId, 0);
    }
    
    public Set<String> getOngoingChallenges() {
        return activeChallenges.keySet();
    }
}

public class HelpMePlease {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        HabitTracker habitTracker = new HabitTracker();
        Journal journal = new Journal();

        List<Challenge> predefinedChallenges = Arrays.asList(
            new Challenge("Exercise", 100),
            new Challenge("Read Books", 50),
            new Challenge("Meditate", 30),
            new Challenge("Drink 5L Water", 5),
            new Challenge("Limit Screen Time to 4 Hours", 4)
        );
        HashMap<String, Challenge> userChallenges = new HashMap<>();
        
        while (true) {
            System.out.println(
                "1. Register\n2. Login\n3. Logout\n4. List Users\n5. View Challenges\n" +
                "6. Take Challenge\n7. Update Challenge\n8. View Medals\n9. View Ongoing Challenges\n" +
                "10. Exit\n11. Add Daily Habit\n12. Update Habit Progress\n13. View Daily Report\n" +
                "14. View My Habits\n15. Add Journal Entry\n16. View My Journals");
            int choice = scanner.nextInt();
            // scanner.nextLine(); // consume the newline
            System.out.println("\n");
            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    System.out.print("Enter Password: ");
                    String password = scanner.next();
                    userManager.register(name, email, password);
                    break;
                case 2:
                    System.out.print("Enter Name: ");
                    String loginName = scanner.next();
                    System.out.print("Enter Password: ");
                    String loginPassword = scanner.next();
                    if (userManager.login(loginName, loginPassword)) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Name to Logout: ");
                    String logoutName = scanner.next();
                    userManager.logout(logoutName);
                    break;
                case 4:
                    userManager.listUsers();
                    break;
                case 5:
                    System.out.println("Available Challenges:");
                    for (int i = 0; i < predefinedChallenges.size(); i++) {
                        System.out.println((i + 1) + ". " + predefinedChallenges.get(i).getName()) ; 
                    }
                    break;
                case 6:
                    System.out.print("Enter User Name: ");
                    String userId = scanner.next();
                    System.out.print("Choose Challenge (Enter number): ");
                    int challengeIndex = scanner.nextInt() - 1;
                    if (challengeIndex >= 0 && challengeIndex < predefinedChallenges.size()) {
                        userChallenges.put(userId, predefinedChallenges.get(challengeIndex));
                        predefinedChallenges.get(challengeIndex).takeChallenge(userId);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 7:
                    System.out.print("Enter User Name: ");
                    String updateUserId = scanner.next();
                    System.out.print("Enter Challenge Progress: ");
                    double progress = scanner.nextDouble();
                    if (userChallenges.containsKey(updateUserId)) {
                        userChallenges.get(updateUserId).updateChallenge(updateUserId, progress, userManager);
                    } else {
                        System.out.println("User has not taken a challenge.");
                    }
                    break;
                case 8:
                    System.out.print("Enter User Name: ");
                    String medalUser = scanner.next();
                    System.out.println("Medals: " + userChallenges.get(medalUser).getMedals(medalUser));
                    break;
                case 9:
                    System.out.println("Ongoing Challenges: " + userChallenges.keySet());
                    break;
                case 10:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                    case 11:
                    System.out.print("Enter User Name: ");
                    String habitsUserId = scanner.next();
                    System.out.print("Enter Habit Name: ");
                    String habitName = scanner.next(); 
                    System.out.print("Enter Daily Goal: ");
                    double habitGoal = scanner.nextDouble();
                    habitTracker.addHabit(habitsUserId, habitName, habitGoal);
                    break;
                
                case 12:
                    System.out.print("Enter User Name: ");
                    String updateUserId1 = scanner.next();
                    System.out.print("Enter Habit Name: ");
                    String updateHabitName = scanner.next();
                    System.out.print("Enter Progress: ");
                    double habitProgress = scanner.nextDouble();
                    habitTracker.updateProgress(updateUserId1, updateHabitName, habitProgress);
                    break;
                
                case 13:
                    System.out.print("Enter User Name: ");
                    String reportUserId = scanner.next();
                    habitTracker.getDailyReport(reportUserId);
                    break;
                case 14:
                    System.out.print("Enter User Name: ");
                    String viewHabitsUserId = scanner.next();
                    habitTracker.viewMyHabits(viewHabitsUserId);
                    break;
                case 15:
                    System.out.print("Enter User Name: ");
                    String journalUserId = scanner.next();
                    System.out.print("Enter Journal Title: ");
                    String title = scanner.next();
                    System.out.print("Enter Journal Content: ");
                    String content = scanner.next();
                    journal.addJournalEntry(journalUserId, title, content);
                    break;

                case 16:
                    System.out.print("Enter User Name: ");
                    String viewJournalUserId = scanner.next();
                    journal.viewJournals(viewJournalUserId);
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
