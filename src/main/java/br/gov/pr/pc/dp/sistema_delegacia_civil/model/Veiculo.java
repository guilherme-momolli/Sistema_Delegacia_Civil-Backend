package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.veiculo.*;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imagem_url")
    private String imagemUrl;

    private String renavam;

    private String placa;

    private String chassi;

    @Column(name = "numero_motor")
    private String numeroMotor;

    @Column(name = "tipo_veiculo")
    private String tipoVeiculo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Especie especie;

    private String marca;

    private String modelo;

    @Column(name = "ano_modelo")
    private String anoModelo;

    @Column(name = "ano_fabricacao")
    private String anoFabricacao;

    @Enumerated(EnumType.STRING)
    private Combustivel combustivel;

    @Enumerated(EnumType.STRING)
    private Cambio cambio;

    @Column(name = "tipo_tracao")
    private String tipoTracao;

    @Column(name = "cor_predominante")
    private String corPredominante;

    @Enumerated(EnumType.STRING)
    private Carroceria carroceria;

    @Column(name = "numero_eixos")
    private Integer numeroEixos;

    @Column(name = "capacidade_carga", precision = 15, scale = 3)
    private BigDecimal capacidadeCarga;

    @Column(name = "potencia_motor", precision = 15, scale = 3)
    private BigDecimal potenciaMotor;

    @Column(precision = 15, scale = 3)
    private BigDecimal cilindrada;

    @Column(name = "peso_bruto", precision = 15, scale = 3)
    private BigDecimal pesoBruto;

    @Column(name = "uf_registro")
    private String ufRegistro;

    @Column(name = "municipio_registro")
    private String municipioRegistro;

    @Column(name = "situacao_veiculo")
    private String situacaoVeiculo;

    @Column(name = "situacao_licenciamento")
    private String situacaoLicenciamento;

    @Column(name = "restricao_judicial")
    private String restricaoJudicial;

    @Column(name = "data_primeiro_licenciamento")
    private LocalDate dataPrimeiroLicenciamento;

    @Column(name = "numero_crv")
    private String numeroCrv;

    @Column(name = "numero_crlv")
    private String numeroCrlv;

    @Column(name = "tabela_fipe")
    private String tabelaFipe;

    @ManyToOne
    @JoinColumn(name = "inquerito_policial_id")
    private InqueritoPolicial inqueritoPolicial;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Pessoa propietario;

    @ManyToOne
    @JoinColumn(name = "boletim_ocorrencia_id")
    private BoletimOcorrencia boletimOcorrencia;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;
}
