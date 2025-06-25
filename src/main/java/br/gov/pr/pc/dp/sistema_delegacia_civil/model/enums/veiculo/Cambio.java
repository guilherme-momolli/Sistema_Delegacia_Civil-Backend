package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.veiculo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Cambio {

    AUTOMATICO("Automatico"),
    AUTOMATIZADO("Automatizado"),
    CVT("Transmissão Continuamente Variável - CVT"),
    MANUAL("Manual");

    private String descricao;

    Cambio(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    public static Cambio fromString(String value) {
        for (Cambio c : Cambio.values()) {
            if (c.descricao.equalsIgnoreCase(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Cambio: " + value);
    }
}
