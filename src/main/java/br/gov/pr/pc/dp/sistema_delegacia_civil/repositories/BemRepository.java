package br.gov.pr.pc.dp.sistema_delegacia_civil.repositories;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BemRepository extends JpaRepository<Bem, Long>, JpaSpecificationExecutor<Bem> {

    Optional<Bem> findById(Long id);
}
