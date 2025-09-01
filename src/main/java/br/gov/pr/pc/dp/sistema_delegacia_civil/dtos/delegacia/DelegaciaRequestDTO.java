package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia;

import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelegaciaRequestDTO {
    private Delegacia delegacia;
    private String senha;
}

