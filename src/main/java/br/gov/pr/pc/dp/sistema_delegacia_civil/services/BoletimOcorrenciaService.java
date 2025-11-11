package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.error.ErrorType;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.boletim_ocorrencia.BoletimOcorrenciaException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.boletim_ocorrencia.BoletimOcorrenciaNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.BemEnvolvimentoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.EnderecoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.PessoaEnvolvimentoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.BoletimOcorrenciaMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.BoletimOcorrenciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.EntityHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoletimOcorrenciaService {

    private final BoletimOcorrenciaRepository boletimRepository;
    private final EntityHelper entityHelper;
    private final EnderecoHelper enderecoHelper;
    private final BemEnvolvimentoHelper bemEnvolvimentoHelper;
    private final PessoaEnvolvimentoHelper pessoaEnvolvimentoHelper;
    private final EnderecoRepository enderecoRepository;

    @Transactional
    public List<BoletimOcorrenciaResponseDTO> findAll() {
        return boletimRepository.findAll().stream()
                .map(BoletimOcorrenciaMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public BoletimOcorrenciaResponseDTO findById(Long id) {
        entityHelper.validarBoletimExistente(id);
        BoletimOcorrencia boletim = boletimRepository.findById(id).orElseThrow();
        return BoletimOcorrenciaMapper.toResponseDTO(boletim);
    }

    @Transactional
    public List<BoletimOcorrenciaResponseDTO> getBoletinsByDelegacia(Long delegaciaId) {
        entityHelper.validarDelegaciaExistente(delegaciaId);
        return boletimRepository.findByDelegaciaId(delegaciaId).stream()
                .map(BoletimOcorrenciaMapper::toResponseDTO)
                .toList();
    }

    public BoletimOcorrenciaDashboardResponseDTO getBoletimOcorrenciaResumo() {
        List<BoletimOcorrencia> boletins = this.boletimRepository.findAll();
        return BoletimOcorrenciaMapper.toBoletimDashboard(boletins);
    }

    @Transactional
    public BoletimOcorrenciaResponseDTO createBoletimOcorrencia(BoletimOcorrenciaRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new BoletimOcorrenciaException(
                    ErrorType.VALIDACAO,
                    "Os dados do boletim de ocorrência são obrigatórios.",
                    "DTO de requisição veio nulo em createBoletimOcorrencia()."
            );
        }

        try {
            BoletimOcorrencia boletim = BoletimOcorrenciaMapper.toEntity(requestDTO);

            entityHelper.validarDelegaciaExistente(boletim.getDelegacia().getId());
            boletim.setEndereco(enderecoHelper.resolverEnderecoRequestDTO(requestDTO.getEndereco(), enderecoRepository));

            BoletimOcorrencia salvo = boletimRepository.save(boletim);

            List<PessoaEnvolvimento> pessoas =
                    pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), salvo);

            List<BemEnvolvimento> bens =
                    bemEnvolvimentoHelper.mapearBensPorBoletimDeOcorrencia(requestDTO.getBensEnvolvidos(), salvo);

            salvo.setPessoasEnvolvidas(pessoas);
            salvo.setBensEnvolvidos(bens);

            BoletimOcorrencia salvoFinal = boletimRepository.save(salvo);

            return BoletimOcorrenciaMapper.toResponseDTO(salvoFinal);

        } catch (BoletimOcorrenciaException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro ao criar boletim de ocorrência: {}", e.getMessage(), e);
            throw new BoletimOcorrenciaException(
                    ErrorType.INTERNO,
                    "Erro ao criar o boletim de ocorrência.",
                    e
            );
        }
    }


    @Transactional
    public BoletimOcorrenciaResponseDTO updateBoletim(Long id, BoletimOcorrenciaRequestDTO requestDTO) {
        entityHelper.validarBoletimExistente(id);

        try {
            BoletimOcorrencia existente = boletimRepository.findById(id)
                    .orElseThrow(() -> new BoletimOcorrenciaNotFoundException(id));

            BoletimOcorrenciaMapper.updateEntityFromDTO(requestDTO, existente);
            entityHelper.validarDelegaciaExistente(existente.getDelegacia().getId());

            existente.getPessoasEnvolvidas().forEach(p -> p.setBoletimOcorrencia(null));
            existente.getPessoasEnvolvidas().clear();

            List<PessoaEnvolvimento> novasPessoas = pessoaEnvolvimentoHelper
                    .mapearPessoas(requestDTO.getPessoasEnvolvidas(), existente);

            novasPessoas.forEach(p -> p.setBoletimOcorrencia(existente));
            existente.getPessoasEnvolvidas().addAll(novasPessoas);

            existente.getBensEnvolvidos().forEach(b -> b.setBoletimOcorrencia(null));
            existente.getBensEnvolvidos().clear();

            List<BemEnvolvimento> novosBens = bemEnvolvimentoHelper
                    .mapearBensPorBoletimDeOcorrencia(requestDTO.getBensEnvolvidos(), existente);

            novosBens.forEach(b -> b.setBoletimOcorrencia(existente));
            existente.getBensEnvolvidos().addAll(novosBens);

            BoletimOcorrencia atualizado = boletimRepository.saveAndFlush(existente);

            return BoletimOcorrenciaMapper.toResponseDTO(atualizado);

        } catch (BoletimOcorrenciaException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro ao atualizar boletim de ocorrência ID {}: {}", id, e.getMessage(), e);
            throw new BoletimOcorrenciaException(
                    ErrorType.INTERNO,
                    "Erro ao atualizar o boletim de ocorrência.",
                    e
            );
        }
    }



    @Transactional
    public void delete(Long id) {
        entityHelper.validarBoletimExistente(id);
        boletimRepository.deleteById(id);
    }
}