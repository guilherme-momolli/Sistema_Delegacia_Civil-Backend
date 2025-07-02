package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.delegacia;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoApreensao {

    ARMA("Arma"),
    DROGA("Droga"),
    OBJETO("Objeto"),
    VEICULO("Veiculo");

    private final String descricao;

    TipoApreensao(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoApreensao fromString(String value) {
        for (TipoApreensao t : TipoApreensao.values()) {
            if (t.descricao.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Valor inválido para tipo de apreensão: " + value);
    }
}
