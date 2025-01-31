package miki.task;

import java.time.LocalDate;

import miki.exception.MikiException;

public abstract class Task {
    private String description;
    private boolean isCompleted;
    private TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isCompleted = false;
        this.taskType = taskType;
    }

    // factory method for parsing from file
    public static Task parseTaskFromFile(String line) throws MikiException {
        String[] tokens = line.split(" \\| ");

        try {
            Task task = null;
            if (tokens[0].equals("D")) {
                task = new Deadline(tokens[2], LocalDate.parse(tokens[3]));
            } else if (tokens[0].equals("E")) {
                task = new Event(tokens[2],
                        LocalDate.parse(tokens[3]),
                        LocalDate.parse(tokens[4]));
            } else {
                task = new ToDo(tokens[2]);
            }
            if (tokens[1].equals("1") && task != null) {
                task.toggleCompletion();
            }
            return task;
        } catch (Exception e) {
            throw new MikiException(e.getMessage());
        }
    }

    public void toggleCompletion() {
        this.isCompleted = !this.isCompleted;
    }

    public boolean checkCompleted() {
        return isCompleted;
    }

    public String toStorageFormat() {
        return (isCompleted ? "1" : "0") + " | " + description;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "]  " + description;
    };
}
