package br.gov.pr.pc.dp.sistema_delegacia_civil.services;


import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.EnderecoHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.helpers.PessoaHelper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.PessoaMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.pessoa.PessoaFiltroDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Pessoa;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.EnderecoRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.PessoaRepository;
import br.gov.pr.pc.dp.sistema_delegacia_civil.specifications.PessoaSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final FileStorageService fileStorageService;
    private final PessoaMapper pessoaMapper;

    private final String subFolder = "Imagens/Pessoas";

    public List<PessoaResponseDTO> listPessoa() {
        return pessoaRepository.findAll()
                .stream()
                .map(PessoaMapper::toResponseDTO)
                .toList();
    }

    public PessoaResponseDTO getById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
        return PessoaMapper.toResponseDTO(pessoa);
    }

    public Page<PessoaResponseDTO> buscarComFiltro(PessoaFiltroDTO filtro, Pageable pageable) {
        return pessoaRepository.findAll(PessoaSpecification.filtroCustomizado(filtro), pageable)
                .map(PessoaMapper::toResponseDTO);
    }

    @Transactional
    public PessoaResponseDTO createPessoa(PessoaRequestDTO dto, MultipartFile imagem) {
        Pessoa pessoa = PessoaMapper.toEntity(dto);

        PessoaHelper.validarPessoa(pessoa);

        pessoaRepository.findByCpf(pessoa.getCpf())
                .ifPresent(p -> { throw new IllegalArgumentException("CPF já cadastrado."); });

        pessoa.setEndereco(EnderecoHelper.resolverEnderecoRequestDTO(dto.getEndereco(), enderecoRepository));

        if (imagem != null && !imagem.isEmpty()) {
            String nomeArquivo = fileStorageService.storeFile(imagem, subFolder);
            pessoa.setImagemUrl(nomeArquivo);
        }

        return PessoaMapper.toResponseDTO(pessoaRepository.save(pessoa));
    }

    @Transactional
    public PessoaResponseDTO updatePessoa(Long id, PessoaRequestDTO dto, MultipartFile imagem) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        Pessoa pessoaAtualizada = PessoaMapper.toEntity(dto);
        pessoaAtualizada.setId(pessoaExistente.getId());

        pessoaAtualizada.setImagemUrl(pessoaExistente.getImagemUrl());
        if (imagem != null && !imagem.isEmpty()) {
            String nomeArquivo = fileStorageService.storeFile(imagem, subFolder);
            pessoaAtualizada.setImagemUrl(nomeArquivo);
        }


        pessoaAtualizada.setEndereco(EnderecoHelper.resolverEnderecoRequestDTO(dto.getEndereco(), enderecoRepository));

        return PessoaMapper.toResponseDTO(pessoaRepository.save(pessoaAtualizada));
    }


    @Transactional
    public void deletePessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        if (pessoa.getImagemUrl() != null) {
            fileStorageService.deleteFile(pessoa.getImagemUrl());
        }

        pessoaRepository.delete(pessoa);
    }
}
