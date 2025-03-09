import java.util.*;

abstract class Goal {
    protected String name;
    protected double goal;
    protected double progress;

    public Goal(String name, double goal) {
        this.name = name;
        this.goal = goal;
        this.progress = 0;
    }

    public String getName() return name;
    

    public double getGoal() return goal;
    

    public double getProgress() return progress;
    

    public void setProgress(double progress) this.progress = progress;
    

    public abstract void updateProgress(double amount);

    public abstract void viewReport();
}

class Challenge extends Goal {
    public Challenge(String name, double goal) {
        super(name, goal);
    }

    @Override
    public void updateProgress(double amount) {
        if (progress + amount <= goal) {
            progress += amount;
            System.out.println("Progress updated in Challenge: " + name + " completed by: " + (progress / goal) * 100 + "%");
        }
        if (progress >= goal) {
            System.out.println("Challenge Completed: " + name);
        }
    }

    @Override
    public void viewReport() {
        System.out.println("--------------------REPORT--------------------");
        System.out.println("Challenge: " + name + " completed by " + (progress / goal) * 100 + "%");
        System.out.println("----------------------------------------------");
    }
}

class Habit extends Goal {
    public Habit(String name, double goal) {
        super(name, goal);
    }

    @Override
    public void updateProgress(double amount) {
        if (progress + amount <= goal) {
            progress += amount;
            System.out.println("Progress updated in Habit: " + name + " completed by: " + (progress / goal) * 100 + "%");
        }
    }

    @Override
    public void viewReport() {
        System.out.println("--------------------REPORT--------------------");
        System.out.println("Habit: " + name + " completed by " + (progress / goal) * 100 + "%");
        System.out.println("----------------------------------------------");
    }
}

class UserAccount {
    @SuppressWarnings("unused")
    private Map<String, Challenge> challenges = new HashMap<>();
    private Map<String, Habit> habits = new HashMap<>();
    private List<String> takenChallenges = new ArrayList<>();
    private Map<String, Double> habitProgress = new HashMap<>();
    private Map<String, Double> challengeProgress = new HashMap<>();

    public void addChallenge(String name, double goal) {
        challenges.put(name, new Challenge(name, goal));
        challengeProgress.put(name, 0.0);
        System.out.println("Challenge Created: " + name);
    }

    public void addHabit(String name, double goal) {
        habits.put(name, new Habit(name, goal));
        habitProgress.put(name, 0.0);
        System.out.println("Habit Created: " + name);
    }

    public void takeChallenge(String name) {
        if (challenges.containsKey(name)) {
            takenChallenges.add(name);
            System.out.println("Challenge taken: " + name);
        } else {
            System.out.println("Challenge not found");
        }
    }

    public void updateChallengeProgress(String name, double amount) {
        if (challenges.containsKey(name)) {
            double currentProgress = challengeProgress.getOrDefault(name, 0.0);
            double newProgress = currentProgress + amount;
            if (newProgress <= challenges.get(name).getGoal()) {
                challengeProgress.put(name, newProgress);
                challenges.get(name).setProgress(newProgress);
                challenges.get(name).updateProgress(amount);
            }
        } else {
            System.out.println("Challenge not found");
        }
    }

    public void updateHabitProgress(String name, double amount) {
        if (habits.containsKey(name)) {
            double currentProgress = habitProgress.getOrDefault(name, 0.0);
            double newProgress = currentProgress + amount;
            if (newProgress <= habits.get(name).getGoal()) {
                habitProgress.put(name, newProgress);
                habits.get(name).setProgress(newProgress);
                habits.get(name).updateProgress(amount);
            }
        } else {
            System.out.println("Habit not found");
        }
    }

    public void viewChallengeReport(String name) {
        if (challenges.containsKey(name)) {
            challenges.get(name).viewReport();
        } else {
            System.out.println("Challenge not found");
        }
    }

    public void viewHabitReport(String name) {
        if (habits.containsKey(name)) {
            habits.get(name).viewReport();
        } else {
            System.out.println("Habit not found");
        }
    }

    public void viewAllChallenges() {
        System.out.println("-------------------AVAILABLE CHALLENGES---------------------------");
        if (challenges.isEmpty()) {
            System.out.println("No Challenges Available");
        } else {
            for (Map.Entry<String, Challenge> entry : challenges.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue().getGoal());
            }
        }
    }

    public void viewMyChallenges() {
        System.out.println("-------------------MY CHALLENGES---------------------------");
        if (takenChallenges.isEmpty()) {
            System.out.println("No Challenges Taken");
        } else {
            for (String entry : takenChallenges) {
                System.out.println(entry + " : " + challenges.get(entry).getGoal());
            }
        }
        System.out.println("-----------------------------------------------------------");
    }

    public void viewAllHabits() {
        System.out.println("-------------------MY HABITS---------------------------");
        if (habits.isEmpty()) {
            System.out.println("No Habits Available");
        } else {
            for (Map.Entry<String, Habit> entry : habits.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue().getGoal());
            }
        }
    }
}

public class oops_prac {
    public static void main(String[] args) {
        UserAccount user = new UserAccount();

        user.addHabit("Workout", 100);
        user.updateHabitProgress("Workout", 50);
        user.updateHabitProgress("Workout", 50);
        user.viewHabitReport("Workout");

        user.addChallenge("Run", 100);
        user.takeChallenge("Run");
        user.updateChallengeProgress("Run", 80);
        user.viewChallengeReport("Run");

        user.viewAllHabits();
        user.viewMyChallenges();
        user.viewAllChallenges();
    }
}