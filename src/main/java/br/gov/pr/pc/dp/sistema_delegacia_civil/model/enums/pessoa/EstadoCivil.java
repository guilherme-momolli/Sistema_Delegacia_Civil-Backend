package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoCivil {

    CASADO("Casado"),
    DIVORCIADO("Divorciado"),
    SEPARADO("Separado"),
    SOLTEIRO("Solteiro"),
    UNIAO_ESTAVEL("União Estavel"),
    VIUVO("Viúvo");

    private String descricao;

    EstadoCivil(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static EstadoCivil fromString(String value) {
        for (EstadoCivil c : EstadoCivil.values()) {
            if (c.descricao.equalsIgnoreCase(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Estado Civil: " + value);
    }
}
