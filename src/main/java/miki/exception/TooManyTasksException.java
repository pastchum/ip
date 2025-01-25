package miki.exception;

public class TooManyTasksException extends MikiException {
    public TooManyTasksException() {
        super();
    }

    public TooManyTasksException(String message) {
        super(message);
    }
}
