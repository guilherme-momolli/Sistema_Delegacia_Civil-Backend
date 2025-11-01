package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.veiculo;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.endereco.UF;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.veiculo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private TipoVeiculo tipoVeiculo;
    private CategoriaVeiculo categoria;
    private EspecieVeiculo especieVeiculo;
    private LocalDate anoModelo;
    private LocalDate anoFabricacao;
    private Combustivel combustivel;
    private Cambio cambio;
    private TipoTracao tipoTracao;
    private String corPredominante;
    private Carroceria carroceria;
    private Integer numeroEixos;
    private BigDecimal capacidadeCarga;
    private BigDecimal potenciaMotor;
    private BigDecimal cilindrada;
    private BigDecimal pesoBruto;
    private UF ufRegistro;
    private String municipioRegistro;
    private SituacaoVeiculo situacaoVeiculo;
    private SituacaoLicenciamento situacaoLicenciamento;
    private String restricaoJudicial;
    private LocalDate dataPrimeiroLicenciamento;
    private String numeroCrv;
    private String numeroCrlv;
    private String tabelaFipe;
}