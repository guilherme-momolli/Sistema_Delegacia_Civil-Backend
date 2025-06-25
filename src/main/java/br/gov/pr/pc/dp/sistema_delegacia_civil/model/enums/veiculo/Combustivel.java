package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.veiculo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Combustivel {

    DIESEL("Diesel"),
    ELETRICO("Elétrico"),
    ETANOL("Etanol"),
    FLEX("Flex"),
    GASOLINA("Gasolina"),
    GNV("Gás Natural Veiuclar - GNV"),
    HIBRIDO("Hibrido");

    private String descricao;

    Combustivel(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Combustivel fromString(String value) {
        for ( Combustivel c : Combustivel.values()) {
            if (c.descricao.equalsIgnoreCase(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Combustivel: " + value);
    }
}
