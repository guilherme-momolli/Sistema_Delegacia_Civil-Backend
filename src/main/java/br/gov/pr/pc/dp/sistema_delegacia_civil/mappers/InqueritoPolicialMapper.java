package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;

import java.util.stream.Collectors;

public class InqueritoPolicialMapper {

    public static InqueritoPolicial toEntity(InqueritoPolicialRequestDTO dto, Delegacia delegacia) {
        if (dto == null) return null;

        InqueritoPolicial entity = new InqueritoPolicial();
        entity.setId(dto.getId());
        entity.setNumeroJustica(dto.getNumeroJustica());
        entity.setOrdemIp(dto.getOrdemIp());
        entity.setData(dto.getData());
        entity.setPeca(dto.getPeca());
        entity.setSituacaoInquerito(dto.getSituacaoInquerito());
        entity.setOrigemForcaPolicial(dto.getOrigemForcaPolicial());
        entity.setNaturezaDoDelito(dto.getNaturezaDoDelito());
        entity.setObservacao(dto.getObservacao());
        entity.setDelegacia(delegacia);

        return entity;
    }

    public static InqueritoPolicialResponseDTO toResponseDTO(InqueritoPolicial entity) {
        if (entity == null) return null;

        return InqueritoPolicialResponseDTO.builder()
                .id(entity.getId())
                .numeroJustica(entity.getNumeroJustica())
                .ordemIp(entity.getOrdemIp())
                .data(entity.getData())
                .peca(entity.getPeca())
                .situacaoInquerito(entity.getSituacaoInquerito())
                .origemForcaPolicial(entity.getOrigemForcaPolicial())
                .naturezaDoDelito(entity.getNaturezaDoDelito())
                .observacao(entity.getObservacao())
                .delegaciaId(entity.getDelegacia() != null ? entity.getDelegacia().getId() : null)
                .pessoasEnvolvidas(
                        entity.getPessoasEnvolvidas() != null
                                ? entity.getPessoasEnvolvidas()
                                .stream()
                                .map(PessoaEnvolvimentoMapper::toResponseDTO)
                                .collect(Collectors.toList())
                                : null
                )
                .build();
    }
}