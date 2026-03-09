import java.io.*;
import java.util.*;

/* Student class */
class Student {
    String name;
    int rollNo;
    String grade;

    Student(String name, int rollNo, String grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNo + "," + name + "," + grade;
    }
}

/* Student Management System */
public class StudentManagementSystem {

    static final String FILE_NAME = "students.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    /* Add student */
    static void addStudent() {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {

            System.out.print("Enter Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty!");
                return;
            }

            System.out.print("Enter Grade: ");
            String grade = sc.nextLine();
            if (grade.isEmpty()) {
                System.out.println("Grade cannot be empty!");
                return;
            }

            Student s = new Student(name, roll, grade);
            fw.write(s.toString() + "\n");
            System.out.println("Student added successfully.");

        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    /* Display all students */
    static void displayStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\nRoll | Name | Grade");
            System.out.println("------------------");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println(data[0] + " | " + data[1] + " | " + data[2]);
            }
        } catch (IOException e) {
            System.out.println("No student records found.");
        }
    }

    /* Search student */
    static void searchStudent() {
        System.out.print("Enter Roll No to search: ");
        int roll = sc.nextInt();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (Integer.parseInt(data[0]) == roll) {
                    System.out.println("Student Found: " + data[1] + ", Grade: " + data[2]);
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    /* Remove student */
    static void removeStudent() {
        System.out.print("Enter Roll No to remove: ");
        int roll = sc.nextInt();
        boolean removed = false;

        File input = new File(FILE_NAME);
        File temp = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(input));
             PrintWriter pw = new PrintWriter(new FileWriter(temp))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (Integer.parseInt(data[0]) != roll) {
                    pw.println(line);
                } else {
                    removed = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing file.");
        }

        input.delete();
        temp.renameTo(input);

        System.out.println(removed ? "Student removed successfully." : "Student not found.");
    }

    /* Edit student */
    static void editStudent() {
        System.out.print("Enter Roll No to edit: ");
        int roll = sc.nextInt();
        sc.nextLine();

        File input = new File(FILE_NAME);
        File temp = new File("temp.txt");
        boolean updated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(input));
             PrintWriter pw = new PrintWriter(new FileWriter(temp))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (Integer.parseInt(data[0]) == roll) {
                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter New Grade: ");
                    String grade = sc.nextLine();

                    pw.println(roll + "," + name + "," + grade);
                    updated = true;
                } else {
                    pw.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error editing file.");
        }

        input.delete();
        temp.renameTo(input);

        System.out.println(updated ? "Student updated successfully." : "Student not found.");
    }
}