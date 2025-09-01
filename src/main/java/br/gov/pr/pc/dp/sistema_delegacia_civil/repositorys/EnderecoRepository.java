package br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findById(Long id);
//    List<Endereco> findByNumero(int numero);
//
//    List<Endereco> findByLogradouro(String logradouro);
//
//    List<Endereco> findByBairro(String bairro);
//
//    List<Endereco> findByMunicpio(String municipio);
//
//    List<Endereco> findByUf(String uf);
//
//    List<Endereco> findByPais(String pais);
//
//    List<Endereco> findByCep(String cep);
}
