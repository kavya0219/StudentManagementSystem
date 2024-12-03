import java.io.*;
import java.util.*;

public class StudentManagementSystem {

    // Student class to represent individual students
    static class Student {
        String name, grade, section;
        int rollNo, standard;

        // Constructor to initialize student details
        public Student(int rollNo, String name, String grade, String section, int standard) {
            this.rollNo = rollNo;
            this.name = name;
            this.grade = grade;
            this.section = section;
            this.standard = standard;
        }

        // Overridden toString() method for displaying student information
        @Override
        public String toString() {
            return "Roll No: " + rollNo + ", Name: " + name + ", Grade: " + grade + ", Class: " + standard + ", Section: " + section;
        }
    }

    // List to store all students
    static List<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Method to add a student
    public static void addStudent() {
        System.out.print("Enter roll number: ");
        int rollNo = sc.nextInt();
        sc.nextLine();  // Consume newline
        
        // Validate if roll number already exists
        if (!isValidRollNo(rollNo)) {
            System.out.println("Roll number already exists! Please enter a unique roll number.");
            return;
        }
        
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter grade: ");
        String grade = sc.nextLine();
        
        System.out.print("Enter section: ");
        String section = sc.nextLine();
        
        System.out.print("Enter class: ");
        int standard = sc.nextInt();
        sc.nextLine();  // Consume newline

        // Create a new student and add to the list
        students.add(new Student(rollNo, name, grade, section, standard));
        System.out.println("Student added!");
    }

    // Method to view all students
    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Method to update student information
    public static void updateStudent() {
        System.out.print("Enter roll number to update: ");
        int rollNoToUpdate = sc.nextInt();
        sc.nextLine(); // Consume newline

        Student studentToUpdate = null;
        for (Student student : students) {
            if (student.rollNo == rollNoToUpdate) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate != null) {
            System.out.println("Updating details for: " + studentToUpdate);
            System.out.print("Enter new name: ");
            studentToUpdate.name = sc.nextLine();
            System.out.print("Enter new grade: ");
            studentToUpdate.grade = sc.nextLine();
            System.out.print("Enter new section: ");
            studentToUpdate.section = sc.nextLine();
            System.out.print("Enter new class: ");
            studentToUpdate.standard = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.println("Student updated!");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method to delete a student
    public static void deleteStudent() {
        System.out.print("Enter roll number to delete: ");
        int rollNoToDelete = sc.nextInt();
        sc.nextLine(); // Consume newline

        Student studentToDelete = null;
        for (Student student : students) {
            if (student.rollNo == rollNoToDelete) {
                studentToDelete = student;
                break;
            }
        }

        if (studentToDelete != null) {
            students.remove(studentToDelete);
            System.out.println("Student deleted!");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method to search for a student by roll number
    public static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        int rollNoToSearch = sc.nextInt();
        sc.nextLine(); // Consume newline

        Student studentToSearch = null;
        for (Student student : students) {
            if (student.rollNo == rollNoToSearch) {
                studentToSearch = student;
                break;
            }
        }

        if (studentToSearch != null) {
            System.out.println("Student found: " + studentToSearch);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method to validate if the roll number is unique
    public static boolean isValidRollNo(int rollNo) {
        for (Student student : students) {
            if (student.rollNo == rollNo) {
                return false;  // Roll number already exists
            }
        }
        return true;
    }

    // Method to save student data to a file
    public static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.write(student.rollNo + "," + student.name + "," + student.grade + "," + student.section + "," + student.standard);
                writer.newLine();
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    // Method to load student data from a file
    public static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4])));
            }
            System.out.println("Data loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    // Main method to run the system
    public static void main(String[] args) {
        loadFromFile();  // Load existing data from file if available

        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Student\n2. View Students\n3. Update Student\n4. Delete Student\n5. Search Student\n6. Save to File\n7. Exit");
            System.out.print("Choose an option (1-7): ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    saveToFile();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    saveToFile();  // Save data before exiting
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        sc.close();  // Close scanner
    }
}