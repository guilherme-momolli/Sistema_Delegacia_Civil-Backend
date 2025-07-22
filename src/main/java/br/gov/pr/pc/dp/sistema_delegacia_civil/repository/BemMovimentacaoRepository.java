package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Bem;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.BemMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BemMovimentacaoRepository extends JpaRepository<BemMovimentacao, Long> {

    Optional<BemMovimentacao> findById(Long id);
}
