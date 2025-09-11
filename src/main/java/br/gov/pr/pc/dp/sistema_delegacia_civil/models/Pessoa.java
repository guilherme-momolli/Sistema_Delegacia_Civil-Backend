package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.EstadoCivil;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.Etnia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.Genero;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.SituacaoPessoa;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name ="sexo")
    @Enumerated(EnumType.STRING)
    private Genero sexo;

    @Column(name="cpf", length = 11)
    private String cpf;

    @Column(name="rg", length = 14)
    private String rg;

    @Column(name = "telefone_fixo")
    private String telefoneFixo;

    @Column(name = "telefone_celular")
    private String telefoneCelular;

    private String email;

    @Column(name = "estado_civil")
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    private String profissao;

    private String nacionalidade;

    private String naturalidade;

    @Column(name = "certificado_registro")
    private String certificadoRegistro;

    @Column(name = "etnia")
    @Enumerated(EnumType.STRING)
    private Etnia etnia;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_pessoa")
    private SituacaoPessoa situacaoPessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String descricao;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

