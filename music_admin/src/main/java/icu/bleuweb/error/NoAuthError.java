package icu.bleuweb.error;

public class NoAuthError extends RuntimeException {
    public NoAuthError(String message) {
        super(message);
    }
}
