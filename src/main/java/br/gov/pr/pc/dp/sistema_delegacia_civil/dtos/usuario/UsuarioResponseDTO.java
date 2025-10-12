package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.usuario;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.usuario.Privilegio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Privilegio privilegio;
    private Long delegaciaId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
