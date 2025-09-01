package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BemMovimentacao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.BemMovimentacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BemMovientacaoService {

    private final BemMovimentacaoRepository bemMovimentacaoRepository;

    @Transactional
    public BemMovimentacao salvar(BemMovimentacao movimentacao) {
        return bemMovimentacaoRepository.save(movimentacao);
    }

    public Optional<BemMovimentacao> buscarPorId(Long id) {
        return bemMovimentacaoRepository.findById(id);
    }

    public List<BemMovimentacao> listarTodas() {
        return bemMovimentacaoRepository.findAll();
    }

    @Transactional
    public BemMovimentacao atualizar(Long id, BemMovimentacao atualizada) {
        return bemMovimentacaoRepository.findById(id)
                .map(existente -> {
                    existente.setBem(atualizada.getBem());
                    existente.setOrigem(atualizada.getOrigem());
                    existente.setDestinoDelegacia(atualizada.getDestinoDelegacia());
                    existente.setDataMovimentacao(atualizada.getDataMovimentacao());
                    existente.setObservacao(atualizada.getObservacao());
                    existente.setUsuarioResponsavel(atualizada.getUsuarioResponsavel());
                    return bemMovimentacaoRepository.save(existente);
                })
                .orElseThrow(() -> new IllegalArgumentException("Movimentação não encontrada com ID: " + id));
    }

    @Transactional
    public void deletar(Long id) {
        if (!bemMovimentacaoRepository.existsById(id)) {
            throw new IllegalArgumentException("Movimentação não encontrada com ID: " + id);
        }
        bemMovimentacaoRepository.deleteById(id);
    }
}

