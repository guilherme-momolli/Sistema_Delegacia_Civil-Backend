package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Droga;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.DrogaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrogaService {

    public final DrogaRepository drogaRepository;

    public List<Droga> listarTodas() {
        return drogaRepository.findAll();
    }

    public Droga buscarPorId(Long id) {
        return drogaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Droga não encontrada com ID: " + id));
    }

    public Droga salvar(Droga droga) {
        return drogaRepository.save(droga);
    }

    public Droga atualizar(Long id, Droga dadosAtualizados) {
        Droga existente = buscarPorId(id);

        existente.setTipoDroga(dadosAtualizados.getTipoDroga());
        existente.setNomePopular(dadosAtualizados.getNomePopular());
        existente.setUnidadeMedida(dadosAtualizados.getUnidadeMedida());
        existente.setQuantidadePorExtenso(dadosAtualizados.getQuantidadePorExtenso());

        return drogaRepository.save(existente);
    }

    public void deletar(Long id) {
        Droga droga = buscarPorId(id);
        drogaRepository.delete(droga);
    }
}
