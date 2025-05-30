import java.util.*;

class Job {
    String jobTitle, companyName;

    Job(String jobTitle, String companyName) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
    }

    void display() {}
}

class FullTimeJob extends Job {
    FullTimeJob(String jobTitle, String companyName) {
        super(jobTitle, companyName);
    }

    @Override
    void display() {
        System.out.println("Full-Time Job: " + jobTitle + " at " + companyName);
    }
}

class Internship extends Job {
    double stipend;

    Internship(String jobTitle, String companyName, double stipend) {
        super(jobTitle, companyName);
        this.stipend = stipend;
    }

    @Override
    void display() {
        System.out.println("Internship: " + jobTitle + " at " + companyName + " with Stipend " + stipend);
    }
}

public class q16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String type = sc.next();
            String title = sc.next();
            String company = sc.next();
            if (type.equals("Internship")) {
                double stipend = sc.nextDouble();
                jobs.add(new Internship(title, company, stipend));
            } else {
                jobs.add(new FullTimeJob(title, company));
            }
        }

        for (Job job : jobs) job.display();
        sc.close();
    }
}
