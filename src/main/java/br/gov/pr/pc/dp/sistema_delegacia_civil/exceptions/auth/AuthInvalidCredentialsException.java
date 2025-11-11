package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.auth;

public class AuthInvalidCredentialsException extends RuntimeException {
    public AuthInvalidCredentialsException() {
        super("Credenciais inválidas");
    }
}
