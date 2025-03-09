
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class User {

    private String email;
    private String id = "User_";
    private int userCount = 0;
    private String name;
    private String password;

    public User(String name, String email, String password) {
        this.email = email;
        this.name = name;
        this.id = id + (userCount++);
        this.password = password;
        System.out.println("User created for " + name + " as ID " + this.id);
    }
    public String getID(){
        return id;
    }
    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        return this.password = password;
    }

    public User createUser(String name, String email, String password) {
        return new User(name, email, password);
    }
}

class UserManager {

    HashMap<String, String> userList = new HashMap<>();
    HashMap<String ,Integer> active_users =new HashMap<>();
    public User register(String name, String email, String password) {
        userList.put(name, password);
        return new User(name, email, password);
        // return createUser(name,email,password);
    }

    public boolean login(String name, String password) {
        if (userList.containsKey(name) && active_users.containsKey(name)) {
            active_users.put(name , active_users.getOrDefault(name , 0 ) + 1);
            if (userList.get(name).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean logout(String name) {
        if (userList.containsKey(name) && active_users.containsKey(name)) {
            active_users.remove(name);
            userList.remove(name);
            return true;
        }
        return false;
    }
}

class Journal {

    public String id;
    public String title;
    public String content;
    public String date;
    HashMap<String, HashMap<String, String>> userJournals = new HashMap<>();

    public Journal(String id) {
        this.id = id;
        System.out.println("Journal Created for user " + id);
        // this.content = content ;
    }
    // public Journal createJournal(String id ,String title, String content){
    //     return new Journal(id,title, content);
    // }   

    public HashMap<String, String> getContent(String title, String content) {
        HashMap<String, String> journal = new HashMap<>();
        journal.put(title, content);
        return journal;
    }

    public String writeJournal(HashMap<String, String> journal) {
        userJournals.put(LocalDate.now().toString(), journal);
        return "Journal written on: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); 
    }

    public String updateJournal(String id, String title, String content) {
        for (Map.Entry<String, HashMap<String, String>> entry : userJournals.entrySet()) {
            for (Map.Entry<String, String> journal : entry.getValue().entrySet()) {
                if (journal.getKey().equals(title)) {
                    journal.setValue(content);
                }
            }
        }
        return "Journal updated on: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +" ";
    }

    public void readJournals() {
        System.out.println("Read Journals ");
        for (Map.Entry<String, HashMap<String, String>> entry : userJournals.entrySet()) {
            System.out.println("Date : " + entry.getKey());
            for (Map.Entry<String, String> journal : entry.getValue().entrySet()) {
                System.out.println("Title : " + journal.getKey());
                System.out.println("Content : " + journal.getValue());
            }
        }
    }

    public void viewJounalTitles() {
        System.out.println("View Journal Titles ");
        for (Map.Entry<String, HashMap<String, String>> entry : userJournals.entrySet()) {
            for (Map.Entry<String, String> journal : entry.getValue().entrySet()) {
                System.out.println("Title : " + journal.getKey());
            }
        }
    }
}

class Habit {
    public String id;
    public String name;
    public double goal;
    public String date;
    HashMap<String, Double> userHabits = new HashMap<>();

    public Habit(String id, String name, double goal) {
        this.id = id;
        this.name = name;
        this.goal = goal;
        userHabits.put(name, goal);
        System.out.println("Habit Created for user " + id);
    }
    public Habit addHabit(String name , double goal){
        userHabits.put( name, goal);
        return new Habit(name,goal);
    }

    public void viewHabitNames() {
        System.out.println("View Habit Names");
        for (Map.Entry<String, Double> entry : userHabits.entrySet()) {
            System.out.println("Name : " + entry.getKey());
        }
    }

    public void viewHabitGoals() {
        System.out.println("View Habit Goals");
        for (Map.Entry<String, Double> entry : userHabits.entrySet()) {
            System.out.println("Goal : " + entry.getValue());
        }
    }

    public void AddDailyHabit(String name, double goal) {
        userHabits.put(name, goal);
    }
}

class DailyHabit extends Habit {
    public double progress;
    HashMap<String, Double> dailyProgress = new HashMap<>();
    HashMap<String, Double> EndofDayReport = new HashMap<>();
    public DailyHabit(String id, String name, double goal) {
        super(id, name, goal);
        // Initialize progress to 0 for the habit
        dailyProgress.put(name, 0.0);
    }

    @Override 
    public void AddDailyHabit(String name, double goal) {
        userHabits.put(name, goal);
        // Initialize progress to 0 when adding a new habit
        dailyProgress.put(name, 0.0);
    }

    public void updateDailyHabit(String name, double progress) {
        if (userHabits.containsKey(name)) {
            // Get current progress or default to 0 if null
            double currentProgress = dailyProgress.getOrDefault(name, 0.0);
            dailyProgress.put(name, currentProgress + progress);
            System.out.println("Progress updated for " + name + ": " + dailyProgress.get(name));
        } else {
            System.out.println("Habit not found: " + name);
        }
    }

    public void checkHabitProgress(String name) {
        if (userHabits.containsKey(name)) {
            double currentProgress = dailyProgress.getOrDefault(name, 0.0);
            double goal = userHabits.get(name);
            double progressPercent = (currentProgress / goal) * 100;
            System.out.println("Progress of " + name + " is: " + String.format("%.2f", progressPercent) + "%");
        } else {
            System.out.println("Habit not found: " + name);
        }
    }

    public void readDailyHabits() {
        System.out.println("\nDaily Habits Progress:");
        for (Map.Entry<String, Double> entry : userHabits.entrySet()) {
            String habitName = entry.getKey();
            double goal = entry.getValue();
            double progress = dailyProgress.getOrDefault(habitName, 0.0);
            System.out.println("Habit: " + habitName);
            System.out.println("Progress: " + progress + " / " + goal);
        }
    }

    public HashMap<String, Double> EndofDayReport(){
        System.out.println("End of Day Report");
        for (Map.Entry<String, Double> entry : userHabits.entrySet()) {
            String habitName = entry.getKey();
            double goal = entry.getValue();
            double progress = dailyProgress.getOrDefault(habitName, 0.0);
            System.out.println("Habit: " + habitName);
            System.out.println("Completed: " + (progress/goal) * 100 + "\n");
            EndofDayReport.put(habitName, (progress/goal) * 100);
        }
        return EndofDayReport;
    }

    public void getDailyBestPerformance(){
        double max = 0;
        String habitName = "";
        for (Map.Entry<String, Double> entry : userHabits.entrySet()) {
            String name = entry.getKey();
            double goal = entry.getValue();
            double progress = dailyProgress.getOrDefault(name, 0.0);
            if (progress > max) {
                max = progress;
                habitName = name;
            }
        }
        System.out.println("Best performance of the day: " + habitName + " with " + max + " progress");
    }

}



class WeeklyHabit extends DailyHabit{
    public double progress;
    HashMap<String, HashMap<String , Double >> EndoFWeekReport = new HashMap<>();
    // HashMap<String, Double> EndofWeekReport = new HashMap<>();

    public WeeklyHabit(String id, String name, double goal){
        super(id ,name,goal);
        System.out.println("Weekly Habit Created for user " + id);
    }

    public void updateToday(HashMap<String, Double> EndofDayReport) {
        EndoFWeekReport.put(LocalDate.now().toString(),EndofDayReport);
    }

    public void EndofWeekReport(){
        System.out.println("End of Week Report");
        for (Map.Entry<String, HashMap<String, Double>> entry : EndoFWeekReport.entrySet()) {
            System.out.println("Week : " + entry.getKey());
            for (Map.Entry<String, Double> habit : entry.getValue().entrySet()) {
                System.out.println("Habit : " + habit.getKey());
                System.out.println("Completed : " + habit.getValue() + "%");
            }
        }
    }

}


public class HealthyHabitTracker {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        User user = userManager.register("Rahul", "rahul@gmail.com", "rahul123");
        
        // Check login success
        if (userManager.login("Rahul", "rahul123")) {
            System.out.println("Login successful");
            System.out.println("\n");
            // Create and use DailyHabit instead of Habit
            DailyHabit habit = new DailyHabit("User_0", "Exercise", 30.0);
            WeeklyHabit weeklyHabit = new WeeklyHabit("User_0", "Running", 100.0);

            // Initialize the progress
            habit.AddDailyHabit("Exercise", 30.0);
            
            // View initial state
            habit.viewHabitNames();
            habit.viewHabitGoals();
            System.out.println("\n");

            // Update and check progress
            habit.updateDailyHabit("Exercise", 15.0);
            habit.checkHabitProgress("Exercise");
            habit.readDailyHabits();
            // habit.getDailyBestPerformance();
            System.out.println("\n");

            // Create and manage journal
            Journal journal = new Journal("User_0");
            journal.writeJournal(journal.getContent("My Journal", "This is my first journal"));
            journal.updateJournal("Journal_1", "My Journal", "This is my updated journal");
            journal.readJournals();
            System.out.println("\n");
            journal.viewJounalTitles();
            System.out.println("\n");

            // End of Day Report
            HashMap<String, Double> myreport = habit.EndofDayReport();
            weeklyHabit.updateToday(myreport);
            weeklyHabit.EndofWeekReport();
            weeklyHabit.getDailyBestPerformance();
            
            System.out.println("\n");

            // Create and use WeeklyHabit
            // WeeklyHabit weeklyHabit = new WeeklyHabit("User_0", "Running", 100.0);
            // weeklyHabit.AddDailyHabit("Running", 100.0);

            // weeklyHabit.updateToday("Running", 50.0);
            // weeklyHabit.getBestPerformance();
            // weeklyHabit.EndofWeekReport();
        } 
        else {
            System.out.println("Login failed");
        }
    }
}