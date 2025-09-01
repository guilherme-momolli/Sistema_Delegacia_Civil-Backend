package br.gov.pr.pc.dp.sistema_delegacia_civil.mapper;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaEnvolvimentoDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;

public class PessoaEnvolvimentoMapper {

    public static PessoaEnvolvimento toEntity(PessoaEnvolvimentoDTO dto, Pessoa pessoa, InqueritoPolicial inquerito) {
        if (dto == null) return null;

        return PessoaEnvolvimento.builder()
                .id(dto.getId())
                .pessoa(pessoa)
                .inqueritoPolicial(inquerito)
                .tipoEnvolvimento(dto.getTipoEnvolvimento()) // já é o enum
                .observacao(dto.getObservacao())
                .build();
    }
}