package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.delegacia.OrigemForcaPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.EstadoCivil;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.Etnia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.Genero;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.pessoa.SituacaoPessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {

    public static PessoaResponseDTO toResponseDTO(Pessoa pessoa) {
        if (pessoa == null) return null;

        PessoaResponseDTO responseDTO = new PessoaResponseDTO();

        responseDTO.setId(pessoa.getId());
        responseDTO.setCertificadoRegistro(pessoa.getCertificadoRegistro());
        responseDTO.setImagemUrl(pessoa.getImagemUrl());
        responseDTO.setNome(pessoa.getNome());
        responseDTO.setNomeSocial(pessoa.getNomeSocial());
        responseDTO.setDataNascimento(pessoa.getDataNascimento());
        responseDTO.setSexo(pessoa.getSexo());
        responseDTO.setCpf(pessoa.getCpf());
        responseDTO.setRg(pessoa.getRg());
        responseDTO.setTelefoneCelular(pessoa.getTelefoneCelular());
        responseDTO.setTelefoneFixo(pessoa.getTelefoneFixo());
        responseDTO.setEmail(pessoa.getEmail());
        responseDTO.setEstadoCivil(pessoa.getEstadoCivil());
        responseDTO.setProfissao(pessoa.getProfissao());
        responseDTO.setNacionalidade(pessoa.getNacionalidade());
        responseDTO.setNaturalidade(pessoa.getNaturalidade());
        responseDTO.setEtnia(pessoa.getEtnia());
        responseDTO.setSituacaoPessoa(pessoa.getSituacaoPessoa());
        responseDTO.setDescricao(pessoa.getDescricao());

        responseDTO.setEndereco(EnderecoMapper.toResponseDTO(pessoa.getEndereco()));

        return responseDTO;
    }

    public static Pessoa toEntity(PessoaRequestDTO dto) {
        if (dto == null) return null;

        Pessoa pessoa = new Pessoa();
        pessoa.setImagemUrl(dto.getImagemUrl());
        pessoa.setNome(dto.getNome());
        pessoa.setNomeSocial(dto.getNomeSocial());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setSexo(dto.getSexo());
        pessoa.setCpf(dto.getCpf());
        pessoa.setCertificadoRegistro(dto.getCertificadoRegistro());
        pessoa.setRg(dto.getRg());
        pessoa.setTelefoneCelular(dto.getTelefoneCelular());
        pessoa.setTelefoneFixo(dto.getTelefoneFixo());
        pessoa.setEmail(dto.getEmail());
        pessoa.setEstadoCivil(dto.getEstadoCivil());
        pessoa.setProfissao(dto.getProfissao());
        pessoa.setNacionalidade(dto.getNacionalidade());
        pessoa.setNaturalidade(dto.getNaturalidade());
        pessoa.setEtnia(dto.getEtnia());
        pessoa.setSituacaoPessoa(dto.getSituacaoPessoa());
        pessoa.setDescricao(dto.getDescricao());
        pessoa.setEndereco(EnderecoMapper.toEntity(dto.getEndereco()));
        return pessoa;
    }

    public static PessoaDashboardResponseDTO toPessoaDashboard(List<Pessoa> pessoas) {
        long totalPessoas = pessoas.size();

        Map<Genero, Long> totalPorGenero = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getSexo, Collectors.counting()));

        Map<Etnia, Long> totalPorEtnia = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getEtnia, Collectors.counting()));

        Map<EstadoCivil, Long> totalPorEstadoCivil = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getEstadoCivil, Collectors.counting()));

        Map<SituacaoPessoa, Long> totalPorSituacaoPessoa = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getSituacaoPessoa, Collectors.counting()));


        return new PessoaDashboardResponseDTO(totalPessoas, totalPorGenero, totalPorEtnia,totalPorEstadoCivil, totalPorSituacaoPessoa);
    }
}