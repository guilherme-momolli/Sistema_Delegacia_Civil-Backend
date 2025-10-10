package br.gov.pr.pc.dp.sistema_delegacia_civil.repositories;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Arma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArmaRepository extends JpaRepository<Arma, Long> {

    Optional<Arma> findById(Long id);

//    List<Arma> findByProprietarioId(Long pessoaId);
//
//    List<Arma> findByNumeroSerie(String numeroSerie);
//
//    List<Arma> findByNumeroRegistro(String numeroRegistro);
//
//    List<Arma> findByNumeroPorte(String numeroPorte);
//
//    List<Arma> findByCalibreIgnoreCase(String calibre);
//
//    List<Arma> findByTipoArmaFogoIgnoreCase(String tipo);
//
//    List<Arma> findByEspecieIgnoreCase(String especie);
//
//    List<Arma> findByMarcaIgnoreCase(String marca);

}
