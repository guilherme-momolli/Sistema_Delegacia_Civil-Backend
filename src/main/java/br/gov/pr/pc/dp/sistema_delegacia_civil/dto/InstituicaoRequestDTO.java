package br.gov.pr.pc.dp.sistema_delegacia_civil.dto;

import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Instituicao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstituicaoRequestDTO {
    private Instituicao instituicao;
    private String senha;
}

