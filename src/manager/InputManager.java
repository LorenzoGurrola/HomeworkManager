package manager;

import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class InputManager {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Assignment> assignments = new ArrayList<>();

    private String assignmentsPath = "assignments.txt";

    public InputManager() {
        this.assignments = FileManager.loadAssignments(assignmentsPath);
    }
   
    public void mainCycle() {
        String input = getInput("Please enter your command, or type 'help': ");
        runCommand(input);
        mainCycle();
    }

    private String getInput(String prompt) {
        System.out.println();
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        System.out.println();
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
                invalidCommand();
                break;
        }
    }

    private void helpCommand() {
        System.out.println();
        System.out.println(FileManager.readFromFile("help.txt"));
    }

    private void addCommand() {
        System.out.println();
        String dueDate = getInput("Please enter the due date in the form (mm/dd)");

        

        String name = getInput("What is the name of the assignment: ");
        String className = getInput("What is the name of the class: ");
        int year = getYear("What year is this due: ");
        int month = getIntInput("What month is this due (as a number, please): ");
        int day = getIntInput("What day of the month is this due: ");
        int hour = getIntInput("What hour is this due (use military time, please): ");
        int minute = getIntInput("What minute of the hour is this due: ");

        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        addAssignment(name, className, date);
        FileManager.saveAssignments(assignments, assignmentsPath);
        System.out.println("Successfully added.");
    }

    private int getYear(String prompt) {
        int year = getIntInput(prompt);
        int validLowerYear = LocalDateTime.now().getYear() - 1;
        int validUpperYear = LocalDateTime.now().getYear() + 1;
        if(year < validLowerYear | year > validUpperYear) {
            System.out.println("Sorry, " + year + "is not a valid year");
            getYear(prompt);
        }
        return year;
    }

    private void dueCommand() {
        for (Assignment assignment : assignments) {
            System.out.println();
            System.out.println("Assignment " + assignment.getId() + ": " + assignment.getName());
            System.out.println("Due on " + DateFormatter.DueOn(assignment.getDueDate()));
            System.out.println("Due in " + DateFormatter.DueIn(assignment.getDueDate()));
            System.out.println("For your " + assignment.getClassName() + " class");
        }
    }

    private void completeCommand() {    
        System.out.println();
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

    private void invalidCommand() {
        System.out.println();
        System.out.println("Sorry, I don't recognize that command.");
    }

}
