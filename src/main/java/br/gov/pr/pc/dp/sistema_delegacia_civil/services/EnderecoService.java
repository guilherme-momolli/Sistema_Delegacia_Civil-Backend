package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoRequestDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.dtos.endereco.EnderecoResponseDTO;
import br.gov.pr.pc.dp.sistema_delegacia_civil.exceptions.file_storage.ResourceNotFoundException;
import br.gov.pr.pc.dp.sistema_delegacia_civil.mappers.EnderecoMapper;
import br.gov.pr.pc.dp.sistema_delegacia_civil.models.Endereco;
import br.gov.pr.pc.dp.sistema_delegacia_civil.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public List<EnderecoResponseDTO> listEndereco() {
        return enderecoRepository.findAll()
                .stream()
                .map(EnderecoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EnderecoResponseDTO getById(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
        return EnderecoMapper.toResponseDTO(endereco);
    }

    public EnderecoResponseDTO createEndereco(EnderecoRequestDTO dto) {
        Endereco endereco = EnderecoMapper.toEntity(dto);
        Endereco salvo = enderecoRepository.save(endereco);
        return EnderecoMapper.toResponseDTO(salvo);
    }

    @Transactional
    public EnderecoResponseDTO updateEndereco(Long id, EnderecoRequestDTO dto) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));

        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setMunicipio(dto.getMunicipio());
        endereco.setCep(dto.getCep());
        endereco.setUf(dto.getUf());
        endereco.setPais(dto.getPais());

        Endereco atualizado = enderecoRepository.save(endereco);
        return EnderecoMapper.toResponseDTO(atualizado);
    }

    public void deleteEndereco(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Endereço não encontrado");
        }
        enderecoRepository.deleteById(id);
    }
}
