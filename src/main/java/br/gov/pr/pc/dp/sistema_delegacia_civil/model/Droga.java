package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.TipoDroga;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.UnidadeMedida;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "droga")
public class Droga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_droga")
    private TipoDroga tipoDroga;

    @Column(name = "nome_popular")
    private String nomePopular;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida")
    private UnidadeMedida unidadeMedida;

    @Column(name = "quantidade")
    private BigDecimal quantidade;

    @Column(name = "quantidade_pericia")
    private Long quantidadePericia;

    @Column(name = "quantidade_extenso")
    private String quantidadePorExtenso;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "numero_lacre")
    private Long numeroLacre;

    @Column(name = "local_droga")
    private String localDroga;

//    @Column(name = "situacao_droga")
//    private String situacaoDroga;

    @ManyToOne
    @JoinColumn(name = "inquerito_policial_id")
    @JsonBackReference
    private InqueritoPolicial inqueritoPolicial;
}
