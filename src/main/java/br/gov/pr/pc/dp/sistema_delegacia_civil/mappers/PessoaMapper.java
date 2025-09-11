package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    public static PessoaResponseDTO toResponseDTO(Pessoa pessoa) {
        if (pessoa == null) return null;

        return new PessoaResponseDTO(
                pessoa.getId(),
                pessoa.getImagemUrl(),
                pessoa.getNome(),
                pessoa.getNomeSocial(),
                pessoa.getDataNascimento(),
                pessoa.getSexo(),
                pessoa.getCpf(),
                pessoa.getRg(),
                pessoa.getTelefoneCelular(),
                pessoa.getTelefoneFixo(),
                pessoa.getEmail(),
                pessoa.getEstadoCivil(),
                pessoa.getProfissao(),
                pessoa.getNacionalidade(),
                pessoa.getNaturalidade(),
                pessoa.getEtnia(),
                pessoa.getSituacaoPessoa(),
                pessoa.getDescricao(),
                EnderecoMapper.toResponseDTO(pessoa.getEndereco())
        );
    }

    public static Pessoa toEntity(PessoaRequestDTO dto) {
        if (dto == null) return null;

        Pessoa pessoa = new Pessoa();
        pessoa.setImagemUrl(dto.getImagemUrl());
        pessoa.setNome(dto.getNome());
        pessoa.setNomeSocial(dto.getNomeSocial());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setSexo(dto.getSexo());
        pessoa.setCpf(dto.getCpf());
        pessoa.setRg(dto.getRg());
        pessoa.setTelefoneCelular(dto.getTelefoneCelular());
        pessoa.setTelefoneFixo(dto.getTelefoneFixo());
        pessoa.setEmail(dto.getEmail());
        pessoa.setEstadoCivil(dto.getEstadoCivil());
        pessoa.setProfissao(dto.getProfissao());
        pessoa.setNacionalidade(dto.getNacionalidade());
        pessoa.setNaturalidade(dto.getNaturalidade());
        pessoa.setEtnia(dto.getEtnia());
        pessoa.setSituacaoPessoa(dto.getSituacaoPessoa());
        pessoa.setDescricao(dto.getDescricao());
        pessoa.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        return pessoa;
    }
}