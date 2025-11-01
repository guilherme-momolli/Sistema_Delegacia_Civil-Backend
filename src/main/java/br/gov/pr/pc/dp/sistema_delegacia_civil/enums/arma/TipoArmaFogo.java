package br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoArmaFogo {

    // Armas curtas
    REVOLVER,
    PISTOLA,
    GARRUCHA,
    DERRINGER,

    // Armas longas
    ESPINGARDA,
    CARABINA,
    FUZIL,
    METRALHADORA,
    RIFLE,
    ESCOPETA,

    // Outras
    SUBMETRALHADORA,
    MOSQUETAO,
    LANCA_GRANADAS,
    LANCA_FOGUETES,
    PISTOLA_METRALHADORA;

}
