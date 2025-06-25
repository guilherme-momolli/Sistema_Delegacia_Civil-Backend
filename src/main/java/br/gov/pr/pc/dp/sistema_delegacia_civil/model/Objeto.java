package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "objeto")
public class Objeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imagem_url")
    private String imagemUrl;

    private String tipoObjeto;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Integer quantidade = 1;

    private BigDecimal valorEstimado;

    private String numeroSerie;

    private String marca;

    private String modelo;

    private String cor;

    private String material;

    private String dimensoes;

    private String estadoConservacao;

    private LocalDate dataApreensao;

    private String localEncontrado;

    private String situacaoObjeto;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "inquerito_policial_id")
    @JsonBackReference
    private InqueritoPolicial inqueritoPolicial;

    @ManyToOne
    @JoinColumn(name = "boletim_ocorrencia_id")
    @JsonBackReference
    private BoletimOcorrencia boletimOcorrencia;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    @JsonBackReference
    private Pessoa proprietario;
}
