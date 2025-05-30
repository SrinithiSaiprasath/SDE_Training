import java.security.KeyStore.Entry;
import java.util.*;
import java.time.LocalDateTime;

class User {
    private String name;
    private String mail;
    private String ContactNo;
    private String password;

    public String getName() {return name;}

    public String getMail() {return mail;}

    public String getPassword() {return password;}
    
    public User() {
    }

    public User(String name, String mail, String ContactNo, String password) {
        this.name = name;
        this.mail = mail;
        this.ContactNo = ContactNo;
        this.password = password;
    }
    
}
class AccountManager {
    public static HashMap<String , User > users = new HashMap<>(); //mail id : User
    private List<User> Users = new ArrayList<>();

    public void register(String name, String mail, String contactNo, String password) {
        User me = new User(name, mail, contactNo, password);
        if (!users.containsKey(me.getMail())) {
            users.put(me.getMail(), me);
            Users.add(me);
            System.out.println("Your account ID is " + me.getMail());
        } else {
            System.out.println("User already registered.");
        }
    }

    public void login(String mailid, String password) {
        // System.out.println(users.containsKey(mailid));
        if (users.containsKey(mailid) && users.get(mailid).getPassword().equals(password)) {
            System.out.println("You are successfully Logged In as: " + users.get(mailid).getMail());
        } else {
            System.out.println("Invalid credentials");
        }
    }
}
class DailyRewards {
    Habit habitname;
    int points;

    public DailyRewards(Habit habitName, int points) {
        this.habitname = habitName;
        this.points = points;
    }

    public Habit getHabit() {
        return habitname;
    }

    public int getPoints() {
        return points;
    }
}
class Journal{
    String Name ; 
    String context ;
    String Date;
    List<Journal> myJournals = new ArrayList<>();
    public String getName(){return Name ;} 
    public String getContext(){return context ; }
    public String getDate(){return Date ; }
    public Journal(){}
    public Journal(String Name, String context ){
        this.Name =  Name ; 
        this.context = context ;
         this.Date = LocalDateTime.now().toString();
    }

    public void AddJournal(String Name, String context){
        myJournals.add(new Journal(Name, context));
    }
    public void viewJournals(){
        for(Journal journal : myJournals){
            System.out.println(journal.getDate()+"\n"+journal.getName() + "\n"+ journal.getContext() + "\n");
        }
    }

}
class Habit implements TrackerOps {
    private String name;
    private String id;
    private double Goal;
    private double progress;
    List<Habit> myHabits = new ArrayList<>();
    List<DailyRewards> myDailyRewards = new ArrayList<>();
    int count = 1;

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public double getGoal() {
        return Goal;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double prog) {
        this.progress += prog;
    }

    public Habit() {
    }

    public Habit(String name, double Goal) {
        this.name = name;
        this.Goal = Goal;
        this.progress = 0;
        this.id = name + count++;
    }

    public void view() {
        if(myHabits.isEmpty()){
            System.out.println("------------------------------------------------");
            System.out.println("No Habits As of Now");
            System.out.println("------------------------------------------------");
        }
        else {
            for (Habit hab : myHabits) {
            System.out.println("------------------------------------------------");
            System.out.println("Habit Name: "+hab.getName() + "\n" +"Habit Goal: "+ hab.getGoal() +
             "\n" +"Habit Progress: "+ hab.getProgress() + "\n");
             System.out.println("------------------------------------------------");
        }
    }
    }

    public void create(String Name, double Goal) {
        myHabits.add(new Habit(Name, Goal));
        System.out.println("Habit created Successfully");
    }

