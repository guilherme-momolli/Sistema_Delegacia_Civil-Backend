package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.pessoa;

public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException(Long id) {
        super("Pessoa não encontrada com ID: " + id);
    }
}
