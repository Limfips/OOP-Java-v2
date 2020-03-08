package rpis81.dudka.oop.model;

import java.io.IOException;

public class DuplicateAccountNumberException extends IOException {
    public DuplicateAccountNumberException() {
        super();
    }

    public DuplicateAccountNumberException(String message) {
        super(message);
    }
}
