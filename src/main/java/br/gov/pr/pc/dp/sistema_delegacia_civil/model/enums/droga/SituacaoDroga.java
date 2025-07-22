package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.droga;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoDroga {
    APREENDIDA("Apreendida"),
    ENCAMINHADA_PARA_PERICIA("Encaminhada para pericia"),
    PERICIADA("Periciada"),
    DESTRUICAO_AUTORIZADA("Destruição autorizada"),
    DESTRUICAO_REALIZADA("Destruição realizada"),
    ARMAZENADA("Armazenada"),
    DEVOLVIDA("Devolvida"),
    OUTRA("Outra");

    private final String descricao;

    SituacaoDroga(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static SituacaoDroga fromString(String value) {
        for (SituacaoDroga s : SituacaoDroga.values()) {
            if (s.descricao.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para situação de droga: " + value);
    }
}