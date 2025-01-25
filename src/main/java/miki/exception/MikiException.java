package miki.exception;

public class MikiException extends Exception {
    public MikiException() {
        super();
    }

    public MikiException(String message) {
        super("Error Occured Dawg.\n" + message);
    }
}
