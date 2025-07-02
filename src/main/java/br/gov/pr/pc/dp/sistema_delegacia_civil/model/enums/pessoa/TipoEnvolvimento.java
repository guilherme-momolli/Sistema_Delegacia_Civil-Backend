package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoEnvolvimento {

    VITIMA("Vitima"),
    INVESTIGADO("Investigado"),
    AUTOR("Autor"),
    RELATOR("Relator");

    private final String descricao;

    TipoEnvolvimento(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoEnvolvimento fromString(String papel) {
        for (TipoEnvolvimento value : TipoEnvolvimento.values()) {
            if (value.name().equalsIgnoreCase(papel)) {
                return value;
            }
        }
        return null;
    }
}
