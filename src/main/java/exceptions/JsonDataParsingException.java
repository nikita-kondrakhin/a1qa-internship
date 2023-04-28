package exceptions;

public class JsonDataParsingException extends RuntimeException {
    public JsonDataParsingException(String message) {
        super(message);
    }

    public JsonDataParsingException(String message, Throwable cause) {
        super(message + ": " + cause.getMessage(), cause);
    }
}
