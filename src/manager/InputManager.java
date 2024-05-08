package manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {
    private Scanner scanner = new Scanner(System.in);
    private HomeworkManager homeworkManager;

    public InputManager(HomeworkManager homeworkManager) {
        this.homeworkManager = homeworkManager;
    }
   
    public void mainCycle() {
        
        System.out.println("Please enter your command, or type 'help': ");
        String input = scanner.nextLine();
        runCommand(input);
        mainCycle();
    }

    private void runCommand(String input) {
        switch(input) {
            case "help":
                helpCommand();
            case "add":
                addCommand();
            case "due":
                dueCommand();
            case "complete":
                completeCommand();
            case "quit":
                quitCommand();
            case "cancel":
                cancelCommand();
        }
    }

    private void helpCommand() {
        System.out.println("\n");
        System.out.println(FileManager.readFromFile("help.txt"));
    }

    private void addCommand() {
        System.out.println();
        System.out.println("What is the name of the assignment: ");
        String name = scanner.nextLine();
        System.out.println("What is the name of the class: ");
        String className = scanner.nextLine();
        System.out.println("What year is this due: ");
        int year = scanner.nextInt();
        System.out.println("What month is this due (as a number, please): ");
        int month = scanner.nextInt();
        System.out.println("What day of the month is this due: ");
        int day = scanner.nextInt();
        System.out.println("What hour is this due (use military time, please): ");
        int hour = scanner.nextInt();
        System.out.println("What minute of the hour is this due: ");
        int minute = scanner.nextInt();

        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        homeworkManager.addAssignment(name, className, date);
        ArrayList<Assignment> assignments = homeworkManager.getAssignments();

        System.out.println("Current Assignments: ");
        for (Assignment assignment : assignments) {
            System.out.println(assignment.getName());
            System.out.println(assignment.getClassName());
            System.out.println(assignment.getDueDate());
        }
        
    }

    private void dueCommand() {

    }

    private void completeCommand() {

    }

    private void quitCommand() {
        System.exit(0);
    }

    private void cancelCommand() {

    }
}
