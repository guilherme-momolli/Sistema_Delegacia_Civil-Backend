package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoDroga {

    ALCOOL("Alcool"),
    CATITONAS("Catitonas"),
    CETAMINA("Cetamina"),
    CHA_DE_LIRO("Cha de liro"),
    COCAINA("Cocaina"),
    COGUMELO("Cogumelo"),
    COLA_DE_SAPATEIRO("Cola de sapateiro"),
    CRACK("Crack"),
    DMT("Dimetiltriptamina"),
    ECSTASY("Ecstasy"),
    FENCICLIDINA("Fenciclidina"),
    HAXIXE("Haxixe"),
    HEROINA("Heroína"),
    K2("K2"),
    K4("K4"),
    K9("K9"),
    KETAMINA("Ketamina"),
    LSD("Lsd"),
    MACONHA("Maconha"),
    MDMA("Mdma"),
    MESCALINA("Mescalina"),
    METANFETAMINA("Metanfetamina"),
    NBOH("Nboh"),
    NBOHME("Nbohme"),
    PASTA_BASE_COCAINA("Pasta base cocaina"),
    PCP("Pcp"),
    PE_DE_MACONHA("Pe de maconha"),
    PO_DE_ANJO("Po de anjo"),
    SOLVENTES_E_INALANTES("Solventes e inalantes"),
    SPECIAL_K("Special k");

    private String descricao;

    TipoDroga(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoDroga fromString(String value) {
        for (TipoDroga t : TipoDroga.values()) {
            if (t.descricao.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Tipo Droga: " + value);
    }
}
