package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InqueritoPolicialMapper {

    public static InqueritoPolicial toEntity(InqueritoPolicialRequestDTO dto, Delegacia delegacia) {
        if (dto == null) return null;

        InqueritoPolicial entity = new InqueritoPolicial();
        entity.setNumeroJustica(dto.getNumeroJustica());
        entity.setOrdemIp(dto.getOrdemIp());
        entity.setData(dto.getData());
        entity.setPeca(dto.getPeca());
        entity.setSituacaoInquerito(dto.getSituacaoInquerito());
        entity.setOrigemForcaPolicial(dto.getOrigemForcaPolicial());
        entity.setNaturezaDoDelito(dto.getNaturezaDoDelito());
        entity.setObservacao(dto.getObservacao());
        entity.setDelegacia(delegacia);

        if (dto.getPessoasEnvolvidas() != null) {
            entity.setPessoasEnvolvidas(
                    dto.getPessoasEnvolvidas().stream()
                            .map(pe -> {
                                PessoaEnvolvimento envolvimento = new PessoaEnvolvimento();

                                Pessoa pessoa = new Pessoa();
                                pessoa.setId(pe.getPessoaId());
                                envolvimento.setPessoa(pessoa);

                                envolvimento.setTipoEnvolvimento(pe.getTipoEnvolvimento());
                                envolvimento.setObservacao(pe.getObservacao());
                                return envolvimento;
                            })
                            .toList()
            );
        }

        if (dto.getBensEnvolvidos() != null) {
            entity.setBensEnvolvidos(
                    dto.getBensEnvolvidos().stream()
                            .map(pe -> {
                                BemEnvolvimento envolvimento = new BemEnvolvimento();

                                Bem bem = new Bem();
                                bem.setId(pe.getBemId());
                                envolvimento.setBem(bem);

                                envolvimento.setTipoEnvolvimento(pe.getTipoEnvolvimento());
                                return envolvimento;
                            })
                            .toList()
            );
        }

        return entity;
    }

    public static void updateEntityFromDTO(InqueritoPolicialRequestDTO dto, InqueritoPolicial entity) {
        if (dto == null || entity == null) return;

        entity.setNumeroJustica(dto.getNumeroJustica());
        entity.setOrdemIp(dto.getOrdemIp());
        entity.setData(dto.getData());
        entity.setPeca(dto.getPeca());
        entity.setSituacaoInquerito(dto.getSituacaoInquerito());
        entity.setOrigemForcaPolicial(dto.getOrigemForcaPolicial());
        entity.setNaturezaDoDelito(dto.getNaturezaDoDelito());
        entity.setObservacao(dto.getObservacao());
    }

    public static InqueritoPolicialResponseDTO toResponseDTO(InqueritoPolicial entity) {
        if (entity == null) {
            return null;
        }

        InqueritoPolicialResponseDTO dto = new InqueritoPolicialResponseDTO();
        dto.setId(entity.getId());
        dto.setNumeroJustica(entity.getNumeroJustica());
        dto.setOrdemIp(entity.getOrdemIp());
        dto.setData(entity.getData());
        dto.setPeca(entity.getPeca());
        dto.setSituacaoInquerito(entity.getSituacaoInquerito());
        dto.setOrigemForcaPolicial(entity.getOrigemForcaPolicial());
        dto.setNaturezaDoDelito(entity.getNaturezaDoDelito());
        dto.setObservacao(entity.getObservacao());

        if (entity.getDelegacia() != null) {
            dto.setDelegaciaId(entity.getDelegacia().getId());
        }
        if (entity.getPessoasEnvolvidas() != null) {
            dto.setPessoasEnvolvidas(
                    entity.getPessoasEnvolvidas() != null ?
                            entity.getPessoasEnvolvidas().stream()
                                    .map(PessoaEnvolvimentoMapper::toResponseDTO)
                                    .toList()
                            : Collections.emptyList()
            );

        }

        if (entity.getBensEnvolvidos() != null) {
            dto.setBensEnvolvidos(
                    entity.getBensEnvolvidos() != null ?
                            entity.getBensEnvolvidos().stream()
                                    .map(BemEnvolvimentoMapper::toResponseDTO)
                                    .toList()
                            : Collections.emptyList()
            );

        }

        return dto;
    }

    public static InqueritoPolicialDashboardResponseDTO toInqueritoDashboard(List<InqueritoPolicial> inqueritos) {
        long totalInqueritos = inqueritos.size();

        Map<SituacaoInquerito, Long> totalPorSituacaoInquerito = inqueritos.stream()
                .collect(Collectors.groupingBy(
                        i -> Optional.ofNullable(i.getSituacaoInquerito()).orElse(SituacaoInquerito.DESCONHECIDO),
                        Collectors.counting()
                ));

        Map<OrigemForcaPolicial, Long> totalPorOrigem = inqueritos.stream()
                .collect(Collectors.groupingBy(
                        i -> Optional.ofNullable(i.getOrigemForcaPolicial()).orElse(OrigemForcaPolicial.DESCONHECIDO),
                        Collectors.counting()
                ));

        return new InqueritoPolicialDashboardResponseDTO(totalInqueritos, totalPorOrigem, totalPorSituacaoInquerito);
    }
}