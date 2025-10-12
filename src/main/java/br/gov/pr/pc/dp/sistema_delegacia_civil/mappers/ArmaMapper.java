package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Arma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import org.springframework.stereotype.Component;

@Component
public class ArmaMapper {

    public static Arma toEntity(ArmaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Arma entity = new Arma();

        entity.setTipoArmaFogo(dto.getTipoArmaFogo());
        entity.setEspecieArma(dto.getEspecieArma());
        entity.setCalibre(dto.getCalibre());
        entity.setNumeroPorte(dto.getNumeroPorte());
        entity.setNumeroSerie(dto.getNumeroSerie());
        entity.setNumeroRegistro(dto.getNumeroRegistro());
        entity.setCapacidade(dto.getCapacidade());

        if (dto.getBemId() != null && dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            entity.setBem(bem);
        }

        return entity;
    }

    public static ArmaResponseDTO toResponseDTO(Arma entity) {
        if (entity == null) {
            return null;
        }

        ArmaResponseDTO dto = new ArmaResponseDTO();

        dto.setId(entity.getId());
        dto.setTipoArmaFogo(entity.getTipoArmaFogo());
        dto.setEspecieArma(entity.getEspecieArma());
        dto.setCalibre(entity.getCalibre());
        dto.setNumeroPorte(entity.getNumeroPorte());
        dto.setNumeroSerie(entity.getNumeroSerie());
        dto.setNumeroRegistro(entity.getNumeroRegistro());
        dto.setCapacidade(entity.getCapacidade());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        if (entity.getBem() != null) {
            Bem bem = entity.getBem();
            BemResponseDTO bemDTO = new BemResponseDTO();
            bemDTO.setId(bem.getId());
            dto.setBemId(bem.getId());
        }

        return dto;
    }
}
