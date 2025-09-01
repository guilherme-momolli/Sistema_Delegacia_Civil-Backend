package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BoletimOcorrencia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.BoletimOcorrenciaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoletimOcorrenciaService {

    @Autowired
    private BoletimOcorrenciaRepository repository;

    public List<BoletimOcorrencia> findAll() {
        return repository.findAll();
    }

    public Optional<BoletimOcorrencia> findById(Long id) {
        return repository.findById(id);
    }

    public List<BoletimOcorrencia> getByDelegacia(Long delegaciaId) {
        return repository.findByDelegaciaId(delegaciaId);
    }

    public BoletimOcorrencia save(BoletimOcorrencia boletim) {
        return repository.save(boletim);
    }

    @Transactional
    public BoletimOcorrencia updateBoletimOcorrencia(Long id, BoletimOcorrencia updated) {
        BoletimOcorrencia boletim = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boletim de ocorrência não encontrado"));

        boletim.setOrigemForcaPolicial(updated.getOrigemForcaPolicial());
        boletim.setDataOcorrencia(updated.getDataOcorrencia());
        boletim.setBoletim(updated.getBoletim());
        boletim.setNatureza(updated.getNatureza());
        boletim.setRepresetacao(updated.getRepresetacao());
        boletim.setEndereco(updated.getEndereco());
        boletim.setDelegacia(updated.getDelegacia());
        boletim.setUpdatedAt(updated.getUpdatedAt());

        return repository.save(boletim);
    }

    public void deleteBoletimOcorrencia(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Boletim não encontrado para exclusão");
        }
        repository.deleteById(id);
    }
}

