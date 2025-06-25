package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa.Etnia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa.Genero;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.pessoa.Situacao;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imagem_url")
    private String imagemUrl;

    private String nome;

    @Column(name = "nome_social")
    private String nomeSocial;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Genero sexo;

    private String cpf;

    private String rg;

    private String telefone;

    private String celular;

    private String email;

    @Column(name = "estado_civil")
    private String estadoCivil;

    private String profissao;

    private String nacionalidade;

    private String naturalidade;

    @Column(name = "etnia")
    private Etnia etnia;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

}

