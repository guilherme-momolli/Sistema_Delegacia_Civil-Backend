//package br.gov.pr.pc.dp.sistema_delegacia_civil.services.auth;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.AuthResponseDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.auth.AuthRequestDTO;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.auth.AuthException;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.auth.AuthInvalidCredentialsException;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.auth.AuthUserNotFoundException;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.global_handler.BusinessException;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Usuario;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.UsuarioRepository;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.utils.JWTUtil;
//import br.gov.pr.pc.dp.sistema_delegacia_civil.utils.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class AuthService {
//
//    private final UsuarioRepository usuarioRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JWTUtil jwtUtil;
//
//    public AuthResponseDTO login(AuthRequestDTO request) {
//        log.info("Tentativa de login: {}", request.getEmail());
//
//        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
//                .orElseThrow(AuthUserNotFoundException::new);
//
//        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
//            log.warn("Senha inválida para: {}", request.getEmail());
//            throw new AuthInvalidCredentialsException();
//        }
//
//        String token = jwtUtil.generateToken(
//                usuario.getEmail(),
//                usuario.getDelegacia().getId(),
//                usuario.getDelegacia().getNome(),
//                usuario.getNome(),
//                usuario.getPrivilegio()
//        );
//
//        log.info("Login bem-sucedido: {} ({})", usuario.getEmail(), usuario.getPrivilegio());
//
//        return new AuthResponseDTO(
//                token,
//                jwtUtil.getExpirationMs() / 1000,
//                "Bearer"
//        );
//    }
//}