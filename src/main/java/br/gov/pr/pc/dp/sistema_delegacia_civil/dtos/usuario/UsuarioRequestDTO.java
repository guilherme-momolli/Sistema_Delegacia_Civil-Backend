package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.usuario;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.Privilegio;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private Privilegio privilegio;
    private Long delegaciaId;

}