package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;


@Data
@Entity
@Table(name = "bem_movimentacao")
public class BemMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bem_id", nullable = false)
    private Bem bem;

    @Column(length = 50)
    private String origem;

    @ManyToOne
    @JoinColumn(name = "destino_delegacia_id")
    private Delegacia destinoDelegacia;

    @Column(name = "data_movimentacao", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dataMovimentacao;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "usuario_responsavel_id")
    private Usuario usuarioResponsavel;
}
