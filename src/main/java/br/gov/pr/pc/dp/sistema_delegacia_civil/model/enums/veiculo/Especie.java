package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.veiculo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Especie {

    BLINDADO("Blindado"),
    CARGA("Carga"),
    COLECAO("Colecao"),
    COMPETICAO("Competição"),
    ESPECIAL("Especial"),
    MISTO("Misto"),
    PASSAGEIRO("Passageiro"),
    PASSEIO("Passeio"),
    REBOQUE("Reboque"),
    TRACAO("Traçao");

    private String descricao;

    Especie(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Especie fromString(String value) {
        for (Especie e : Especie.values()) {
            if (e.descricao.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Especie: " + value);
    }

}
