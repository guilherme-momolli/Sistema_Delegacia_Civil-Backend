package br.gov.pr.pc.dp.sistema_delegacia_civil.service;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dto.AuthRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dto.AuthResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Usuario;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repository.UsuarioRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO login(AuthRequestDTO request) {
        logger.info("Tentativa de login para email: {}", request.getEmail());

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.warn("Usuário não encontrado para o email: {}", request.getEmail());
                    return new BadCredentialsException("Credenciais inválidas");
                });

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            logger.warn("Senha inválida para o email: {}", request.getEmail());
            throw new BadCredentialsException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getInstituicao().getId(), usuario.getInstituicao().getNomeFantasia(), usuario.getNome(), usuario.getPrivilegio());
        logger.info("Login bem-sucedido para email: {}", request.getEmail());

        return new AuthResponseDTO(token);
    }
}
