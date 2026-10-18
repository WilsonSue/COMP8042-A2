package src.queue;

public class Applicant {
    int applicantNumber;
    float grade;

    public Applicant(int applicantNumber, int grade) {
        this.applicantNumber = applicantNumber;
        this.grade = grade;
    }

    public String toString() {
        return "Applicant " + applicantNumber + " got a grade of " + grade;
    }

    private void validateGrade(float grade) throws IllegalArgumentException {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("grade must be between 0 and 100");
        }
    }

    public void setGrade(float grade) {
        validateGrade(grade);
        this.grade = grade;
    }
}