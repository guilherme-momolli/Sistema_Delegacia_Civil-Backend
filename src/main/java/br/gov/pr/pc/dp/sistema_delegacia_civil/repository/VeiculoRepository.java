package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Apreensao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca(String placa);

    Optional<Veiculo> findByRenavam(String renavam);

    Optional<Veiculo> findByChassi(String chassi);

    List<Veiculo> findByMarcaIgnoreCase(String marca);

    List<Veiculo> findByModeloIgnoreCase(String modelo);

    List<Veiculo> findByCorPredominanteIgnoreCase(String corPredominante);

    List<Veiculo> findByTipoVeiculoIgnoreCase(String tipoVeiculo);

    List<Veiculo> findBySituacaoVeiculoIgnoreCase(String situacaoVeiculo);

    List<Veiculo> findByRestricaoJudicialIgnoreCase(String restricaoJudicial);

    List<Veiculo> findByProprietarioId(Long proprietarioId);

    List<Veiculo> findByMunicipioRegistroIgnoreCaseAndUfRegistro(String municipio, String uf);

    List<Veiculo> findByMarcaIgnoreCaseAndModeloIgnoreCase(String marca, String modelo);

}
