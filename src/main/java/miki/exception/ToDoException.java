package miki.exception;

/**
 * Represents an exception that occurs when a todo task fails.
 */
public class ToDoException extends TaskException {
    public ToDoException() {
        super();
    }

    public ToDoException(String message) {
        super(message
                + "Try the following format:\n"
                + "Todo     : Todo {TASK DESCRIPTION}");
    }
}
