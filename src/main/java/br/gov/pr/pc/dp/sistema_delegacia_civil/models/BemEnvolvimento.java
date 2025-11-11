package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.bem.BemTipoEnvolvimento;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boletim_id")
    @JsonBackReference("boletim-bens")
    private BoletimOcorrencia boletimOcorrencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquerito_id")
    @JsonBackReference("inquerito-bens")
    private InqueritoPolicial inqueritoPolicial;

    @Column(name = "tipo_envolvimento", length = 50)
    @Enumerated(EnumType.STRING)
    private BemTipoEnvolvimento tipoEnvolvimento;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @CreationTimestamp
    @Column(name = "data_envolvimento", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDate dataEnvolvimento;
}