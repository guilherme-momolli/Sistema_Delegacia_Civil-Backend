package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.delagacia;

public class DelegaciaNotFoundException extends RuntimeException {
    public DelegaciaNotFoundException(Long id) {
        super("Delegacia não encontrado com ID: " + id);
    }
}
