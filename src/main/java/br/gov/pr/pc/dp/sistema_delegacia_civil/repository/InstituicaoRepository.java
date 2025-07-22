package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
}
