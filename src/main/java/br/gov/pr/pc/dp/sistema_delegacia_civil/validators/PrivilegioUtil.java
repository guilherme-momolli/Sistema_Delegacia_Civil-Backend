package br.gov.pr.pc.dp.sistema_delegacia_civil.validators;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.Privilegio;

public class PrivilegioUtil {

    public static boolean temPrivilegioSuperior(Privilegio executor, Privilegio alvo) {
        return executor.ordinal() > alvo.ordinal();
    }

    public static boolean temPrivilegioSuperiorOuIgual(Privilegio executor, Privilegio alvo) {
        return executor.ordinal() >= alvo.ordinal();
    }
}
