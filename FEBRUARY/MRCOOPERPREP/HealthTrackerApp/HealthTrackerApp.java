import java.time.LocalDate;
import java.util.*;

// import MRCOOPERPREP.PiggyBankApp.User;

// User Class
class User {
    private int userId = 1000;
    private String name;
    private static int usercount ;
    private int age;
    private String gender;
    private double height;
    private double weight;
    private List<User> users;
    public User(String name, int age, String gender, double height, double weight) {
        this.userId = userId + usercount; 
        usercount++;
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
    public User getUser(int id) { 
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }
    // public int getUserId(){ return userId ; }
    public double getHeight() { return height; }
    public int getAge() { return age; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
}

// HealthRecord Class
class HealthRecord {
    private String recordId;
    private LocalDate date;
    private double weight;
    private double height;
    private int age;
    private String bloodPressure;
    private int heartRate;

    public HealthRecord(int age ,double weight,double Height, String bloodPressure, int heartRate) {
        this.recordId = UUID.randomUUID().toString();
        this.date = LocalDate.now();
        this.weight = weight;
        this.age =age;
        this.height = Height ; 
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
    }
    // public void analyzeTrends() {
        
    //     int avgHeartRate = 72 ; 
    //     double expectedWeight = 22 * ((this.getHeight()) * (this.getHeight())); // BMI of 22 is considered healthy
    //     int expectedHeartRate = 220 - this.getAge(); // Max heart rate formula
    //     double avgWeight = (100 - this.getHeight()); // Ideal weight is 100 kg
    //     if (avgHeartRate > expectedHeartRate) {
    //         System.out.println("Warning: Average heart rate is higher than expected.");
    //     } else {
    //         System.out.println("Average heart rate is within the normal range.");
    //     }

    //     if (100 > expectedWeight) {
    //          System.out.println("Warning: Average weight is higher than expected.");
    //     } else {
    //         System.out.println("Average weight is within the normal range.");
    //     }
    // }

    public LocalDate getDate() { return date; }
    public double getHeight(){return height ; }
    public double getWeight() { return weight; }
    public int getAge(){return age ; }
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
        this.steps += steps;
        this.caloriesBurned += caloriesBurned;
        this.workoutType = workoutType;
        this.workoutDuration = workoutDuration;
    }

    public String getWeeklySummary() {
        
        return "Weekly Activity: " + steps + " steps, " + caloriesBurned + " calories burned.";
    }
}

// HealthAnalysis Class
class HealthAnalysis {
    private int userId;
    HealthRecord healthrecord; 
    private List<HealthRecord> healthRecords;
    private List<User> users;

    public HealthAnalysis(String userId, List<HealthRecord> healthRecords) {
        this.userId = Integer.parseInt(userId);
        this.healthRecords = healthRecords;
    }

