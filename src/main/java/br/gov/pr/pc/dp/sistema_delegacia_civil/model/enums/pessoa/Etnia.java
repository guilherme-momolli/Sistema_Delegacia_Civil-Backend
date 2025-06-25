package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Etnia {

    BRANCA("Branca"),
    PRETA("Preta"),
    PARDA("Parda"),
    AMARELA("Amarela"),
    INDIGENA("Indigena");

    private String descricao;

    Etnia(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    public static Etnia fromString(String value) {
        for (Etnia e : Etnia.values()) {
            if (e.descricao.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Etnia: " + value);
    }
}
