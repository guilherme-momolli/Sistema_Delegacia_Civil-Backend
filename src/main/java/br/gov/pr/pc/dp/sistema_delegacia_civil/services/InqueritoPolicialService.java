package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.inquerito_policial.InqueritoPolicialRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.EnderecoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.PessoaEnvolvimentoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.InqueritoPolicialMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.InqueritoPolicialRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.EntityValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InqueritoPolicialService {

    private final InqueritoPolicialRepository inqueritoRepository;
    private final EntityValidator entityValidator;
    private final PessoaEnvolvimentoHelper pessoaEnvolvimentoHelper;

    @Transactional
    public List<InqueritoPolicial> findAll() {
        return inqueritoRepository.findAll();
    }

    @Transactional
    public InqueritoPolicial findById(Long id) {
        entityValidator.validarInqueritoPolicalExistente(id);
        return inqueritoRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<InqueritoPolicial> getInqueritosByDelegacia(Long delegaciaId) {
        entityValidator.validarDelegaciaExistente(delegaciaId);
        return inqueritoRepository.findByDelegaciaId(delegaciaId);
    }

    @Transactional
    public InqueritoPolicial createInqueritoPolicial(InqueritoPolicialRequestDTO requestDTO) {

        entityValidator.validarDelegaciaExistente(requestDTO.getDelegaciaId());

        Delegacia delegacia = new Delegacia();
        delegacia.setId(requestDTO.getDelegaciaId());
        InqueritoPolicial inquerito = InqueritoPolicialMapper.toEntity(requestDTO, delegacia);

        InqueritoPolicial salvo = inqueritoRepository.save(inquerito);

        List<PessoaEnvolvimento> envolvimentos =
                pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), salvo);
        salvo.getPessoasEnvolvidas().addAll(envolvimentos);

        return inqueritoRepository.save(salvo);
    }

    @Transactional
    public InqueritoPolicial updateInqueritoPolicial(Long id, InqueritoPolicialRequestDTO requestDTO) {

        InqueritoPolicial existente = findById(id);

        if (requestDTO.getDelegaciaId() != null) {
            entityValidator.validarDelegaciaExistente(requestDTO.getDelegaciaId());
            Delegacia delegacia = new Delegacia();
            delegacia.setId(requestDTO.getDelegaciaId());
            existente.setDelegacia(delegacia);
        }

        existente.setNumeroJustica(requestDTO.getNumeroJustica());
        existente.setOrdemIp(requestDTO.getOrdemIp());
        existente.setData(requestDTO.getData());
        existente.setPeca(requestDTO.getPeca());
        existente.setSituacaoInquerito(requestDTO.getSituacaoInquerito());
        existente.setOrigemForcaPolicial(requestDTO.getOrigemForcaPolicial());
        existente.setNaturezaDoDelito(requestDTO.getNaturezaDoDelito());
        existente.setObservacao(requestDTO.getObservacao());

        existente.getPessoasEnvolvidas().clear();
        List<PessoaEnvolvimento> envolvimentos =
                pessoaEnvolvimentoHelper.mapearPessoas(requestDTO.getPessoasEnvolvidas(), existente);
        existente.getPessoasEnvolvidas().addAll(envolvimentos);

        return inqueritoRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        entityValidator.validarInqueritoPolicalExistente(id);
        inqueritoRepository.deleteById(id);
    }
}

