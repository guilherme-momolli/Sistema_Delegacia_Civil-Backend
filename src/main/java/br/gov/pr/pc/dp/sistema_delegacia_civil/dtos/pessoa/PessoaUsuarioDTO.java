package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.usuario.Privilegio;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaUsuarioDTO {
    private Pessoa pessoa;
    private Long delegaciaId;
    private String senha;
    private Privilegio privilegio;
    private String email;
}