package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.delegacia;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoInquerito {

    ANDAMENTO("Andamento"),
    FINALIZADO("Finalizado"),
    DENUNCIADO("Denunciado"),
    RELATADO("Relatado");

    private final String descricao;

    SituacaoInquerito(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static SituacaoInquerito fromString(String value) {
        for (SituacaoInquerito s : SituacaoInquerito.values()) {
            if (s.descricao.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para situação: " + value);
    }
}
