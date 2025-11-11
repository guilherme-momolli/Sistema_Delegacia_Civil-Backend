package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.inquerito_policial;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.error.ErrorType;

public class InqueritoPolicialNotFoundException extends InqueritoPolicialException {

    public InqueritoPolicialNotFoundException(Long id) {
        super(
                ErrorType.NAO_ENCONTRADO,
                "Inquérito Policial não encontrado com ID: " + id
        );
    }

    public InqueritoPolicialNotFoundException(String message) {
        super(ErrorType.NAO_ENCONTRADO, message);
    }
}