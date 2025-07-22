package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoBem {
    APREENDIDO("Apreendido"),
    DEVOLVIDO("Devolvido"),
    DANIFICADO("Danificado"),
    DESTRUIDO("Destruido"),
    EXTRAVIADO("Extraviado"),
    PERDA_TOTAL("Perda total"),
    ROUBADO("Roubado");

    private final String descricao;

    SituacaoBem(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static SituacaoBem fromString(String value) {
        for (SituacaoBem s : SituacaoBem.values()) {
            if (s.descricao.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para situação de bem: " + value);
    }
}
