package manager;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Assignment> assignments = new ArrayList<>();

    private String assignmentsPath = "assignments.txt";

    public InputManager() {
        this.assignments = FileManager.loadAssignments(assignmentsPath);
        // FileManager.writeToFile("", assignmentsPath, false, false);
        // addAssignment("Ceramic Piece", "Pottery 101", 
        //                    LocalDateTime.of(2024, 5, 21, 12, 0, 0));
        // addAssignment("500 Word Paper", "English 101", 
        //                    LocalDateTime.of(2024, 5, 15, 3, 20, 0));
        // addAssignment("Data Science Final", "Intro to Data Science", 
        //                     LocalDateTime.of(2024, 6, 1, 12, 0));
        FileManager.saveAssignments(assignments, assignmentsPath);
    }
   
    public void mainCycle() {
        String input = getInput("Please enter your command, or type 'help': ");
        runCommand(input);
        mainCycle();
    }

    private String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    private void runCommand(String input) {
        switch(input) {
            case "help":
                helpCommand();
                break;
            case "add":
                addCommand();
                break;
            case "due":
                dueCommand();
                break;
            case "complete":
                completeCommand();
                break;
            case "quit":
                quitCommand();
                break;
            case "cancel":
                cancelCommand();
                break;
            default:
                break;
        }
    }

    private void helpCommand() {
        System.out.println(FileManager.readFromFile("help.txt"));
    }

    private void addCommand() {
        String name = getInput("What is the name of the assignment: ");
        String className = getInput("What is the name of the class: ");
        int year = getIntInput("What year is this due: ");
        int month = getIntInput("What month is this due (as a number, please): ");
        int day = getIntInput("What day of the month is this due: ");
        int hour = getIntInput("What hour is this due (use military time, please): ");
        int minute = getIntInput("What minute of the hour is this due: ");

        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        addAssignment(name, className, date);
        FileManager.saveAssignments(assignments, assignmentsPath);
        System.out.println("Successfully added.");
    }

    private void dueCommand() {
        for (Assignment assignment : assignments) {
            System.out.println();
            System.out.println("Assignment " + assignment.getId() + ": " + assignment.getName());
            System.out.println("Due on: " + assignment.getDueDate());
            System.out.println("ID: " + assignment.getId());
        }
    }

    private void completeCommand() {    
        String assignmentDisplay = "";
        for (Assignment assignment : assignments) {
            String toAdd = "\n" + assignment.getId() + ". " + assignment.getName();
            assignmentDisplay += toAdd;
        }
        int assignmentIdToDelete = getIntInput("Which of these assignments did you complete (enter a number, please): " + assignmentDisplay);
        Assignment assignmentToDelete = null;
        for (Assignment assignment : assignments) {
            if(assignment.getId() == assignmentIdToDelete) {
                assignmentToDelete = assignment;
            }
        }
        if(assignmentToDelete != null) {
            assignments.remove(assignmentToDelete);
            FileManager.saveAssignments(assignments, assignmentsPath);
            System.out.println("Congratulations on completing " + assignmentToDelete.getName());
        }
    }

    private void quitCommand() {
        System.exit(0);
    }

    private void cancelCommand() {

    }

    public void addAssignment(String name, String className, LocalDateTime dueDate) {
        int id = findLargestId() + 1;
        Assignment assignment = new Assignment(name, className, dueDate, id);
        assignments.add(assignment);
    }

    private int findLargestId() {
        int largest = 0;
        for (Assignment assignment : assignments) {
            if(largest < assignment.getId()) {
                largest = assignment.getId();
            }
        }
        return largest;
    }

}
