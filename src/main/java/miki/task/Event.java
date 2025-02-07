package miki.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
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
                + start.getYear() + ", "
                + String.format("%02d", start.getHour()) + ":"
                + String.format("%02d", start.getMinute())
                + "  to  "
                + end.getDayOfMonth() + " "
                + end.getMonth() + " "
                + end.getYear() + ", "
                + String.format("%02d", end.getHour()) + ":"
                + String.format("%02d", end.getMinute());
    }
}
