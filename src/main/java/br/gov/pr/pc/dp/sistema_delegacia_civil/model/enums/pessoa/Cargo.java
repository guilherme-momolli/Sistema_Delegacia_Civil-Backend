package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Cargo {

    AGENTE("Agente"),
    ATENDENTE("Atendente"),
    DELEGADO("Delegado"),
    ESCRIVÃO("Escrivão"),
    ESTAGIARIO("Estágiario"),
    INVESTIGADOR("Investigado"),
    PERITO("Perito"),
    PAPILOSCOPISTA("Papiloscotista"),
    TI("Tecnologo da Informação - T.I");

    private final String descricao;

    Cargo(String descricao) {
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
        throw new IllegalArgumentException("Valor inválido para cargo: " + descricao);
    }
}
