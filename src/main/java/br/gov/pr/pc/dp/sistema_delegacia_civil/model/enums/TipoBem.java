package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoBem {
    ARMA("Arma"),
    DROGA("Droga"),
    OBJETO("Objeto"),
    VEICULO("Veiculo");

    private final String descricao;

    TipoBem(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoBem fromString(String value) {
        for (TipoBem t : TipoBem.values()) {
            if (t.descricao.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Valor inválido para tipo de bem: " + value);
    }


}
