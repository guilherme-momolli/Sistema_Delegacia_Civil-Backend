package br.gov.pr.pc.dp.sistema_delegacia_civil.specifications;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem.BemFiltroDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Bem;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class BemSpecification {

    public static Specification<Bem> filtroCustomizado(BemFiltroDTO filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 🔹 Tipo do bem (ARMA, DROGA, OBJETO, VEICULO)
            if (filtro.getTipoBem() != null && !filtro.getTipoBem().isBlank()) {
                predicates.add(cb.equal(root.get("tipoBem"), filtro.getTipoBem()));
            }

            // 🔹 Situação do bem (por exemplo: APREENDIDO, DEVOLVIDO, EM_TRANSITO)
            if (filtro.getSituacao() != null && !filtro.getSituacao().isBlank()) {
                predicates.add(cb.equal(root.get("situacao"), filtro.getSituacao()));
            }

            // 🔹 Descrição (busca flexível, ignorando acentuação e case)
            if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
                String descricaoBusca = "%" + filtro.getDescricao().toLowerCase() + "%";
                predicates.add(cb.like(
                        cb.function("translate", String.class, cb.lower(root.get("descricao")),
                                cb.literal("áàâãäéèêëíìîïóòôõöúùûüç"),
                                cb.literal("aaaaaeeeeiiiiooooouuuuc")
                        ),
                        descricaoBusca
                ));
            }

            // 🔹 Número de série (para objetos e armas)
            if (filtro.getNumeroSerie() != null && !filtro.getNumeroSerie().isBlank()) {
                predicates.add(cb.equal(root.get("numeroSerie"), filtro.getNumeroSerie()));
            }

            // 🔹 Delegacia (filtragem por delegacia vinculada)
            if (filtro.getDelegaciaId() != null) {
                predicates.add(cb.equal(root.get("delegacia").get("id"), filtro.getDelegaciaId()));
            }

            // 🔹 Pessoa associada (ex: proprietário, envolvido)
            if (filtro.getPessoaId() != null) {
                predicates.add(cb.equal(root.get("pessoa").get("id"), filtro.getPessoaId()));
            }

            // 🔹 Data de criação (intervalo)
            if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
                predicates.add(cb.between(root.get("createdAt"), filtro.getDataInicio(), filtro.getDataFim()));
            } else if (filtro.getDataInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filtro.getDataInicio()));
            } else if (filtro.getDataFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), filtro.getDataFim()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}