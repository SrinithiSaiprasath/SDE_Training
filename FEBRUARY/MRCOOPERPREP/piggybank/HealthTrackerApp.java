
import java.util.*;
import java.time.LocalDate;

// User Class
class User {
    private String userId;
    private String name;
    private int age;
    private String gender;
    private double height;
    private double weight;

    public User(String name, int age, String gender, double height, double weight) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public double calculateBMI() {
        return weight / (height * height);
    }

    public void updateProfile(String name, int age, String gender, double height, double weight) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
}

// HealthRecord Class
class HealthRecord {
    private String recordId;
    private LocalDate date;
    private double weight;
    private String bloodPressure;
    private int heartRate;

    public HealthRecord(double weight, String bloodPressure, int heartRate) {
        this.recordId = UUID.randomUUID().toString();
        this.date = LocalDate.now();
        this.weight = weight;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
    }

    public LocalDate getDate() { return date; }
    public double getWeight() { return weight; }
    public String getBloodPressure() { return bloodPressure; }
    public int getHeartRate() { return heartRate; }
}

// ActivityTracker Class
class ActivityTracker {
    private int steps;
    private double caloriesBurned;
    private String workoutType;
    private int workoutDuration;

    public ActivityTracker(int steps, double caloriesBurned, String workoutType, int workoutDuration) {
        this.steps = steps;
        this.caloriesBurned = caloriesBurned;
        this.workoutType = workoutType;
        this.workoutDuration = workoutDuration;
    }

    public String getWeeklySummary() {
        return "Weekly Activity: " + steps + " steps, " + caloriesBurned + " calories burned.";
    }
}

// HealthAnalysis Class
class HealthAnalysis {
    private String userId;
    private List<HealthRecord> healthRecords;

    public HealthAnalysis(String userId, List<HealthRecord> healthRecords) {
        this.userId = userId;
        this.healthRecords = healthRecords;
    }

    public String analyzeTrends() {
        if (healthRecords.isEmpty()) return "No health records found.";
        double avgWeight = healthRecords.stream().mapToDouble(HealthRecord::getWeight).average().orElse(0);
        return "Average weight trend: " + avgWeight;
    }
}

// HealthTrackerApp Class (Main Controller)
public class HealthTrackerApp {
    private List<User> users;
    private List<ActivityTracker> activities;
    private List<HealthRecord> healthRecords;

    public HealthTrackerApp() {
        users = new ArrayList<>();
        activities = new ArrayList<>();
        healthRecords = new ArrayList<>();
    }

    public void registerUser(String name, int age, String gender, double height, double weight) {
        users.add(new User(name, age, gender, height, weight));
    }

    public void trackHealth(double weight, String bloodPressure, int heartRate) {
        healthRecords.add(new HealthRecord(weight, bloodPressure, heartRate));
    }

    public void generateReport() {
        if (healthRecords.isEmpty()) {
            System.out.println("No health records available.");
            return;
        }
        HealthAnalysis analysis = new HealthAnalysis("User", healthRecords);
        System.out.println(analysis.analyzeTrends());
    }

    public static void main(String[] args) {
        HealthTrackerApp app = new HealthTrackerApp();
        app.registerUser("Alice", 25, "Female", 1.65, 60.0);
        app.trackHealth(61.0, "120/80", 72);
        app.trackHealth(62.5, "118/76", 75);
        app.generateReport();


    }
}
