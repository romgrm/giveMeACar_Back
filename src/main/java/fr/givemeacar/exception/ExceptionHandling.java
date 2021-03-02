package fr.givemeacar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionHandling {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Erreur je sais pas ou")
    public class TestException extends RuntimeException {

    }
}
