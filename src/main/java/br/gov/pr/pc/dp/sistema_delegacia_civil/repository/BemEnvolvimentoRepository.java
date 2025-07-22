package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.BemEnvolvimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BemEnvolvimentoRepository extends JpaRepository<BemEnvolvimento, Long> {

    Optional<BemEnvolvimento> findById(Long id);

}
