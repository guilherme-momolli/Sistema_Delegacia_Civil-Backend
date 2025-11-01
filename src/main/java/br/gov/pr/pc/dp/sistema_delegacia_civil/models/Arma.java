package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.Calibre;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.EspecieArma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.SituacaoArmaFogo;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.TipoArmaFogo;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "arma")
public class Arma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bem_id", unique = true, nullable = false)
    private Bem bem;

    @Column(name = "tipo_arma_fogo", length = 50)
    @Enumerated(EnumType.STRING)
    private TipoArmaFogo tipoArmaFogo;

    @Column(name = "situacao_arma_fogo", length = 15)
    @Enumerated(EnumType.STRING)
    private SituacaoArmaFogo situacaoArmaFogo;

    @Column(name= "especie_arma", length = 50)
    @Enumerated(EnumType.STRING)
    private EspecieArma especieArma;

    @Column(length = 255)
    @Enumerated(EnumType.STRING)
    private Calibre calibre;

    @Column(name = "numero_porte", length = 50)
    private String numeroPorte;

    @Column(name = "numero_serie", length = 50)
    private String numeroSerie;

    @Column(name = "numero_registro", length = 50)
    private String numeroRegistro;

    @Column(length = 50)
    private String capacidade;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime updatedAt;
}