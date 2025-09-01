package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String exceptionString) {
        super(exceptionString);
    }

    public ResourceNotFoundException(String exceptionString, Throwable cause) {
        super(exceptionString, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}

