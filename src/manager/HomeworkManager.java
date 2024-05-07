package manager;

import java.util.Scanner;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class HomeworkManager {
    Scanner scanner = new Scanner(System.in);
    FileManager fileManager = new FileManager();
    
    String namePath = "name.txt";
    String assignmentsPath = "assignments.txt";

    ArrayList<Assignment> assignments = new ArrayList<>();

    public HomeworkManager() {
        loadAssignments();
        Assignment assignment1 = new Assignment("Essay","English 101", LocalDateTime.of(2024, 5, 4, 12, 00));
        assignments.add(assignment1);
        saveAssignments();
    }

    public static void main(String[] args) {
        HomeworkManager homeworkManager = new HomeworkManager();
    }

    private void greet() {
        File file = new File(namePath);
        if(!file.exists()) {
            createName();
        }
        String name = fileManager.readFromFile(namePath);
        System.out.println("Hey " + name + "!");
    }

    private void createName() {
        System.out.println("Hey! Welcome to the Homework Manager! What is your name?");
        String input = scanner.nextLine();
        fileManager.writeToFile(input, namePath);
    }

    private void loadAssignments() {
        File file = new File(assignmentsPath);
        if(!file.exists()) {
            fileManager.serialize(new ArrayList<Assignment>(), assignmentsPath);
        }
        assignments = fileManager.deserialize(assignmentsPath);
        System.out.println(assignments);
    }

    private void saveAssignments() {
        fileManager.serialize(assignments, assignmentsPath);
    }
}
