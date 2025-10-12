package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Droga;
import org.springframework.stereotype.Component;

@Component
public class DrogaMapper {

    public static Droga toEntity(DrogaRequestDTO dto) {
        if (dto == null) return null;

        Droga droga = new Droga();
        droga.setId(dto.getId());
        droga.setTipoDroga(dto.getTipoDroga());
        droga.setNomePopular(dto.getNomePopular());
        droga.setUnidadeMedida(dto.getUnidadeMedida());
        droga.setQuantidadePorExtenso(dto.getQuantidadePorExtenso());

        if (dto.getBemId() != null && dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            droga.setBem(bem);
        }

        return droga;
    }

    public static DrogaResponseDTO toResponseDTO(Droga entity) {
        if (entity == null) return null;

        DrogaResponseDTO dto = new DrogaResponseDTO();
        dto.setId(entity.getId());
        if (entity.getBem() != null) {
            Bem bem = entity.getBem();
            BemResponseDTO bemDTO = new BemResponseDTO();
            bemDTO.setId(bem.getId());
            dto.setBemId(bem.getId());
        }
        dto.setTipoDroga(entity.getTipoDroga());
        dto.setNomePopular(entity.getNomePopular());
        dto.setUnidadeMedida(entity.getUnidadeMedida());
        dto.setQuantidadePorExtenso(entity.getQuantidadePorExtenso());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}