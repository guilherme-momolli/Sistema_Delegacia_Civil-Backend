package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    ArrayList<Endereco> findAll();
    Optional<Endereco> findById(Long id);

}
