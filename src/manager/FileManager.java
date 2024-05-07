package manager;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public void writeToFile(String content, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to this file:");
            e.printStackTrace();
        }
    }

    public String readFromFile(String filePath) {
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

    public void serialize(ArrayList<Assignment> list, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Assignment> deserialize(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Assignment>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
