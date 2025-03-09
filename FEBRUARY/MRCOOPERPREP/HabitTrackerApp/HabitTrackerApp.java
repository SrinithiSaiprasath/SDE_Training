import java.util.*;

// Abstract class for Habit
abstract class Habit {
    private String name;
    private int goal;
    private int progress;
    private String reminderTime;
    
    public Habit(String name, int goal, String reminderTime) {
        this.name = name;
        this.goal = goal;
        this.progress = 0;
        this.reminderTime = reminderTime;
    }

    public String getName() { return name; }
    public int getGoal() { return goal; }
    public int getProgress() { return progress; }
    public String getReminderTime() { return reminderTime; }
    
    public abstract void completeHabit();
}

class DailyHabit extends Habit {
    public DailyHabit(String name, int goal, String reminderTime) {
        super(name, goal, reminderTime);
    }

    @Override
    public void completeHabit() {
        if (getProgress() < getGoal()) {
            System.out.println(getName() + " progress updated.");
        } else {
            System.out.println(getName() + " goal already reached!");
        }
    }
}

class WeeklyHabit extends Habit {
    public WeeklyHabit(String name, int goal, String reminderTime) {
        super(name, goal, reminderTime);
    }

    @Override
    public void completeHabit() {
        if (getProgress() < getGoal()) {
            System.out.println(getName() + " progress updated.");
        } else {
            System.out.println(getName() + " goal already reached!");
        }
    }
}

class Message {
    private String sender;
    private String content;
    
    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }
    
    public String getSender() { return sender; }
    public String getContent() { return content; }
}
class Journal{
    private List<String> journal = new ArrayList<>();
    
}

class User {
    private String username;
    private List<Habit> habits;
    private List<Message> messages;

    public User(String username) {
        this.username = username;
        this.habits = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public String getUsername() { return username; }
    
    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void completeHabit(String name) {
        for (Habit habit : habits) {
            if (habit.getName().equalsIgnoreCase(name)) {
                habit.completeHabit();
                return;
            }
        }
        System.out.println("Habit not found!");
    }
    
    public void sendMessage(User receiver, String content) {
        receiver.receiveMessage(new Message(this.username, content));
    }
    
    private void receiveMessage(Message message) {
        messages.add(message);
    }
    
    public void viewMessages() {
        if (messages.isEmpty()) {
            System.out.println("No new messages.");
        } else {
            for (Message message : messages) {
                System.out.println("From " + message.getSender() + ": " + message.getContent());
            }
            messages.clear();
        }
    }
     
    public void addJournalEntry(String entry) {
        journal.addEntry(entry);
    }
    
    public void viewJournalEntries() {
        journal.viewEntries();
    }
}

class UserManager {
    private static Map<String, User> users = new HashMap<>();
    
    public static User registerUser(String username) {
        if (users.containsKey(username)) {
            System.out.println("Username already taken!");
            return null;
        }
        User user = new User(username);
        users.put(username, user);
        System.out.println("User registered successfully: " + username);
        return user;
    }
    
    public static User loginUser(String username) {
        if (users.containsKey(username)) {
            System.out.println("Login successful: " + username);
            return users.get(username);
        }
        System.out.println("User not found!");
        return null;
    }
}

public class HabitTrackerApp {
    public static void main(String[] args) {
        User alice = UserManager.registerUser("Alice");
        User bob = UserManager.registerUser("Bob");

        alice.addHabit(new DailyHabit("Exercise", 5, "07:00 AM"));
        alice.addHabit(new WeeklyHabit("Read a Book", 3, "08:00 PM"));

        bob.addHabit(new WeeklyHabit("Drink Water", 10, "09:00 AM"));
        bob.addHabit(new DailyHabit("Meditate", 4, "06:30 AM"));

        alice.completeHabit("Exercise");
        alice.completeHabit("Exercise");
        alice.completeHabit("Read a Book");

        bob.completeHabit("Drink Water");
        bob.completeHabit("Meditate");

        alice.sendMessage(bob, "Keep up the good work!");
        bob.sendMessage(alice, "Thanks! You too!");

        alice.addJournalEntry("Had a great workout today!");
        bob.addJournalEntry("Feeling refreshed after meditation.");

        System.out.println("\n--- Alice's Data ---");
        // alice.showHabits();
        // alice.showJournal();
        alice.viewMessages();

        System.out.println("\n--- Bob's Data ---");
        // bob.showHabits();
        // bob.showJournal();
        bob.viewMessages();
    }
}
