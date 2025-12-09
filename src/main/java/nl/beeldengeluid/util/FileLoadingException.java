package nl.beeldengeluid.util;

import java.io.IOException;

public class FileLoadingException extends RuntimeException {
    public FileLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
