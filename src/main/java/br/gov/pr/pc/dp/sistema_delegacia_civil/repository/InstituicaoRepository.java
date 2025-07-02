package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Apreensao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {


    Optional<Instituicao> findByEmail(String email);

    @Query("SELECT u.instituicao FROM Usuario u WHERE u.email = :email")
    Optional<Instituicao> findInstituicaoByUsuarioEmail(@Param("email") String email);

}
