package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;

public class PessoaEnvolvimentoMapper {

    public static PessoaEnvolvimento toEntity(PessoaEnvolvimentoRequestDTO dto, Pessoa pessoa, InqueritoPolicial inquerito) {
        if (dto == null || pessoa == null || inquerito == null) return null;

        return PessoaEnvolvimento.builder()
                .pessoa(pessoa)
                .inqueritoPolicial(inquerito)
                .tipoEnvolvimento(dto.getTipoEnvolvimento())
                .observacao(dto.getObservacao())
                .build();
    }

    public static PessoaEnvolvimento toEntity(PessoaEnvolvimentoRequestDTO dto, Pessoa pessoa, BoletimOcorrencia boletim) {
        if (dto == null || pessoa == null || boletim == null) return null;

        return PessoaEnvolvimento.builder()
                .pessoa(pessoa)
                .boletimOcorrencia(boletim)
                .tipoEnvolvimento(dto.getTipoEnvolvimento())
                .observacao(dto.getObservacao())
                .build();
    }

    public static PessoaEnvolvimentoResponseDTO toResponseDTO(PessoaEnvolvimento entity) {
        if (entity == null) return null;

        return new PessoaEnvolvimentoResponseDTO(
                entity.getId(),
                entity.getPessoa() != null ? entity.getPessoa().getId() : null,
                entity.getInqueritoPolicial() != null ? entity.getInqueritoPolicial().getId() : null,
                entity.getBoletimOcorrencia() != null ? entity.getBoletimOcorrencia().getId() : null,
                entity.getTipoEnvolvimento(),
                entity.getObservacao()
        );
    }
}
