package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem_envolvimento.BemEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem_envolvimento.BemEnvolvimentoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BemEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import org.springframework.stereotype.Component;


@Component
public class BemEnvolvimentoMapper {

    public static BemEnvolvimento toEntity(BemEnvolvimentoRequestDTO dto, Bem bem, InqueritoPolicial inquerito) {
        if (dto == null || bem == null || inquerito == null) return null;

        return BemEnvolvimento.builder()
                .bem(bem)
                .inqueritoPolicial(inquerito)
                .tipoEnvolvimento(dto.getTipoEnvolvimento())
                .build();
    }

    public static BemEnvolvimento toEntity(BemEnvolvimentoRequestDTO dto, Bem bem, BoletimOcorrencia boletim) {
        if (dto == null || bem == null || boletim == null) return null;

        return BemEnvolvimento.builder()
                .bem(bem)
                .boletimOcorrencia(boletim)
                .tipoEnvolvimento(dto.getTipoEnvolvimento())
                .build();
    }

    public static BemEnvolvimentoResponseDTO toResponseDTO(BemEnvolvimento entity) {
        if (entity == null) return null;

        return BemEnvolvimentoResponseDTO.builder()
                .id(entity.getId())
                .bemId(entity.getBem().getId())
                .boletimOcorrenciaId(
                        entity.getBoletimOcorrencia() != null ? entity.getBoletimOcorrencia().getId() : null
                )
                .inqueritoPolicialId(
                        entity.getInqueritoPolicial() != null ? entity.getInqueritoPolicial().getId() : null
                )
                .tipoEnvolvimento(entity.getTipoEnvolvimento())
                .dataEnvolvimento(entity.getDataEnvolvimento())
                .build();
    }
}