    public void updateProgress(String name, double prog) {
        for (Habit habits : myHabits) {
            if (habits.getName().equalsIgnoreCase(name)) {
                habits.setProgress(prog);
                if (habits.getProgress() >= habits.getGoal()) {
                    System.out.println("------------------------------------------------");
                    System.out.println("Goal: " + habits.getName() + " is ACHIEVED");
                    System.out.println("------------------------------------------------");
                    myDailyRewards.add(new DailyRewards(habits, 50));
                } else {
                    System.out.println("------------------------------------------------");
                    System.out.println("Progress of " + habits.getName() + " is  updated." + "\n" + "Yet to Achieve: "
                            + ((((habits.getGoal() - habits.getProgress())/habits.getGoal()) * 100) + "%"));
                    System.out.println("------------------------------------------------");
                }
            }
        }
    }

    public void viewProgress() {
        for (Habit habits : myHabits) {
            System.out.println("------------------------------------------------");
            if (habits.getGoal() < habits.progress) {
                System.out.println("Habit Name: " + habits.getName() + "\n" + "Status: " + "\n" + "DAILY GOAL ACHIEVED"
                        + "\n" + "Goal " + habits.getGoal() + "\n" + "Progress: " + habits.getProgress());
            }
            else{
            System.out.println("Habit Name: " + habits.getName() + "\n" + "Status: " + "\n"
                    + "DAILY YET TO ACHIEVED BY " + ((habits.getProgress() / habits.getGoal()) * 100) + "\n" + "Goal "
                    + habits.getGoal() + "\n" + "Progress: " + habits.getProgress());
            System.out.println("------------------------------------------------");
        }
    }
    }

    public void viewRewards() {
        for (DailyRewards reward : myDailyRewards) {
            System.out.println("------------------------------------------------");
            System.out.println("Habit: " + reward.getHabit().getName() + "Points: " + reward.getPoints() + "\n");
            System.out.println("------------------------------------------------");
        }
    }

}
class Badges {
    Challenge challenge;
    int point;

    public Badges(Challenge challenge, int point) {
        this.challenge = challenge;
        this.point = point;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public int getPoints() {
        return point;
    }
}
class Community{
    private HashMap<String, List<String>> messages = new HashMap<>(); // User mail : List of messages
    private HashMap<String, List<String>> friends = new HashMap<>(); // User mail : List of friends

    public void sendMessage(String senderMail, String receiverMail, String message) {
        if (!messages.containsKey(receiverMail)) {
            messages.put(receiverMail, new ArrayList<>());
        }
        messages.get(receiverMail).add("From " + senderMail + ": " + message);
        System.out.println("Message sent successfully!");
    }

    public void viewMessages(String userMail) {
        if (messages.containsKey(userMail) && !messages.get(userMail).isEmpty()) {
            System.out.println("Your Messages:");
            for (String msg : messages.get(userMail)) {
                System.out.println(msg);
            }
        } else {
            System.out.println("No messages found.");
        }
    }

    public void shareBadge(String userMail, Badges badge) {
        System.out.println("Badge shared successfully!");
        System.out.println("Badge Details:");
        System.out.println("Challenge: " + badge.getChallenge().getName());
        System.out.println("Points: " + badge.getPoints());
    }

    public void viewUsers() {
        System.out.println("List of Users:");
        for (String mail : AccountManager.users.keySet()) {
            System.out.println(mail);
        }
    }

    public void addFriend(String userMail, String friendMail) {
        if (!AccountManager.users.containsKey(friendMail)) {
            System.out.println("User not found.");
            return;
        }
        if (!friends.containsKey(userMail)) {
            friends.put(userMail, new ArrayList<>());
        }
        if (friends.get(userMail).contains(friendMail)) {
            System.out.println("User is already your friend.");
        } else {
            friends.get(userMail).add(friendMail);
            System.out.println("Friend added successfully!");
        }
    }

    public void viewFriends(String userMail) {
        if (friends.containsKey(userMail) && !friends.get(userMail).isEmpty()) {
            System.out.println("Your Friends:");
            for (String friend : friends.get(userMail)) {
                System.out.println(friend);
            }
        } else {
            System.out.println("No friends found.");
        }
    }
}
class Challenge {
    private String name;
    private String id;
    private double Goal;
    private double progress;
    public Challenge(){

    }
    int count;
    private String DeadLine;
    private String genre;
    private List<Challenge> myChallenges = new ArrayList<>();
    List<Badges> myBadges = new ArrayList<>();

