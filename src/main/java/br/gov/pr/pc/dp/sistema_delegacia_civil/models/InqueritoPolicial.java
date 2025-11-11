package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.Peca;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "inquerito_policial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InqueritoPolicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_justica")
    private String numeroJustica;

    @Column(name = "ordem_ip")
    private Long ordemIp;

    @Column(name = "data_abertura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data;

    @Column(name = "peca")
    @Enumerated(EnumType.STRING)
    private Peca peca;

    @Column(name = "situacao_inquerito")
    @Enumerated(EnumType.STRING)
    private SituacaoInquerito situacaoInquerito;

    @Column(name = "origem_forca_policial")
    @Enumerated(EnumType.STRING)
    private OrigemForcaPolicial origemForcaPolicial;

    @OneToMany(mappedBy = "inqueritoPolicial", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("inquerito-pessoas")
    @ToString.Exclude
    private List<PessoaEnvolvimento> pessoasEnvolvidas = new ArrayList<>();

    @OneToMany(mappedBy = "inqueritoPolicial", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("inquerito-bens")
    @ToString.Exclude
    private List<BemEnvolvimento> bensEnvolvidos = new ArrayList<>();

    @Column(name = "natureza_do_delito")
    private String naturezaDoDelito;

    private String observacao;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delegacia_id", nullable = false)
    @JsonIgnoreProperties("inqueritosPoliciais")
    private Delegacia delegacia;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
