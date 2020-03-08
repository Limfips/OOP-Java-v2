package rpis81.dudka.oop.model;

public class InvalidAccountNumberException extends RuntimeException {
    public InvalidAccountNumberException() {
        super();
    }

    public InvalidAccountNumberException(String message) {
        super(message);
    }
}
