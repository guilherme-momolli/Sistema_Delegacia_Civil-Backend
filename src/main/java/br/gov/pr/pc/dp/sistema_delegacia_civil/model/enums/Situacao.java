package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Situacao {

    ANDAMENTO("Andamento"),
    FINALIZADO("Finalizado"),
    DENUNCIADO("Denunciado"),
    RELATADO("Relatado");

    private final String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Situacao fromString(String value) {
        for (Situacao s : Situacao.values()) {
            if (s.descricao.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Situação: " + value);
    }
}
