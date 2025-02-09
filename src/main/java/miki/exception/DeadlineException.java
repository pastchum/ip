package miki.exception;

/**
 * Represents an exception that occurs when a deadline task is not formatted
 * correctly.
 */
public class DeadlineException extends TaskException {
    public DeadlineException() {
        super();
    }

    public DeadlineException(String message) {
        super(message
                + "Try the following format:\n"
                + "Deadline  : Deadline {TASK DESCRIPTION} /by YYYY-MM-DD HH:MM\n");
    }
}
