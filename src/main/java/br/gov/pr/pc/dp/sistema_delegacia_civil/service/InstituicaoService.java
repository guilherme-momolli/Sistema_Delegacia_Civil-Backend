package br.gov.pr.pc.dp.sistema_delegacia_civil.service;

import br.gov.pr.pc.dp.sistema_delegacia_civil.exception.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Endereco;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Instituicao;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.Usuario;
import br.gov.pr.pc.dp.sistema_delegacia_civil.model.enums.Privilegio;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repository.EnderecoRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repository.InstituicaoRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Instituicao> listInstituicoes() {
        return instituicaoRepository.findAll();
    }

    public Instituicao getById(Long id) {
        return instituicaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada."));
    }

    @Transactional
    public Instituicao createInstituicao(Instituicao instituicao, String senha) {

        instituicaoRepository.findByEmail(instituicao.getEmail()).ifPresent(i -> {
            throw new IllegalArgumentException("E-mail já está em uso.");
        });

        if (instituicao.getEndereco() != null) {
            Endereco endereco = instituicao.getEndereco().getId() == null
                    ? enderecoRepository.save(instituicao.getEndereco())
                    : enderecoRepository.findById(instituicao.getEndereco().getId())
                    .map(existing -> {
                        existing.setLogradouro(instituicao.getEndereco().getLogradouro());
                        existing.setBairro(instituicao.getEndereco().getBairro());
                        existing.setNumero(instituicao.getEndereco().getNumero());
                        existing.setMunicipio(instituicao.getEndereco().getMunicipio());
                        existing.setUf(instituicao.getEndereco().getUf());
                        existing.setPais(instituicao.getEndereco().getPais());
                        return enderecoRepository.save(existing);
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado."));
            instituicao.setEndereco(endereco);
        }

        Instituicao novaInstituicao = instituicaoRepository.save(instituicao);

        Usuario usuario = new Usuario();
        usuario.setNome(novaInstituicao.getNomeFantasia());
        usuario.setEmail(novaInstituicao.getEmail());
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setPrivilegio(Privilegio.ADMIN_MASTER);
        usuario.setInstituicao(novaInstituicao);

        usuarioRepository.save(usuario);

        return novaInstituicao;
    }

    @Transactional
    public Instituicao updateInstituicao(Long id, Instituicao novaInstituicao, String senha) {
        Instituicao instituicaoExistente = getById(id);

        if (!instituicaoExistente.getEmail().equals(novaInstituicao.getEmail())) {
            Usuario usuario = usuarioRepository.findByEmail(instituicaoExistente.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
            usuario.setEmail(novaInstituicao.getEmail());
            usuarioRepository.save(usuario);
        }

        instituicaoExistente.setSecretaria(novaInstituicao.getSecretaria());
        instituicaoExistente.setNomeFantasia(novaInstituicao.getNomeFantasia());
        instituicaoExistente.setEmail(novaInstituicao.getEmail());
        instituicaoExistente.setTelefoneFixo(novaInstituicao.getTelefoneFixo());
        instituicaoExistente.setTelefoneCelular(novaInstituicao.getTelefoneCelular());

        if (novaInstituicao.getEndereco() != null) {
            Endereco endereco = enderecoRepository.findById(novaInstituicao.getEndereco().getId())
                    .map(existing -> {
                        existing.setLogradouro(novaInstituicao.getEndereco().getLogradouro());
                        existing.setBairro(novaInstituicao.getEndereco().getBairro());
                        existing.setNumero(novaInstituicao.getEndereco().getNumero());
                        existing.setMunicipio(novaInstituicao.getEndereco().getMunicipio());
                        existing.setUf(novaInstituicao.getEndereco().getUf());
                        existing.setPais(novaInstituicao.getEndereco().getPais());
                        return enderecoRepository.save(existing);
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado."));
            instituicaoExistente.setEndereco(endereco);
        }

        if (senha != null && !senha.isBlank()) {
            Usuario usuario = usuarioRepository.findByEmail(instituicaoExistente.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
            usuario.setSenha(passwordEncoder.encode(senha));
            usuarioRepository.save(usuario);
        }

        return instituicaoRepository.save(instituicaoExistente);
    }

    @Transactional
    public void deleteInstituicao(Long id) {
        Instituicao instituicao = getById(id);

        List<Usuario> usuariosVinculados = usuarioRepository.findByInstituicaoId(instituicao);
        if (!usuariosVinculados.isEmpty()) {
            usuarioRepository.deleteAll(usuariosVinculados);
            log.info("Usuários vinculados à instituição {} removidos com sucesso", id);
        }

        instituicaoRepository.delete(instituicao);
        log.info("Instituição deletada: ID={}, Nome={}", instituicao.getId(), instituicao.getNomeFantasia());
    }
}
