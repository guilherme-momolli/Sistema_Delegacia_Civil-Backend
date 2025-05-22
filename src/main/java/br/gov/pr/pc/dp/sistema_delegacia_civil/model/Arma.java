package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Situacao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.TipoArmaFogo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "arma")
public class Arma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_arma")
    private TipoArmaFogo tipoArmaFogo;

    private String especie;

    private String marca;

    private String calibre;

    @Column(name = "numero_porte")
    private String numeroPorte;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "numero_registro")
    private String numeroRegistro;

    private int capacidade;

    private String quantidade;

    @Column(name = "numero_lacre")
    private String numeroLacre;

    private BigDecimal valor;

    @Column(name = "local_arma")
    private String localArma;

//    @Enumerated(EnumType.STRING)
//    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "inquerito_policial_id")
    @JsonBackReference
    private InqueritoPolicial inqueritoPolicial;
}
