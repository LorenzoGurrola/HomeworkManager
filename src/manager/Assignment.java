package manager;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Assignment implements Serializable{
    String name;
    String className;
    LocalDateTime dueDateTime;
    
    public Assignment(String name, String className, LocalDateTime dueDateTime) {
        this.name = name;
        this.className = className;
        this.dueDateTime = dueDateTime;
    }
    
}
