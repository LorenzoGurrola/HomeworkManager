package manager;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.Duration;

public class DateFormatter {
    public static String DueOn(LocalDateTime dateTime) {
        return dateTime.getMonth().name() + " " + dateTime.getDayOfMonth() + " at " + dateTime.getHour() + ":" + dateTime.getMinute();
    }

    public static String DueIn(LocalDateTime dueDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, dueDate);
        Period period = Period.between(now.toLocalDate(), dueDate.toLocalDate());
        int days = period.getDays() - 1;
        int hours = duration.toHoursPart();
        return days + " days and " + hours + " hours";
    }
}
