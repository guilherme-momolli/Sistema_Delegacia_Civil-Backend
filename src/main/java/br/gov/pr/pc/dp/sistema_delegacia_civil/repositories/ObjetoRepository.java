package br.gov.pr.pc.dp.sistema_delegacia_civil.repositories;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Objeto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObjetoRepository extends JpaRepository<Objeto, Long> {

    Optional<Objeto> findById(Long id);

    //    List<Objeto> findByTipoObjetoIgnoreCase(String tipoObjeto);
//    List<Objeto> findByMarcaIgnoreCase(String marca);
//    List<Objeto> findByModeloIgnoreCase(String modelo);
//    List<Objeto> findByCorIgnoreCase(String cor);
//    List<Objeto> findByMaterialIgnoreCase(String material);
//    List<Objeto> findByNumeroSerie(String numeroSerie);
//    List<Objeto> findByEstadoConservacaoIgnoreCase(String estado);
//    List<Objeto> findBySituacaoObjetoIgnoreCase(String situacao);
//    List<Objeto> findByLocalEncontradoIgnoreCase(String local);
//    List<Objeto> findByDataApreensao(Timestamp dataApreensao);
//    List<Objeto> findByProprietarioId(Long proprietarioId);
//    List<Objeto> findByDescricaoContainingIgnoreCase(String palavraChave);
}
