package br.gov.pr.pc.dp.sistema_delegacia_civil.services;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaFiltroDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.EnderecoRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositorys.PessoaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.specifications.PessoaSpecification;
import br.gov.pr.pc.dp.sistema_delegacia_civil.validators.documentos.CpfValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final FileStorageService fileStorageService;
    private final EnderecoRepository enderecoRepository;
    private final String subFolder = "Imagens/Pessoa";

    public List<Pessoa> listPessoa() {
        return pessoaRepository.findAll();
    }

    public Pessoa getById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
    }

    public Page<Pessoa> buscarComFiltro(PessoaFiltroDTO filtro, Pageable pageable) {
        return pessoaRepository.findAll(PessoaSpecification.filtroCustomizado(filtro), pageable);
    }

    @Transactional
    public Pessoa createPessoa(Pessoa pessoa, MultipartFile imagem) {
        if (!CpfValidator.isValidCPF(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        if (pessoa.getEndereco() != null) {
            Endereco endereco = pessoa.getEndereco().getId() == null
                    ? enderecoRepository.save(pessoa.getEndereco())
                    : enderecoRepository.findById(pessoa.getEndereco().getId())
                    .map(existing -> {
                        existing.setLogradouro(pessoa.getEndereco().getLogradouro());
                        existing.setBairro(pessoa.getEndereco().getBairro());
                        existing.setNumero(pessoa.getEndereco().getNumero());
                        existing.setMunicipio(pessoa.getEndereco().getMunicipio());
                        existing.setUf(pessoa.getEndereco().getUf());
                        existing.setPais(pessoa.getEndereco().getPais());
                        return enderecoRepository.save(existing);
                    })
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado."));
            pessoa.setEndereco(endereco);
        }

        Optional<Pessoa> pessoaExistente = pessoaRepository.findByCpf(pessoa.getCpf());
        if (pessoaExistente.isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        if (imagem != null && !imagem.isEmpty()) {
            String nomeArquivo = fileStorageService.storeFile(imagem, subFolder);
            pessoa.setImagemUrl(nomeArquivo);
        }

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public Pessoa updatePessoa(Long id, Pessoa pessoaAtualizada, MultipartFile imagem) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        // Atualizar campos permitidos
        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setCpf(pessoaAtualizada.getCpf());
        pessoaExistente.setRg(pessoaAtualizada.getRg());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setEmail(pessoaAtualizada.getEmail());
        pessoaExistente.setTelefoneCelular(pessoaAtualizada.getTelefoneCelular());
        pessoaExistente.setTelefoneFixo(pessoaAtualizada.getTelefoneFixo());
        pessoaExistente.setSexo(pessoaAtualizada.getSexo());
        pessoaExistente.setEstadoCivil(pessoaAtualizada.getEstadoCivil());
        pessoaExistente.setNacionalidade(pessoaAtualizada.getNacionalidade());
        pessoaExistente.setNaturalidade(pessoaAtualizada.getNaturalidade());
        pessoaExistente.setProfissao(pessoaAtualizada.getProfissao());

        if (imagem != null && !imagem.isEmpty()) {
            String nomeArquivo = fileStorageService.storeFile(imagem, subFolder);
            pessoaExistente.setImagemUrl(nomeArquivo);
        }

        return pessoaRepository.save(pessoaExistente);
    }

    @Transactional
    public void deletePessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        if (pessoa.getImagemUrl() != null) {
            fileStorageService.deleteFile(pessoa.getImagemUrl());
        }

        pessoaRepository.deleteById(id);
    }
}

