import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int subjects = 0;

        // Input number of subjects
        while (true) {
            System.out.print("Enter number of subjects: ");
            if (sc.hasNextInt()) {
                subjects = sc.nextInt();
                if (subjects > 0) break;
                else System.out.println("Please enter a positive number.");
            } else {
                System.out.println("Invalid input! Enter numbers only.");
                sc.next();
            }
        }

        int totalMarks = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        int[] marks = new int[subjects];

        // Input marks
        for (int i = 0; i < subjects; i++) {
            while (true) {
                System.out.print("Enter marks of subject " + (i + 1) + " (out of 100): ");
                if (sc.hasNextInt()) {
                    marks[i] = sc.nextInt();
                    if (marks[i] >= 0 && marks[i] <= 100) {
                        totalMarks += marks[i];
                        highest = Math.max(highest, marks[i]);
                        lowest = Math.min(lowest, marks[i]);
                        break;
                    } else {
                        System.out.println("Marks must be between 0 and 100.");
                    }
                } else {
                    System.out.println("Invalid input! Enter numbers only.");
                    sc.next();
                }
            }
        }

        double average = (double) totalMarks / subjects;

        // Result based on 33% rule
        String result = (average >= 33) ? "PASS" : "FAIL";

        // Grade calculation
        String grade;
        if (average >= 90) grade = "A+";
        else if (average >= 80) grade = "A";
        else if (average >= 70) grade = "B";
        else if (average >= 60) grade = "C";
        else if (average >= 50) grade = "D";
        else if (average >= 33) grade = "E";
        else grade = "Fail";

        // Performance remark (FAIL-safe)
        String remark;
        if (average < 33) {
            remark = "Needs Improvement";
        } else if (average >= 90) {
            remark = "Outstanding Performance";
        } else if (average >= 75) {
            remark = "Very Good Performance";
        } else if (average >= 60) {
            remark = "Good Performance";
        } else {
            remark = "Average Performance";
        }

        // Display result
        System.out.println("\n========= STUDENT RESULT =========");
        for (int i = 0; i < subjects; i++) {
            System.out.println("Subject " + (i + 1) + ": " + marks[i]);
        }

        System.out.println("--------------------------------");
        System.out.println("Total Marks       : " + totalMarks);
        System.out.println("Average Percentage: " + average + "%");
        System.out.println("Highest Marks     : " + highest);
        System.out.println("Lowest Marks      : " + lowest);
        System.out.println("Grade             : " + grade);
        System.out.println("Overall Result    : " + result);
        System.out.println("Remark            : " + remark);
        System.out.println("================================");

        sc.close();
    }
}
