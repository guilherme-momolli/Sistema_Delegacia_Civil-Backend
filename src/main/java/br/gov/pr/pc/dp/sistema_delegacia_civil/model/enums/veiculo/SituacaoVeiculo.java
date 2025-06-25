package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.veiculo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoVeiculo {
    ALIENADO("Alienado"),
    ARRENDADO("Arrendado"),
    BAIXADO("Baixado"),
    COMUNICADO("Comunicado de venda"),
    FURTADO("Furtado / Roubo"),
    LEILAO("Leilão"),
    NORMAL("Normal"),
    PROCESSO_BAIXA("Processo de baixa"),
    REBOQUE("Reboque / em Pátio"),
    RECUPERADO("Recuperado"),
    RESERVA_DOMINIO("Reserva de dominio"),
    RESTRICAO_ADMISTRATIVA("Restricao administrativa"),
    RESTRICAO_JUDICIAL("Restricao judicial"),
    RETIDO("Retido para regularização"),
    SINISTRADO("Sinistrado");

    private String descricao;

    SituacaoVeiculo(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static SituacaoVeiculo fromString(String value) {
        for ( SituacaoVeiculo s : SituacaoVeiculo.values()) {
            if (s.descricao.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Situação do veiculo: " + value);
    }

}
