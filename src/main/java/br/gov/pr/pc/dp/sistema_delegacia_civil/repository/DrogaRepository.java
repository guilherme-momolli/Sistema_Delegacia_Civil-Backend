package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Droga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface DrogaRepository extends JpaRepository<Droga, Long> {

    List<Droga> findByTipoDrogaIgnoreCase(String tipoDroga);

    List<Droga> findByNomePopularIgnoreCase(String nomePopular);

    List<Droga> findByNumeroLacre(Long numeroLacre);

    List<Droga> findByLocalDrogaIgnoreCase(String localDroga);

    List<Droga> findByQuantidade(Double quantidade);

    List<Droga> findByQuantidadeBetween(Double min, Double max);

}
