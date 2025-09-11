package br.gov.pr.pc.dp.sistema_delegacia_civil.helpers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.PessoaEnvolvimentoMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PessoaEnvolvimentoHelper {

    private final PessoaRepository pessoaRepository;

    public List<PessoaEnvolvimento> mapearPessoas(List<PessoaEnvolvimentoRequestDTO> pessoasDTO, BoletimOcorrencia boletim) {
        if (pessoasDTO == null || pessoasDTO.isEmpty()) return new ArrayList<>();

        return pessoasDTO.stream()
                .map(dto -> {
                    Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada: " + dto.getPessoaId()));
                    return PessoaEnvolvimentoMapper.toEntity(dto, pessoa, boletim);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<PessoaEnvolvimento> mapearPessoas(List<PessoaEnvolvimentoRequestDTO> pessoasDTO, InqueritoPolicial inquerito) {
        if (pessoasDTO == null || pessoasDTO.isEmpty()) return new ArrayList<>();

        return pessoasDTO.stream()
                .map(dto -> {
                    Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada: " + dto.getPessoaId()));
                    return PessoaEnvolvimentoMapper.toEntity(dto, pessoa, inquerito);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
