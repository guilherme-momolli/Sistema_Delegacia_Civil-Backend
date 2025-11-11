package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.inquerito_policial;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.error.ErrorType;


public class InqueritoPolicialException extends RuntimeException {

    private final ErrorType errorType;
    private final String userMessage;
    private final String developerMessage;

    public InqueritoPolicialException(ErrorType errorType, String userMessage) {
        super(userMessage);
        this.errorType = errorType;
        this.userMessage = userMessage;
        this.developerMessage = null;
    }

    public InqueritoPolicialException(ErrorType errorType, String userMessage, String developerMessage) {
        super(userMessage);
        this.errorType = errorType;
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    public InqueritoPolicialException(ErrorType errorType, String userMessage, Throwable cause) {
        super(userMessage, cause);
        this.errorType = errorType;
        this.userMessage = userMessage;
        this.developerMessage = cause.getMessage();
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}