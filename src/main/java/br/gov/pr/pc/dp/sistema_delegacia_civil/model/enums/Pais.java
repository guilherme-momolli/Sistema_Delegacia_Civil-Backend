package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Pais {

    ARGENTINA("Argentina"),
    BRASIL("Brasil"),
    Paraguai("Paraguai");

    private String descricao;

    Pais(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Pais fromString(String value) {
        for ( Pais p : Pais.values()) {
            if (p.descricao.equalsIgnoreCase(value)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Valor inválido para País: " + value);
    }
}
