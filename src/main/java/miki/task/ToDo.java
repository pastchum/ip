package miki.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toStorageFormat() {
        return "T | " + super.toStorageFormat();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString() + "\n";
    }
}
