package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Pais;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.UF;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private int numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "uf")
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(name = "pais")
    @Enumerated(EnumType.STRING)
    private Pais pais;
}

