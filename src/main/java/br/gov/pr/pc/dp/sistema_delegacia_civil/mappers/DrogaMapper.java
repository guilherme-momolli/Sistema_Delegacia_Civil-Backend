package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.arma.ArmaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.droga.DrogaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.Calibre;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.EspecieArma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.SituacaoArmaFogo;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.arma.TipoArmaFogo;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.droga.SituacaoDroga;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.droga.TipoDroga;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Arma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Droga;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        droga.setQuantidade(dto.getQuantidade());

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
        dto.setQuantidade(entity.getQuantidade());
        return dto;
    }

    public static void toUpdate(DrogaRequestDTO dto, Droga entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getTipoDroga() != null) {
            entity.setTipoDroga(dto.getTipoDroga());
        }

        if (dto.getNomePopular() != null) {
            entity.setNomePopular(dto.getNomePopular());
        }

        if (dto.getUnidadeMedida() != null) {
            entity.setUnidadeMedida(dto.getUnidadeMedida());
        }

        if (dto.getQuantidadePorExtenso() != null) {
            entity.setQuantidadePorExtenso(dto.getQuantidadePorExtenso());
        }

        if (dto.getQuantidade() != null) {
            entity.setQuantidade(dto.getQuantidade());
        }

        if (dto.getBemId() != null) {
            Bem bem = new Bem();
            bem.setId(dto.getBemId());
            entity.setBem(bem);
        }
    }


    public static DrogaDashboardResponseDTO toDrogaDashboard(List<Droga> drogas){

        Map<TipoDroga, Long> totalPorTipoDroga = drogas.stream()
                .collect(Collectors.groupingBy(Droga::getTipoDroga, Collectors.counting()));

        return new DrogaDashboardResponseDTO(totalPorTipoDroga);
    }
}