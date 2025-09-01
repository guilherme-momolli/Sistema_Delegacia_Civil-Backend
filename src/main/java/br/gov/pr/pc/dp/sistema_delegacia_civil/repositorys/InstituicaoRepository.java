package br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
}
