package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UnidadeMedida {

    // SÓLIDOS (massa)
    GRAMA("g"),
    QUILOGRAMA("kg"),
    MILIGRAMA("mg"),
    TONELADA("t"),

    // SÓLIDOS (quantidade/unidade)
    UNIDADE("unidade"),
    DUZIA("dúzia"),
    CENTENA("centena"),
    MILHEIRO("milheiro"),

    // LÍQUIDOS (volume)
    MILILITRO("ml"),
    LITRO("l"),
    CENTIMETRO_CUBICO("cm³"),
    METRO_CUBICO("m³"),
    DECILITRO("dl"),
    CENTILITRO("cl");

    private final String descricao;

    UnidadeMedida(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static UnidadeMedida fromString(String value) {
        for (UnidadeMedida unidade : UnidadeMedida.values()) {
            if (unidade.descricao.equalsIgnoreCase(value)) {
                return unidade;
            }
        }
        throw new IllegalArgumentException("Valor inválido para UnidadeMedida: " + value);
    }
}
