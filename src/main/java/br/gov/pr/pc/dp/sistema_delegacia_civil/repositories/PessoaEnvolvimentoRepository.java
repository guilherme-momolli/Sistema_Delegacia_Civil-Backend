package br.gov.pr.pc.dp.sistema_delegacia_civil.repositories;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.PessoaEnvolvimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaEnvolvimentoRepository extends JpaRepository<PessoaEnvolvimento, Long> {

    Optional<PessoaEnvolvimento> findById(Long id);

    List<PessoaEnvolvimento> findByInqueritoPolicialId(Long inqueritoId);

    List<PessoaEnvolvimento> findByBoletimOcorrenciaId(Long boletimId);


}
