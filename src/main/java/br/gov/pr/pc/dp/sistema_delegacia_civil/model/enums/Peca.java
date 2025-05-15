package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Peca {

    APF("Auto de Presião em Flagrante"),
    PORTARIA("Portaria");

    private String descricao;

    Peca(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Peca fromString(String value) {
        for ( Peca p : Peca.values()) {
            if (p.descricao.equalsIgnoreCase(value)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Peça: " + value);
    }

}
