package miki.exception;

public class EventException extends TaskException {
    public EventException() {
        super();
    }

    public EventException(String message) {
        super(message
                + "Try the following format:\n"
                + "Event    : Event {TASK DESCRIPTION} /from YYYY-MM-DD /to YYYY-MM-DD\n");
    }
}
