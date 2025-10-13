package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "bem_envolvimento")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BemEnvolvimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bem_id", nullable = false)
    private Bem bem;

    @ManyToOne
    @JoinColumn(name = "boletim_id")
    private BoletimOcorrencia boletimOcorrencia;

    @ManyToOne
    @JoinColumn(name = "inquerito_id")
    private InqueritoPolicial inqueritoPolicial;

    @Column(name = "tipo_envolvimento", length = 50)
    private String tipoEnvolvimento;

    @CreationTimestamp
    @Column(name = "data_envolvimento", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDate dataEnvolvimento;
}