package br.gov.pr.pc.dp.sistema_delegacia_civil.repository;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByCpf(String cpf);

    Optional<Pessoa> findByRg(String rg);

    Optional<Pessoa> findByEmail(String email);

    List<Pessoa> findByNomeIgnoreCaseContaining(String nome);

    List<Pessoa> findByNomeSocialIgnoreCaseContaining(String nomeSocial);

    List<Pessoa> findByTelefoneCelular(String telefoneCelular);

    List<Pessoa> findBySexoIgnoreCase(String sexo);

    List<Pessoa> findBySituacaoPessoaIgnoreCase(String situacaoPessoa);
}
