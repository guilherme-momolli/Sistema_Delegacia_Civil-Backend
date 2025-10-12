package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.usuario;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.usuario.Privilegio;
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