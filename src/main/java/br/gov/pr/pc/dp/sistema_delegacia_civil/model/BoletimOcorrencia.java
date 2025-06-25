package br.gov.pr.pc.dp.sistema_delegacia_civil.model;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Origem;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "boletim_ocorrencia")
public class BoletimOcorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Origem origem;

    @Column(name = "data_ocorrencia")
    private LocalDateTime dataOcorrencia;

    private String boletim;

    private String natureza;

    private String represetacao;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Pessoa autor;

    @ManyToOne
    @JoinColumn(name = "vitima_id")
    private Pessoa vitima;

    @OneToMany(mappedBy = "boletimOcorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Veiculo> veiculos;

}
