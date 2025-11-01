package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaDashboardResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.boletim_ocorrencia.BoletimOcorrenciaResponseDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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

        BoletimOcorrencia boletim = BoletimOcorrenciaMapper.toEntity(requestDTO);
        entityHelper.validarDelegaciaExistente(boletim.getDelegacia().getId());
        boletim.setEndereco(enderecoHelper.resolverEnderecoRequestDTO(requestDTO.getEndereco(), enderecoRepository));

        BoletimOcorrencia salvo = boletimRepository.save(boletim);

        List<PessoaEnvolvimento> envolvimentos =
                pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), salvo);
        List<BemEnvolvimento> bemEnvolvimentos =
                bemEnvolvimentoHelper.mapearBensPorBoletimDeOcorrencia(requestDTO.getBensEnvolvidos(), salvo);

        salvo.setPessoasEnvolvidas(new ArrayList<>(envolvimentos));
        salvo.setBensEnvolvidos(new ArrayList<>(bemEnvolvimentos));

        boletimRepository.save(salvo);

        return BoletimOcorrenciaMapper.toResponseDTO(salvo);
    }

    @Transactional
    public BoletimOcorrenciaResponseDTO updateBoletim(Long id, BoletimOcorrenciaRequestDTO requestDTO) {

        BoletimOcorrencia existing = boletimRepository.findById(id).orElseThrow();
        BoletimOcorrencia boletimAtualizado = BoletimOcorrenciaMapper.toEntity(requestDTO);

        if (boletimAtualizado.getDelegacia() != null && boletimAtualizado.getDelegacia().getId() != null) {
            entityHelper.validarDelegaciaExistente(boletimAtualizado.getDelegacia().getId());
            existing.setDelegacia(boletimAtualizado.getDelegacia());
        }

        existing.setOrigemForcaPolicial(boletimAtualizado.getOrigemForcaPolicial());
        existing.setDataOcorrencia(boletimAtualizado.getDataOcorrencia());
        existing.setBoletim(boletimAtualizado.getBoletim());
        existing.setNatureza(boletimAtualizado.getNatureza());
        existing.setRepresentacao(boletimAtualizado.getRepresentacao());

        List<PessoaEnvolvimento> envolvimentos =
                pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), existing);
        existing.setPessoasEnvolvidas(new ArrayList<>(envolvimentos));

        List<BemEnvolvimento> bensEnvolvidos =
                bemEnvolvimentoHelper.mapearBensPorBoletimDeOcorrencia(requestDTO.getBensEnvolvidos(), existing);
        existing.setBensEnvolvidos(new ArrayList<>(bensEnvolvidos));

        BoletimOcorrencia atualizado = boletimRepository.save(existing);

        return BoletimOcorrenciaMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public void delete(Long id) {
        entityHelper.validarBoletimExistente(id);
        boletimRepository.deleteById(id);
    }
}