package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {


    private final JdbcTemplate jdbcTemplate;

    public Map<String, Object> getTotais() {
        Map<String, Object> result = new HashMap<>();
        result.put("delegacias", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM delegacia", Integer.class));
        result.put("bos", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM boletim_ocorrencia", Integer.class));
        result.put("inqueritos", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM inquerito_policial", Integer.class));
        result.put("bens", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM bem", Integer.class));
        return result;
    }

    public List<Map<String, Object>> getBosPorMes() {
        return jdbcTemplate.queryForList(
                "SELECT TO_CHAR(data_registro, 'YYYY-MM') as mes, COUNT(*) as total " +
                        "FROM boletim_ocorrencia GROUP BY mes ORDER BY mes"
        );
    }

    public List<Map<String, Object>> getInqueritosPorDelegacia() {
        return jdbcTemplate.queryForList(
                "SELECT d.nome as delegacia, COUNT(*) as total " +
                        "FROM inquerito_policial i " +
                        "JOIN delegacia d ON d.id = i.delegacia_id " +
                        "GROUP BY d.nome ORDER BY total DESC"
        );
    }

    public List<Map<String, Object>> getBensPorTipo() {
        return jdbcTemplate.queryForList(
                "SELECT tipo, COUNT(*) as total FROM bem GROUP BY tipo ORDER BY total DESC"
        );
    }


}
