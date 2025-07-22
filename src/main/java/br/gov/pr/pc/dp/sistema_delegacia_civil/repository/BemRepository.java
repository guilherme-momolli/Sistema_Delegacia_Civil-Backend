package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Bem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BemRepository extends JpaRepository<Bem, Long> {

    Optional<Bem> findById(Long id);
}
