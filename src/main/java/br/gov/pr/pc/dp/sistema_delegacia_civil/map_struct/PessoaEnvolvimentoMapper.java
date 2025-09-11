package br.gov.pr.pc.dp.sistema_delegacia_civil.map_struct;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaEnvolvimentoMapper {

    PessoaEnvolvimentoMapper INSTANCE = Mappers.getMapper(PessoaEnvolvimentoMapper.class);

    @Mapping(source = "pessoa.id", target = "pessoaId")
    @Mapping(source = "inqueritoPolicial.id", target = "inqueritoId")
    @Mapping(source = "boletimOcorrencia.id", target = "boletimId")
    PessoaEnvolvimentoRequestDTO toDTO(PessoaEnvolvimento entity);

    @Mapping(source = "pessoaId", target = "pessoa.id")
    @Mapping(source = "inqueritoId", target = "inqueritoPolicial.id")
    @Mapping(source = "boletimId", target = "boletimOcorrencia.id")
    PessoaEnvolvimento toEntity(PessoaEnvolvimentoRequestDTO dto);
}