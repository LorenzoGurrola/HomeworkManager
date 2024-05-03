package manager;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class HomeworkManager {
    public HomeworkManager() {
        greet();
    }

    public static void main(String[] args) {
        HomeworkManager homeworkManager = new HomeworkManager();
    }

    private void writeToFile(String filePath, String content) {
        try (FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(content);
            System.out.println("String was saved successfully!");
        } catch (IOException e) {
            System.out.println("An error while writing this file occurred:");
            e.printStackTrace();
        }
    }

    private void readFromFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator()); // preserve line breaks
            }

            System.out.println("File content read successfully:");
            System.out.println(contentBuilder.toString());
        } catch (IOException e) {
            System.out.println("An error while reading this file occurred:");
            e.printStackTrace();
        }
    }

    private void greet() {
        Scanner scanner = new Scanner(System.in);
        File file = new File("name.txt");
        if(!file.exists()) {
            createName();
        }
        //continue greeting
    }

    private void createName() {
        
    }

    
}
