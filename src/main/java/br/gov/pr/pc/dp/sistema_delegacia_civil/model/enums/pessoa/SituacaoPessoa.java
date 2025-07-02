package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoPessoa {

    CONDENADO("Condenado"),
    DESAPARECIDO("Desaparecido"),
    DESCONHECIDO("Desconhecido"),
    FALECIDO("Falecido"),
    FORAJIDO("Forajido"),
    INDICIADO("Indicado"),
    INVESTIGADO("Investigado"),
    PRESO("Preso"),
    SUSPEITO("Suspeito"),
    TESTEMUNHA("Testemunha"),
    RE("Ré");

    private final String descricao;

    SituacaoPessoa(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static SituacaoPessoa fromString(String descricao) {
        for (SituacaoPessoa situacaoPessoa : SituacaoPessoa.values()) {
            if (situacaoPessoa.getDescricao().equals(descricao)) {
                return situacaoPessoa;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Situação: " + descricao);
    }
}
