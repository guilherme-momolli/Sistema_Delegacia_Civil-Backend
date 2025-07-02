package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Apreensao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.BoletimOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoletimOcorrenciaRepository extends JpaRepository<BoletimOcorrencia, Long> {

    Optional<BoletimOcorrencia> findByBoletim(String boletim);

    List<BoletimOcorrencia> findByInstituicaoId(Long instituicaoId);

    List<BoletimOcorrencia> findByNaturezaIgnoreCase(String natureza);

    List<BoletimOcorrencia> findByOrigemIgnoreCase(String origem);

    List<BoletimOcorrencia> findByDataOcorrencia(Timestamp data);

    List<BoletimOcorrencia> findByDataOcorrenciaBetween(Timestamp inicio, Timestamp fim);
}
