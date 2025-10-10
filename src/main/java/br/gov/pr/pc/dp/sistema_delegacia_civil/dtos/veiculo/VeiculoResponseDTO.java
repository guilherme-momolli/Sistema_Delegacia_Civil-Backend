package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoResponseDTO {

    private Long id;
    private Long bemId;
    private String renavam;
    private String placa;
    private String chassi;
    private String numeroMotor;
    private String tipoVeiculo;
    private String categoria;
    private String especie;
    private String anoModelo;
    private String anoFabricacao;
    private String combustivel;
    private String cambio;
    private String tipoTracao;
    private String corPredominante;
    private String carroceria;
    private Integer numeroEixos;
    private BigDecimal capacidadeCarga;
    private BigDecimal potenciaMotor;
    private BigDecimal cilindrada;
    private BigDecimal pesoBruto;
    private String ufRegistro;
    private String municipioRegistro;
    private String situacaoVeiculo;
    private String situacaoLicenciamento;
    private String restricaoJudicial;
    private LocalDateTime dataPrimeiroLicenciamento;
    private String numeroCrv;
    private String numeroCrlv;
    private String tabelaFipe;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}