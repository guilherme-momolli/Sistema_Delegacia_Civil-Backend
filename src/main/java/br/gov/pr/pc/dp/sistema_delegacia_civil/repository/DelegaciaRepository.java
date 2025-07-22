package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Delegacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DelegaciaRepository extends JpaRepository<Delegacia, Long> {

    Optional<Delegacia> findById(Long id);

    Optional<Delegacia> findByEmail(String email);

    @Query("SELECT u.delegacia FROM Usuario u WHERE u.email = :email")
    Optional<Delegacia> findDelegaciaByUsuarioEmail(@Param("email") String email);

}
