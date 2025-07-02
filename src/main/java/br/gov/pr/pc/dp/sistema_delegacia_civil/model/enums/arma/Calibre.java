package br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.arma;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Calibre {

    CALIBRE_10MM_AUTO("10mm Auto"),
    CALIBRE_12_GAUGE("12 Gauge"),
    CALIBRE_16_GAUGE("16 Gauge"),
    CALIBRE_20_GAUGE("20 Gauge"),
    CALIBRE_22_LONG_RIFLE("22 Long Rifle"),
    CALIBRE_22_SHORT("22 Short"),
    CALIBRE_22_WMR("22 Winchester Magnum Rimfire"),
    CALIBRE_223_REMINGTON("223 Remington"),
    CALIBRE_243_WINCHESTER("243 Winchester"),
    CALIBRE_25_ACP("25 ACP"),
    CALIBRE_270_WINCHESTER("270 Winchester"),
    CALIBRE_28_GAUGE("28 Gauge"),
    CALIBRE_30_06_SPRINGFIELD("30-06 Springfield"),
    CALIBRE_30_30_WINCHESTER("30-30 Winchester"),
    CALIBRE_300_BLACKOUT("300 Blackout"),
    CALIBRE_308_WINCHESTER("308 Winchester"),
    CALIBRE_32_ACP("32 ACP"),
    CALIBRE_32_S_W("32 S&W"),
    CALIBRE_32_S_W_LONG("32 S&W Long"),
    CALIBRE_32_GAUGE("32 Gauge"),
    CALIBRE_338_LAPUA_MAGNUM("338 Lapua Magnum"),
    CALIBRE_357_MAGNUM("357 Magnum"),
    CALIBRE_357_SIG("357 SIG"),
    CALIBRE_36_GAUGE("36 Gauge"),
    CALIBRE_380_ACP("380 ACP"),
    CALIBRE_38_SPECIAL("38 Special"),
    CALIBRE_38_SUPER("38 Super"),
    CALIBRE_40_S_W("40 S&W"),
    CALIBRE_410_BORE(".410 Bore"),
    CALIBRE_44_40_WINCHESTER("44-40 Winchester"),
    CALIBRE_44_MAGNUM("44 Magnum"),
    CALIBRE_45_ACP("45 ACP"),
    CALIBRE_45_COLT("45 Colt"),
    CALIBRE_454_CASULL("454 Casull"),
    CALIBRE_458_SOCOM("458 SOCOM"),
    CALIBRE_4_6X30MM("4.6x30mm"),
    CALIBRE_5_45X39MM("5.45x39mm"),
    CALIBRE_5_56_NATO("5.56 NATO"),
    CALIBRE_5_7X28MM("5.7x28mm"),
    CALIBRE_5_8X42MM("5.8x42mm"),
    CALIBRE_50_BMG("50 BMG"),
    CALIBRE_6_5_CREEDMOOR("6.5 Creedmoor"),
    CALIBRE_6_8_SPC("6.8 SPC"),
    CALIBRE_6MM_ARC("6mm ARC"),
    CALIBRE_7_62_NATO("7.62 NATO"),
    CALIBRE_7_62X39MM("7.62x39mm"),
    CALIBRE_7_62X51MM("7.62x51mm"),
    CALIBRE_7_65MM_BROWNING("7.65mm Browning"),
    CALIBRE_7MM_08_REMINGTON("7mm-08 Remington"),
    CALIBRE_7MM_REMINGTON_MAGNUM("7mm Remington Magnum"),
    CALIBRE_8MM_MAUSER("8mm Mauser"),
    CALIBRE_9_3X62MM("9.3x62mm"),
    CALIBRE_9MM_LUGER("9mm Luger");

    private final String descricao;

    Calibre(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public String getDescricao() {
        return descricao;
    }

    @JsonValue
    public static Calibre fromString(String descricao) {
        for (Calibre calibre : Calibre.values()) {
            if (calibre.getDescricao().equals(descricao)) {
                return calibre;
            }
        }
        throw new IllegalArgumentException("Invalid Calibre: " + descricao);
    }
}