    public String analyzeTrends() {
        if (healthRecords.isEmpty()) {
            return "No health records found.";
        }
        
        // Calculate averages using streams
        double avgWeight = healthRecords.stream()
            .mapToDouble(HealthRecord::getWeight)
            .average()
            .orElse(0);
            
        int avgHeartRate = (int) healthRecords.stream()
            .mapToInt(HealthRecord::getHeartRate)
            .average()
            .orElse(0);
            
        double avgHeight = healthRecords.stream()
            .mapToDouble(HealthRecord::getHeight)
            .average()
            .orElse(0);
            
        int avgAge = (int) healthRecords.stream()
            .mapToInt(HealthRecord::getAge)
            .average()
            .orElse(0);
    
        // Calculate health metrics
        double expectedWeight = 22 * (avgHeight * avgHeight); // BMI of 22 is considered healthy
        int expectedHeartRate = 220 - avgAge; // Max heart rate formula
    
        StringBuilder analysis = new StringBuilder();
        
        // Analyze heart rate
        analysis.append("Heart Rate Analysis:\n");
        if (avgHeartRate > expectedHeartRate) {
            analysis.append("WARNING: Average heart rate (")
                   .append(avgHeartRate)
                   .append(" bpm) is higher than expected (")
                   .append(expectedHeartRate)
                   .append(" bpm)\n");
        } else {
            analysis.append("Average heart rate (")
                   .append(avgHeartRate)
                   .append(" bpm) is within normal range\n");
        }
    
        // Analyze weight
        analysis.append("\nWeight Analysis:\n");
        if (avgWeight > expectedWeight) {
            analysis.append("WARNING: Current weight (")
                   .append(String.format("%.1f", avgWeight))
                   .append(" kg) is higher than recommended (")
                   .append(String.format("%.1f", expectedWeight))
                   .append(" kg)\n");
        } else {
            analysis.append("Current weight (")
                   .append(String.format("%.1f", avgWeight))
                   .append(" kg) is within healthy range\n");
        }
    
        // Add BMI calculation
        double bmi = (avgWeight / (avgHeight * avgHeight) )*100;
        analysis.append("\nBMI Analysis:\n")
               .append("Current BMI: ")
               .append(String.format("%.3f", bmi*100))
               .append(" (");
    
        // BMI Categories
        if (bmi*100 < 18.5) {
            analysis.append("Underweight");
        } else if (bmi*100 < 25) {
            analysis.append("Normal weight");
        } else if (bmi*100 < 30) {
            analysis.append("Overweight");
        } else {
            analysis.append("Obese");
        }
        analysis.append(")");
    
        return analysis.toString();
    }
    private User getUser(String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
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
    public void trackHealth(double weight, double height, String bloodPressure, int heartRate) {
        int age = users.isEmpty() ? 0 : users.get(0).getAge();
        healthRecords.add(new HealthRecord(age, weight, height, bloodPressure, heartRate));
    }

    public User getUser(int id) { 
        for (User user : users) {
            if (user.getUserId() == id) {
        HealthAnalysis analysis = new HealthAnalysis("User", healthRecords);
            }
        }
        return null;
    }
    public void generateReport() {
        if (healthRecords.isEmpty()) {
            System.out.println("No health records available.");
            return;
        }
        // Pass the first user's ID as string if available
        String userId = users.isEmpty() ? "Unknown" : String.valueOf(users.get(0).getUserId());
        HealthAnalysis analysis = new HealthAnalysis(userId, healthRecords);
        System.out.println(analysis.analyzeTrends());
    }

    public static void main(String[] args) {
        HealthTrackerApp u1 = new HealthTrackerApp();
        u1.registerUser("Alice", 25, "Female", 1.65, 60.0);
        u1.trackHealth(61.0,154, "120/80", 72);
        u1.trackHealth(62.5,165, "118/76", 75);
        u1.trackHealth(60.5, 155,"122/78", 70);
        u1.activities.add(new ActivityTracker(10000, 500, "Running", 60));
        System.out.println(u1.activities.get(0).getWeeklySummary());
        u1.generateReport();

        HealthTrackerApp u2 = new HealthTrackerApp();
        u2.registerUser("Bob", 30, "Male", 1.75, 80.0);
        u2.trackHealth(81.0, 165 , "130/85", 80);
        u2.trackHealth(79.5, 168, "128/82", 78);
        u2.trackHealth(80.0, 175 , "125/80", 76);
        u2.activities.add(new ActivityTracker(8000, 400, "Walking", 45));
        System.out.println(u2.activities.get(0).getWeeklySummary());
        u2.generateReport();

        HealthTrackerApp u3 = new HealthTrackerApp();
        u3.registerUser("Charlie", 28, "Male", 1.80, 75.0);
        u3.trackHealth(76.0,165 , "110/70", 68);
        u3.trackHealth(74.5,172 ,  "115/75", 70);
        u3.trackHealth(75.0, 180, "112/72", 69);
        u3.activities.add(new ActivityTracker(12000, 600, "Cycling", 90));  // Add this line
        System.out.println(u3.activities.get(0).getWeeklySummary());
        u3.generateReport();

        
        // u1.activities.add(new ActivityTracker(10000, 500, "Running", 60));
        // u2.activities.add(new ActivityTracker(8000, 400, "Walking", 45));
        // u3.activities.add(new ActivityTracker(12000, 600, "Cycling", 90));


        // System.out.println(u1.activities.get(0).getWeeklySummary());
        // System.out.println(u2.activities.get(0).getWeeklySummary());
        // System.out.println(u3.activities.get(0).getWeeklySummary());

        
        // u1.generateReport();
        // u2.generateReport();
        // u3.generateReport();
    }
}
