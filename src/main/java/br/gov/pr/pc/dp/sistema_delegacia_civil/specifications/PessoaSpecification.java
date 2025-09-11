package br.gov.pr.pc.dp.sistema_delegacia_civil.specifications;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaFiltroDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class PessoaSpecification {

        public static Specification<Pessoa> filtroCustomizado(PessoaFiltroDTO filtro) {
            return (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();

                // 🔹 Busca flexível por nome (case-insensitive + accent-insensitive)
                if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                    String nomeBusca = "%" + filtro.getNome().toLowerCase() + "%";
                    predicates.add(cb.like(
                            cb.function("translate", String.class, cb.lower(root.get("nome")),
                                    cb.literal("áàâãäéèêëíìîïóòôõöúùûüç"),
                                    cb.literal("aaaaaeeeeiiiiooooouuuuc")
                            ),
                            nomeBusca
                    ));
                }

                // 🔹 Busca por CPF
                if (filtro.getCpf() != null && !filtro.getCpf().isBlank()) {
                    predicates.add(cb.equal(root.get("cpf"), filtro.getCpf()));
                }

                // 🔹 Busca por RG
                if (filtro.getRg() != null && !filtro.getRg().isBlank()) {
                    predicates.add(cb.equal(root.get("rg"), filtro.getRg()));
                }

                // 🔹 Filtro por sexo (enum)
                if (filtro.getSexo() != null) {
                    predicates.add(cb.equal(root.get("sexo"), filtro.getSexo()));
                }

                // 🔹 Filtro por situação da pessoa (enum)
                if (filtro.getSituacaoPessoa() != null) {
                    predicates.add(cb.equal(root.get("situacaoPessoa"), filtro.getSituacaoPessoa()));
                }


                return cb.and(predicates.toArray(new Predicate[0]));
            };
        }
    }
