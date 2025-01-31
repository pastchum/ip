package miki.task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
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
                "        Deadline: " + deadline;
    }
}
