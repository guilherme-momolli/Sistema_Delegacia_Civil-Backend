package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BemEnvolvimento;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.BemEnvolvimentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BemEnvolvimentoService {

    private final BemEnvolvimentoRepository bemEnvolvimentoRepository;

    @Transactional
    public BemEnvolvimento salvar(BemEnvolvimento bemEnvolvimento) {
        return bemEnvolvimentoRepository.save(bemEnvolvimento);
    }

    public Optional<BemEnvolvimento> buscarPorId(Long id) {
        return bemEnvolvimentoRepository.findById(id);
    }

    public List<BemEnvolvimento> listarTodos() {
        return bemEnvolvimentoRepository.findAll();
    }

    @Transactional
    public BemEnvolvimento atualizar(Long id, BemEnvolvimento atualizado) {
        return bemEnvolvimentoRepository.findById(id)
                .map(existente -> {
                    existente.setBem(atualizado.getBem());
                    existente.setBoletimOcorrencia(atualizado.getBoletimOcorrencia());
                    existente.setInqueritoPolicial(atualizado.getInqueritoPolicial());
                    existente.setTipoEnvolvimento(atualizado.getTipoEnvolvimento());
                    return bemEnvolvimentoRepository.save(existente);
                })
                .orElseThrow(() -> new IllegalArgumentException("BemEnvolvimento não encontrado com ID: " + id));
    }

    @Transactional
    public void deletar(Long id) {
        if (!bemEnvolvimentoRepository.existsById(id)) {
            throw new IllegalArgumentException("BemEnvolvimento não encontrado com ID: " + id);
        }
        bemEnvolvimentoRepository.deleteById(id);
    }
}