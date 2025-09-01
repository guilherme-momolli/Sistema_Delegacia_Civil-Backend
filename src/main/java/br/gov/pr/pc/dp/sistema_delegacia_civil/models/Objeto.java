package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "objeto")
public class Objeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "bem_id", unique = true, nullable = false)
    private Bem bem;

    @Column(name = "tipo_objeto", length = 25)
    private String tipoObjeto;

    @Column(name = "numero_serie", length = 50)
    private String numeroSerie;

    @Column(length = 50)
    private String cor;

    @Column(length = 50)
    private String material;

    @Column(length = 100)
    private String dimensoes;

    @Column(name = "estado_conservacao", length = 25)
    private String estadoConservacao;

    @Column(name = "situacao_objeto", length = 50)
    private String situacaoObjeto;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
