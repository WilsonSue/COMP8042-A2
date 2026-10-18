package src.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QuickGrader {
    private Queue<Applicant> queue;
    private Applicant[] applicants;
    private int numberApplicants;
    private int skip;

    public QuickGrader(int numberApplicants, int skip) {
        this.numberApplicants = numberApplicants;
        this.skip = skip;
        this.applicants = new Applicant[numberApplicants];
        this.queue = new LinkedList<>();
        initApplicants();
    }

    private void initApplicants() {
        for (int i = 0; i < numberApplicants; i++) {
            applicants[i] = new Applicant(i, 0);
            queue.add(applicants[i]);
        }
    }

    private void runGrading() {
        int eliminatedCount = 1;

        while (!queue.isEmpty()) {
            for (int i = 0; i < skip - 1; i++) { // Dequeue and re-enqueue skip-1 times
                queue.add(queue.poll());
            }
            Applicant eliminated = queue.poll(); // Permanently remove the nth applicant

            float grade = ((float) eliminatedCount / numberApplicants) * 100;
            eliminated.setGrade(grade);
            eliminatedCount++;
        }
    }

    public Applicant[] getGrading() {
        runGrading();
        return applicants;
    }

    public static void main(String[] args) {
        System.out.println("Question 1: a=12, n=3");
        QuickGrader task1 = new QuickGrader(12, 3);
        Applicant[] grades1 = task1.getGrading();
        for (Applicant app : grades1) {
            System.out.println(app);
        }

        System.out.println("\nQuestion 2: a=180, n=37");
        QuickGrader task2 = new QuickGrader(180, 37);
        Applicant[] grades2 = task2.getGrading();
        for (Applicant app : grades2) {
            if (Math.abs(app.grade - 50.0f) < 0.01) {
                System.out.println("Sit in position: " + app.applicantNumber + "to get 50%.");
            }
        }

        System.out.println("\nQuestion 3: a=1100, n=259");
        QuickGrader task3 = new QuickGrader(1100, 259);
        Applicant[] grades3 = task3.getGrading();
        for (Applicant app : grades3) {
            if (Math.abs(app.grade - 100.0f) < 0.01) {
                System.out.println("Sit in position: " + app.applicantNumber + "to get the best grade.");
            }
        }
    }
}