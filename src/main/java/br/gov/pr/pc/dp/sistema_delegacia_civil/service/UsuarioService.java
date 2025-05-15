package br.gov.pr.pc.dp.sistema_delegacia_civil.service;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dto.CadastroRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exception.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Instituicao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Usuario;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Privilegio;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repository.InstituicaoRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repository.UsuarioRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.gov.pr.pc.dp.sistema_delegacia_civil.validators.PrivilegioUtil.temPrivilegioSuperior;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final JwtUtil jwtUtil;

    public List<Usuario> listUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public List<Usuario> getUsuariosByInstituicaoId(Long instituicaoId) {
        return usuarioRepository.findByInstituicaoId(instituicaoId);
    }

    @Transactional
    public Usuario createUsuario(CadastroRequestDTO cadastroRequestDTO, String token) {
        if (usuarioRepository.findByEmail(cadastroRequestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Instituicao instituicao = instituicaoRepository.findById(cadastroRequestDTO.getInstituicaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada"));

        if (cadastroRequestDTO.getPrivilegio() == null) {
            throw new IllegalArgumentException("É obrigatório informar um privilégio para o usuário.");
        }

        Privilegio privilegioNovo = cadastroRequestDTO.getPrivilegio();
        Privilegio privilegioExecutor = Privilegio.valueOf(jwtUtil.extractPrivilegio(token));

        if (!temPrivilegioSuperior(privilegioExecutor, privilegioNovo)) {
            throw new RuntimeException("Você não tem permissão para criar um usuário com esse privilégio.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(cadastroRequestDTO.getNome());
        usuario.setEmail(cadastroRequestDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(cadastroRequestDTO.getSenha()));
        usuario.setPrivilegio(privilegioNovo);
        usuario.setInstituicao(instituicao);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuario, String token) {
        Usuario usuarioParaAtualizar = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Privilegio privilegioAtual = usuarioParaAtualizar.getPrivilegio();
        Privilegio privilegioNovo = usuario.getPrivilegio();
        Privilegio privilegioExecutor = Privilegio.valueOf(jwtUtil.extractPrivilegio(token));

        if (!temPrivilegioSuperior(privilegioExecutor, privilegioAtual)) {
            throw new RuntimeException("Você não tem permissão para editar este usuário.");
        }

        if (!temPrivilegioSuperior(privilegioExecutor, privilegioNovo)) {
            throw new RuntimeException("Você não pode definir esse privilégio para o usuário.");
        }

        usuarioParaAtualizar.setNome(usuario.getNome());
        usuarioParaAtualizar.setEmail(usuario.getEmail());
        usuarioParaAtualizar.setPrivilegio(privilegioNovo);

        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuarioParaAtualizar.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return usuarioRepository.save(usuarioParaAtualizar);
    }


    @Transactional
    public void deleteUsuario(Long id, String token) {
        Usuario usuarioAlvo = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Privilegio privilegioAlvo = usuarioAlvo.getPrivilegio();

        String privilegioStr = jwtUtil.extractPrivilegio(token);
        Privilegio privilegioExecutor = Privilegio.valueOf(privilegioStr);

        if (!temPrivilegioSuperior(privilegioExecutor, privilegioAlvo)) {
            throw new RuntimeException("Você não tem permissão para excluir este usuário.");
        }

        usuarioRepository.deleteById(id);
    }

}
