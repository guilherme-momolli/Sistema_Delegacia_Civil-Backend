package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Arma;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.ArmaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArmaService {

    private final ArmaRepository armaRepository;

    public List<Arma> listarTodasArmas() {
        return armaRepository.findAll();
    }

    public Arma getById(Long id) {
        return armaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arma não encontrada com ID: " + id));
    }

    public Arma salvar(Arma arma) {
        return armaRepository.save(arma);
    }

    public Arma atualizar(Long id, Arma dadosAtualizados) {
        Arma existente = getById(id);

        existente.setTipoArmaFogo(dadosAtualizados.getTipoArmaFogo());
        existente.setEspecie(dadosAtualizados.getEspecie());
        existente.setCalibre(dadosAtualizados.getCalibre());
        existente.setNumeroPorte(dadosAtualizados.getNumeroPorte());
        existente.setNumeroSerie(dadosAtualizados.getNumeroSerie());
        existente.setNumeroRegistro(dadosAtualizados.getNumeroRegistro());
        existente.setCapacidade(dadosAtualizados.getCapacidade());

        return armaRepository.save(existente);
    }

    public void deletar(Long id) {
        Arma arma = getById(id);
        armaRepository.delete(arma);
    }

}
