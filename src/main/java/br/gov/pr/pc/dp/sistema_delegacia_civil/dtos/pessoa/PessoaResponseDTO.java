package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.EstadoCivil;
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
    private EstadoCivil estadoCivil;
    private String profissao;
    private String nacionalidade;
    private String naturalidade;
    private Etnia etnia;
    private SituacaoPessoa situacaoPessoa;
    private String descricao;
    private EnderecoResponseDTO endereco;

    public static PessoaResponseDTO fromEntity(Pessoa pessoa) {
        PessoaResponseDTO dto = new PessoaResponseDTO();
        dto.setId(pessoa.getId());
        dto.setImagemUrl(pessoa.getImagemUrl());
        dto.setNome(pessoa.getNome());
        dto.setNomeSocial(pessoa.getNomeSocial());
        dto.setDataNascimento(pessoa.getDataNascimento());
        dto.setSexo(pessoa.getSexo());
        dto.setCpf(pessoa.getCpf());
        dto.setRg(pessoa.getRg());
        dto.setTelefoneCelular(pessoa.getTelefoneCelular());
        dto.setTelefoneFixo(pessoa.getTelefoneFixo());
        dto.setEmail(pessoa.getEmail());
        dto.setEstadoCivil(pessoa.getEstadoCivil());
        dto.setProfissao(pessoa.getProfissao());
        dto.setNacionalidade(pessoa.getNacionalidade());
        dto.setNaturalidade(pessoa.getNaturalidade());
        dto.setEtnia(pessoa.getEtnia());
        dto.setSituacaoPessoa(pessoa.getSituacaoPessoa());
        dto.setDescricao(pessoa.getDescricao());
        if (pessoa.getEndereco() != null) {
            dto.setEndereco(EnderecoResponseDTO.fromEntity(pessoa.getEndereco()));
        }
        return dto;
    }
}
