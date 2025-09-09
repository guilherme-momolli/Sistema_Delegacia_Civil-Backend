package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "boletim_ocorrencia")
public class BoletimOcorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "origem_forca_policial")
    private OrigemForcaPolicial origemForcaPolicial;

    @Column(name = "data_ocorrencia")
    private LocalDateTime dataOcorrencia;

    private String boletim;

    private String natureza;

    @Column(name = "representacao")
    private String representacao;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "boletimOcorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("boletim-pessoas")
    private List<PessoaEnvolvimento> pessoasEnvolvidas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delegacia_id", nullable = false)
    @JsonIgnoreProperties("BoletimOcorrencia")
    private Delegacia delegacia;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
