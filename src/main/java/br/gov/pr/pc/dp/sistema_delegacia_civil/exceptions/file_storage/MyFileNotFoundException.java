package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MyFileNotFoundException(String exceptionString) {
        super(exceptionString);
    }

    public MyFileNotFoundException(String exceptionString, Throwable cause) {
        super(exceptionString, cause);
    }
}
