package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Delegacia;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Usuario;
import br.gov.pr.pc.dp.sistema_delegacia_civil.enums.Privilegio;
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
    private final String subFolder = "Imagens/Delegacia";

    public List<Delegacia> listAllDelegacias() {
        return delegaciaRepository.findAll();
    }

    public Delegacia getById(Long id) {
        return delegaciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada."));
    }

    @Transactional
    public Delegacia createDelegacia(Delegacia delegacia, String senha, MultipartFile imagem) {
        delegaciaRepository.findByEmail(delegacia.getEmail()).ifPresent(i -> {
            throw new IllegalArgumentException("E-mail já está em uso.");
        });

        if (delegacia.getEndereco() != null) {
            Endereco endereco = delegacia.getEndereco().getId() == null
                    ? enderecoRepository.save(delegacia.getEndereco())
                    : enderecoRepository.findById(delegacia.getEndereco().getId())
                    .map(existing -> {
                        existing.setLogradouro(delegacia.getEndereco().getLogradouro());
                        existing.setBairro(delegacia.getEndereco().getBairro());
                        existing.setNumero(delegacia.getEndereco().getNumero());
                        existing.setMunicipio(delegacia.getEndereco().getMunicipio());
                        existing.setUf(delegacia.getEndereco().getUf());
                        existing.setPais(delegacia.getEndereco().getPais());
                        return enderecoRepository.save(existing);
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado."));
            delegacia.setEndereco(endereco);
        }

        if (imagem != null && !imagem.isEmpty()) {
            String fileName = fileStorageService.storeFile(imagem, subFolder);
            delegacia.setImagemUrl(fileName);
        }

        Delegacia novaDelegacia = delegaciaRepository.save(delegacia);

        Usuario usuario = new Usuario();
        usuario.setNome(novaDelegacia.getNome());
        usuario.setEmail(novaDelegacia.getEmail());
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setPrivilegio(Privilegio.ADMIN_MASTER);
        usuario.setDelegacia(novaDelegacia);

        usuarioRepository.save(usuario);

        return novaDelegacia;
    }

    @Transactional
    public Delegacia updateDelegacia(Long id, Delegacia novaDelegacia, String senha, MultipartFile imagem) {
        Delegacia delegaciaExistente = getById(id);

        if (!delegaciaExistente.getEmail().equals(novaDelegacia.getEmail())) {
            Usuario usuario = usuarioRepository.findByEmail(delegaciaExistente.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
            usuario.setEmail(novaDelegacia.getEmail());
            usuarioRepository.save(usuario);
        }

        delegaciaExistente.setSecretaria(novaDelegacia.getSecretaria());
        delegaciaExistente.setNome(novaDelegacia.getNome());
        delegaciaExistente.setEmail(novaDelegacia.getEmail());
        delegaciaExistente.setTelefoneFixo(novaDelegacia.getTelefoneFixo());
        delegaciaExistente.setTelefoneCelular(novaDelegacia.getTelefoneCelular());

        if (novaDelegacia.getEndereco() != null) {
            Endereco endereco = enderecoRepository.findById(novaDelegacia.getEndereco().getId())
                    .map(existing -> {
                        existing.setLogradouro(novaDelegacia.getEndereco().getLogradouro());
                        existing.setBairro(novaDelegacia.getEndereco().getBairro());
                        existing.setNumero(novaDelegacia.getEndereco().getNumero());
                        existing.setMunicipio(novaDelegacia.getEndereco().getMunicipio());
                        existing.setUf(novaDelegacia.getEndereco().getUf());
                        existing.setPais(novaDelegacia.getEndereco().getPais());
                        return enderecoRepository.save(existing);
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado."));
            delegaciaExistente.setEndereco(endereco);
        }

        if (senha != null && !senha.isBlank()) {
            Usuario usuario = usuarioRepository.findByEmail(delegaciaExistente.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
            usuario.setSenha(passwordEncoder.encode(senha));
            usuarioRepository.save(usuario);
        }

        if (imagem != null && !imagem.isEmpty()) {

            String imagemUrl = fileStorageService.storeFile(imagem, subFolder);
            delegaciaExistente.setImagemUrl(imagemUrl);
        }

        return delegaciaRepository.save(delegaciaExistente);
    }

    @Transactional
    public void deleteDelegacia(Long id) {
        Delegacia delegacia = getById(id);

        List<Usuario> usuariosVinculados = usuarioRepository.findByDelegaciaId(delegacia);
        if (!usuariosVinculados.isEmpty()) {
            usuarioRepository.deleteAll(usuariosVinculados);
            log.info("Usuários vinculados à instituição {} removidos com sucesso", id);
        }

        delegaciaRepository.delete(delegacia);
        log.info("Instituição deletada: ID={}, Nome={}", delegacia.getId(), delegacia.getNome());
    }
}
