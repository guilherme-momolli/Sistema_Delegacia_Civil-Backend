package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Situacao {

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
    RE("RE");

    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Situacao fromString(String descricao) {
        for (Situacao situacao : Situacao.values()) {
            if (situacao.getDescricao().equals(descricao)) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Situação: " + descricao);
    }
}
