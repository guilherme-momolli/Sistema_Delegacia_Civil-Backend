package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.delegacia;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Origem {
    PMPR("Policia Militar do Paraná"),
    PCPR("Policia Civil do Paraná");

    private final String descricao;

    Origem(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Origem fromString(String value) {
        for ( Origem o : Origem.values()) {
            if (o.descricao.equalsIgnoreCase(value)) {
                return o;
            }
        }
        throw new IllegalArgumentException("Valor inválido para origem: " + value);
    }
}
