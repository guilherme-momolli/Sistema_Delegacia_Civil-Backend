package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.PessoaBoletim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaBoletimRepository extends JpaRepository<PessoaBoletim, Long> {

    List<PessoaBoletim> findByBoletimId(Long boletimId);

    List<PessoaBoletim> findByPessoaId(Long pessoaId);

    List<PessoaBoletim> findByPessoaIdAndBoletimId(Long pessoaId, Long boletimId);

    List<PessoaBoletim> findByTipoEnvolvimentoIgnoreCase(String tipoEnvolvimento);

}
