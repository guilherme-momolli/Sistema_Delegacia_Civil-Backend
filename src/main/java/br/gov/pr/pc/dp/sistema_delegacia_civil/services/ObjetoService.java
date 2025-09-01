package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Objeto;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.ObjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ObjetoService {

    private final ObjetoRepository objetoRepository;

    public List<Objeto> listarTodos() {
        return objetoRepository.findAll();
    }

    public Objeto buscarPorId(Long id) {
        return objetoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Objeto não encontrado com ID: " + id));
    }

    public Objeto salvar(Objeto objeto) {
        return objetoRepository.save(objeto);
    }

    public Objeto atualizar(Long id, Objeto dadosAtualizados) {
        Objeto existente = buscarPorId(id);

        existente.setTipoObjeto(dadosAtualizados.getTipoObjeto());
        existente.setNumeroSerie(dadosAtualizados.getNumeroSerie());
        existente.setCor(dadosAtualizados.getCor());
        existente.setMaterial(dadosAtualizados.getMaterial());
        existente.setDimensoes(dadosAtualizados.getDimensoes());
        existente.setEstadoConservacao(dadosAtualizados.getEstadoConservacao());
        existente.setSituacaoObjeto(dadosAtualizados.getSituacaoObjeto());

        return objetoRepository.save(existente);
    }

    public void deletar(Long id) {
        Objeto objeto = buscarPorId(id);
        objetoRepository.delete(objeto);
    }
}

