package miki.task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toStorageFormat() {
        return "E | " + super.toStorageFormat()
                + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "\n" +
                "        From  "
                + start.getDayOfMonth() + " "
                + start.getMonth() + " "
                + start.getYear()
                + "  to  "
                + end.getDayOfMonth() + " "
                + end.getMonth() + " "
                + end.getYear();
    }
}
