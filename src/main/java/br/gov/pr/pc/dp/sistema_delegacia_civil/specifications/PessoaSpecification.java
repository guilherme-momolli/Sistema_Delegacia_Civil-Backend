package br.gov.pr.pc.dp.sistema_delegacia_civil.specifications;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaFiltroDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate; // ✅ import correto

import java.util.ArrayList;
import java.util.List;

public class PessoaSpecification {

    public static Specification<Pessoa> filtroCustomizado(PessoaFiltroDTO filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }

            if (filtro.getCpf() != null && !filtro.getCpf().isEmpty()) {
                predicates.add(cb.equal(root.get("cpf"), filtro.getCpf()));
            }

            if (filtro.getRg() != null && !filtro.getRg().isEmpty()) {
                predicates.add(cb.equal(root.get("rg"), filtro.getRg()));
            }

            if (filtro.getSexo() != null) {
                predicates.add(cb.equal(root.get("sexo"), filtro.getSexo()));
            }

            if (filtro.getSituacaoPessoa() != null) {
                predicates.add(cb.equal(root.get("situacaoPessoa"), filtro.getSituacaoPessoa()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}