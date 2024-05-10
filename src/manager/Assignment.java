package manager;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Assignment implements Serializable{
    private String name;
    private String className;
    private LocalDateTime dueDate;
    private int id;
    
    public Assignment(String name, String className, LocalDateTime dueDate, int id) {
        this.name = name;
        this.className = className;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
}
