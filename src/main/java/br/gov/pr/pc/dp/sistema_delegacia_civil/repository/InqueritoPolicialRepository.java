package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.InqueritoPolicial;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InqueritoPolicialRepository extends JpaRepository<InqueritoPolicial, Long> {

    List<InqueritoPolicial> findByDelegaciaId(Long delegaciaId);

    @EntityGraph(attributePaths = {"apreensoes", "apreensoes.objeto", "apreensoes.arma", "apreensoes.droga", "apreensoes.veiculo"})
    Optional<InqueritoPolicial> findById(Long id);

}