    public String getName() {return name;}

    public String getID() {return id;}

    public double getGoal() {return Goal;}

    public double getProgress() {return progress;}

    public void setProgress(double prog) {this.progress += prog;}

    public Challenge(String name, double Goal, String DeadLine, String genre) {
        this.name = name;
        this.Goal = Goal;
        this.progress = 0;
        this.genre = genre;
        this.DeadLine = DeadLine;
        this.id = name + count++;
    }

    public void createChallenges(String Name, double Goal, String deadline, String genre) {
        myChallenges.add(new Challenge(Name, Goal, deadline, genre));
    }

    public void updateProgress(String name, double prog) {
        for (Challenge challenge : myChallenges) {
            if (challenge.getName().equalsIgnoreCase(name)) {
                challenge.setProgress(prog);
                if (challenge.getProgress() >= challenge.getGoal()) {
                    System.out.println("Goal: " + challenge.getName() + " is ACHIEVED");
                    myBadges.add(new Badges(challenge, 50));
                } else {
                    System.out
                            .println("Progress of " + challenge.getName() + " is  updated." + "\n" + "Yet to Achieve: "
                                    + (((challenge.getProgress() / challenge.getGoal()) * 100) + "%"));
                }
            }
        }
    }

    public void viewProgress() {
        for (Challenge challenge : myChallenges) {
            System.out.println("------------------------------------------------");
            if (challenge.getGoal() < challenge.progress) {
                System.out.println(
                        "Challenge Name: " + challenge.getName() + "\n" + "Status: " + "\n" + "CHALLENGE ACHIEVED"
                                + "\n" + "Goal " + challenge.getGoal() + "\n" + "Progress: " + challenge.getProgress());
            }
            System.out.println("Habit Name: " + challenge.getName() + "\n" + "Status: " + "\n"
                    + "CHALLENGE YET TO ACHIEVED BY " + ((challenge.getProgress() / challenge.getGoal()) * 100) + "in"
                    + "\n" + "Goal "
                    + challenge.getGoal() + "\n" + "Progress: " + challenge.getProgress());
            System.out.println("------------------------------------------------");
        }
    }

    public void viewRewards() {
        for (Badges reward : myBadges) {
            System.out.println("------------------------------------------------");
            System.out.println("Habit: " + reward.getChallenge().getName() + "Points: " + reward.getPoints() + "\n");
            System.out.println("------------------------------------------------");
        }
    }
}
interface TrackerOps {
    void view();

    void create(String Name, double Goal);

    void updateProgress(String name, double prog);

    void viewProgress();

