package miki.exception;

public class ToDoException extends TaskException {
    public ToDoException() {
        super();
    }

    public ToDoException(String message) {
        super("Error Occured Dawg.\n" + message);
    }
}
