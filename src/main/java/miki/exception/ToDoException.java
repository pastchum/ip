package miki.exception;

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
