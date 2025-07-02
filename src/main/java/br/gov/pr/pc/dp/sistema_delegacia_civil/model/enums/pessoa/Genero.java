package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Genero {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    HOMEM_TRANSGENERO("Homem transgenero"),
    MULHER_TRANSGENERO("Mulher transgenero");

    private final String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    public static Genero fromString(String value) {
        for (Genero g : Genero.values()) {
            if (g.descricao.equalsIgnoreCase(value)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Gênero: " + value);
    }
}
