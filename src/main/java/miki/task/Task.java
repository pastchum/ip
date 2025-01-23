package miki.task;

public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void toggleCompletion() {
        this.isCompleted = !this.isCompleted;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "]" + description;
    };
}
