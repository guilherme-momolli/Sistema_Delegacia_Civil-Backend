package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "arma")
public class Arma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "bem_id", unique = true, nullable = false)
    private Bem bem;

    @Column(name = "tipo_arma_fogo", length = 255)
    private String tipoArmaFogo;

    @Column(length = 255)
    private String especie;

    @Column(length = 255)
    private String calibre;

    @Column(name = "numero_porte", length = 255)
    private String numeroPorte;

    @Column(name = "numero_serie", length = 255)
    private String numeroSerie;

    @Column(name = "numero_registro", length = 255)
    private String numeroRegistro;

    @Column(length = 255)
    private String capacidade;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

}
