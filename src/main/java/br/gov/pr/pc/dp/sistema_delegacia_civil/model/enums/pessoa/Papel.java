package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Papel {

    VITIMA("Vitima"),
    INVESTIGADO("Investigado"),
    AUTOR("Autor"),
    RELATOR("Relator");

    private String descricao;

    Papel(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Papel fromString(String papel) {
        for (Papel value : Papel.values()) {
            if (value.name().equalsIgnoreCase(papel)) {
                return value;
            }
        }
        return null;
    }
}
