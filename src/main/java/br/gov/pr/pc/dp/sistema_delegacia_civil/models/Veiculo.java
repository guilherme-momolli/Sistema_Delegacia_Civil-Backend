package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.endereco.UF;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.veiculo.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "bem_id", unique = true, nullable = false)
    private Bem bem;

    @Column(length = 11)
    private String renavam;

    @Column(length = 7)
    private String placa;

    @Column(length = 17)
    private String chassi;

    @Column(name = "numero_motor", length = 25)
    private String numeroMotor;

    @Column(name = "tipo_veiculo", length = 50)
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private CategoriaVeiculo categoria;

    @Column(name ="especie_veiculo", length = 50)
    @Enumerated(EnumType.STRING)
    private EspecieVeiculo especieVeiculo;

    @Column(name = "ano_modelo", length = 4)
    private LocalDate anoModelo;

    @Column(name = "ano_fabricacao", length = 4)
    private LocalDate anoFabricacao;

    @Column(length = 25)
    @Enumerated(EnumType.STRING)
    private Combustivel combustivel;

    @Column(length = 11)
    @Enumerated(EnumType.STRING)
    private Cambio cambio;

    @Column(name = "tipo_tracao", length = 25)
    @Enumerated(EnumType.STRING)
    private  TipoTracao tipoTracao;

    @Column(name = "cor_predominante", length = 50)
    private String corPredominante;

    @Column(length = 50)
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

    @Column(name = "uf_registro", length = 2)
    @Enumerated(EnumType.STRING)
    private UF ufRegistro;

    @Column(name = "municipio_registro", length = 28)
    private String municipioRegistro;

    @Column(name = "situacao_veiculo", length = 50)
    @Enumerated(EnumType.STRING)
    private SituacaoVeiculo situacaoVeiculo;

    @Column(name = "situacao_licenciamento", length = 50)
    @Enumerated(EnumType.STRING)
    private SituacaoLicenciamento situacaoLicenciamento;

    @Column(name = "restricao_judicial", length = 50)
    private String restricaoJudicial;

    @Column(name = "data_primeiro_licenciamento")
    private LocalDate dataPrimeiroLicenciamento;

    @Column(name = "numero_crv", length = 50)
    private String numeroCrv;

    @Column(name = "numero_crlv", length = 50)
    private String numeroCrlv;

    @Column(name = "tabela_fipe", length = 50)
    private String tabelaFipe;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}