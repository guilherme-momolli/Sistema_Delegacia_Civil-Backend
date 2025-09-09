package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.inquerito_policial;

public class InqueritoPolicialNotFoundException extends RuntimeException {
    public InqueritoPolicialNotFoundException(Long id) {
        super("Inquerito Policial não encontrado com ID: " + id);
    }
}
