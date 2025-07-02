package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Apreensao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Instituicao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByInstituicaoId(Instituicao instituicao);

    List<Usuario> findByInstituicaoId(Long instituicaoId);


}
