package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;

public class EnderecoMapper {

    public static EnderecoResponseDTO toResponseDTO(Endereco endereco) {
        if (endereco == null) return null;

        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getNumero(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getMunicipio(),
                endereco.getUf(),
                endereco.getPais(),
                endereco.getCep(),
                endereco.getCreatedAt(),
                endereco.getUpdatedAt()
        );
    }

    public static Endereco toEntity(EnderecoRequestDTO dto) {
        if (dto == null) return null;

        Endereco endereco = new Endereco();
        endereco.setNumero(dto.getNumero());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());
        endereco.setMunicipio(dto.getMunicipio());
        endereco.setUf(dto.getUf());
        endereco.setPais(dto.getPais());
        endereco.setCep(dto.getCep());
        return endereco;
    }
}