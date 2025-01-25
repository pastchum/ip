package miki.exception;

public class DeleteFailedException extends MikiException {
    public DeleteFailedException() {
        super();
    }

    public DeleteFailedException(String message) {
        super("Delete Task failed\n" + message);
    }
}
