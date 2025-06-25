package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.arma;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EspecieArma {
    ARMA_ARTESANAL("Arma Artesanal"),
    ARMA_AIRSOFT("Arma Airsoft"),
    ARMA_COMBINADA("Arma Combinada"),
    ARMA_FOGO("Arma de Fogo"),
    ARMA_NAO_LETAL("Arma Nao Letal"),
    ARMA_PRESSAO("Arma de Pressao"),
    ARMA_SIMULACRO("Arma Simulacro");

    private final String descricao;

    EspecieArma(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static EspecieArma fromString(String descricao) {
        for (EspecieArma especie : EspecieArma.values()) {
            if (especie.getDescricao().equals(descricao)) {
                return especie;
            }
        }
        throw new IllegalArgumentException("Invalid Especie de Arma: " + descricao);
    }


}
