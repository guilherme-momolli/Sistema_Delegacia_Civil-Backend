package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.PessoaInquerito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PessoaInqueritoRepository extends JpaRepository<PessoaInquerito, Long> {

    List<PessoaInquerito> findByInqueritoId(Long inqueritoId);

    List<PessoaInquerito> findByPessoaId(Long pessoaId);

    List<PessoaInquerito> findByPessoaIdAndInqueritoId(Long pessoaId, Long inqueritoId);

    List<PessoaInquerito> findByTipoEnvolvimentoIgnoreCase(String tipoEnvolvimento);

}
