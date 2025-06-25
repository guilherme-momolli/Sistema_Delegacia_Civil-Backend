package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.arma;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoArmaFogo {

    // Armas curtas
    REVOLVER("Revólver"),
    PISTOLA("Pistola"),
    GARRUCHA("Garrucha"),
    DERRINGER("Derringer"),

    // Armas longas
    ESPINGARDA("Espingarda"),
    CARABINA("Carabina"),
    FUZIL("Fuzil"),
    METRALHADORA("Metralhadora"),
    RIFLE("Rifle"),
    ESCOPETA("Escopeta"),

    // Outras
    SUBMETRALHADORA("Submetralhadora"),
    MOSQUETAO("Mosquetão"),
    LANCA_GRANADAS("Lança-granadas"),
    LANCA_FOGUETES("Lança-foguetes"),
    PISTOLA_METRALHADORA("Pistola-metralhadora");

    private final String descricao;

    TipoArmaFogo(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoArmaFogo fromString(String value) {
        for (TipoArmaFogo tipo : TipoArmaFogo.values()) {
            if (tipo.descricao.equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido para TipoArmaFogo: " + value);
    }
}
