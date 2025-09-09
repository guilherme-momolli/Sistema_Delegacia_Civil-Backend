package br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.usuario;

import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.Privilegio;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Usuario;
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

    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setPrivilegio(usuario.getPrivilegio());
        dto.setDelegaciaId(usuario.getDelegacia() != null ? usuario.getDelegacia().getId() : null);
        dto.setCreatedAt(usuario.getCreatedAt());
        dto.setUpdatedAt(usuario.getUpdatedAt());

        return dto;
    }
}
