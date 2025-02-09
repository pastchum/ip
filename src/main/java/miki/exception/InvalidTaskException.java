package miki.exception;

/**
 * Represents an exception that occurs when a task is not formatted correctly.
 */
public class InvalidTaskException extends TaskException {
    public InvalidTaskException() {
        super();
    }

    public InvalidTaskException(String message) {
        super(message
                + "Use the following formats to set tasks:\n"
                + "ToDo     : ToDo {TASK DESCRIPTION}\n"
                + "Deadline : Deadline {TASK DESCRIPTION} /by YYYY-MM-DD\n"
                + "Event    : Event {TASK DESCRIPTION} /from YYYY-MM-DD /to YYYY-MM-DD\n");
    }
}
