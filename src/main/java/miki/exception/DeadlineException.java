package miki.exception;

public class DeadlineException extends TaskException {
    public DeadlineException() {
        super();
    }

    public DeadlineException(String message) {
        super(message);
    }
}
