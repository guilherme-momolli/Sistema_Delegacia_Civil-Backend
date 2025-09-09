package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.endereco;

public class EnderecoNotFoundException extends RuntimeException {
    public EnderecoNotFoundException(Long id) {
        super("Endereço não encontrado com ID: " + id);
    }
}
