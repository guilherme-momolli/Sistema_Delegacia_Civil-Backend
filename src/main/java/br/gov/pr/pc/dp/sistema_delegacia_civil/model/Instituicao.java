package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "instituicao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="secretaria", nullable = false, length = 255)
    private String secretaria;

    @Column(name="nome_fantasia", nullable = false, length = 255)
    private String nomeFantasia;

    @Email
    @Column(unique = true)
    private String email;

    @Column(name="telefone_fixo", unique = true, length = 20, nullable = true)
    private String telefoneFixo;

    @Column(name="telefone_celular", unique = true, length = 20, nullable = true)
    private String telefoneCelular;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

}
