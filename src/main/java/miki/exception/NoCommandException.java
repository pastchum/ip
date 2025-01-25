package miki.exception;

public class NoCommandException extends MikiException {
    public NoCommandException() {
    }

    public NoCommandException(String message) {
        super(message);
    }
}