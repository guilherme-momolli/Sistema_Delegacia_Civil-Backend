package br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoArmaFogo {
    APREENDIDA("Apreendida"),
    CANCELADA("Cancelada"),
    DESTRUIDA("Destruida"),
    DEVOLVIDA("Devolvida"),
    EXTRAVIADA("Extraviada"),
    FURTADA("Furtada"),
    IRREGULAR("Irregular"),
    REGISTRADA("Registrada"),
    REGULARIZADA("Regularizada"),
    ROUBADA("Roubada"),
    SEM_REGISTRO("Sem Registro");

    private final String descricao;

    SituacaoArmaFogo(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static SituacaoArmaFogo fromString(String descricao) {
        for (SituacaoArmaFogo situacao : SituacaoArmaFogo.values()) {
            if (situacao.getDescricao().equals(descricao)) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Invalid Situacao da Arma de Fogo: " + descricao);
    }
}
