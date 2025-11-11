package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.global_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiError {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final Object details;

    public ApiError(HttpStatus status, String error, Object details) {
        this.status = status.value();
        this.error = error;
        this.details = details;
    }
}