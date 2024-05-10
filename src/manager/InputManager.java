package manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Assignment> assignments = new ArrayList<>();
   
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

        System.out.println("Successfully added.");
    }

    private void dueCommand() {
        
        for (Assignment assignment : assignments) {
            System.out.println();
            System.out.println("Name of assignment: " + assignment.getName());
            System.out.println("Class name: " + assignment.getClassName());
            System.out.println("Due on: " + assignment.getDueDate());
        }
    }

    private void completeCommand() {    
        String assignmentDisplay = "";
        int counter = 1;

        for (Assignment assignment : assignments) {
            String toAdd = "\n" + counter + ". " + assignment.getName();
            assignmentDisplay += toAdd;
            counter ++;
        }

        int assignmentToDelete = getIntInput("Which of these assignments did you complete (enter a number, please): " + assignmentDisplay);

        
    }

    private void quitCommand() {
        System.exit(0);
    }

    private void cancelCommand() {

    }

    public void addAssignment(String name, String className, LocalDateTime dueDate) {
        Assignment assignment = new Assignment(name, className, dueDate);
        assignments.add(assignment);
    }

}
