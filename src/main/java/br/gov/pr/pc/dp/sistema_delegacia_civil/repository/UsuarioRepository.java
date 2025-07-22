package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByDelegaciaId(Delegacia delegacia);

    Optional<Usuario> findByPessoaId(Long pessoaId);

    List<Usuario> findByDelegaciaId(Long delegaciaId);

}
