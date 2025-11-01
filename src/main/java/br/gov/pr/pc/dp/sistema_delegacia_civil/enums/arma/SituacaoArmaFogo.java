package br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoArmaFogo {

    CANCELADA,
    IRREGULAR,
    REGISTRADA,
    REGULARIZADA,
    SEM_REGISTRO;

}
