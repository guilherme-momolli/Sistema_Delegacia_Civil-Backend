package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.global_handler;

public abstract class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}