package br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.auth;

import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.global_handler.BusinessException;

public class AuthUserNotFoundException extends BusinessException {
    public AuthUserNotFoundException() {
        super("Usuário não encontrado");
    }
}
