package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.bem;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BemFiltroDTO {
    private String tipoBem;
    private String descricao;
    private String numeroSerie;
    private String situacao;
    private Long delegaciaId;
    private Long pessoaId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
