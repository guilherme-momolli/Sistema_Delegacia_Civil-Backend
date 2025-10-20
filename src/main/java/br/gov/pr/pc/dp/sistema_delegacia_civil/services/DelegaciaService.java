package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia.DelegaciaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.delegacia.DelegaciaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.EnderecoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.DelegaciaMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Usuario;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.usuario.Privilegio;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.EnderecoRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.DelegaciaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DelegaciaService {

    private final DelegaciaRepository delegaciaRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileStorageService fileStorageService;
    private final String subFolder = "Imagens/Delegacias";

    @Transactional
    public List<DelegaciaResponseDTO> listAll() {
        return delegaciaRepository.findAll()
                .stream()
                .map(DelegaciaMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public DelegaciaResponseDTO getById(Long id) {
        Delegacia delegacia = delegaciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delegacia não encontrada"));
        return DelegaciaMapper.toResponseDTO(delegacia);
    }

    @Transactional
    public DelegaciaResponseDTO createDelegacia(DelegaciaRequestDTO dto, MultipartFile imagem) {
        if (delegaciaRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        Delegacia delegacia = DelegaciaMapper.toEntity(dto);

        delegacia.setEndereco(EnderecoHelper.resolverEnderecoRequestDTO(dto.getEndereco(), enderecoRepository));


        if (imagem != null && !imagem.isEmpty()) {
            String imagemUrl = fileStorageService.storeFile(imagem, subFolder);
            delegacia.setImagemUrl(imagemUrl);
        }

        Delegacia novaDelegacia = delegaciaRepository.save(delegacia);

        Usuario usuario = new Usuario();
        usuario.setNome(novaDelegacia.getNome());
        usuario.setEmail(novaDelegacia.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPrivilegio(Privilegio.ADMIN_MASTER);
        usuario.setDelegacia(novaDelegacia);
        usuarioRepository.save(usuario);

        return DelegaciaMapper.toResponseDTO(novaDelegacia);
    }

    @Transactional
    public DelegaciaResponseDTO updateDelegacia(Long id, DelegaciaRequestDTO dto, MultipartFile imagem) {
        Delegacia delegaciaExistente = delegaciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delegacia não encontrada"));

        if (!delegaciaExistente.getEmail().equals(dto.getEmail())) {
            Usuario usuario = usuarioRepository.findByEmail(delegaciaExistente.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            usuario.setEmail(dto.getEmail());
            usuarioRepository.save(usuario);
        }

        delegaciaExistente.setNome(dto.getNome());
        delegaciaExistente.setEmail(dto.getEmail());
        delegaciaExistente.setSecretaria(dto.getSecretaria());
        delegaciaExistente.setTelefoneFixo(dto.getTelefoneFixo());
        delegaciaExistente.setTelefoneCelular(dto.getTelefoneCelular());

        delegaciaExistente.setEndereco(EnderecoHelper.resolverEnderecoRequestDTO(dto.getEndereco(), enderecoRepository));


        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            Usuario usuario = usuarioRepository.findByEmail(delegaciaExistente.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
            usuarioRepository.save(usuario);
        }

        if (imagem != null && !imagem.isEmpty()) {
            String imagemUrl = fileStorageService.storeFile(imagem, subFolder);
            delegaciaExistente.setImagemUrl(imagemUrl);
        }

        Delegacia atualizado = delegaciaRepository.save(delegaciaExistente);
        return DelegaciaMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public void deleteDelegacia(Long id) {
        Delegacia delegacia = delegaciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delegacia não encontrada"));

        List<Usuario> usuarios = usuarioRepository.findByDelegaciaId(delegacia.getId());
        if (!usuarios.isEmpty()) {
            usuarioRepository.deleteAll(usuarios);
        }

        delegaciaRepository.delete(delegacia);
    }
}