package manager;

import java.util.Scanner;
import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomeworkManager {
    private Scanner scanner = new Scanner(System.in);
    private InputManager inputManager = new InputManager(this);
    private Random random = new Random();
    
    private String namePath = "name.txt";
    private String assignmentsPath = "assignments.txt";

    private ArrayList<Assignment> assignments = new ArrayList<>();

    public HomeworkManager() {
        greet();
        inputManager.mainCycle();
        scanner.close();
    }

    public static void main(String[] args) {
        HomeworkManager homeworkManager = new HomeworkManager();
    }

    private void greet() {
        File file = new File(namePath);
        if(!file.exists()) {
            createName();
        }
        String name = FileManager.readSingleLine(namePath);
        String greeting = chooseGreeting();
        System.out.println("\n" + greeting + " " + name + ".");
    }

    private void createName() {
        System.out.println("Hey! Welcome to the Homework Manager! What is your name?");
        String input = scanner.nextLine();
        FileManager.writeToFile(input, namePath);
    }

    private String chooseGreeting() {
        int n = random.nextInt(5);
        switch(n) {
            case 0:
                return "Hey";
            case 1:
                return "Greetings";
            case 2:
                return "Salutations";
            case 3:
                return "Hello";
            case 4:
                return "How are you doing";
            default:
                return "Hey";
        }
    }

    public void addAssignment(String name, String className, LocalDateTime dueDate) {
        Assignment assignment = new Assignment(name, className, dueDate);
        assignments.add(assignment);
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
}
