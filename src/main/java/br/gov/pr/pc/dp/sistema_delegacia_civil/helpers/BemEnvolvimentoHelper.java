package br.gov.pr.pc.dp.sistema_delegacia_civil.helpers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem_envolvimento.BemEnvolvimentoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.BemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class BemEnvolvimentoHelper {

    private final BemRepository bemRepository;

    public List<BemEnvolvimento> mapearBensPorBoletimDeOcorrencia(
            List<BemEnvolvimentoRequestDTO> bensDTO, BoletimOcorrencia boletim) {

        if (bensDTO == null || bensDTO.isEmpty()) return new ArrayList<>();

        return bensDTO.stream()
                .map(dto -> {
                    Bem bem = bemRepository.findById(dto.getBemId())
                            .orElseThrow(() -> new IllegalArgumentException("Bem não encontrado: " + dto.getBemId()));

                    return BemEnvolvimento.builder()
                            .bem(bem)
                            .boletimOcorrencia(boletim)
                            .tipoEnvolvimento(dto.getTipoEnvolvimento())
                            .dataEnvolvimento(dto.getDataEnvolvimento())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<BemEnvolvimento> mapearBensPorInqueritoPolicial(
            List<BemEnvolvimentoRequestDTO> bensDTO, InqueritoPolicial inquerito) {

        if (bensDTO == null || bensDTO.isEmpty()) return new ArrayList<>();

        return bensDTO.stream()
                .map(dto -> {
                    Bem bem = bemRepository.findById(dto.getBemId())
                            .orElseThrow(() -> new IllegalArgumentException("Bem não encontrado: " + dto.getBemId()));

                    return BemEnvolvimento.builder()
                            .bem(bem)
                            .inqueritoPolicial(inquerito)
                            .tipoEnvolvimento(dto.getTipoEnvolvimento())
                            .dataEnvolvimento(dto.getDataEnvolvimento())
                            .build();
                })
                .collect(Collectors.toList());
    }
}