package br.gov.pr.pc.dp.sistema_delegacia_civil.service;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.InqueritoPolicial;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repository.InqueritoPolicialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InqueritoPolicialService {

    @Autowired
    private InqueritoPolicialRepository repository;

    public List<InqueritoPolicial> findAll() {
        return repository.findAll();
    }

    public Optional<InqueritoPolicial> findById(Long id) {
        return repository.findById(id);
    }

    public List<InqueritoPolicial> getInqueritosByInstituicao(Long instituicaoId) {
        return repository.findByInstituicaoId(instituicaoId);
    }

    public InqueritoPolicial save(InqueritoPolicial inquerito) {
        return repository.save(inquerito);
    }

    @Transactional
    public InqueritoPolicial update(Long id, InqueritoPolicial updated) {
        InqueritoPolicial inquerito = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquérito não encontrado"));

        inquerito.setNumeroJustica(updated.getNumeroJustica());
        inquerito.setOrdemIp(updated.getOrdemIp());
        inquerito.setData(updated.getData());
        inquerito.setPeca(updated.getPeca());
        inquerito.setSituacao(updated.getSituacao());
        inquerito.setRelator(updated.getRelator());
        inquerito.setOrigemForcaPolicial(updated.getOrigemForcaPolicial());
        inquerito.setInvestigado(updated.getInvestigado());
        inquerito.setVitima(updated.getVitima());
        inquerito.setNaturezaDoDelito(updated.getNaturezaDoDelito());
        inquerito.setArmas(updated.getArmas());
        inquerito.setDrogas(updated.getDrogas());
        inquerito.setInstituicao(updated.getInstituicao());
        inquerito.setObservacao(updated.getObservacao());

        return repository.save(inquerito);
    }


    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Inquérito não encontrado para exclusão");
        }
        repository.deleteById(id);
    }

}

