package br.gov.pr.pc.dp.sistema_delegacia_civil.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name ="instituicao")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="imagem_url", length = 255)
    private String imagemUrl;

    @Column(name="secretaria", length = 255)
    private String secretaria;

    @Column(name="razao_social", nullable = false, length = 255)
    private String razao_social;

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

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
