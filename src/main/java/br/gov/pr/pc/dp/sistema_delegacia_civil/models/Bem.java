package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.SituacaoBem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.TipoBem;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "bem")
public class Bem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_bem", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TipoBem tipoBem;

    @Column(name = "imagem_url", length = 255)
    private String imagemUrl;

    private String marca;

    private String modelo;

    @Column(name = "valor_estimado", precision = 15, scale = 2)
    private BigDecimal valorEstimado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delegacia_id")
    private Delegacia delegacia;

    @Column(name = "situacao_bem", length = 50)
    @Enumerated(EnumType.STRING)
    private SituacaoBem situacaoBem;

    @Column(length = 100)
    private String origem;

    @Column(name = "numero_lacre", length = 50)
    private String numeroLacre;

    @Column(name = "local_bem", length = 50)
    private String localBem;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "bem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Objeto objeto;

    @OneToOne(mappedBy = "bem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Veiculo veiculo;

    @OneToOne(mappedBy = "bem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Droga droga;

    @OneToOne(mappedBy = "bem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Arma arma;

    @OneToMany(mappedBy = "bem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BemEnvolvimento> bemEnvolvimentos;

}
