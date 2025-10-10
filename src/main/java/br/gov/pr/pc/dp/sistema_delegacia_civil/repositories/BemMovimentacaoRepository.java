package br.gov.pr.pc.dp.sistema_delegacia_civil.repositories;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.BemMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BemMovimentacaoRepository extends JpaRepository<BemMovimentacao, Long> {

    Optional<BemMovimentacao> findById(Long id);
}
