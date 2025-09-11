package br.gov.pr.pc.dp.sistema_delegacia_civil.mappers;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.usuario.UsuarioRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.usuario.UsuarioResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPrivilegio(),
                usuario.getDelegacia() != null ? usuario.getDelegacia().getId() : null,
                usuario.getCreatedAt(),
                usuario.getUpdatedAt()
        );
    }

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setPrivilegio(dto.getPrivilegio());

        if (dto.getDelegaciaId() != null) {
            Delegacia delegacia = new Delegacia();
            delegacia.setId(dto.getDelegaciaId());
            usuario.setDelegacia(delegacia);
        }

        return usuario;
    }

}
