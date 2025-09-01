package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.TipoEnvolvimento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "pessoa_envolvimento")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaEnvolvimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boletim_id") // pode ser null
    @JsonBackReference("boletim-pessoas")
    private BoletimOcorrencia boletimOcorrencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquerito_id") // pode ser null
    @JsonBackReference("inquerito-pessoas")
    private InqueritoPolicial inqueritoPolicial;


    @Column(name = "tipo_envolvimento", length = 50)
    @Enumerated(EnumType.STRING)
    private TipoEnvolvimento tipoEnvolvimento;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}