package miki.exception;

public class InvalidTaskException extends TaskException {
    public InvalidTaskException() {
        super();
    }

    public InvalidTaskException(String message) {
        super(message
                + "Use the following formats to set tasks:\n"
                + "ToDo     : ToDo {TASK DESCRIPTION}\n"
                + "Deadline : Deadline {TASK DESCRIPTION} /by {DEADLINE}\n"
                + "Event    : Event {TASK DESCRIPTION} /from {START} /to {END}");
    }
}
