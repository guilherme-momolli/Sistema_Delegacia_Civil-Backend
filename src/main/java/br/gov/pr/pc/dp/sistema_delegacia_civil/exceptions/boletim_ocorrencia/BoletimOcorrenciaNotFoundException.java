package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.boletim_ocorrencia;

public class BoletimOcorrenciaNotFoundException extends RuntimeException {
    public BoletimOcorrenciaNotFoundException(Long id) {
        super("Pessoa não encontrada com ID: " + id);
    }
}
