package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.SituacaoInquerito;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoletimOcorrenciaMapper {

    public static BoletimOcorrencia toEntity(BoletimOcorrenciaRequestDTO dto) {
        if (dto == null) return null;

        BoletimOcorrencia entity = new BoletimOcorrencia();
        entity.setBoletim(dto.getBoletim());
        entity.setNatureza(dto.getNatureza());
        entity.setRepresentacao(dto.getRepresentacao());
        entity.setDataOcorrencia(dto.getDataOcorrencia());
        entity.setOrigemForcaPolicial(dto.getOrigemForcaPolicial());

        if (dto.getEndereco() != null) {
            entity.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        }

        if (dto.getDelegaciaId() != null) {
            Delegacia delegacia = new Delegacia();
            delegacia.setId(dto.getDelegaciaId());
            entity.setDelegacia(delegacia);
        }

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

    public static BoletimOcorrenciaResponseDTO toResponseDTO(BoletimOcorrencia entity) {
        if (entity == null) return null;

        BoletimOcorrenciaResponseDTO dto = new BoletimOcorrenciaResponseDTO();
        dto.setId(entity.getId());
        dto.setBoletim(entity.getBoletim());
        dto.setNatureza(entity.getNatureza());
        dto.setRepresentacao(entity.getRepresentacao());
        dto.setDataOcorrencia(entity.getDataOcorrencia());
        dto.setOrigemForcaPolicial(entity.getOrigemForcaPolicial());
        dto.setDelegaciaId(entity.getDelegacia() != null ? entity.getDelegacia().getId() : null);

        if (entity.getEndereco() != null) {
            dto.setEndereco(EnderecoMapper.toResponseDTO(entity.getEndereco()));
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

    public static void updateEntityFromDTO(BoletimOcorrenciaRequestDTO dto, BoletimOcorrencia entity) {
        if (dto == null || entity == null) return;

        entity.setBoletim(dto.getBoletim());
        entity.setNatureza(dto.getNatureza());
        entity.setRepresentacao(dto.getRepresentacao());
        entity.setDataOcorrencia(dto.getDataOcorrencia());
        entity.setOrigemForcaPolicial(dto.getOrigemForcaPolicial());

        if (dto.getEndereco() != null) {
            if (entity.getEndereco() == null) {
                entity.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
            } else {
                EnderecoMapper.updateEntityFromDTO(dto.getEndereco(), entity.getEndereco());
            }
        }

        if (dto.getDelegaciaId() != null) {
            Delegacia delegacia = new Delegacia();
            delegacia.setId(dto.getDelegaciaId());
            entity.setDelegacia(delegacia);
        }

        // Atualizar PESSOAS ENVOLVIDAS
        entity.getPessoasEnvolvidas().clear();
        if (dto.getPessoasEnvolvidas() != null) {
            dto.getPessoasEnvolvidas().forEach(pe -> {
                PessoaEnvolvimento envolvimento = new PessoaEnvolvimento();

                Pessoa pessoa = new Pessoa();
                pessoa.setId(pe.getPessoaId());
                envolvimento.setPessoa(pessoa);
                envolvimento.setTipoEnvolvimento(pe.getTipoEnvolvimento());
                envolvimento.setObservacao(pe.getObservacao());

                // 🔑 Define o lado dono
                envolvimento.setBoletimOcorrencia(entity);

                entity.getPessoasEnvolvidas().add(envolvimento);
            });
        }

        // Atualizar BENS ENVOLVIDOS
        entity.getBensEnvolvidos().clear();
        if (dto.getBensEnvolvidos() != null) {
            dto.getBensEnvolvidos().forEach(be -> {
                BemEnvolvimento envolvimento = new BemEnvolvimento();

                Bem bem = new Bem();
                bem.setId(be.getBemId());
                envolvimento.setBem(bem);
                envolvimento.setTipoEnvolvimento(be.getTipoEnvolvimento());
                envolvimento.setObservacao(be.getObservacao());

                // 🔑 Define o lado dono da relação
                envolvimento.setBoletimOcorrencia(entity);

                entity.getBensEnvolvidos().add(envolvimento);
            });
        }
    }


    public static BoletimOcorrenciaDashboardResponseDTO toBoletimDashboard(List<BoletimOcorrencia> boletins) {
        long totalBoletins = boletins.size();

        Map<OrigemForcaPolicial, Long> totalPorOrigem = boletins.stream()
                .collect(Collectors.groupingBy(BoletimOcorrencia::getOrigemForcaPolicial, Collectors.counting()));


        return new BoletimOcorrenciaDashboardResponseDTO(totalBoletins, totalPorOrigem);
    }
}
