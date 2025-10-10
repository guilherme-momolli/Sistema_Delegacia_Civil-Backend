package br.gov.pr.pc.dp.sistema_delegacia_civil.repositories;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BemEnvolvimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BemEnvolvimentoRepository extends JpaRepository<BemEnvolvimento, Long> {

    Optional<BemEnvolvimento> findById(Long id);

    List<BemEnvolvimento> findByInqueritoPolicialId(Long inqueritoId);


}
