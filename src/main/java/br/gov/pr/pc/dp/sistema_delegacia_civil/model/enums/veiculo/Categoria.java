package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.veiculo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Categoria {

    ALUGUEL("Aluguel"),
    APRENDIZAGEM("Aprendizagem"),
    CARGA("Carga / Transporte escolar / Emergência"),
    COLECIONADOR("Colecionador"),
    COMERCIAL("Comercial"),
    DIPLOMATICO("Diplomatico"),
    EXPERIENCIA("Experiencia"),
    MISSAO("Missão"),
    OFICIAL("Oficial"),
    PARTICULAR("Particular");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Categoria fromString(String value) {
        for (Categoria c : Categoria.values()) {
            if (c.descricao.equalsIgnoreCase(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Categoria: " + value);
    }
}
