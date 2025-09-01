package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.Etnia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.Genero;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.SituacaoPessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaResponseDTO {

    private Long id;

    private String imagemUrl;

    private String nome;

    private String nomeSocial;

    private LocalDate dataNascimento;

    private Genero sexo;

    private String cpf;

    private String rg;

    private String telefoneCelular;

    private String telefoneFixo;

    private String email;

    private String estadoCivil;

    private String profissao;

    private String nacionalidade;

    private String naturalidade;

    private Etnia etnia;

    private SituacaoPessoa situacaoPessoa;

    private String descricao;

    private String enderecoResumo;

}
