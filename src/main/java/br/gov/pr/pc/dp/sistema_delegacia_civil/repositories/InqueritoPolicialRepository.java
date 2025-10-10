package br.gov.pr.pc.dp.sistema_delegacia_civil.repositories;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.InqueritoPolicial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InqueritoPolicialRepository extends JpaRepository<InqueritoPolicial, Long> {

    List<InqueritoPolicial> findByDelegaciaId(Long delegaciaId);

    Optional<InqueritoPolicial> findById(Long id);

}
