package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.InqueritoPolicial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface InqueritoPolicialRepository extends JpaRepository<InqueritoPolicial, Long> {
    ArrayList<InqueritoPolicial> findAll();
    Optional<InqueritoPolicial> findById(Long id);
    List<InqueritoPolicial> findByInstituicaoId(Long instituicaoId);

}
