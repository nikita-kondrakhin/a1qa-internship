package exceptions;

public class LogFileException extends RuntimeException {
    public LogFileException(String message, Throwable cause) {
        super(message + ": " + cause.getMessage(), cause);
    }
}

