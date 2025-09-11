package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.EnderecoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.PessoaEnvolvimentoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.BoletimOcorrenciaMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.*;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.BoletimOcorrenciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.EntityValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoletimOcorrenciaService {

    private final BoletimOcorrenciaRepository boletimRepository;
    private final EntityValidator entityValidator;
    private final EnderecoHelper enderecoHelper;
    private final PessoaEnvolvimentoHelper pessoaEnvolvimentoHelper;

    @Transactional
    public List<BoletimOcorrencia> findAll() {
        return boletimRepository.findAll();
    }

    @Transactional
    public BoletimOcorrencia findById(Long id) {
        entityValidator.validarBoletimExistente(id);
        return boletimRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<BoletimOcorrencia> getBoletinsByDelegacia(Long delegaciaId) {
        entityValidator.validarDelegaciaExistente(delegaciaId);
        return boletimRepository.findByDelegaciaId(delegaciaId);
    }

    @Transactional
    public BoletimOcorrencia createBoletimOcorrencia(BoletimOcorrenciaRequestDTO requestDTO) {

        BoletimOcorrencia boletim = BoletimOcorrenciaMapper.toEntity(requestDTO);
        entityValidator.validarDelegaciaExistente(boletim.getDelegacia().getId());
        boletim.setEndereco(enderecoHelper.resolveEndereco(boletim.getEndereco()));

        BoletimOcorrencia salvo = boletimRepository.save(boletim);

        List<PessoaEnvolvimento> envolvimentos =
                pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), salvo);

        salvo.setPessoasEnvolvidas(new ArrayList<>(envolvimentos));

        return boletimRepository.save(salvo);
    }

    @Transactional
    public BoletimOcorrencia updateBoletim(Long id, BoletimOcorrenciaRequestDTO requestDTO) {

        BoletimOcorrencia existing = findById(id);
        BoletimOcorrencia boletimAtualizado = BoletimOcorrenciaMapper.toEntity(requestDTO);

        if (boletimAtualizado.getDelegacia() != null && boletimAtualizado.getDelegacia().getId() != null) {
            entityValidator.validarDelegaciaExistente(boletimAtualizado.getDelegacia().getId());
            existing.setDelegacia(boletimAtualizado.getDelegacia());
        }

        existing.setOrigemForcaPolicial(boletimAtualizado.getOrigemForcaPolicial());
        existing.setDataOcorrencia(boletimAtualizado.getDataOcorrencia());
        existing.setBoletim(boletimAtualizado.getBoletim());
        existing.setNatureza(boletimAtualizado.getNatureza());
        existing.setRepresentacao(boletimAtualizado.getRepresentacao());

        existing.setEndereco(enderecoHelper.resolveEndereco(boletimAtualizado.getEndereco()));

        List<PessoaEnvolvimento> envolvimentos =
                pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), existing);
        existing.setPessoasEnvolvidas(new ArrayList<>(envolvimentos));

        return boletimRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        entityValidator.validarBoletimExistente(id);
        boletimRepository.deleteById(id);
    }
}