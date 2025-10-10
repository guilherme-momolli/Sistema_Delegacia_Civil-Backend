package br.gov.pr.pc.dp.sistema_delegacia_civil.helpers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa_envolvimento.PessoaEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.PessoaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.services.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PessoaEnvolvimentoHelper {

    private final PessoaRepository pessoaRepository;

    public List<PessoaEnvolvimento> mapearPessoas(
            List<PessoaEnvolvimentoRequestDTO> pessoasDTO, BoletimOcorrencia boletim) {

        if (pessoasDTO == null || pessoasDTO.isEmpty()) return new ArrayList<>();

        return pessoasDTO.stream()
                .map(dto -> {
                    Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                            .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada: " + dto.getPessoaId()));

                    return PessoaEnvolvimento.builder()
                            .pessoa(pessoa)
                            .boletimOcorrencia(boletim)
                            .tipoEnvolvimento(dto.getTipoEnvolvimento())
                            .observacao(dto.getObservacao())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<PessoaEnvolvimento> mapearPessoas(
            List<PessoaEnvolvimentoRequestDTO> pessoasDTO, InqueritoPolicial inquerito) {

        if (pessoasDTO == null || pessoasDTO.isEmpty()) return new ArrayList<>();

        return pessoasDTO.stream()
                .map(dto -> {
                    Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
                            .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada: " + dto.getPessoaId()));

                    return PessoaEnvolvimento.builder()
                            .pessoa(pessoa)
                            .inqueritoPolicial(inquerito)
                            .tipoEnvolvimento(dto.getTipoEnvolvimento())
                            .observacao(dto.getObservacao())
                            .build();
                })
                .collect(Collectors.toList());
    }
}