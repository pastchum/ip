package miki.task;

public enum TaskType {
    EVENT,
    DEADLINE,
    TODO;

    @Override
    public String toString() {
        switch (this) {
            case EVENT:
                return "Event";
            case DEADLINE:
                return "Deadline";
            case TODO:
                return "ToDo";
            default:
                throw new IllegalArgumentException("Unknown task type: " + this);
        }
    }
}