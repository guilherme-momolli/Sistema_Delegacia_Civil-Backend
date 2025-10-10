package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia.CadastroRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Usuario;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.Privilegio;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.DelegaciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.UsuarioRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final DelegaciaRepository delegaciaRepository;
    private final JwtUtil jwtUtil;

    public List<Usuario> listUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public List<Usuario> getUsuariosByDelegaciaId(Long delegaciaId) {
        return usuarioRepository.findByDelegaciaId(delegaciaId);
    }

    @Transactional
    public Usuario createUsuario(CadastroRequestDTO cadastroRequestDTO) {
        if (usuarioRepository.findByEmail(cadastroRequestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Delegacia delegacia = delegaciaRepository.findById(cadastroRequestDTO.getDelegaciaId())
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada"));

        if (cadastroRequestDTO.getPrivilegio() == null) {
            throw new IllegalArgumentException("É obrigatório informar um privilégio para o usuário.");
        }

        Privilegio privilegioNovo = cadastroRequestDTO.getPrivilegio();

        Usuario usuario = new Usuario();
        usuario.setNome(cadastroRequestDTO.getNome());
        usuario.setEmail(cadastroRequestDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(cadastroRequestDTO.getSenha()));
        usuario.setPrivilegio(privilegioNovo);
        usuario.setDelegacia(delegacia);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario usuarioParaAtualizar = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {
            throw new RuntimeException("Já existe um usuário com este email.");
        }

        Privilegio privilegioAtual = usuarioParaAtualizar.getPrivilegio();

        usuarioParaAtualizar.setNome(usuario.getNome());
        usuarioParaAtualizar.setEmail(usuario.getEmail());
        usuarioParaAtualizar.setPrivilegio(usuario.getPrivilegio());

        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuarioParaAtualizar.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return usuarioRepository.save(usuarioParaAtualizar);
    }


    @Transactional
    public void deleteUsuario(Long id) {
        Usuario usuarioAlvo = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.deleteById(id);
    }

}
