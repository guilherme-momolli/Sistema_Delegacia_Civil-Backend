package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Apreensao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ApreensaoRepository extends JpaRepository<Apreensao, Long> {

    List<Apreensao> findByApreensaoId(Long id);

    List<Apreensao> findByInqueritoId(Long inqueritoId);

    List<Apreensao> findByBoletimId(Long boletimId);

    List<Apreensao> findByResponsavelId(Long responsavelId);

    List<Apreensao> findByNumeroLacre(String numeroLacre);

    List<Apreensao> findByTipoApreensaoIgnoreCase(String tipoApreensao);

    List<Apreensao> findByDataApreensao(java.sql.Timestamp dataApreensao);

    List<Apreensao> findByDataApreensaoBetween(java.sql.Timestamp inicio, java.sql.Timestamp fim);
}

