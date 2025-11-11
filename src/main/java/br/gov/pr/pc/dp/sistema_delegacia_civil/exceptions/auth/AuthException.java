package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.auth;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthException extends RuntimeException {
    private final HttpStatus status;
    private final String code;

    public AuthException(String message, HttpStatus status, String code) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public static AuthException invalidCredentials() {
        return new AuthException("Credenciais inválidas", HttpStatus.UNAUTHORIZED, "AUTH_001");
    }

    public static AuthException userNotFound() {
        return new AuthException("Usuário não encontrado", HttpStatus.NOT_FOUND, "AUTH_002");
    }
}