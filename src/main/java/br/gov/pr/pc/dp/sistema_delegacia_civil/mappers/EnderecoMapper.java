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
        endereco.setId(dto.getId());
        endereco.setNumero(dto.getNumero());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setBairro(dto.getBairro());
        endereco.setMunicipio(dto.getMunicipio());
        endereco.setUf(dto.getUf());
        endereco.setPais(dto.getPais());
        endereco.setCep(dto.getCep());
        return endereco;
    }

    public static void updateEntityFromDTO(EnderecoRequestDTO dto, Endereco entity) {
        if (dto == null || entity == null) return;

        if (dto.getNumero() != null) entity.setNumero(dto.getNumero());
        if (dto.getLogradouro() != null) entity.setLogradouro(dto.getLogradouro());
        if (dto.getBairro() != null) entity.setBairro(dto.getBairro());
        if (dto.getMunicipio() != null) entity.setMunicipio(dto.getMunicipio());
        if (dto.getUf() != null) entity.setUf(dto.getUf());
        if (dto.getPais() != null) entity.setPais(dto.getPais());
        if (dto.getCep() != null) entity.setCep(dto.getCep());
    }

}