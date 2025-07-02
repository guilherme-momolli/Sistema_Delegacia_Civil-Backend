package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoInstituicao {

    DELEGACIA("Delegacia"),
    PRIVADA("Privada"),
    PUBLICA("Pública");

    private final String descricao;

    TipoInstituicao(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoInstituicao fromString(String value) {
        for (TipoInstituicao t : TipoInstituicao.values()) {
            if (t.descricao.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Valor inválido para tipo de insituição: " + value);
    }
}
