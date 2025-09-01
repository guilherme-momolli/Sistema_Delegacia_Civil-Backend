package br.gov.pr.pc.dp.sistema_delegacia_civil.mapper;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    public PessoaResponseDTO toResponseDTO(Pessoa pessoa) {
        if (pessoa == null) return null;

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
            dto.setEnderecoResumo(String.format("%s, %s - %s, %s/%s",
                    pessoa.getEndereco().getLogradouro(),
                    pessoa.getEndereco().getNumero(),
                    pessoa.getEndereco().getBairro(),
                    pessoa.getEndereco().getMunicipio(),
                    pessoa.getEndereco().getUf()
            ));
        }

        return dto;
    }
}