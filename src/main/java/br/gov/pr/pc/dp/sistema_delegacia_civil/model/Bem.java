package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.TipoBem;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Data
@Entity
@Table(name = "bem")
public class Bem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_bem", nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private TipoBem tipoBem;

    @Column(name = "imagem_url", length = 255)
    private String imagemUrl;

    @Column(length = 255)
    private String marca;

    @Column(length = 255)
    private String modelo;

    @Column(name = "valor_estimado", precision = 15, scale = 2)
    private BigDecimal valorEstimado;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "delegacia_id")
    private Delegacia delegacia;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @Column(name = "situacao_bem", length = 50)
    private String situacaoBem;

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

    @Column(nullable = false)
    private Boolean ativo = true;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}
