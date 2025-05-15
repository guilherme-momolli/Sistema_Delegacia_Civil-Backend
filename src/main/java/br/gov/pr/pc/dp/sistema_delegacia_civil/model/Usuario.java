package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Privilegio;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Privilegio privilegio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;
}
