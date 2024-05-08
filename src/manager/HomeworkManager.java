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
    private InputManager inputManager = new InputManager();
    private Random random = new Random();
    
    private String namePath = "name.txt";
    private String assignmentsPath = "assignments.txt";

    public HomeworkManager() {
        inputManager.addAssignment("Ceramic Piece", "Pottery 101", 
                                   LocalDateTime.of(2024, 5, 21, 12, 0, 0));
        inputManager.addAssignment("500 Word Paper", "English 101", 
                                   LocalDateTime.of(2024, 5, 15, 3, 20, 0));
        inputManager.addAssignment("Data Science Final", "Intro to Data Science", 
                                   LocalDateTime.of(2024, 6, 1, 12, 0));
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

    
}
