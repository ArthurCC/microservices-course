package fr.camposcosta.customerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FatalException extends RuntimeException {
    public FatalException(String message) {
        super(message);
    }
}
