package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.UnidadeMedida;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.droga.TipoDroga;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "droga")
public class Droga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "bem_id", unique = true, nullable = false)
    private Bem bem;

    @Column(name = "tipo_droga", length = 50)
    @Enumerated(EnumType.STRING)
    private TipoDroga tipoDroga;

    @Column(name = "nome_popular", length = 255)
    private String nomePopular;

    @Column(name = "unidade_medida", length = 50)
    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    private BigDecimal quantidade;

    @Column(name = "quantidade_por_extenso", columnDefinition = "TEXT")
    private String quantidadePorExtenso;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
