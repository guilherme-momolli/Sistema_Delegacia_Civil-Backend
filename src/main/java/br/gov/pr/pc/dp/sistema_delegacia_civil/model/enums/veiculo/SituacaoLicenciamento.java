package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.veiculo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoLicenciamento {
    ATRASADO("Atrasado"),
    BLOQUEADO("Bloqueado"),
    REGULAR("Regular");

    private String descricao;

    SituacaoLicenciamento(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static SituacaoLicenciamento fromString(String value) {
        for ( SituacaoLicenciamento s : SituacaoLicenciamento.values()) {
            if (s.descricao.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Situação do licenciamento: " + value);
    }

}
