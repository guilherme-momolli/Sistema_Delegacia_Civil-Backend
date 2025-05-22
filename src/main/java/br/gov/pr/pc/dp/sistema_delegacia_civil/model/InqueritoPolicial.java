package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
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

    @Column(name = "data")
    private Timestamp data;

    @Column(name = "peca")
    private String peca;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    private String relator;

    @Column(name = "origem_forca_policial")
    private String origemForcaPolicial;

    private String investigado;

    private String vitima;

    @OneToMany(mappedBy = "inqueritoPolicial", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Droga> drogas = new ArrayList<>();

    @OneToMany(mappedBy = "inqueritoPolicial", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Arma> armas = new ArrayList<>();

//     @OneToMany(mappedBy = "inqueritoPolicial")
//     private List<BoletimOcorrencia> boletins;

    @Column(name = "natureza_do_delito")
    private String naturezaDoDelito;

    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id", nullable = false)
    @JsonIgnoreProperties("inqueritosPoliciais")
    private Instituicao instituicao;
}
