package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Veiculo;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veículo não encontrado com ID: " + id));
    }

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo dadosAtualizados) {
        Veiculo existente = buscarPorId(id);

        existente.setRenavam(dadosAtualizados.getRenavam());
        existente.setPlaca(dadosAtualizados.getPlaca());
        existente.setChassi(dadosAtualizados.getChassi());
        existente.setNumeroMotor(dadosAtualizados.getNumeroMotor());
        existente.setTipoVeiculo(dadosAtualizados.getTipoVeiculo());
        existente.setCategoria(dadosAtualizados.getCategoria());
        existente.setEspecie(dadosAtualizados.getEspecie());
        existente.setAnoModelo(dadosAtualizados.getAnoModelo());
        existente.setAnoFabricacao(dadosAtualizados.getAnoFabricacao());
        existente.setCombustivel(dadosAtualizados.getCombustivel());
        existente.setCambio(dadosAtualizados.getCambio());
        existente.setTipoTracao(dadosAtualizados.getTipoTracao());
        existente.setCorPredominante(dadosAtualizados.getCorPredominante());
        existente.setCarroceria(dadosAtualizados.getCarroceria());
        existente.setNumeroEixos(dadosAtualizados.getNumeroEixos());
        existente.setCapacidadeCarga(dadosAtualizados.getCapacidadeCarga());
        existente.setPotenciaMotor(dadosAtualizados.getPotenciaMotor());
        existente.setCilindrada(dadosAtualizados.getCilindrada());
        existente.setPesoBruto(dadosAtualizados.getPesoBruto());
        existente.setUfRegistro(dadosAtualizados.getUfRegistro());
        existente.setMunicipioRegistro(dadosAtualizados.getMunicipioRegistro());
        existente.setSituacaoVeiculo(dadosAtualizados.getSituacaoVeiculo());
        existente.setSituacaoLicenciamento(dadosAtualizados.getSituacaoLicenciamento());
        existente.setRestricaoJudicial(dadosAtualizados.getRestricaoJudicial());
        existente.setDataPrimeiroLicenciamento(dadosAtualizados.getDataPrimeiroLicenciamento());
        existente.setNumeroCrv(dadosAtualizados.getNumeroCrv());
        existente.setNumeroCrlv(dadosAtualizados.getNumeroCrlv());
        existente.setTabelaFipe(dadosAtualizados.getTabelaFipe());

        return veiculoRepository.save(existente);
    }

    public void deletar(Long id) {
        Veiculo veiculo = buscarPorId(id);
        veiculoRepository.delete(veiculo);
    }
}
