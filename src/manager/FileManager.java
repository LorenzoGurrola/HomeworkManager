package manager;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FileManager {
    public static void writeToFile(String content, String filePath, boolean includeNewLine, boolean append) {
        try (FileWriter fileWriter = new FileWriter(filePath, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
            if(includeNewLine) {
                bufferedWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to this file:");
            e.printStackTrace();
        }
    }

    public static String readFromFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator()); // preserve line breaks
            }
            return contentBuilder.toString();
        } catch (IOException e) {
            System.out.println("An error occurred while reading this file:");
            e.printStackTrace();
            return "";
        }
    }

    public static String readSingleLine(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contentBuilder.append(line);
            }
            return contentBuilder.toString();
        } catch (IOException e) {
            System.out.println("An error occurred while reading this file:");
            e.printStackTrace();
            return "";
        }
    }

    public static void saveAssignments(ArrayList<Assignment> assignments, String filePath) {
        FileManager.writeToFile("", filePath, false, false);
        for (Assignment assignment : assignments) {
            writeToFile(assignment.getName(), filePath, true, true);
            writeToFile(assignment.getClassName(), filePath, true, true);
            writeToFile(assignment.getDueDate().toString(), filePath, true, true);
            writeToFile(String.valueOf(assignment.getId()), filePath, true, true);
        }
    }

    public static ArrayList<Assignment> loadAssignments(String filePath) {
        String[] assignmentsFile = readFromFile(filePath).split("\\n");
        ArrayList<Assignment> assignments = new ArrayList<>();
        int count = 1;
        String name = null;
        String className = null;
        LocalDateTime dueDate = null;
        int id = -10;
        for (String s : assignmentsFile) {
            switch(count) {
                case 1:
                    name = s;
                    break;
                case 2:
                    className = s;
                    break;
                case 3:
                    dueDate = LocalDateTime.parse(s);
                    break;
                case 4:
                    id = Integer.parseInt(s);
                    count = 0;
                    assignments.add(new Assignment(name, className, dueDate, id));
                    break;
            }
            count++;
        }
        return assignments;
    }

    // public static void serialize(ArrayList<Assignment> list, String filename) {
    //     try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
    //         out.writeObject(list);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static ArrayList<Assignment> deserialize(String filename) {
    //     try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
    //         return (ArrayList<Assignment>) in.readObject();
    //     } catch (ClassNotFoundException | IOException e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }
}
