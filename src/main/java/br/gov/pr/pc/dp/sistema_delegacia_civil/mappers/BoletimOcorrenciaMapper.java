package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;

import java.util.Collections;

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

        return dto;
    }
}