    void viewRewards();
}
public class Habit_Tracker {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in); 
        AccountManager userManager =  new AccountManager();
        Habit myHabits ; 
    while(true){
        System.out.println("Welcome to Habit Tracker");
        System.out.println("1.Register" + "\n" + "2.Login" +"\n"+ "3.Exit");

        System.out.println("Enter Your Choice");
        int choice =  sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter Your name");
                String Name =  sc.next();
                System.out.println("Enter your contact No");
                String contactNo =  sc.next() ; 
                System.out.println("Enter Mail ID");
                String mail =  sc.next();
                System.out.println("Enter Your password");
                String password =  sc.next();
                userManager.register(Name, mail, contactNo,password);
                break;
            case 2:
                System.out.println("Enter Mail ID");
                String m = sc.next();
                System.out.println("Enter Password");
                String pas = sc.next();
                userManager.login(m, pas);
                LoginOperations();
                // break ;
            default:
                break;
        }
        
    }
    }

    public static void LoginOperations(){
        Scanner sc =  new Scanner(System.in); 
        System.out.println("Hello User...!");
        System.out.println("Choose the Operations reuqired");
        System.out.println("1.Habit Operations" + "\n" + "2.Challenges Operations" +
         "\n" + "3.View Community" + "\n" + "4.Journal"+"5.Logout" + "\n");

        System.out.println("Enter your choice");
        int c =  sc.nextInt();
        switch (c) {
            case 1:
                HabitOperations();     
                // break;
            case 2:
                ChallengeOperations();  
                // break ; 
            case 3:
                JournalOperations();
                // break ; 
            case 4:
                // CommunityOperations();
                // break;
            default:
                break;
        }

    }
    //Habit Operations
    public static void HabitOperations(){
        Scanner sc =  new Scanner(System.in); 
        Habit myHabit =  new Habit(); 
        System.out.println("Hello User...!");
        while(true){
        System.out.println("Choose the Operations reuqired");
        System.out.println("1.Create Habit" + "\n" + "2.View Habits" +
         "\n" + "3.Update Progress" + "\n" + "4.View Today Progress"+"\n" + 
         "5.View My Rewards" + "\n" + "6.Exit");
        System.out.println("Enter your Choice");
        int c =  sc.nextInt();

        switch (c) {
            case 1:
                System.out.println("Enter Habit Name");
                String name = sc.next() ; 
                System.out.println("Enter Goal ");
                double goal =  sc.nextDouble();
                myHabit.create(name , goal);
                // break;
            case 2:
                myHabit.view();
            case 3:
                System.out.println("Enter the Habit Name you want to Update");
                String n = sc.next();
                System.out.println("Enter the progress");
                double prog =  sc.nextDouble() ; 
                myHabit.updateProgress(n, prog);
                // break ; 
            case 4:
                myHabit.viewProgress();
            case 5:
                myHabit.viewRewards();
            case 6:
                return ; 
            default:
                break;
        }
    }
    }

    //Challenge Operations
    public static void ChallengeOperations(){
        Scanner sc =  new Scanner(System.in); 
        Challenge myHabit =  new Challenge(); 
        System.out.println("Hello User...!");
        while(true){
        System.out.println("Choose the Operations reuqired");
        System.out.println("1.Create Challenge" + "\n" + "2.View challenges" +
         "\n" + "3.Update challenges" + "\n" + "4.View Today Progress"+"\n" + 
         "5.View My Rewards" + "\n");
        System.out.println("Enter your Choice");
        int c =  sc.nextInt();

        switch (c) {
            case 1:
                System.out.println("Enter Habit Name");
                String name = sc.next() ; 
                System.out.println("Enter Goal ");
                double goal =  sc.nextDouble();
                System.out.println("Enter deadline Name");
                String deadline = sc.next() ; 
                System.out.println("Enter genre Name");
                String genre = sc.next() ; 
                myHabit.createChallenges(name , goal ,deadline , genre );
                // break;
            case 2:
                myHabit.viewProgress();
            case 3:
                System.out.println("Enter the Challenge Name you want to Update");
                String n = sc.next();
                System.out.println("Enter the progress");
                double prog =  sc.nextDouble() ; 
                myHabit.updateProgress(n, prog);
                break ; 
            case 4:
                myHabit.viewProgress();
            case 5:
                myHabit.viewRewards();
            default:
                break;
        }
    }
    }
    public static void JournalOperations(){
        Scanner sc =  new Scanner(System.in); 
        Journal myJournal =  new Journal(); 
        System.out.println("Hello User...!");
        System.out.println("Choose the Operations reuqired");
        System.out.println("1.Add Journal" + "\n" + "2.View Journal" +"\n" + "3.Exit" + "\n");
        System.out.println("Enter your Choice");
        int c =  sc.nextInt();
        switch (c) {
            case 1:
                System.out.println("Enter Name");
                String Name =  sc.next();
                System.out.println("Enter Content");
                String content =  sc.next();
                myJournal.AddJournal(Name, content);
                break;
            case 2:
                myJournal.viewJournals();
            case 3:
                break ; 
            default:
                break;
        }
    }
}



