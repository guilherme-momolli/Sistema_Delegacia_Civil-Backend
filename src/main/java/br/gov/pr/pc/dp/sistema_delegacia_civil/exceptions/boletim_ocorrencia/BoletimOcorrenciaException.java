package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.boletim_ocorrencia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.error.ErrorType;
import lombok.Getter;

@Getter
public class BoletimOcorrenciaException extends RuntimeException {
    private final ErrorType errorType;
    private final String detalhe;

    public BoletimOcorrenciaException(ErrorType errorType, String mensagem, String detalhe) {
        super(mensagem);
        this.errorType = errorType;
        this.detalhe = detalhe;
    }

    public BoletimOcorrenciaException(ErrorType errorType, String mensagem, Throwable causa) {
        super(mensagem, causa);
        this.errorType = errorType;
        this.detalhe = causa.getMessage();
    }
}
