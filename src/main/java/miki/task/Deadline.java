package miki.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toStorageFormat() {
        return "D | " + super.toStorageFormat()
                + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "\n" +
                "        Deadline: "
                + deadline.getDayOfMonth() + " "
                + deadline.getMonth() + " "
                + deadline.getYear();
    }
}
